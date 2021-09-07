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
          {{ game.id }}
        </li>
        <li>
          {{ game.gameState }}
        </li>
        <li v-for="(player, index) in this.game.players" v-bind:key="index">
          {{ player.name }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      game: {
        name: "",
        description: "",
        gameState: ""
      },
    };
  },

  /**
   * Fetch information about specific game
   */
  async created() {
    await fetch(`https://hvz-experis-api.herokuapp.com/api/v1/game/${this.gameId}`)
      .then((respons) => {
        respons.json().then((data) => {
          this.game.name = data.name;
          this.game.gameState = data.gameState;
          //  this.game.description = data.description;

        });
      })
      .catch(function (err) {
        console.error("Fetch Error: ", err);
      });
  },


  computed: {
    ...mapState(['gameId'])
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

