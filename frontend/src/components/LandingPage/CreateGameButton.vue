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
        /**
         * Route to the CreateGamePage
         */
        handleClick() {
            this.$router.push("/create-game")
        }
    }
}
</script>

<style>
    .createGameButton {
        background-color: rgba(95, 95, 255, 0.479);
        margin-left: 50px;
        padding: 8px;
        color: #fff;
        border: none;
        border-radius: 8px;
        margin-bottom: 10px;
    }
    .createGameButton:hover {
        cursor: pointer;
        background-color: rgba(132, 132, 253, 0.479);
    }
</style>