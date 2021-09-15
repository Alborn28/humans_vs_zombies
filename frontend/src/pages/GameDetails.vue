<template>
  <div class="game-details">
    <Navbar />
    <GameInfo />
    <GameMap />
    <PlayerList />
    <SquadList/>
    <RegisterButton />
    <StartGameButton />
    <EndGameButton />
    <div class="row">
      <PlayerDetails />
      <Chat />
    </div>
    
  </div>
</template>

<script>
import Navbar from '../components/Navbar/Navbar.vue'
import GameInfo from '../components/GameDetails/GameInfo.vue'
import GameMap from '../components/GameDetails/GameMap.vue'
import PlayerList from '../components/GameDetails/PlayerList.vue'
import RegisterButton from '../components/GameDetails/RegisterButton.vue'
import StartGameButton from '../components/GameDetails/StartGameButton.vue'
import EndGameButton from '../components/GameDetails/EndGameButton.vue'
import Chat from '../components/GameDetails/Chat.vue'
import PlayerDetails from '../components/GameDetails/PlayerDetails.vue'
import { mapState } from 'vuex'
import SquadList from '../components/GameDetails/SquadList.vue'


export default {
    name: "GameDetails",
    components: {
        Navbar,
        GameInfo, 
        GameMap,
        PlayerList,
        RegisterButton,
        StartGameButton,
        Chat,
        EndGameButton,
        PlayerDetails,
        SquadList
    },

    created() {
        if(!this.authenticated || this.gameId == 0) {
            this.$router.push("/");
        }
    },

    computed: {
        ...mapState(['gameId', 'authenticated'])
    }
}

</script>

<style>
.row{
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-left: 50px;
    margin-right: 50px;
}
</style>