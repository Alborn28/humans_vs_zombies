<template>
  <div v-if="isAdmin && game.gameState === 'REGISTRATION'">
    <button class="start-btn" type="button" @click="startGame">
      Start Game
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
    ...mapActions(["startGame"])
  }
};
</script>

<style scoped>
  .start-btn {
    width: 300px;
    height: 35px;
    padding: 8px;
    border: none;
    border-radius: 8px;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    font-family: ZombieCarshel;
    font-size: 20px;
    transition: .3s;
    background-color: rgba(53, 223, 53, 0.479);
    color: #fff;
    margin-bottom: 50px;
  }
  .start-btn:hover {
    transition: .3s;
    cursor: pointer;
    background-color: rgba(161, 255, 161, 0.479);
    transition: .3s;
  }
</style>