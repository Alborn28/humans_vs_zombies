<template>
  <div class="player-stats">
    <ul>
      <li v-for="(player, index) in players" v-bind:key="index">
        {{ player.email }}
      </li>
    </ul>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  computed: {
    ...mapState(["players"]),
  },

  created() {
    this.fetchPlayers(1);
    console.log(this.players);
  },

  methods: {
    ...mapActions(["fetchPlayers"]),
  },
};
</script>

<style>
.player-stats {
  border: 1px solid black;
  padding: 5px;
  margin-right: 400px;
  margin-left: 400px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.player-stats ul{
    list-style: none;
}
</style>