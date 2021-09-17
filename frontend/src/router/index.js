import VueRouter from 'vue-router'
import Start from '../pages/LandingPage.vue'
import Game from '../pages/GameDetails.vue'
import CreateGame from '../pages/CreateGame.vue'
import AdminPage from '../pages/AdminPage.vue'


/**
 * Sets up the routes for the application
 */
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
    },
    {
        path: '/admin-page',
        name: 'AdminPage',
        component: AdminPage
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router
