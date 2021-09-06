<template>
  <div>
      <ul v-if="gamesLoaded">
          <li v-for="(game, index) of games" v-bind:key="index">
              <div class="game">
                  <p>Name: {{game.name}}</p>
                  <p>Game state: {{game.gameState}}</p>
                  <p>Registered players: {{game.players.length}}</p>
                  <p>Start date: </p>
                  <p>End date: </p>
              </div>
          </li>
      </ul>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
export default {
    name: "GameList",
    data() {
        return {
            gamesLoaded: false
        }
    },

    async created() {
        await this.fetchGames();
        this.gamesLoaded = true;
    },

    computed: {
        ...mapState(['games'])
    },

    methods: {
        ...mapActions(['fetchGames']),
    }
}
</script>

<style>
    .game {
        border: 1px solid black;
        padding: 5px;
    }
</style>