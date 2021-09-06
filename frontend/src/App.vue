<template>
<div>
  <Navbar />
  <GameDetails/>
  </div>
</template>

<script>
import Navbar from './components/Navbar/Navbar.vue'
import GameDetails from './pages/GameDetails.vue'

export default {
  name: 'App',
  components: {
    Navbar,
    GameDetails
  }
}
</script>

<style>

</style>
