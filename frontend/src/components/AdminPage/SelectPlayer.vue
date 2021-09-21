<template>
  <div class="select-container">
    <h3>select player</h3>
    <select @change="handleChangePlayer" class="select-component">
      <option
        v-for="(player, index) in players"
        :key="index"
        :value="player.id"
        class="option-component"
      >
        {{ player.username }}
      </option>
    </select>
    <button @click="handleSelectPlayer" class="button">Select player</button>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
    props:{
        gameId:Number
    },

  data() {
    return {
      players: [],
      playerId:null,
    };
  },
  computed: {
    ...mapState(["apiUrl" ]),
  },
  async created() {
    const response = await fetch(this.apiUrl + `/game/${this.gameId}/player`)
    const data = await response.json();
    this.players=data;
    this.playerId=this.players.length === 0 ? null : this.players[0].id;
  },

  methods: {
    handleChangePlayer(event) {
      this.playerId = event.target.value;
    },
    async handleSelectPlayer() {
      const response = await fetch(this.apiUrl + "/game/" + this.gameId + "/player/" +this.playerId);
      const data = await response.json();
      
      this.$emit("player-selected", data)
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