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
  props: {
    globalChats: Array
  },
  data() {
    return {
      msg: "",
    };
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