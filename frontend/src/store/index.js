import Vue from 'vue'
import Vuex from 'vuex'
import Keycloak from 'keycloak-js'

Vue.use(Vuex);
Vue.config.devtools = true

export default new Vuex.Store({
    state: {
        keycloak: {},
        token: "",
        authenticated: false,
        role: "",
        players: [],
        apiUrl: "https://hvz-experis-api.herokuapp.com/api/v1",
        games: [],
        gameId: 0,
        game: {},
        player: {},
        chats: []
    },
    mutations: {
        setKeycloak: (state, payload) => {
            state.keycloak = payload;
        },
        setToken: (state, payload) => {
            state.token = payload;
        },
        setAuthenticated: (state, payload) => {
            state.authenticated = payload;
        },

        setRole: (state, payload) => {
            state.role = payload;
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
            commit('setRole', kc.tokenParsed.roles[0]);
            //console.log(kc.token)
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
         * Fetch a list of all the games
         */
        async fetchGames({ state, commit }) {
            const response = await fetch(state.apiUrl + "/game");
            const data = await response.json();
            commit('setGames', data);
        },

        /**
         * Fetch a list of all the players registered for the current game
         */
        async fetchPlayers({ state, commit }) {
            const response = await fetch(state.apiUrl + `/game/${state.gameId}/player`)
            const data = await response.json()
            commit('setPlayers', data)
        },

        /**
         * Fetch the current game
         */
        async fetchGame({ state, commit }) {
            const response = await fetch(state.apiUrl + `/game/${state.gameId}`);
            const data = await response.json();
            commit('setGame', data);
        },

        /**
         * Register player for the current game
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
        },

        /**
         * Fetch player
         */
        async fetchPlayer({ state, commit, getters }) {
            const response = await fetch(`https://hvz-experis-api.herokuapp.com/api/v1/game/${state.gameId}/player/email/${getters.decodedToken.email}`);
            const data = await response.json();
            commit("setPlayer", data);
        },

        /**
         * Fetch all the chats in the current game
         */
        async fetchChats({ state, commit }) {
            const response = await fetch(`https://hvz-experis-api.herokuapp.com/api/v1/game/${state.gameId}/chat`);
            const data = await response.json();
            commit("setChats", data);
        },

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

            dispatch('fetchGame')
            dispatch('fetchPlayer')
        },

        async endGame({ state, dispatch }) {
            await fetch(state.apiUrl + `/game/${state.gameId}/end`, {
                method: "PUT",
                headers: {
                    "Authorization": "Bearer " + state.token,
                    "Content-Type": "application/json"
                }
            });
            dispatch('fetchGame')
        },

        async kill({ state, dispatch }, bitecode, story) {
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
                    //Koordinater
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
        }
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