<template>
  <div>
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
        <li>
          Game state: {{ game.gameState }}
        </li>
        <li v-if="game.gameState === 'REGISTRATION'">
          Rules: 
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
  created() {
    this.fetchGame();
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
  .game-info {
    border: 1px solid black;
    list-style: none;
    padding: 5px;
    margin-right: 400px;
    margin-left: 400px;
    margin-top: 10px;
    margin-bottom: 10px;
  }
  .game-title{
    text-align: center;
    margin-top: 20px;
  }
  .game-info ul {
    list-style: none;
  }
</style>

