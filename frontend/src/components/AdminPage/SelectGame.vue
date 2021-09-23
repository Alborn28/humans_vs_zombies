<template>
  <div class="select-container">
    <h3>select game</h3>
    <select @change="handleChangeGame" class="select-component">
      <option
        v-for="(game, index) in games"
        :key="index"
        :value="game.id"
        class="option-component"
      >
        {{ game.name }}
      </option>
    </select>
    <button @click="handleSelectGame" class="button">Select game</button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      gameId: "",
      game: {},
    };
  },
  computed: {
    ...mapState(["games", "apiUrl", "token"]),
  },
  async created() {
    await this.fetchGames();
    this.gameId = this.games.length === 0 ? null : this.games[0].id;
  },

  methods: {
    ...mapActions(["fetchGames"]),
    handleChangeGame(event) {
      this.gameId = event.target.value;
    },
    async handleSelectGame() {
      const response = await fetch(this.apiUrl + "/game/" + this.gameId);
      const data = await response.json();
      this.game = data;
      this.game.startDate = this.game.startDate.slice(0, -6);
      this.game.endDate = this.game.endDate.slice(0, -6);
      this.$emit("game-selected", this.game)
      
    },
  },
};
</script>

<style scoped>
.select-container {
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
  backdrop-filter: blur(25px);
  background-color: rgba(255, 255, 255, 0.1);
  border: 5px solid rgba(255, 255, 255, 0.3);
  box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
  z-index: 10;
  min-height: 260px;
  max-height: 260px;
  min-width: 290px;
  max-width: 290px;
  padding: 20px;
}
.select-component {
  margin-top: 10px;
  background-color: lime;
  color: #834fd6;
  width: 150px;
  border: none;
  text-align-last: center;
  font-size: 16px;
  border-radius: 8px;
}
.option-component {
  border-radius: 8px;
}
.select-component:focus {
  outline: none;
}
.button {
  margin-top: 10px;
  width: 150px;
  border-radius: 8px;
  border: none;
  color: lime;
  background-color: rgba(95, 95, 255, 0.479);
  transition: .3s;
  font-size: 16px;
}

button:hover {
    cursor: pointer;
    background-color:rgba(132, 132, 253, 0.479);
    transition: .3s;
  }

   @media screen and (max-width: 350px) {
    .select-container {
      width: 90%;
    }
  }
</style>