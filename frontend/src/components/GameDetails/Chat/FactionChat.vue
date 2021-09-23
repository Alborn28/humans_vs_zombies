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
  props: {
    factionChats: Array
  },
  data() {
    return {
      msg: "",
    };
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