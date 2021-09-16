<template>
  <div v-if="game.gameState === 'REGISTRATION' && player.id === null">
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
    /**
     * The method registeres a new player to the specific game. The game is specified with the game ID.
     * The token is used to authentication of the user. email is fetched from the store (index.js) as a part of the decoded token object
     */
    ...mapActions(["registerPlayer"])
  }
};
</script>

<style>
  .reg-btn {
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
  .reg-btn:hover {
    transition: .3s;
    cursor: pointer;
    background-color: rgba(132, 132, 253, 0.479);
    transition: .3s;
  }
</style>