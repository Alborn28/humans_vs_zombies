<template>
  <div class="chat" v-if="player.id !== null">
    <form class="chat-form" @submit.prevent="sendMessage(msg)"> 
      <ul>
        <li v-for="(chat, index) in chats" :key="index">
            {{chat}}
        </li>
      </ul>
      <input v-model="msg" class="chat-msg" placeholder="Message..." />
      <button class="send-chat-msg" type="submit">Send</button>
    </form>
  </div>
</template>

<script>
import {mapState} from 'vuex';
export default {
  data(){
    return{
      chats: [],
      msg: ""
    }
  },
  computed:{
    ...mapState(["player"])
  },
  methods:{
    sendMessage(msg) {
      this.$socket.emit("chatMessage", msg)
      this.msg = ""
    }
  },
  sockets:{
    chatMessage(data){
      this.chats.push(data)
    }
  }
  
};
</script>

<style>
  .chat {
    border: 1px solid black;
  }
  .chat ul {
    list-style: none;
  }
</style>