<template>
  <div v-if="isAdmin && game.gameState === 'REGISTRATION'">
    <button class="reg-btn" type="button" @click="startGame">
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