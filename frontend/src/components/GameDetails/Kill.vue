<template>
  <div class="killWindow" v-if="game.gameState === 'IN_PROGRESS' && player.human === false">
      <form @submit="handleSubmit">
          <input type="text" v-model="biteCode">
          <input type="text" v-model="story">
          <button type="submit">KILL</button>
      </form>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
    data() {
        return {
            biteCode: "",
            story: ""
        }
    },

    computed: {
        ...mapState(["game", "player"])
    },

    methods: {
        ...mapActions(["kill"]),

        async handleSubmit(event) {
            event.preventDefault();
            this.kill(this.biteCode, this.story);
        }
    }
}
</script>

<style scoped>
    .killWindow {
        border: 1px solid black;
        padding: 5px;
        width: 40%;
        margin: auto;
        margin-bottom: 50px;
    }
</style>