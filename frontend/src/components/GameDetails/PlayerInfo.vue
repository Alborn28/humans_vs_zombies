<template>
  <div class="player-info">
    <ul>
      <li>
        <p>{{ player.biteCode }}</p>
      </li>
      <li>
        <p>{{player.username}}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
export default {
  data() {
    return {
      player: {
        biteCode: "",
        email: "",
      },
    };
  },
  /**
   * Fetch player with player email.
   */
  async created() {
    await fetch(`https://hvz-experis-api.herokuapp.com/api/v1/game/${this.gameId}/player/email/${this.decodedToken.email}`)
      .then((respons) => {
        respons.json().then((data) => {
          this.player = data;
        });
      })
      .catch(function (err) {
        console.error("Fetch Error: ", err);
      });
  },

  computed: {
    ...mapState(["gameId"]),
    ...mapGetters(["decodedToken"]),
  },
};
</script>

<style>
  .player-info {
    border: 1px solid black;
  }

  .player-info ul {
    list-style: none;
  }
</style>