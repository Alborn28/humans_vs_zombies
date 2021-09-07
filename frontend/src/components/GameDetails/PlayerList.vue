<template>

  <div class="player-list">
      <ul v-if="playersLoaded">
          <li v-for="(player, index) in players" v-bind:key="index" >
              {{player.email}}
          </li>
      </ul>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {

data(){
    return{
        playersLoaded: false
    }
},

/**
 * Fetches all the players from a specific game
 */
async created() {
    await this.fetchPlayers();
    this.playersLoaded = true;
  },

  computed: {
    ...mapState(['players'])
  },

  methods: {
    ...mapActions(['fetchPlayers'])
  }
}
</script>

<style>
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