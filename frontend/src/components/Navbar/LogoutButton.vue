<template>
    <button class="logout-btn" type="button" @click="handleLogout()">
        Log out
    </button>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: "LogoutButton",

    methods: {
        ...mapActions(['logout']),
        /**
         * When a user clicks on the logout button, the logout keycloak method gets called from the store.
         */
        async handleLogout() {
            this.logout();
        }
    }
}
</script>

<style scoped>
    .logout-btn {
        width: 100px;
        padding: 8px;
        border: none;
        border-radius: 8px;
        margin-right: 30px; 
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        transition: .3s;
        background-color: rgba(95, 95, 255, 0.479);
        color: #fff;
    }
    .logout-btn:hover {
        transition: .3s;
        cursor: pointer;
        background-color: rgba(132, 132, 253, 0.479);
        transition: .3s;
    }
</style>