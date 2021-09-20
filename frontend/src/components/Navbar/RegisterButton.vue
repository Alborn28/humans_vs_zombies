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
        async handleRegister() {
            this.register();
        }
    }
}
</script>

<style scoped>
    .register-btn {
        width: 140px;
        padding: 8px;
        border: none;
        border-radius: 8px;
        font-family: ZombieCarshel; 
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
    @media screen and (max-width: 580px) {
  .register-btn{
   
    width: 100%;
  }
}
</style>