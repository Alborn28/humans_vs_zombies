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
        async handleClick() {
            this.login();
        }
    }
}
</script>

<style scoped>
    .login-btn {
        width: 140px;
        padding: 8px;
        border: none;
        border-radius: 8px;
        font-size: 16px;
        font-family: ZombieCarshel;
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
    @media screen and (max-width: 740px) {
  .login-btn{
    
    width: 100%;
  }
}
</style>