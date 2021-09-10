<template>
  <div>
    <input type="name" v-model="name"  />
    <button @click="register_A_Squad()">register squad</button>
  </div>
</template>


<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      name: "",
    };
  },

  methods: {
    ...mapActions(["registerSquad"]),

    register_A_Squad() {
      this.registerSquad(this.name);
    },
  },
};
</script>

<style>
</style>