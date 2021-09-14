<template>
  <div class="chat" v-if="player.id !== null">
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
        v-if="this.squadId !== null"
        v-on:click="activetab = 3"
        v-bind:class="[activetab === 3 ? 'active' : '']"
        >Squad</a
      >
    </div>

    <div class="chat-container">
      <div v-if="activetab === 1" class="chat-content">
        <form class="chat-form" @submit.prevent="sendGlobalMessage(msg)">
          <ul>
            <li v-for="(chat, index) in globalChats" :key="index">
              {{ chat }}
            </li>
          </ul>
          <input v-model="msg" class="chat-msg" placeholder="Message..." />
          <button class="send-chat-msg" type="submit">Send</button>
        </form>
      </div>
      <div v-if="activetab === 2" class="chat-content">
        <form class="chat-form" @submit.prevent="sendFactionMessage(msg)">
          <ul>
            <li v-for="(chat, index) in factionChats" :key="index">
              {{ chat }}
            </li>
          </ul>
          <input v-model="msg" class="chat-msg" placeholder="Message..." />
          <button class="send-chat-msg" type="submit">Send</button>
        </form>
      </div>

      <div v-if="activetab === 3 && this.squadId !== null" class="chat-content">
        <form class="chat-form" @submit.prevent="sendSquadMessage(msg)">
          <ul>
            <li v-for="(chat, index) in squadChats" :key="index">
              {{ chat }}
            </li>
          </ul>
          <input v-model="msg" class="chat-msg" placeholder="Message..." />
          <button class="send-chat-msg" type="submit">Send</button>
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
    };
  },
  async created() {
    await this.fetchPlayer();
    await this.fetchSquad();
    socket.auth = {
      gameId: this.gameId,
      username: this.player.username,
      human: this.player.human,
      squadId: this.squadId
    };
    socket.connect();
    socket.on("globalMessage", (msg) => {
      this.globalChats.push(msg);
    });
    socket.on("factionMessage", (msg) => {
      this.factionChats.push(msg);
    });
    socket.on("squadMessage", (msg) => {
      this.squadChats.push(msg);
    });
    socket.on("connect_error", (error) => {
      console.log(error.message)
    });
  },
  destroyed() {
    socket.disconnect();
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
    ...mapActions(["fetchPlayer", "fetchSquad"]),
    sendGlobalMessage(msg) {
      console.log(msg)
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

<style>
.tabs {
  overflow: hidden;
  margin-left: 20px;
  margin-bottom: -2px;
}

.chat-content ul {
  list-style-type: none;
  border: 1px solid black;
  padding: 5px;
  min-height: 150px;
  max-height: 150px;
  min-width: 250px;
  max-width: 250px;
  overflow-y: scroll;
  overflow-x: hidden;
  word-wrap: break-word;
}

.tabs a {
  float: left;
  cursor: pointer;
  padding: 12px 24px;
  transition: background-color 0.2s;
  border: 1px solid #ccc;
  border-right: none;
  background-color: #f1f1f1;
  border-radius: 10px 10px 0 0;
  font-weight: bold;
}
.tabs a:last-child {
  border-right: 1px solid #ccc;
}

/* Change background color of tabs on hover */
.tabs a:hover {
  background-color: #aaa;
  color: #fff;
}

/* Styling for active tab */
.tabs a.active {
  background-color: #fff;
  color: #484848;
  border-bottom: 2px solid #fff;
  cursor: default;
}

/* Style the tab content */
.chat-content {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 3px 3px 6px #e1e1e1;
  border: 1px solid #ccc;
}
</style>