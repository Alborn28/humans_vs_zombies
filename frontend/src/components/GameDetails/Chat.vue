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
        v-on:click="activetab = 3"
        v-bind:class="[activetab === 3 ? 'active' : '']"
        >Squad</a
      >
    </div>

    <div class="chat-container">
      <div v-if="activetab === 1" class="chat-content">
        <form class="chat-form" @submit.prevent="sendMessage(msg)">
          <ul>
            <li v-for="(chat, index) in chats" :key="index">
              {{ chat }}
            </li>
          </ul>
          <input v-model="msg" class="chat-msg" placeholder="Message..." />
          <button class="send-chat-msg" type="submit">Send</button>
        </form>
      </div>
      <div v-if="activetab === 2" class="chat-content"></div>
      <div v-if="activetab === 3" class="chat-content"></div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import socket from '../../socket/socket';
export default {
  data() {
    return {
      chats: [],
      msg: "",
      activetab: 1
    };
  },
  created(){
    socket.auth = {gameId: this.gameId}
    socket.connect()
    socket.on("chatMessage", (msg) => {
      this.chats.push(msg)
    })
  },
  destroyed(){
    socket.disconnect()
  },
  computed: {
    ...mapState(["player", "gameId"]),
  },
  methods: {
    sendMessage(msg) {
      socket.emit("chatMessage", msg);
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