<template>
  <div>
    <ul>
      <li v-for="(chat,index) in chats" v-bind:key="index">
        <h3 v-if="chat.humanGlobal===true">
            {{ chat.message }}
        </h3>
        <h3 v-if="chat.humanGlobal===false" >
            {{ chat.message }}
            </h3>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  data() {
    return {
      chats: [
        {
          id: 0,
          message: "",
          chatTime: "",
          humanGlobal: Boolean,
          zombieGlobal: Boolean,
          squadOnly: Boolean,
          game: {
            id: 0,
          },
          player: {
            id: 0,
          },
          squad: {
            id: 0,
          },
        },
      ],
    };
  },

  async created() {
    await fetch(`https://hvz-experis-api.herokuapp.com/api/v1/game/${this.gameId}/chat`)
      .then((respons) => {
        respons.json().then((data) => {
          this.chats = data;
          //  this.game.players = data.players;
        });
      })
      .catch(function (err) {
        console.error("Fetch Error :-S", err);
      });
  },

  computed: {
    ...mapState(['gameId'])
  }
};
</script>

<style>
</style>