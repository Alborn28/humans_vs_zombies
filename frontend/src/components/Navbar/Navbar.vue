<template>
    <div class="navbar">
        <span v-if="!this.authenticated">
            <LoginButton />
        </span>
        <span v-if="this.authenticated">
            <LogoutButton />
        </span>
        <span v-if="!this.authenticated">
            <RegisterButton />
        </span>
    </div>
</template>

<script>
import LoginButton from './LoginButton.vue'
import LogoutButton from './LogoutButton.vue'
import RegisterButton from './RegisterButton.vue'
import { mapState } from 'vuex'

export default {
    name: "Navbar",
    components: {
        LoginButton,
        LogoutButton,
        RegisterButton
    },
    computed: {
        ...mapState(['authenticated'])
    }
}
</script>

<style>
    .navbar {
        width: 100%;
        height: 70px;
        background-color: #000000;
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>