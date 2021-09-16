<template>
  <div class="game">
    <div class="game-title">
      <h1>
        {{ game.name }}
      </h1>
    </div>
    <div class="game-info">
      <ul>
        <li>
          Description: {{ game.description }}
        </li>
        <li class="game-state">
          Game state: {{ game.gameState.replace('_', ' ').toLowerCase() }}
        </li>
        <div v-if="game.gameState === 'IN_PROGRESS'">
          <li>
            Players alive: {{playersAlive}}
          </li>
          <li>
            Time left: 
          </li>
        </div>
        <div v-if="game.gameState === 'COMPLETE'">
          <li>
            Winning faction:
          </li>
          <li>
            Zombie with most kills: 
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
  }
  .game-title{
    text-align: center;
    text-transform: uppercase;
    margin-bottom: 10px;
  }
  .game-info ul {
    list-style: none;
  }
  .game-state {
    text-transform: capitalize;
  }
</style>

