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
          <input id="nwLat" type="text" v-model="nwLat">
      </div>

      <div>
          <label for="nwLng">Northwest longitude: </label>
          <input id="nwLng" type="text" v-model="nwLng">
      </div>

      <div>
          <label for="seLat">Southeast latitude: </label>
          <input id="seLat" type="text" v-model="seLat">
      </div>

      <div>
          <label for="seLng">Southeast longitude: </label>
          <input id="seLng" type="text" v-model="seLng">
      </div>
      
      <div>
          <button type="submit">Create game</button>
      </div>
  </form>
</template>

<script>
import { mapState } from 'vuex';
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

    computed: {
        ...mapState(['apiUrl', 'token'])
    },

    methods: {
        handleSubmit(event) {
            event.preventDefault();

            //TODO - Fyll på body i fetch-requesten med resterande parametrar

            fetch(this.apiUrl + "/game", {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + this.token, 
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name: this.name,
                    gameState: "REGISTRATION"
                })
            })
            .then(() => alert("Game created successfully!"))
            .catch((error) => {
                alert("Failed to create the game...")
                console.log(error);
            });
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