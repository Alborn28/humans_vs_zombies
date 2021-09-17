<template>
  <div class="squad-list" v-if="squadId===null && squadsLoaded && player.id !== null" >
      <h3 class="title" >squad lists</h3>
      <ul>
          <li class="squad" v-for="(squad,index) in squads" v-bind:key="index" @click="joinSquad({rank: 'MEMBER', squadId: squad.id})">
              <p class="name"><strong>Name:</strong> {{squad.name}}</p>
              
              <p class="members"><strong>Members:</strong> {{squad.squadMembers.length}}</p>
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

<style scoped>
.title{
  text-transform: uppercase;
}
  .squad-list{
    width:35%;
    margin:auto;
    color: lime;
    text-align: center;
    backdrop-filter: blur(25px);
    background-color: rgba(255, 255, 255, 0.1);
    box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
    border: 5px solid rgba(255, 255, 255, 0.3);
    padding:10px;
    font-size: 20px;
    border-radius: 8px;
  }

  .squad-list ul{
    list-style: none;
    padding:10px
  }
  .squad{
    padding: 5px;
        margin: auto;
        margin-bottom: 10px;  
        backdrop-filter: blur(25px);
        background-color: rgba(255, 255, 255, 0.1);
        box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
        z-index: 10;
        color: rgba(178, 58, 248, 0.781);
        width: 100%;
        border-radius: 8px;
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
  }
  .squad:hover{
    background-color: rgb(233, 233, 233, 0.2);
        cursor: pointer;
  }
  
 
</style>