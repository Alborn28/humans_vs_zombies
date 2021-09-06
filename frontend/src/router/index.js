import VueRouter from 'vue-router'
import Start from '../pages/LandingPage.vue'
import Game from '../pages/GameDetails.vue'

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
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router
