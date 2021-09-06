<template>
    <div class="app">
        <Navbar />
        <router-view />
    </div>
</template>

<script>
import Navbar from '../src/components/Navbar/Navbar.vue'
export default {
    name: 'App',
      components: {
          Navbar
    },
}
</script>

<style>
    * {
        margin: 0;
        padding: 0;
    }
</style>