<template>
  <div class="background">
      <div class="content">
        <Navbar />
        <GameList />
      </div>
  </div>
</template>

<script>
import GameList from '../components/LandingPage/GameList.vue'
import Navbar from '../components/Navbar/Navbar.vue'

export default {
    name: "LandingPage",
    components: {
        GameList,
        Navbar
    }
}
</script>

<style scoped>
    .background {
        background-image: url("/assets/background.jpg");
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
        width: 100%;
        height: 100%;
        position: fixed;
    }
    .content {
        overflow-y: scroll;
        overflow-x: hidden;
        height: 100%;
    }
</style>
