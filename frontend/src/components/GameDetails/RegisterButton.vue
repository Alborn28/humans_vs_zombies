<template>
  <div>
      <button class="reg-btn" type="button" @click="registrate()">Registration button</button>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
export default {

 
    methods:{
    /**
   * The method registeres a new player to the specific game. The game is specified with the game ID.
   * The token is used to authentication of the user. email is fetched from the store (index.js) as a part of the decoded token object
   * 
   */
        async registrate(){
            await fetch(this.apiUrl + `/game/${this.gameId}/player`,{
              method: "POST",
                headers: {
                    "Authorization": "Bearer " + this.token, 
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: this.decodedToken.email,
                    username: this.decodedToken.preferred_username,
                    human: true,
                    patientZero: false,
                    game : {
                      id: this.gameId
                    }
                })
            });
            
        }

    },
    computed:{
      ...mapState(['apiUrl', 'gameId', 'token']),
      ...mapGetters(['decodedToken'])
    }

}
</script>

<style>
.reg-btn{
  padding: 5px;
  align-items: center;
  margin-right: 400px;
  margin-left: 400px;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>