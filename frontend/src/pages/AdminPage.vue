<template>
  <div class="admin-components-container">
    <Navbar />
    <div class="select-game-components">
        <SelectGame v-if="!gameSelected" @game-selected="handleGameSelected" />
        <UpdateGame :game="game" v-if="gameSelected"/> 
        <SelectPlayer v-if="gameSelected && !playerSelected" :gameId="game.id" @player-selected="handlePlayerSelected"/>
        <UpdatePlayer v-if="playerSelected" :gameId="game.id" :player="player" />
        <SelectSquad v-if="gameSelected && !squadSelected" :gameId="game.id" @squad-selected="handleSquadSelected"/>
        <UpdateSquad v-if="squadSelected" :gameId="game.id" :squad="squad"/>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SelectGame from '../components/AdminPage/SelectGame.vue';
import UpdateGame from '../components/AdminPage/UpdateGame.vue';
import SelectPlayer from '../components/AdminPage/SelectPlayer.vue';
import UpdatePlayer from '../components/AdminPage/UpdatePlayer.vue';
import Navbar from "../components/Navbar/Navbar.vue";
import SelectSquad from '../components/AdminPage/SelectSquad.vue';
import UpdateSquad from '../components/AdminPage/UpdateSquad.vue';
export default {


  components: {
    Navbar,
    SelectGame,
    UpdateGame,
    SelectPlayer,
    UpdatePlayer,
    SelectSquad,
    UpdateSquad
  },
  data() {
    return {
      game: {},
      gameSelected: false,
      playerSelected:false,
      player:{},
      squad:{},
      squadSelected:false
    };
  },
  async created() {
    if (!this.isAdmin) {
      this.$router.push("/");
    }
  },
  computed: {
    ...mapGetters(["isAdmin"]),
    
  },
  methods: {
    handleGameSelected(game){
        this.game=game;
        this.gameSelected=true;
    },
    handlePlayerSelected(player){
            this.player=player;
            this.playerSelected=true;
    },
    handleSquadSelected(squad){
            this.squad=squad;
            this.squadSelected=true;
    }
  },
};
</script>

<style scoped>
.select-game-components {
  justify-content: center;
  align-items: center;
  display: flex;
  color: aliceblue;
}
.select-game-components * {
  margin: 5px;
}
.admin-components-container {
  background-image: url("/assets/admin-bg.jpg");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  width: 100%;
  height: 100%;
  position: fixed;
  overflow-y: scroll;
  overflow-x: hidden;
}
 @media screen and (max-width: 900px) {
    .select-game-components   {
      flex-direction: column;
    }
}
</style>