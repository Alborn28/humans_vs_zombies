<template>
  <div class="game">
    <div class="game-title">
      <h1 class="game-header">
        {{ game.name }}
      </h1>
    </div>
    <div class="game-info">
      <div class="left-column">
        <p><strong>Description: </strong></p>

        <p><strong> Game state: </strong></p>

        <p v-if="game.gameState==='IN_PROGRESS'" ><strong> Players alive: </strong></p>

        <p v-if="game.gameState==='COMPLETE'" ><strong> Winning faction: </strong></p>
      </div>
      <div class="right-column">
        <p>{{ game.description }}</p>
        <p class="capitalize">{{ game.gameState.replace("_", " ").toLowerCase() }}</p>
        <p v-if="game.gameState==='IN_PROGRESS'" >{{ playersAlive }} / {{ game.players.length }}</p>
        <p v-if="game.gameState==='COMPLETE'" >{{playersAlive > 0 ? 'HUMAN' : 'ZOMBIE'}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";

export default {
  computed: {
    ...mapState(["game"]),
    ...mapGetters(["playersAlive"]),
  }
};
</script>
<style>
.capitalize{
  text-transform: capitalize;
}
.game {
  color: lime;
}
.game-info {
  padding: 5px;
  margin: auto;
  width: 50%;
  backdrop-filter: blur(25px);
  background-color: rgba(255, 255, 255, 0.1);
  box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
  border: 5px solid rgba(255, 255, 255, 0.3);
  border-radius: 8px;
  color: rgba(178, 58, 248, 0.781);
  font-size: 22px;
  display: flex;
}

.game-title {
  text-align: center;
  text-transform: uppercase;
  margin-bottom: 10px;
  font-size: 30px;
}
.game-header {
  font-family: plasdrip;
}
.game-info ul {
  list-style: none;
}
.game-state {
  text-transform: capitalize;
}

.left-column {
    width: 50%;
    text-align: right;
    margin-right: 10px;
}

.right-column {
    width: 50%;
    text-align: left;
    margin-left: 10px;
}

 @media screen and (max-width: 800px) {
    .game-info  {
      width: 90%;
    }
  }
</style>

