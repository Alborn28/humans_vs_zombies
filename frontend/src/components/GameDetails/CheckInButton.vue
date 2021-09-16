<template>
  <div v-if="this.game.gameState === 'IN_PROGRESS' && this.squadId !== null && player.human" class="button-container">
    <button @click="postCheckIn" class="check-in">Check in</button>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  computed: {
    ...mapState(['squadId', 'game', 'player'])
  },
  methods: {
    ...mapActions(['postCheckIn']),
  }
}
</script>

<style scoped>
 .check-in {
    width: 400px;
    padding: 12px;
    border: none;
    border-radius: 8px;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    transition: .3s;
    background-color: rgba(95, 95, 255, 0.479);
    color: lime;
    margin-top: 25px; 
    font-size: 18px;
  }
  .check-in:hover {
    transition: .3s;
    cursor: pointer;
    background-color: rgba(132, 132, 253, 0.479);
    transition: .3s;
  }
</style>