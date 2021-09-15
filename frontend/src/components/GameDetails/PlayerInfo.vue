<template>
  <div class="player-info" v-if="playerLoaded">
    <ul>
      <li>
        <p>Username: {{player.username}}</p>
      </li>
      <li>
        <p>Bite code: {{ player.biteCode }}</p>
      </li>
      <li>
        <p>Faction: {{player.human ? "Human" : "Zombie"}}</p>
      </li>
      <li v-if="player.human">
        <p>Squad: {{squadId === null ? "None" : squad.name}}</p>
      </li>
      <li v-if="!player.human">
        <p>Kills: {{player.kills.length}}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  /**
   * Fetch player with player email.
   */
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
    border: 1px solid black;
    padding: 5px;
  }

  .player-info ul {
    list-style: none;
  }
</style>