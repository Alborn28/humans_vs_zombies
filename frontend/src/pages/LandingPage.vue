<template>
  <div>
      <GameList />
      <CreateGameButton />
  </div>
</template>

<script>
import GameList from '../components/LandingPage/GameList.vue'
import CreateGameButton from '../components/LandingPage/CreateGameButton.vue'

export default {
    name: "LandingPage",
    components: {
        GameList,
        CreateGameButton
    }
}
</script>

<style>
    
</style>
