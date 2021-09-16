<template>
    <div>
        <button class="register-btn" type="button" @click="handleRegister()">Register</button>
    </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: "RegisterButton",

    methods: {
        ...mapActions(['register']),
        /**
         * When a user clicks on the register button, the keycloak register method gets called from the store. 
         */
        async handleRegister() {
            this.register();
        }
    }
}
</script>

<style scoped>
    .register-btn {
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
    .register-btn:hover {
        transition: .3s;
        cursor: pointer;
        background-color: rgba(132, 132, 253, 0.479);
        transition: .3s;
    }
</style>