<template>
  <CreateGameForm />
</template>

<script>
import { mapGetters } from 'vuex';
import CreateGameForm from '../components/CreateGame/CreateGameForm.vue'

export default {
    name: "CreateGame",
    components: {
        CreateGameForm
    },

    created() {
        if(!this.isAdmin) {
            this.$router.push("/");
        }
    },

    computed: {
        ...mapGetters(['isAdmin'])
    }
}
</script>

<style>

</style>