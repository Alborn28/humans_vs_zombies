<template>
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
</template>

<script>
import socket from "../../../socket/socket";

export default {
  data() {
    return {
      squadChats: [],
      msg: "",
    };
  },

  created() {
    socket.on("squadMessage", (msg) => {
      this.squadChats.push(msg);
      this.$nextTick(() => {
        const chatWindow = this.$el.querySelector("#squad-chat-window");
        chatWindow.scrollTop = chatWindow.scrollHeight;
      });
    });
  },

  methods: {
    sendSquadMessage(msg) {
      socket.emit("squadMessage", msg);
      this.msg = "";
    },
  },
};
</script>

<style>
</style>