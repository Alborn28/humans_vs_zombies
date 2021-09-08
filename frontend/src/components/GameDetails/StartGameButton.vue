<template>
  <div>
    <button class="reg-btn" type="button" @click="startGame">
      Start Game
    </button>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  methods: {
    /**
     * The method registeres a new player to the specific game. The game is specified with the game ID.
     * The token is used to authentication of the user. email is fetched from the store (index.js) as a part of the decoded token object
     */
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