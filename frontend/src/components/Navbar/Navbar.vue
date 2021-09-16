<template>
    <div class="navbar">
        <HomeButton />
        <span v-if="!this.authenticated">
            <LoginButton />
        </span>
        <span v-if="this.authenticated">
            <LogoutButton />
        </span>
        <span v-if="!this.authenticated">
            <RegisterButton />
        </span>
        <span v-if="isAdmin">
            <CreateGameButton />
        </span>
    </div>
</template>

<script>
import LoginButton from './LoginButton.vue'
import LogoutButton from './LogoutButton.vue'
import RegisterButton from './RegisterButton.vue'
import HomeButton from './HomeButton.vue'
import CreateGameButton from '../LandingPage/CreateGameButton.vue'
import { mapGetters, mapState } from 'vuex'

export default {
    name: "Navbar",
    components: {
        LoginButton,
        LogoutButton,
        RegisterButton,
        HomeButton,
        CreateGameButton
    },
    computed: {
        ...mapState(['authenticated']),
        ...mapGetters(['isAdmin'])
    }
}
</script>

<style>
    .navbar {
        width: 100%;
        min-height: 70px;
        background-color: #000000;
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 10;
        margin-bottom: 50px;
    }
</style>