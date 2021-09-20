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
    @font-face {
        font-family: 'yataghanregular';
        src: url('../public/assets/fonts/yataghan-webfont.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }
    @font-face {
        font-family: 'plasdrip';
        src: url('../public/assets/fonts/plasdrip-webfont.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }
    @font-face {
        font-family: 'plasdrpe';
        src: url('../public/assets/fonts/plasdrpe-webfont.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }
    @font-face {
        font-family: 'Death-Party-ttf';
        src: url('../public/assets/fonts/Death-Party-ttf.ttf') format('truetype');
        font-weight: normal;
        font-style: normal;
    }
    @font-face {
        font-family: 'Death-Party-otf';
        src: url('../public/assets/fonts/Death-Party-otf.otf') format('opentype');
        font-weight: normal;
        font-style: normal;
    }
    @font-face {
        font-family: 'Mantune';
        src: url('../public/assets/fonts/Mantune.ttf') format('truetype');
        font-weight: normal;
        font-style: normal;
    } 
    @font-face {
        font-family: 'ShadowsIntoLight';
        src: url('../public/assets/fonts/ShadowsIntoLight-Regular.ttf') format('truetype');
        font-weight: normal;
        font-style: normal;
    }
     @font-face {
        font-family: 'Spooky-Ghoster-ttf';
        src: url('../public/assets/fonts/Spooky-Ghoster.ttf') format('truetype');
        font-weight: normal;
        font-style: normal;
    }
    @font-face {
        font-family: 'Spooky-Ghoster-otf';
        src: url('../public/assets/fonts/Spooky-Ghoster.otf') format('opentype');
        font-weight: normal;
        font-style: normal;
    }
    @font-face {
        font-family: 'ZombieCarshel';
        src: url('../public/assets/fonts/ZombieCarshel.ttf') format('truetype');
        font-weight: normal;
        font-style: normal;
    } 
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'ShadowsIntoLight';
    }
    .app {
        min-width: 300px;
        width: 100%;
        height: 100vh;
    }
</style>