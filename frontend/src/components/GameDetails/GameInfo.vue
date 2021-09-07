<template>
  <div class="column">
    <h1>
      {{ game.name }}
    </h1>
    <ul>
      <li>
        {{ game.gameState }}
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import { mapState } from "vuex";
// import { mapActions } from "vuex";

export default Vue.extend({
  data() {
    return {
      game: {
        name: "",
        description: "",
        gameState: ""
      },
    };
  },

  async created() {
    await fetch(`https://hvz-experis-api.herokuapp.com/api/v1/game/${this.gameId}`)
      .then((respons) => {
        respons.json().then((data) => {
          this.game.name = data.name;
          this.game.gameState = data.gameState;
          //  this.game.description = data.description;
        });
      })
      .catch(function (err) {
        console.error("Fetch Error :-S", err);
      });
  },

  computed: {
    ...mapState(['gameId'])
  }
});
</script>

<style>

</style>

