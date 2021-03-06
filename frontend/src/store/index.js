import Vue from 'vue'
import Vuex from 'vuex'
import Keycloak from 'keycloak-js'
import socket from '../socket/socket'
import { latLng } from 'leaflet';

Vue.use(Vuex);
Vue.config.devtools = true

export default new Vuex.Store({
    state: {
        keycloak: {},
        token: "",
        authenticated: false,
        players: [],
        apiUrl: "https://hvz-experis-api.herokuapp.com/api/v1",
        baseApiUrl: "https://hvz-experis-api.herokuapp.com",
        games: [],
        gameId: 0,
        game: {},
        player: {},
        chats: [],
        squad: {},
        squads: [],
        squadId: null,
        squadMembers: [],
        checkIns: [],
        location: null,
        kills: [],
        playerCheckIn: null
    },
    mutations: {
        setSquadMembers: (state, payload) => {
            state.squadMembers = payload
        },
        setSquadId: (state, payload) => {
            state.squadId = payload
        },
        setSquad: (state, payload) => {
            state.squad = payload
        },
        setSquads: (state, payload) => {
            state.squads = payload
        },
        setKeycloak: (state, payload) => {
            state.keycloak = payload;
        },
        setToken: (state, payload) => {
            state.token = payload;
        },
        setAuthenticated: (state, payload) => {
            state.authenticated = payload;
        },

        setPlayers: (state, payload) => {
            state.players = payload;
        },

        setGames: (state, payload) => {
            state.games = payload;
        },

        setGameId: (state, payload) => {
            state.gameId = payload;
        },

        setGame: (state, payload) => {
            state.game = payload;
        },

        setPlayer: (state, payload) => {
            state.player = payload;
        },

        setChats: (state, payload) => {
            state.chats = payload;
        },

        setCheckIns: (state, payload) => {
            state.checkIns = payload;
        },

        setLocation: (state, payload) => {
            state.location = payload;
        },

        setKills: (state, payload) => {
            state.kills = payload;
        },

        setPlayerCheckIn: (state, payload) => {
            state.playerCheckIn = payload;
        }
    },
    actions: {
        /**
         * Initialize the keycloak
         */
        async init({ commit }) {
            const kc = Keycloak('keycloak.json');

            await kc.init({ onLoad: 'check-sso' });

            commit('setKeycloak', kc);
            commit('setToken', kc.token);
            commit('setAuthenticated', kc.authenticated);
        },

        async login({ state }) {
            if (!state.authenticated) {
                await state.keycloak.login();
            }
        },

        async logout({ state }) {
            if (state.authenticated) {
                await state.keycloak.logout();
            }
        },

        async register({ state }) {
            if (!state.authenticated) {
                await state.keycloak.register();
            }
        },

        /**
         * Creates a squad and the member who created the squad also becomes the leader of it.
         */
        async registerSquad({ state, dispatch }, name) {
            const response = await fetch(state.apiUrl + `/game/${state.gameId}/squad`, {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + state.token,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name: name,
                    game: {
                        id: state.gameId
                    }
                })
            })

            const data = await response.json()
            dispatch('joinSquad', {
                rank: "LEADER",
                squadId: data.id
            })
            dispatch('fetchPlayer')
            dispatch('fetchGame')
            dispatch('fetchSquads')
            socket.emit("createdSquad");
        },

        /**
         * Fetches a list of all the squads.
         */
        async fetchSquads({ state, commit }) {
            const response = await fetch(state.apiUrl + `/game/${state.gameId}/squad`)
            const data = await response.json()
            commit('setSquads', data)
        },

        /**
         * Fetches information about the squad the current player is a member of.
         */
        async fetchSquad({ state, commit, dispatch }) {
            await dispatch("fetchPlayer");
            if (state.player.squadMember !== null) {
                const response = await fetch(state.baseApiUrl + state.player.squadMember.squad)
                const data = await response.json()
                commit('setSquad', data)
                commit("setSquadId", data.id)
                dispatch("fetchSquadMembers")
            }

            else {
                commit('setSquad', {})
                commit("setSquadId", null)
            }
        },

        /**
         * The current player joins a squad.
         */
        async joinSquad({ state, dispatch, commit }, { rank, squadId }) {
            await fetch(state.apiUrl + `/game/${state.gameId}/squad/${squadId}/join`, {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + state.token,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    rank: rank,
                    game: {
                        id: state.gameId
                    },
                    squad: {
                        id: squadId
                    },
                    player: {
                        id: state.player.id
                    }
                })
            })

            await dispatch('fetchPlayer')
            await dispatch('fetchSquad')
            commit("setSquadId", squadId)
            dispatch("fetchSquadCheckIns")

            // Reconnect to the socket with the updated squadId, in order to receive squad chats.
            socket.disconnect();
            socket.auth = {
                gameId: state.gameId,
                username: state.player.username,
                human: state.player.human,
                squadId: state.squadId,
            };
            socket.connect();

            socket.emit("joinedSquad")
        },

        /**
         * Leave the squad.
         */
        async leaveSquad({ state, dispatch }) {
            let localSquadId = state.squadId;
            await fetch(state.apiUrl + `/game/${state.gameId}/squad/${state.squadId}/leave/${state.player.id}`, {
                method: "DELETE",
                headers: {
                    "Authorization": "Bearer " + state.token,
                    "Content-Type": "application/json"
                }
            })

            await dispatch('fetchSquad')

            //Removes the squad from the database if the player leaving is the last squad member remaining.
            const response = await fetch(state.apiUrl + `/game/${state.gameId}/squad`)
            const data = await response.json()
            if (data[0].squadMembers.length < 1) {
                await fetch(state.apiUrl + "/game/" + state.gameId + "/squad/" + localSquadId, {
                    method: "DELETE",
                    headers: {
                        "Authorization": "Bearer " + state.token,
                        "Content-Type": "application/json"
                    },
                })
            }

            dispatch("fetchSquads")

            // Reconnect to the socket with squadId set to null, in order to not receive squad chats.
            socket.disconnect();
            socket.auth = {
                gameId: state.gameId,
                username: state.player.username,
                human: state.player.human,
                squadId: null,
            };
            socket.connect();
        },

        /**
         * Fetch a list of all the games.
         */
        async fetchGames({ state, commit }) {
            const response = await fetch(state.apiUrl + "/game");
            const data = await response.json();
            commit('setGames', data);
        },

        /**
         * Fetch a list of all the players registered for the current game.
         */
        async fetchPlayers({ state, commit }) {
            const response = await fetch(state.apiUrl + `/game/${state.gameId}/player`)
            const data = await response.json()
            commit('setPlayers', data)
        },

        /**
         * Fetch the current game.
         */
        async fetchGame({ state, commit }) {
            const response = await fetch(state.apiUrl + `/game/${state.gameId}`);
            const data = await response.json();
            commit('setGame', data);
        },

        /**
         * Register player for the current game.
         */
        async registerPlayer({ state, getters, dispatch }) {
            await fetch(state.apiUrl + `/game/${state.gameId}/player`, {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + state.token,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: getters.decodedToken.email,
                    username: getters.decodedToken.preferred_username,
                    human: true,
                    patientZero: false,
                    game: {
                        id: state.gameId
                    }
                })
            });
            dispatch('fetchPlayers');
            dispatch('fetchPlayer');
            socket.emit("joinedGame")
        },
        /**
         * Fetch the current player.
         */
        async fetchPlayer({ state, commit, getters }) {
            const response = await fetch(state.apiUrl + `/game/${state.gameId}/player/email`, {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + state.token,
                    "Content-Type": "application/json"
                },
                body:
                    getters.decodedToken.email
            }
            );
            const data = await response.json();
            commit("setPlayer", data);
        },

        /**
         * Fetch all the members of the current squad.
         */
        async fetchSquadMembers({ state, commit }) {
            let list = [];
            for (let i = 0; i < state.squad.squadMembers.length; i++) {
                const response = await fetch(state.baseApiUrl + state.squad.squadMembers[i].player);
                const data = await response.json();
                list.push(data)
            }

            commit("setSquadMembers", list)
        },

        /**
         * Fetch all the chats in the current game
         */
        async fetchChats({ state, commit }) {
            const response = await fetch(state.apiUrl + `/game/${state.gameId}/chat`);
            const data = await response.json();
            commit("setChats", data);
        },

        /**
         * Update the gameState to IN_PROGRESS, and randomly set one player as patient zero.
         */
        async startGame({ state, dispatch }) {
            await fetch(state.apiUrl + `/game/${state.gameId}/start`, {
                method: "PUT",
                headers: {
                    "Authorization": "Bearer " + state.token,
                    "Content-Type": "application/json"
                }
            });

            await dispatch("fetchPlayers");

            const numOfPlayers = state.players.length;

            if (numOfPlayers > 0) {
                const patientZeroIndex = Math.floor(Math.random() * numOfPlayers);

                await fetch(state.apiUrl + `/game/${state.gameId}/player/${state.players[patientZeroIndex].id}`, {
                    method: "PUT",
                    headers: {
                        "Authorization": "Bearer " + state.token,
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        id: state.players[patientZeroIndex].id,
                        email: state.players[patientZeroIndex].email,
                        username: state.players[patientZeroIndex].username,
                        human: false,
                        patientZero: true,
                        biteCode: state.players[patientZeroIndex].biteCode,
                        game: {
                            id: state.gameId
                        }
                    })
                })
            }

            dispatch('fetchGame')
            dispatch('fetchPlayer')
            socket.emit("gameStarted");
        },

        /**
         * Set gameState to COMPLETE
         */
        async endGame({ state, dispatch }) {
            await fetch(state.apiUrl + `/game/${state.gameId}/end`, {
                method: "PUT",
                headers: {
                    "Authorization": "Bearer " + state.token,
                    "Content-Type": "application/json"
                }
            });
            dispatch('fetchGame')
            socket.emit("gameStarted");
        },

        /**
         * Kill a player by entering their bite code.
         */
        async kill({ state, dispatch }, { bitecode, story }) {
            let timeOfDeath = new Date(Date.now());
            timeOfDeath.setHours(timeOfDeath.getHours() + 2);

            await fetch(state.apiUrl + `/game/${state.gameId}/kill`, {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + state.token,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    timeOfDeath: timeOfDeath,
                    story: story,
                    biteCode: bitecode,
                    lat: state.location.lat,
                    lng: state.location.lng,
                    game: {
                        id: state.gameId
                    },
                    killer: {
                        id: state.player.id
                    }
                })
            });
            dispatch('fetchPlayer')
            dispatch('fetchPlayers')
            dispatch('fetchGame')
            await dispatch('fetchKills')
        },

        /**
         * Fetch all the kills in the current game.
         */
        async fetchKills({ state, commit }) {
            const response = await fetch(state.apiUrl + `/game/${state.gameId}/kill`);
            const data = await response.json();
            for (let i = 0; i < data.length; i++) {
                const res = await fetch(state.baseApiUrl + data[i].killer);
                const killer = await res.json();

                const resp = await fetch(state.baseApiUrl + data[i].victim);
                const victim = await resp.json();

                let date = new Date(data[i].timeOfDeath)
                date.setHours(date.getHours() - 2)
                data[i] = {
                    ...data[i],
                    latlng: new latLng(data[i].lat, data[i].lng),
                    timeOfDeath: date.toLocaleString(),
                    killer: killer.username,
                    victim: victim.username
                }
            }
            commit("setKills", data);
        },

        /**
         * Post a check in to your squad.
         */
        async postCheckIn({ state, commit }) {
            commit('setPlayerCheckIn', state.location);
            let timeOfCheckIn = new Date(Date.now());
            timeOfCheckIn.setHours(timeOfCheckIn.getHours() + 2);

            await fetch(state.apiUrl + `/game/${state.gameId}/squad/${state.squadId}/check-in`, {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + state.token,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    timeOfCheckIn: timeOfCheckIn,
                    lat: state.location.lat,
                    lng: state.location.lng,
                    game: {
                        id: state.gameId
                    },
                    squad: {
                        id: state.squadId,
                    },
                    squadMember: {
                        id: state.player.squadMember.id
                    }
                })
            });

            socket.emit("checkIn")
        },

        /**
         * Fetch all the check ins from your squad.
         */
        async fetchSquadCheckIns({ state, commit, dispatch }) {
            await dispatch("fetchSquad");

            if (state.squadId !== null) {
                const response = await fetch(state.apiUrl + `/game/${state.gameId}/squad/${state.squadId}/check-in`);
                const data = await response.json();
                for (let i = 0; i < data.length; i++) {
                    const res = await fetch(state.baseApiUrl + data[i].squadMember);
                    const squadMember = await res.json();

                    if (!squadMember.human) {
                        data.splice(i, 1)
                        continue;
                    }

                    let date = new Date(data[i].timeOfCheckIn)
                    date.setHours(date.getHours() - 2)
                    data[i] = {
                        ...data[i],
                        latlng: new latLng(data[i].lat, data[i].lng),
                        timeOfCheckIn: date.toLocaleString(),
                        squadMember: squadMember.username
                    }
                }
                commit("setCheckIns", data);
            }

            else {
                commit("setCheckIns", []);
            }
        },
    },
    getters: {
        /**
         * Decode the jwt token to json format
         */
        decodedToken: (state) => {
            const base64Url = state.token.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));

            return JSON.parse(jsonPayload);
        },

        /**
         * Check if the current user is an Administrator
         */
        isAdmin: (state, getters) => {
            if (!state.authenticated) {
                return false;
            }
            return getters.decodedToken.roles.includes("Administrator");
        },

        playersAlive: (state) => {
            return state.players.filter(player => player.human).length;
        }
    }
})