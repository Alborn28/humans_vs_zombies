<template>
  <div v-if="player.id !== null">
    <div class="tabs">
      <a
        v-on:click="activetab = 1"
        v-bind:class="[activetab === 1 ? 'active' : '']"
        >Global</a
      >
      <a
        v-on:click="activetab = 2"
        v-bind:class="[activetab === 2 ? 'active' : '']"
        >Faction</a
      >
      <a
        v-if="this.squadId !== null && player.human"
        v-on:click="activetab = 3"
        v-bind:class="[activetab === 3 ? 'active' : '']"
        >Squad</a
      >
    </div>

    <div class="chat-container">
      <div v-if="activetab === 1" class="chat-content">
        <GlobalChat />
      </div>
      <div v-if="activetab === 2" class="chat-content">
        <FactionChat />
      </div>
      <div v-if="activetab === 3 && this.squadId !== null" class="chat-content">
        <SquadChat />
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import socket from "../../../socket/socket";
import FactionChat from './FactionChat.vue';
import GlobalChat from './GlobalChat.vue';
import SquadChat from './SquadChat.vue';

export default {
  components: { GlobalChat, FactionChat, SquadChat },
  data() {
    return {
      msg: "",
      activetab: 1,
      connected: false
    };
  },
  watch: {
    player() {
      /**
       * Check if we are registered to the game and not connected to the socket,
       * in that case connect.
      */
      if(this.player.id !== null && !this.connected) {
        this.connected = true;

        socket.auth = {
          gameId: this.gameId,
          username: this.player.username,
          human: this.player.human,
          squadId: this.squadId
        };

        socket.connect();
        socket.on("connect_error", (error) => {
          console.log(error.message)
        });
      }
    } 
  },

  /**
   * Disconnect from the socket when the component is removed from the DOM.
   */
  destroyed() {
    socket.disconnect();
    this.connected = false;
  },
  
  /**
   * If the player leaves the squad and has the squad chat open, open the global chat instead.
   */
  updated(){
    if(this.activetab === 3 && this.squadId === null){
      this.activetab = 1;
    }
  },
  computed: {
    ...mapState(["player", "gameId", "squadId"]),
  }
};
</script>

<style>
.send-chat-msg{    
    border: none;
    border-radius: 8px;
    font-family: ZombieCarshel;
    transition: .3s;
    background-color: rgba(95, 95, 255, 0.479);
    color: lime; 
    padding: 8px;
    padding-left: 12px;    
    padding-right: 12px;
    font-size: 14px;    
  }
  .send-chat-msg:hover {
    transition: .3s;
    cursor: pointer;
    background-color: rgba(132, 132, 253, 0.479);
    transition: .3s;
  }
.chat-msg{
  background-color: #834fd6; 
  padding: 8px;
  border:none;
  outline:none;
  color:lime;
  border-radius:8px;
  margin-right:5px;
  font-size: 14px;

}
.chat-msg:focus::placeholder {
  color: transparent;
}
.chat-msg::placeholder{
    color: lime;
}

.tabs {
  overflow: hidden;
  margin-left: 20px;
  margin-bottom: -2px;
}

.chat-content ul {
  list-style-type: none;
  overflow-y: scroll;
  overflow-x: hidden;
  word-wrap: break-word;
  min-height: 240px;
  min-width: 290px;
  max-height: 240px;
  max-width: 290px;
  backdrop-filter: blur(25px);
  background-color: rgba(255, 255, 255, 0.1);
  box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
  z-index: 10;
  color: rgba(178, 58, 248, 0.781);
  padding: 10px;
  margin: 10px;
  font-size: 1.2em;
  border-radius:8px;
}

.chat-content li {
  padding: 3px;
}

.chat-content ul::-webkit-scrollbar {
  display: none;
}

.tabs a {
  float: left;
  cursor: pointer;
  padding: 12px 24px;
  transition: background-color 0.2s;
  border: 1px solid #ccc;
  border-right: none;
  background-color: #540dc5;
  border-radius: 10px 10px 0 0;
  font-weight: bold;
  color: #fff;
}
.tabs a:last-child {
  border-right: 1px solid #ccc;
}

/* Change background color of tabs on hover */
.tabs a:hover {
  background-color: #834fd6;
}

/* Styling for active tab */
.tabs a.active {
  background-color: #834fd6;
  color: #fff;
  border-bottom: 2px solid #834fd6;
  cursor: default;
}

/* Style the tab content */
.chat-content {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 8px;
  border: 5px solid rgba(255, 255, 255, 0.3);
  padding: 10px;
  max-height: 320px;
  max-width: 340px;
  background-color: rgb(10, 10, 10);
  margin-bottom: 25px;
}

.input-container {
  margin: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

@media screen and (max-width: 400px) {
  .chat-content {
    width: 90%;
    margin: auto;
  }

  .chat-content ul {
    min-width: 0;
    max-width: 200px;
    margin: auto;
  }
} 

</style>