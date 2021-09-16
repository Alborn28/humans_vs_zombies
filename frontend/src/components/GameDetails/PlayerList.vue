<template>

  <div class="player-list" v-if="players.length > 0 && game.gameState === 'REGISTRATION'">
    <h3 class="title">Registered players</h3>
      <ul>
          <li class="player" v-for="(player, index) in players" v-bind:key="index" >
              <h4>{{player.username}}</h4>
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
.title {
  text-transform: uppercase;
  margin-bottom: 15px;
}

  .player-list{
    width:50%;
    margin:auto;
    color: lime;
    text-align: center;
    border: 5px solid rgba(255, 255, 255, 0.3);
    border-radius: 8px;
    padding:10px;
    margin-top: 25px;
  }

  .player-list ul{
    list-style: none;
  }

  .player {
    margin-bottom: 8px;
    color: rgba(178, 58, 248, 0.781);
  }
</style>