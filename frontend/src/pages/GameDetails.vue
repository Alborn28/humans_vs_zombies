<template>
  <div class="game-details">
    <GameInfo />
    <GameMap />
    <PlayerList />
    <RegisterButton />
    <div class="row">
      <PlayerInfo />
      <Chat />
    </div>
    <PlayerStats />
  </div>
</template>

<script>

import GameInfo from '../components/GameDetails/GameInfo.vue'
import GameMap from '../components/GameDetails/GameMap.vue'
import PlayerList from '../components/GameDetails/PlayerList.vue'
import RegisterButton from '../components/GameDetails/RegisterButton.vue'
import Chat from '../components/GameDetails/Chat.vue'
import PlayerStats from '../components/GameDetails/PlayerStats.vue'
import PlayerInfo from '../components/GameDetails/PlayerInfo.vue'
import { mapState } from 'vuex'


export default {
    name: "GameDetails",
    components: {
        GameInfo, 
        GameMap,
        PlayerList,
        RegisterButton,
        Chat,
        PlayerStats,
        PlayerInfo
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