<template>
  <form class="createGameForm" @submit="handleSubmit">
      <div>
        <label for="name">Name: </label>
        <input required id="name" type="text" v-model="name">
      </div>
      
      <div>
        <label for="description">Description: </label>
        <input required id="description" type="text" v-model="description">
      </div>

      <div>
          <label for="startDate">Start date: </label>
          <input required id="startDate" type="datetime-local" v-model="startDate">
      </div>

      <div>
          <label for="endDate">End date: </label>
          <input required id="endDate" type="datetime-local" v-model="endDate">
      </div>

      <div>
          <label for="nwLat">Northwest latitude: </label>
          <input required id="nwLat" type="text" v-model="nwLat">
      </div>

      <div>
          <label for="nwLng">Northwest longitude: </label>
          <input required id="nwLng" type="text" v-model="nwLng">
      </div>

      <div>
          <label for="seLat">Southeast latitude: </label>
          <input required id="seLat" type="text" v-model="seLat">
      </div>

      <div>
          <label for="seLng">Southeast longitude: </label>
          <input required id="seLng" type="text" v-model="seLng">
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
            endDate: "",
            nwLat: "",
            nwLng: "",
            seLat: "",
            seLng: ""
        }
    },

    methods: {
        handleSubmit(event) {
            event.preventDefault();
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