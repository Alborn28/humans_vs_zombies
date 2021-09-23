<template>
  <div class="update-player-container">
    <form class="update-player-form">
      <h3>Edit {{player.username}} </h3>
      <div>
        <label><strong>Is human? </strong></label>
        <select v-model="player.human" class="select-component">
            <option value="true">True</option>
            <option value="false">False</option>
        </select>
      </div>
      <div>
          <label><strong>Is patient zero? </strong></label>
        <select v-model="player.patientZero" class="select-component">
            <option value="true">True</option>
            <option value="false">False</option>
        </select>
      </div>
      <div>
        <label><strong>Bite code: </strong></label>
        <input type="text" v-model="player.biteCode" class="select-component">
      </div>
      <button type="button" @click="updatePlayer">Update Player</button>
    </form>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
    props:{
        player:Object,
        gameId: Number,
    },
    computed: {
        ...mapState(["apiUrl", "token"]),
    },

  methods: {
    async updatePlayer() {
      await fetch(this.apiUrl + "/game/" + this.gameId + "/player/" + this.player.id, {
        method: "PATCH",
        headers: {
          Authorization: "Bearer " + this.token,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: this.player.id,
          human: this.player.human,
          patientZero: this.player.patientZero,
          biteCode: this.player.biteCode
        }),
      });

      alert("Player updated!")
      this.$router.push("/")
    },
  },
};
</script>

<style>
.select-component {
  margin-top: 10px;
  background-color: lime;
  color: #834fd6;
  width: 150px;
  border: none;
  text-align-last: center;
  border-radius: 8px;
}

.update-player-form {
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

.update-player-form button {
  margin-top: 10px;
  width: 150px;
  border-radius: 8px;
  border: none;
  color: lime;
  background-color: rgba(95, 95, 255, 0.479);
  transition: .3s;
  font-size: 16px;
}
.game-state {
  text-transform: capitalize;
}

.update-player-form button:hover {
    cursor: pointer;
    background-color: rgba(132, 132, 253, 0.479);
    transition: .3s;
  }

@media screen and (max-width: 350px) {
  .update-player-form  {
    width: 90%;
  }
}
</style>