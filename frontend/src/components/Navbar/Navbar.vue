<template>
    <div class="navbar">
        <HomeButton />
        <span v-if="!this.authenticated">
            <LoginButton />
        </span>
        <span v-if="!this.authenticated">
            <RegisterButton />
        </span>
        <span v-if="isAdmin">
            <CreateGameButton />
        </span>
        <span v-if="isAdmin">
            <AdminPageButton />
        </span>
        <span v-if="this.authenticated">
            <LogoutButton />
        </span>
    </div>
</template>

<script>
import LoginButton from './LoginButton.vue'
import LogoutButton from './LogoutButton.vue'
import RegisterButton from './RegisterButton.vue'
import HomeButton from './HomeButton.vue'
import CreateGameButton from './CreateGameButton.vue'
import AdminPageButton from './AdminPageButton.vue'
import { mapGetters, mapState } from 'vuex'

export default {
    name: "Navbar",
    components: {
        LoginButton,
        LogoutButton,
        RegisterButton,
        HomeButton,
        CreateGameButton,
        AdminPageButton
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

        
        padding: 5px;
        backdrop-filter: blur(75px);
        background-color: rgba(255, 255, 255, 0.1);
        box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
        /* border: 1px solid rgba(255, 255, 255, 0.3); */
    }
</style>