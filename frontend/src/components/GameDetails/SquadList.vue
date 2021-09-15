<template>
  <div class="squad-list" v-if="squadId===null && squadsLoaded && player.id !== null" >
      <h4>squad lists</h4>
      <ul>
          <li v-for="(squad,index) in squads" v-bind:key="index" @click="joinSquad({rank: 'MEMBER', squadId: squad.id})">
              {{squad.name}}
          </li>
      </ul>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {
  data() {
    return {
      squadsLoaded: false
    }
  },

/**
 * Fetches all the squads from a specific game
 */
async created() {  
    await this.fetchSquads();    
    await this.fetchSquad();
    this.squadsLoaded = true;    
  },

  computed: {
    ...mapState(['squads', "squadId", "player"])
  },

  methods: {
    ...mapActions(['fetchSquads', "fetchSquad", "joinSquad"])
  }
}
</script>

<style>

  .squad-list{
  border: 1px solid black;
  padding: 5px;
  margin-right: 400px;
  margin-left: 400px;
  margin-top: 10px;
  margin-bottom: 10px;
  }

  .squad-list ul{
    list-style: none;
  }
</style>