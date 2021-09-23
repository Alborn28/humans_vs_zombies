<template>
  <div class="btn-container" v-if="game.gameState === 'IN_PROGRESS' && isAdmin ">
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
    width: 300px;
    padding: 8px;
    border: none;
    border-radius: 8px;
    font-family: ZombieCarshel;
    transition: .3s;
    background-color: rgba(211, 30, 45, 0.479);
    color: #fff;
    margin-bottom: 25px;
    font-size: 18px;
  }
  .end-btn:hover {
    transition: .3s;
    cursor: pointer;
    background-color: rgba(230, 100, 111, 0.479);
    transition: .3s;
  }
  @media screen and (max-width: 330px) {
    .btn-container {
      width: 90%;
    }
    .end-btn  {
      width: 100%;
    }
  } 
</style>