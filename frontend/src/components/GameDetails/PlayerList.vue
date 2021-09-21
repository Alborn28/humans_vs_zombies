<template>
  <div class="player-list" v-if="players.length > 0 && game.gameState === 'REGISTRATION'">
    <h3 class="title"><strong>Registered players</strong></h3>
      <ul>
          <li class="player" v-for="(player, index) in players" v-bind:key="index" >
              <h3 class="players">{{player.username}}</h3>
          </li>
      </ul>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {
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
    width:35%;
    margin:auto;
    color: lime;
    text-align: center;
    backdrop-filter: blur(25px);
    background-color: rgba(255, 255, 255, 0.1);
    box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
    border: 5px solid rgba(255, 255, 255, 0.3);
    border-radius: 8px;
    padding:10px;
    margin-top: 25px;
    font-size: 20px;
  }

  .player-list ul{
    list-style: none;
  }

  .player {
    margin-bottom: 8px;
    color: rgba(178, 58, 248, 0.781);
  }

   @media screen and (max-width: 800px) {
    .player-list  {
      width: 90%;
    }
  }  
</style>