<template>
  <div>
      <button type="button" @click="handleClick()">Login</button>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: "LoginButton",

    async created() {
        this.init();
    },

    methods: {
        ...mapActions(['login', 'init']),
        async handleClick() {
            this.login();
        }
    }
}
</script>

<style>

</style>