<template>
  <div>
      <GameList />
      <CreateGame />
  </div>
</template>

<script>
import GameList from '../components/LandingPage/GameList.vue'
import CreateGame from '../components/LandingPage/CreateGame.vue'

export default {
    name: "LandingPage",
    components: {
        GameList,
        CreateGame
    }
}
</script>

<style>
    
</style>