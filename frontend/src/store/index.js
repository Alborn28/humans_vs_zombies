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
        setGames: (state, payload) => {
            state.games = payload;
        }
    },
    actions: {
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

        async fetchGames({ state, commit }) {
            const response = await fetch(state.apiUrl + "/game");
            const data = await response.json();
            commit('setGames', data);
        }
    },
    getters: {}
})