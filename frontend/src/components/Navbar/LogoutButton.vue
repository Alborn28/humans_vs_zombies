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
        async handleLogout() {
            this.logout();
        }
    }
}
</script>

<style scoped>
    .logout-btn {
        width: 140px;
        padding: 8px;
        border: none;
        border-radius: 8px;
        
        font-family: ZombieCarshel;
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
    @media screen and (max-width: 740px) {
  .logout-btn{
    width: 100%;
  }
}
</style>