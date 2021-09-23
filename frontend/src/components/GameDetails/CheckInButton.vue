<template>
  <div class="btn-container" v-if="this.game.gameState === 'IN_PROGRESS' && this.squadId !== null && player.human">
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
    width: 300px;
    padding: 12px;
    border: none;
    border-radius: 8px;
    font-family: ZombieCarshel;
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
  }
    @media screen and (max-width: 330px) {
      .btn-container {
        width: 90%;
      }
      .check-in  {
        width: 100%;
      }
    } 
  
</style>