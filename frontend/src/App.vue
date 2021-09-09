<template>
    <div class="app">
        <Navbar />
        <router-view />
    </div>
</template>

<script>
import { mapActions } from 'vuex'
import Navbar from '../src/components/Navbar/Navbar.vue'
export default {
    name: 'App',
      components: {
          Navbar
    },

    created() {
        this.init();
    },

    methods: {
        ...mapActions(['init'])
    }
}
</script>

<style>
    * {
        margin: 0;
        padding: 0;
    }
</style>