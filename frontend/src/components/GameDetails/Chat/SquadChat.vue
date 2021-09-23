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
  props: {
    squadChats: Array
  },
  data() {
    return {
      msg: "",
    };
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