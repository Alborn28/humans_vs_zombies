import VueRouter from 'vue-router'
import Start from '../pages/LandingPage.vue'
import Game from '../pages/GameDetails.vue'
import CreateGame from '../pages/CreateGame.vue'

const routes = [
    {
        path: '/',
        name: 'Start',
        component: Start
    },
    {
        path: '/game-details',
        name: 'Game',
        component: Game
    },
    {
        path: '/create-game',
        name: 'CreateGame',
        component: CreateGame
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router
