<template>
  <div v-if="squadId===null && squadLoaded">
    <input type="name" v-model="name"  />
    <button @click="register_A_Squad()">register squad</button>
  </div>
</template>


<script>
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      name: "",
      squadLoaded:false
    };
  },

  computed:{
      ...mapState(["squadId"])
  },
  async created(){
    await this.fetchSquad()
    this.squadLoaded=true
  },

  methods: {
    ...mapActions(["registerSquad","fetchSquad"]),

    register_A_Squad() {
      this.registerSquad(this.name);
    },
  },
};
</script>

<style>
</style>