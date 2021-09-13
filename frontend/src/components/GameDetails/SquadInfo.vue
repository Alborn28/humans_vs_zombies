<template>
  <div v-if="squadLoaded">
    <div v-if="squadId === null">
      <input type="name" v-model="name" />
      <button @click="register_A_Squad()">register squad</button>
    </div>
    <div v-if="squadId !== null">
      <p> <strong>Your squad: </strong> {{squad.name}}</p>

      <ul>
        <li v-for="(member,index) in squadMembers" :key="index" >
          {{member.username}}
          {{squad.squadMembers[index].rank}}
          {{member.human ? "Alive" : "Dead"}}
        </li>
      </ul>
      <button class="leave-squad" @click="leaveSquad">Leave squad</button>
    </div>
  </div>
</template>


<script>
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      name: "",
      squadLoaded: false
    };
  },

  computed: {
    ...mapState(["squadId", "squad", "squadMembers"]),
  },
  async created() {
    await this.fetchSquad();
    this.squadLoaded = true;
  },

  methods: {
    ...mapActions(["registerSquad", "fetchSquad", "leaveSquad"]),

   async register_A_Squad() {
      await this.registerSquad(this.name);
    },
  },
};
</script>

<style>
</style>