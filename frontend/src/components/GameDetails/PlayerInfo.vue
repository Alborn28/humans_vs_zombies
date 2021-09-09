<template>
  <div class="player-info" v-if="player.id !== null">
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
      <li>
        <p>Squad om man har någon</p>
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
  async created() {
    this.fetchPlayer();
  },

  computed: {
    ...mapState(["player"])
  },

  methods: {
    ...mapActions(["fetchPlayer"])
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