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

      <CreateGameMap @zoomUpdated="handleZoomUpdated" @boundsUpdated="handleBoundsUpdated" /> 
      
      <div>
          <button type="submit">Create game</button>
      </div>
  </form>
</template>

<script>
import { mapState } from 'vuex';
import CreateGameMap from './CreateGameMap.vue';

export default {
    name: "CreateGameForm",
    components: {
        CreateGameMap
    },

    data() {
        return {
            name: "",
            description: "",
            startDate: "",
            endDate: "",
            neLat: "",
            neLng: "",
            swLat: "",
            swLng: "",
            zoom: 12
        }
    },

    computed: {
        ...mapState(['apiUrl', 'token'])
    },

    methods: {
        /**
         * Post the created game to the database
         */
        handleSubmit(event) {
            event.preventDefault();

            fetch(this.apiUrl + "/game", {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + this.token, 
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name: this.name,
                    gameState: "REGISTRATION",
                    description: this.description,
                    startDate: this.startDate,
                    endDate: this.endDate,
                    neLat: this.neLat,
                    neLng: this.neLng,
                    swLat: this.swLat,
                    swLng: this.swLng
                })
            })
            .then(response => {
                if(response.status >= 200 && response.status <= 299) {
                    alert("Game created successfully!");
                }

                else if(response.status === 401 || response.status === 403) {
                    alert("You need to be an admin to create a game");
                }

                else {
                    alert("Failed to create the game...")
                }
            })
        },

        handleZoomUpdated(zoom){
            this.zoom = zoom;
        },

        handleBoundsUpdated(bounds){
            this.neLat = bounds._northEast.lat;
            this.neLng = bounds._northEast.lng;
            this.swLat = bounds._southWest.lat;
            this.swLng = bounds._southWest.lng;
        }
    }
}
</script>

<style>
    .createGameForm {
        margin: auto;
        width: 100%;
        margin-top: 50px;
    }
</style>