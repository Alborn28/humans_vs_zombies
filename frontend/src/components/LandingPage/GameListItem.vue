<template>
  <div class="game" @click="$emit('gameClicked', game.id)">
    <div class="left-column">
      <p><strong>Name: </strong></p>
      <p><strong>Game state:</strong></p>
      <p><strong>Registered players: </strong></p>
      <p><strong>Start date:</strong></p>
      <p><strong>End date:</strong></p>
    </div>
    <div class="right-column">
      <p>{{ game.name }}</p>
      <p class="game-state">
        {{ game.gameState.replace("_", " ").toLowerCase() }}
      </p>
      <p>{{ game.players.length }}</p>
      <p>{{ startDate }}</p>
      <p>{{ endDate }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "GameListItem",
  props: {
    game: Object,
  },
  data() {
    return {
      startDate: "",
      endDate: "",
    };
  },

  /**
   * Format the startDate and endDate
   */
  created() {
    let date = new Date(this.game.startDate);
    date.setHours(date.getHours() - 2);
    this.startDate = date.toLocaleString();

    date = new Date(this.game.endDate);
    date.setHours(date.getHours() - 2);
    this.endDate = date.toLocaleString();
  },
};
</script>

<style scoped>
.game {
  padding: 5px;
  margin: auto;
  margin-bottom: 10px;
  backdrop-filter: blur(25px);
  background-color: rgba(255, 255, 255, 0.1);
  box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
  border: 5px solid rgba(255, 255, 255, 0.3);
  z-index: 10;
  color: lime;
  width: 50%;
  border-radius: 8px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  text-align: center;
  display: flex;
}

.game:hover {
  background-color: rgb(233, 233, 233, 0.2);
  cursor: pointer;
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

.game-state {
  text-transform: capitalize;
}
@media screen and (max-width: 580px) {
  .game {
    width: 90%;
  }
}
</style>