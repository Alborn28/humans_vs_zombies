<template>
  <div class="player-info" v-if="playerLoaded">
    <h3 class="title"> {{ player.username }} </h3>
    <ul>
      <li>
        <p><strong>Bite code:</strong> {{ player.biteCode }}</p>
      </li>
      <li>
        <p><strong>Faction:</strong> {{player.human ? "Human" : "Zombie"}}</p>
      </li>
      <li v-if="player.human">
        <p><strong>Squad:</strong> {{squadId === null ? "None" : squad.name}}</p>
      </li>
      <li v-if="!player.human">
        <p><strong>Kills: </strong>{{player.kills.length}}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  data(){
    return{
      playerLoaded:false,
    }
  },

  async created() {
    await this.fetchPlayer();
    await this.fetchSquad();
    this.playerLoaded=true;
  },

   computed: {
    ...mapState(["player", "squad", "squadId"])
  },

  methods: {
    ...mapActions(["fetchPlayer", "fetchSquad"])
  }
};
</script>

<style>
  .player-info {
    color: rgba(178, 58, 248, 0.781);
    font-size: 1.4em;
    margin-bottom: 20px;
  }

  .player-info ul {
    list-style: none;
    backdrop-filter: blur(25px);
    background-color: rgba(255, 255, 255, 0.1);
    border: 5px solid rgba(255, 255, 255, 0.3);
    box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
    z-index: 10;
    padding: 25px;
    text-align:center;
    border-radius:8px;
  }
  .title {
    margin-bottom: 20px;
    text-align: center;
  }
</style>