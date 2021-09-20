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
        <form class="chat-form" @submit.prevent="sendGlobalMessage(msg)">
          <ul id="global-chat-window">
            <li v-for="(chat, index) in globalChats" :key="index">
              {{ chat }}
            </li>
          </ul>
          <div class="input-container">
            <input v-model="msg" class="chat-msg" placeholder="Message..." />
            <button class="send-chat-msg" type="submit">Send</button>
          </div>
        </form>
      </div>
      <div v-if="activetab === 2" class="chat-content">
        <form class="chat-form" @submit.prevent="sendFactionMessage(msg)">
          <ul id="faction-chat-window">
            <li v-for="(chat, index) in factionChats" :key="index">
              {{ chat }}
            </li>
          </ul>
          <div class="input-container">
            <input v-model="msg" class="chat-msg" placeholder="Message..." />
            <button class="send-chat-msg" type="submit">Send</button>
          </div>
        </form>
      </div>

      <div v-if="activetab === 3 && this.squadId !== null" class="chat-content">
        <form class="chat-form" @submit.prevent="sendSquadMessage(msg)">
          <ul id="squad-chat-window">
            <li v-for="(chat, index) in squadChats" :key="index">
              {{ chat }}
            </li>
          </ul>
          <div class="input-container">
            <input v-model="msg" class="chat-msg" placeholder="Message..." />
            <button class="send-chat-msg" type="submit">Send</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import socket from "../../socket/socket";
export default {
  data() {
    return {
      globalChats: [],
      factionChats: [],
      squadChats: [],
      msg: "",
      activetab: 1,
      connected: false
    };
  },
  watch: {
    player() {
      if(this.player.id !== null && !this.connected) {
        this.connected = true;
        socket.auth = {
        gameId: this.gameId,
        username: this.player.username,
        human: this.player.human,
        squadId: this.squadId
        };
        socket.connect();
        socket.on("globalMessage", (msg) => {
          this.globalChats.push(msg);
          this.$nextTick(() => {
            const chatWindow = this.$el.querySelector('#global-chat-window');
            chatWindow.scrollTop = chatWindow.scrollHeight;
          })
        });
        socket.on("factionMessage", (msg) => {
          this.factionChats.push(msg);
          this.$nextTick(() => {
            const chatWindow = this.$el.querySelector('#faction-chat-window');
            chatWindow.scrollTop = chatWindow.scrollHeight;
          })
        });
        socket.on("squadMessage", (msg) => {
          this.squadChats.push(msg);
          this.$nextTick(() => {
            const chatWindow = this.$el.querySelector('#squad-chat-window');
            chatWindow.scrollTop = chatWindow.scrollHeight;
          })
        });
        socket.on("connect_error", (error) => {
          console.log(error.message)
        });
      }
    } 
  },
  async created() {
    await this.fetchSquad();
  },
  destroyed() {
    socket.disconnect();
    this.connected = false;
  },
  updated(){
    if(this.activetab === 3 && this.squadId === null){
      this.activetab = 1;
    }
  },
  computed: {
    ...mapState(["player", "gameId", "squadId"]),
  },
  methods: {
    ...mapActions(["fetchSquad"]),
    sendGlobalMessage(msg) {
      socket.emit("globalMessage", msg);
      this.msg = "";
    },
    sendFactionMessage(msg) {
      socket.emit("factionMessage", msg);
      this.msg = "";
    },
    sendSquadMessage(msg) {
      socket.emit("squadMessage", msg);
      this.msg = "";
    },
  },
};
</script>

<style scoped>
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
}

.input-container {
  margin: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>