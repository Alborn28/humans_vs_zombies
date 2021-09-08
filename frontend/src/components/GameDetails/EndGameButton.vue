<template>
  <div v-if="game.gameState === 'IN_PROGRESS' && role === 'Administrator' ">
    <button class="reg-btn" type="button" @click="endGame">
      End Game
    </button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  computed: {
    ...mapState(['game', 'role'])
  },
  methods: {
    ...mapActions(["endGame"])
  }
};
</script>

<style>
.reg-btn {
  padding: 5px;
  align-items: center;
  margin-right: 400px;
  margin-left: 400px;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>