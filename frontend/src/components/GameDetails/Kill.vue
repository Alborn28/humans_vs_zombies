<template>
  <div
    class="killWindow"
    v-if="game.gameState === 'IN_PROGRESS' && player.human === false">
    <form @submit="handleSubmit">
      <div>
        <label>insert bite code</label>
        <input type="text" v-model="biteCode" />
      </div>
      <div>
        <label>insert story-description</label>
        <input type="text" v-model="story" />
      </div>
      <button type="submit">KILL</button>
    </form>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      biteCode: "",
      story: "",
    };
  },

  computed: {
    ...mapState(["game", "player"]),
  },

  methods: {
    ...mapActions(["kill"]),

    async handleSubmit(event) {
      event.preventDefault();
      this.kill(this.biteCode, this.story);
    },
  },
};
</script>

<style scoped>
.killWindow {
  border: 1px solid black;
  padding: 5px;
}
</style>