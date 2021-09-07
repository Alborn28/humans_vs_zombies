<template>
  <div class="player-list">
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
  .player-list{
  border: 1px solid black;
  padding: 5px;
  margin-right: 400px;
  margin-left: 400px;
  margin-top: 10px;
  margin-bottom: 10px;
  }

  .player-list ul{
    list-style: none;
  }
</style>