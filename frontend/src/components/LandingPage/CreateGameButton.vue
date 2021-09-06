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
            //Route to Create Game Page
        }
    }
}
</script>

<style>
    .createGameButton {
        background-color: rgba(95, 95, 255, 0.479)
    }
</style>