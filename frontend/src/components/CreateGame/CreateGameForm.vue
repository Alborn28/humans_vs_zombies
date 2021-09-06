<template>
  <form class="createGameForm" @submit="handleSubmit">
      <div>
        <label for="name">Name: </label>
        <input id="name" type="text" v-model="name">
      </div>
      
      <div>
        <label for="description">Description: </label>
        <input id="description" type="text" v-model="description">
      </div>

      <div>
          <label for="startDate">Start date: </label>
          <input id="startDate" type="datetime-local" v-model="startDate">
      </div>

      <div>
          <label for="endDate">End date: </label>
          <input id="endDate" type="datetime-local" v-model="endDate">
      </div>
      
      <div>
          <button type="submit">Create game</button>
      </div>
  </form>
</template>

<script>
export default {
    name: "CreateGameForm",
    data() {
        return {
            name: "",
            description: "",
            startDate: "",
            endDate: ""
        }
    },

    methods: {
        handleSubmit(event) {
            event.preventDefault();
            console.log(this.name);
            console.log(this.description);
            console.log(this.startDate);
            console.log(this.endDate);
        }
    }
}
</script>

<style>
    .createGameForm {
        margin: auto;
        width: 70%;
        margin-top: 50px;
    }
</style>