<template>
  <div>
      <select name="" id="" v-for="game in fetchGames()">
          <option>
              game 1
          </option>
          <option>
              game 2
          </option>
      </select>
  </div>
</template>

<script>
import {  mapActions, mapGetters, mapState } from 'vuex';
export default {

    computed:{
        ...mapGetters(["isAdmin"]),
        ...mapState(["games"])
    },
    methods:{
        ...mapActions(["fetchGames"])
    },
   async created(){  
        if (!this.isAdmin) {
      this.$router.push("/");
        }
        await this.fetchGames();
    },



}
</script>

<style>

</style>