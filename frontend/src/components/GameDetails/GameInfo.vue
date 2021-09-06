<template>
  <div class="column">
    <h1>
      {{ game.name }}
    </h1>
    <ul>
      <li>
        {{ game.id }}
      </li>
      <li>
        {{ game.gameState }}
      </li>
      <li v-for="(player,index) in this.game.players" v-bind:key="index" >
        {{ player.name }}
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
// import { mapActions } from "vuex";

export default Vue.extend({
  data() {
    return {
      game: {
        id: null,
        name: "",
        gameState: "",
        players: [
          {
            name: "Markus",
          },
          {
            name: "Aljosa",
          },
        ],
      },
    };
  },

  async created() {
    await fetch("https://hvz-experis-api.herokuapp.com/api/v1/game/1")
      .then((respons) => {
        respons.json().then((data) => {
          this.game.id = data.id;
          this.game.name = data.name;
          this.game.gameState = data.gameState;
        //  this.game.players = data.players;
        });
      })
      .catch(function (err) {
        console.error("Fetch Error :-S", err);
      });
  },

  methods: {
  },
});
</script>

