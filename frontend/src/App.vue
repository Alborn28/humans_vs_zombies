<template>
<div>
  <Navbar />
  <CreateGame />
</div>
</template>

<script>
import Navbar from './components/Navbar/Navbar.vue'
import CreateGame from './pages/CreateGame.vue'

export default {
  name: 'App',
  components: {
    Navbar,
    CreateGame
  }
}
</script>

<style>

</style>
