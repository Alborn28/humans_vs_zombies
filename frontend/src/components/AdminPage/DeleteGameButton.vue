<template>
      <button class="deleteGameButton" @click="deleteGame">Delete game</button>
 
</template>

<script>
import { mapState } from 'vuex';
export default {
    props:{
        gameId:Number
    },
    methods:{
      async deleteGame() {
      await fetch(this.apiUrl + "/game/" + this.gameId, {
        method: "DELETE",
        headers: {
          Authorization: "Bearer " + this.token,
          "Content-Type": "application/json",
        },
      });
      alert("Game deleted!");
      this.$router.push("/");
    },
    },
    computed:{
        ...mapState(["token", "apiUrl"])
    }
}
</script>

<style>
.deleteGameButton{
    border-radius: 8px;
    width:15%;
    background-color:rgba(211, 30, 45, .8);
    color: aliceblue;
    border:none;
    transition: 3;
    padding:8px;
    font-size: 20px;
}
.deleteGameButton:hover{
    background-color: rgba(132, 132, 253, 0.8);
    transition: .3;
    cursor: pointer
}

</style>