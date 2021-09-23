<template>
  <div class="create-game-page">
    <div class="content">
      <Navbar />
      <CreateGameForm />
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import CreateGameForm from "../components/CreateGame/CreateGameForm.vue";
import Navbar from "../components/Navbar/Navbar.vue";

export default {
  name: "CreateGame",
  components: {
    CreateGameForm,
    Navbar,
  },

  created() {
    document.title = "Humans VS Zombies";
    
    if (!this.isAdmin) {
      this.$router.push("/");
    }
  },

  computed: {
    ...mapGetters(["isAdmin"]),
  },
};
</script>

<style scoped>
    *{
        color: lime;
    }
    .create-game-page {
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