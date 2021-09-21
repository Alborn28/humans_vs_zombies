<template>
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
</template>

<script>
import socket from "../../../socket/socket";

export default {
  data() {
    return {
      factionChats: [],
      msg: "",
    };
  },

  created() {
    socket.on("factionMessage", (msg) => {
      this.factionChats.push(msg);
      this.$nextTick(() => {
        const chatWindow = this.$el.querySelector("#faction-chat-window");
        chatWindow.scrollTop = chatWindow.scrollHeight;
      });
    });
  },

  methods: {
    sendFactionMessage(msg) {
      socket.emit("factionMessage", msg);
      this.msg = "";
    },
  },
};
</script>

<style>
</style>