<template>
  <div class="game">
    <div class="game-title">
      <h1 class="game-header">
        {{ game.name }}
      </h1>
    </div>
    <div class="game-info">
      <ul>
        <li>
          <strong>Description: </strong> {{ game.description }}
        </li>
        <li class="game-state">
         <strong> Game state: </strong> {{ game.gameState.replace('_', ' ').toLowerCase() }}
        </li>
        <div v-if="game.gameState === 'IN_PROGRESS'">
          <li>
           <strong> Players alive: </strong> {{playersAlive}} 
          </li>
          <li>
           <strong> Time left: </strong>
          </li>
        </div>
        <div v-if="game.gameState === 'COMPLETE'">
          <li>
            <strong> Winning faction: </strong>
          </li>
          <li>
           <strong> Zombie with most kills: </strong>
          </li>
        </div>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";

export default {
  async created() {
    await this.fetchGame();
  },

  computed: {
    ...mapState(['game']),
    ...mapGetters(['playersAlive'])
  },

  methods: {
    ...mapActions(['fetchGame'])
  }
};

</script>
<style>
  .game {
    color: lime;
  }
  .game-info {
    list-style: none;
    padding: 5px;
    margin: auto;
    width: 50%;
    backdrop-filter: blur(25px);
    background-color: rgba(255, 255, 255, 0.1);
    box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
    border: 5px solid rgba(255, 255, 255, 0.3);
    border-radius: 8px;
    color: rgba(178, 58, 248, 0.781);
    font-size: 20px;
  }
  .game-title{
    text-align: center;
    text-transform: uppercase;
    margin-bottom: 10px;
  }
  .game-header{
    font-family: plasdrip;
  }
  .game-info ul {
    list-style: none;
  }
  .game-state {
    text-transform: capitalize;
  }
</style>

