<template>
    <div class="app">
        <router-view />
    </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name: 'App',

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
    .app {
        width: 100%;
        height: 100vh;
    }
</style>