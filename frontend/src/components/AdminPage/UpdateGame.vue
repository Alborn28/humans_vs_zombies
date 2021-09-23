<template>
  <div class="update-game-container">
    <form class="update-game-form">
      <h3>Edit Game</h3>
      <div>
        <label><strong>Name: </strong></label>
        <input v-model="game.name" class="select-component" />
      </div>
      <div>
        <label><strong>Description: </strong></label>
        <input v-model="game.description" class="select-component" />
      </div>
      <div>
        <label class="game-state"><strong>Game state: </strong></label>
        <select v-model="game.gameState" class="select-component">
          <option value="REGISTRATION">Registration</option>
          <option value="IN_PROGRESS">In Progress</option>
          <option value="COMPLETE">Complete</option>
        </select>
      </div>
      <div>
        <label><strong>Start date: </strong></label>
        <input
          v-model="game.startDate"
          type="datetime-local"
          class="select-component"
        />
      </div>
      <div>
        <label><strong>End date: </strong></label>
        <input
          v-model="game.endDate"
          type="datetime-local"
          class="select-component"
        />
      </div>
      <button type="button" @click="updateGame">Update Game</button>
    </form>
    
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  props: {
    game: Object,
  },
  computed: {
    ...mapState(["apiUrl", "token"]),
  },

  methods: {
    async updateGame() {
      await fetch(this.apiUrl + "/game/" + this.game.id, {
        method: "PATCH",
        headers: {
          Authorization: "Bearer " + this.token,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: this.game.id,
          name: this.game.name,
          description: this.game.description,
          gameState: this.game.gameState,
          startDate: this.game.startDate,
          endDate: this.game.endDate,
        }),
      });

      alert("Game updated!");
      this.$router.push("/");
    },
  },
};
</script>

<style scoped>
.select-component {
  margin-top: 10px;
  background-color: lime;
  color: #834fd6;
  width: 150px;
  border: none;
  text-align-last: center;
  border-radius: 8px;
}
.update-game-form {
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
  backdrop-filter: blur(25px);
  background-color: rgba(255, 255, 255, 0.1);
  border: 5px solid rgba(255, 255, 255, 0.3);
  box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
  z-index: 10;
  padding: 8px;
  color: aliceblue;
  min-height: 280px;
  max-height: 280px;
  min-width: 290px;
  max-width: 290px;
}

.update-game-form button {
  margin-top: 10px;
  width: 150px;
  border-radius: 8px;
  border: none;
  color: lime;
  background-color: rgba(95, 95, 255, 0.479);
  transition: 0.3s;
  font-size: 16px;
}
.game-state {
  text-transform: capitalize;
}

.update-game-form button:hover {
  cursor: pointer;
  background-color: rgba(132, 132, 253, 0.479);
  transition: 0.3s;
}
@media screen and (max-width: 350px) {
  .update-game-form {
    width: 90%;
  }
}
</style>