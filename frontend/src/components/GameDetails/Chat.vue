<template>
  <div class="chat" v-if="player.id !== null">
    <ul>
      <li v-for="(chat, index) in chats" v-bind:key="index">
        <h3 v-if="chat.humanGlobal === true">
          {{ chat.message }}
        </h3>
        <h3 v-if="chat.humanGlobal === false">
          {{ chat.message }}
        </h3>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  /**
   * Fetch all the chats for this specific game.
   */
  async created() {
    this.fetchChats();
  },

  computed: {
    ...mapState(["chats", "player"]),
  },

  methods: {
    ...mapActions(["fetchChats"])
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