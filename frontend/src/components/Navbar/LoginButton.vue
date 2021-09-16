<template>
    <div>
        <button class="login-btn" type="button" @click="handleClick()">Login</button>
    </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: "LoginButton",
 
    methods: {
        ...mapActions(['login']),
        /**
         * When a user clicks on the login button, the keycloak login method gets called from the store. 
         */
        async handleClick() {
            this.login();
        }
    }
}
</script>

<style scoped>
    .login-btn {
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
    .login-btn:hover {
        transition: .3s;
        cursor: pointer;
        background-color: rgba(132, 132, 253, 0.479);
        transition: .3s;
    }
</style>