<template>
<div v-if="player.id !== null">
  <div class="kill-container" v-if="game.gameState === 'IN_PROGRESS' && player.human === false">
    <form class="killWindow">
      <div class="kill-label">
        <label>Bite code:</label>
        <label>Description:</label>
      </div>
      <div class="kill-input">
        <input type="text" v-model="biteCode" required/>
        <textarea class="kill-description" rows="3" v-model="story" placeholder="(Optional)"/>
      </div>
    </form>
    <div class="kill-button-container">
      <button class="kill-button" type="button" @click="killPlayer">KILL</button>
    </div>
  </div>
</div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import socket from "../../socket/socket";

export default {
  data() {
    return {
      biteCode: "",
      story: "",
    };
  },

  computed: {
    ...mapState(["game", "player"]),
  },

  methods: {
    ...mapActions(["kill"]),

    async killPlayer(event) {
      event.preventDefault();
      this.kill({ bitecode: this.biteCode, story: this.story });
      socket.emit("kill", this.biteCode);
      this.biteCode = "";
      this.story = "";
    },
  },
};
</script>

<style scoped>
.kill-container{
 width:35%;
    margin:auto;
    color: lime;
    text-align: center;
    backdrop-filter: blur(25px);
    background-color: rgba(255, 255, 255, 0.1);
    box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
    border: 5px solid rgba(255, 255, 255, 0.3);
    border-radius: 8px;
    padding:10px;
    margin-top: 25px;
}
.killWindow {
  display: flex;
  justify-content: center;

}
.kill-label {
  width: 50%;
  display: flex;
  flex-direction: column;
  text-align: right;
  margin-right: 5px;
  color: lime;
}
.kill-label label{
  margin-bottom: 15px;

}
.kill-button-container{
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.kill-button {
    width: 100px;
    padding: 8px;
    border: none;
    border-radius: 8px;
    font-family: ZombieCarshel;   
    transition: .3s;
    background-color: rgba(255, 0, 21, 0.514);
    color: #fff;

  }
  .kill-button:hover {
    transition: .3s;
    cursor: pointer;
    background-color: rgba(250, 43, 60, 0.616);
    transition: .3s;
  }
.kill-input {
  width: 50%;
  display: flex;
  flex-direction: column;
  margin-left: 5px;
  
}
.kill-description{
  margin-top: 10px;
  border-radius: 8px;
  width: 150px;
  border:none;
   background-color: #834fd6;
  padding: 5px;
  color: lime;
}
.kill-description::placeholder{

  color: lime;
}
.kill-input input {
  border-radius: 8px;
  width: 150px;
  border:none;
  background-color: #834fd6;
  padding: 5px;
  color: lime;
}
.kill-description:focus{
  outline: none;
}
.kill-input input:focus{
  outline: none;
}
.kill-description:focus::placeholder{
  color:transparent;
}
</style>