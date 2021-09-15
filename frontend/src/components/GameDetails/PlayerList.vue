<template>

  <div class="player-list" v-if="players.length > 0 && game.gameState === 'REGISTRATION'">
      <ul>
          <li v-for="(player, index) in players" v-bind:key="index" >
              {{player.username}}
          </li>
      </ul>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {
/**
 * Fetches all the players from a specific game
 */
async created() {
    await this.fetchPlayers();
  },

  computed: {
    ...mapState(['players', 'game'])
  },

  methods: {
    ...mapActions(['fetchPlayers'])
  }
}
</script>

<style scoped>
  .player-list{
  border: 1px solid black;
  padding: 5px;
  margin-right: 400px;
  margin-left: 400px;
  margin-top: 10px;
  margin-bottom: 10px;
  }

  .player-list ul{
    list-style: none;
  }
</style>