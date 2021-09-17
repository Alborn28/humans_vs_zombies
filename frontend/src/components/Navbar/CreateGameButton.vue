<template>
  <button v-if="isAdmin" type="button" class="createGameButton" @click="handleClick">Create Game</button>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: "CreateGameButton",
    computed: {
        ...mapGetters(['isAdmin'])
    },
    methods: {
        handleClick() {
            this.$router.push("/create-game")
        }
    }
}
</script>

<style>
    .createGameButton {
        width: 140px;
        padding: 8px;
        border: none;
        border-radius: 8px;
        margin-right: 30px;
        font-family: ZombieCarshel;
        transition: .3s;
        background-color: rgba(95, 95, 255, 0.479);
        color: #fff;
    }
    .createGameButton:hover {
        cursor: pointer;
        background-color: rgba(132, 132, 253, 0.479);
        transition: .3s;
    }
</style>