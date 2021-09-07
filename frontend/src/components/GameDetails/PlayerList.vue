<template>
  <div>
      <ul v-if="playersLoaded">
          <li v-for="(player, index) in players" v-bind:key="index" >
              {{player.email}}
          </li>
      </ul>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {

data(){
    return{
        playersLoaded: false
    }
},

async created() {
    await this.fetchPlayers();
    this.playersLoaded = true;
  },

  computed: {
    ...mapState(['players'])
  },

  methods: {
    ...mapActions(['fetchPlayers'])
  }
}
</script>

<style>

</style>