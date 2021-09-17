<template>
  <div class="admin-components-container" >

      <Navbar />
      <div class="select-game-components">
      <div v-if="!gameSelected" class="select-container">
        <h3>select game</h3>
            <select @change="handleChangeGame" class="select-component">
          <option v-for="(game, index) in games" :key="index" :value="game.id" class="option-component">
              {{game.name}}
          </option>
        </select>
        <button @click="handleSelectGame" class="button" >
            Select game
        </button>
      </div>
      <div v-if="gameSelected">
            <form>
                <label><strong>Name: </strong></label><input v-model="game.name"/>
                <label><strong>Description: </strong></label><input v-model="game.description" />
                <label class="game-state"><strong>Game state: </strong></label>
                <select v-model="game.gameState">
                    <option value="REGISTRATION">Registration</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="COMPLETE">Complete</option>
                </select>
                <label><strong>Start date: </strong></label><input v-model="game.startDate" type="datetime-local" />
                <label><strong>End date: </strong></label><input v-model="game.endDate" type="datetime-local" />
            </form>
            <button @click="updateGame">Update Game</button>
      </div>
      </div>
  </div>
</template>

<script>
import {  mapActions, mapGetters, mapState } from 'vuex';
import Navbar from '../components/Navbar/Navbar.vue'
export default {
    components: {
        Navbar
    },
    data() {
        return {
            gameId: '',
            game: {},
            gameSelected: false
        }
    },
    async created(){  
        if (!this.isAdmin) {
            this.$router.push("/");
        }
        await this.fetchGames();
        this.gameId = this.games[0].id;
    },
    computed:{
        ...mapGetters(["isAdmin"]),
        ...mapState(["games", "apiUrl", "token"])
    },
    methods:{
        ...mapActions(["fetchGames"]),
        handleChangeGame(event) {
            this.gameId = event.target.value;
        },
        async handleSelectGame() {
            this.gameSelected = true;
            const response = await fetch(this.apiUrl + '/game/' + this.gameId);
            const data = await response.json();
            this.game = data;
            this.game.startDate = this.game.startDate.slice(0, -6);
            this.game.endDate = this.game.endDate.slice(0, -6);
        },
        async updateGame() {
            await fetch(this.apiUrl + '/game/' + this.gameId, {
                method: 'PATCH',
                headers: {
                    "Authorization": "Bearer " + this.token,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    id: this.game.id,
                    name: this.game.name,
                    description: this.game.description,
                    gameState: this.game.gameState,
                    startDate: this.game.startDate,
                    endDate: this.game.endDate
                })
            });
        }
    },
}
</script>

<style scoped>

.select-game-components{
    justify-content: center;
        align-items: center;
        display: flex;
}
.admin-components-container{
    background-image: url("/assets/admin-bg.jpg");
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    width: 100%;
    height: 100%;
    position: fixed;
    
}
    .game-state {
        text-transform: capitalize;
    }
    .select-container{
        justify-content: center;
        align-items: center;
        display: flex;
    flex-direction: column;
     backdrop-filter: blur(25px);
    background-color: rgba(255, 255, 255, 0.1);
    border: 5px solid rgba(255, 255, 255, 0.3);
    box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
    z-index: 10;
    width:30%;
    
        padding: 20px;
    }
    .select-component{
        
        margin-top: 10px;
        background-color: lime;
        color: red;
        width:150px;
        border:none;
        text-align-last:center;
    
    border-radius: 8px;
    }
    .option-component{
        
        border-radius: 8px;

    }
    .select-component:focus{
        outline:none;
    }
    .button{
        margin-top: 10px;
        width:150px;
        border-radius: 8px;
        border:none;
        color: red;
        background-color: lime;
}
</style>