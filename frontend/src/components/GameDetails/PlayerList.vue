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
export default {

data(){
    return{
        players:[{}]
    }
    
},


async created() {
    await fetch("https://hvz-experis-api.herokuapp.com/api/v1/game/2/player")
      .then((respons) => {
        respons.json().then((data) => {
          this.players=data
          console.log(data);
        });
      })
      .catch(function (err) {
        console.error("Fetch Error :-S", err);
      });
  },
}
</script>

<style>

</style>