<template>
  <div v-if="game.gameState === 'IN_PROGRESS' && isAdmin ">
    <button class="end-btn" type="button" @click="endGame">
      End Game
    </button>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
export default {
  computed: {
    ...mapState(['game']),
    ...mapGetters(['isAdmin'])
  },
  methods: {
    ...mapActions(["endGame"])
  }
};
</script>

<style scoped>
  .end-btn {
    width: 100px;
    padding: 8px;
    border: none;
    border-radius: 8px;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    transition: .3s;
    background-color: rgba(211, 30, 45, 0.479);
    color: #fff;
  }
  .end-btn:hover {
    transition: .3s;
    cursor: pointer;
    background-color: rgba(230, 100, 111, 0.479);
    transition: .3s;
  }
</style>