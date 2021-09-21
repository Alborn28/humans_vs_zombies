<template>
  <div class="btn-container" v-if="game.gameState === 'REGISTRATION' && player.id === null">
    <button class="reg-btn" type="button" @click="registerPlayer()">
      Registration button
    </button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  computed: {
    ...mapState(['game', 'player'])
  },
  
  methods: {
    ...mapActions(["registerPlayer"])
  }
};
</script>

<style>
  .reg-btn {
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
  .reg-btn:hover {
    transition: .3s;
    cursor: pointer;
    background-color: rgba(132, 132, 253, 0.479);
    transition: .3s;
  }
  @media screen and (max-width: 330px) {
    .btn-container {
      width: 90%;
    }
    .reg-btn  {
      width: 100%;
    }
  } 
</style>