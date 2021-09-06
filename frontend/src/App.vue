<template>
<div>
  <Navbar />
  <LandingPage />
</div>
</template>

<script>
import Navbar from './components/Navbar/Navbar.vue'
import LandingPage from './pages/LandingPage.vue'

export default {
  name: 'App',
  components: {
    Navbar,
    LandingPage
  }
}
</script>

<style>

</style>
