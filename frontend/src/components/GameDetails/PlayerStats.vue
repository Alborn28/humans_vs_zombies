<template>
<div>
    <ul>
        <li v-for="(player, index) in players" v-bind:key="index" >
                {{player.email}}
        </li>
    </ul>
</div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  
    computed:{
        ...mapState(["players"])
    },

  created() {
      this.fetchPlayers();
  },

  methods: {
    ...mapActions(["fetchPlayers"]),
  },
};
</script>

<style>
</style>