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

        players:[]

        apiUrl: "https://hvz-experis-api.herokuapp.com/api/v1",
        games: []

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

        setPlayers:(state, payload)=>{
            state.players=payload;

        setGames: (state, payload) => {
            state.games = payload;

        }
    },
    actions: {

        async fetchPlayers ({commit},id){
            const respons= await fetch(`https://hvz-experis-api.herokuapp.com/api/v1/game/${id}/player`)
            const data = await respons.json()
            commit('setPlayers', data)
        },

        async init({ commit }) {
            const kc = Keycloak('keycloak.json');

            await kc.init({ onLoad: 'check-sso' });

            commit('setKeycloak', kc);
            commit('setToken', kc.token);
            commit('setAuthenticated', kc.authenticated);
        },

        async login({ state }) {
            if(!state.authenticated) {
                await state.keycloak.login();
            }
        },

        async logout({ state }) {
            if(state.authenticated) {
                await state.keycloak.logout();
            }
        },

        async register({ state }) {
            if(!state.authenticated) {
                await state.keycloak.register();
            }
        },
              
        async fetchGames({ state, commit }) {
            const response = await fetch(state.apiUrl + "/game");
            const data = await response.json();
            commit('setGames', data);
        }
    },
    getters: {
        decodedToken: (state) => {
            const base64Url = state.token.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));
        
            return JSON.parse(jsonPayload);
        },

        isAdmin: (state, getters) => {
            if(!state.authenticated) {
                return false;
            }
            
            return getters.decodedToken.roles.includes("Administrator");
        }
    }
})