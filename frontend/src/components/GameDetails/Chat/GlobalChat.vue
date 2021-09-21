<template>
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
</template>

<script>
import socket from "../../../socket/socket";

export default {
  data() {
    return {
      globalChats: [],
      msg: "",
    };
  },

  created() {
    socket.on("globalMessage", (msg) => {
      this.globalChats.push(msg);
      this.$nextTick(() => {
        const chatWindow = this.$el.querySelector("#global-chat-window");
        chatWindow.scrollTop = chatWindow.scrollHeight;
      });
    });
  },

  methods: {
    sendGlobalMessage(msg) {
      socket.emit("globalMessage", msg);
      this.msg = "";
    },
  },
};
</script>

<style>
</style>