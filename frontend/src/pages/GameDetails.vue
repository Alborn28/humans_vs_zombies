<template>
  <div class="game-details">
    <div class="content">
      <Navbar />
      <GameInfo />
      <GameMap />
      <PlayerList />
      <div class="buttons">
        <CheckInButton />
        <RegisterButton />
      </div>
      <SquadList/>
      <div class="buttons">
        <StartGameButton />
        <EndGameButton />
      </div>
      <div class="row">
        <PlayerDetails />
        <Chat />
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '../components/Navbar/Navbar.vue'
import GameInfo from '../components/GameDetails/GameInfo.vue'
import GameMap from '../components/GameDetails/GameMap.vue'
import CheckInButton from '../components/GameDetails/CheckInButton.vue'
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
        CheckInButton,
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

<style scoped>
  .game-details {
    background-image: url("/assets/background.jpg");
    background-position: 50% 80%;
    background-repeat: no-repeat;
    transform: scaleX(1.25);
    width: 100%;
    height: 100%;
    position: fixed;
  }
  .content {
    transform: scaleX(0.8);
    overflow-y: scroll;
    overflow-x: hidden;
    height: 100%;
  }
  .buttons {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 25px;
  }
  .row{
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-left: 50px;
    margin-right: 50px;
    margin-bottom: 30px;
  }
</style>