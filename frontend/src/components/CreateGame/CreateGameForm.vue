<template>
  <form class="create-game-form" @submit="handleSubmit">
    <h3 class="create-game-header">CREATE GAME</h3>  
    <div class="form-container">
      <div class="input-label">
        <label for="name">Name: </label>
        <label for="description">Description: </label>
        <label for="startDate">Start date: </label>
        <label for="endDate">End date: </label>
      </div>
      <div class="input-container">
        <input class="name" required id="name" type="text" v-model="name" />
        <input class="description" required id="description" type="text" v-model="description" />
        <input class="start-date" required id="startDate" type="datetime-local" v-model="startDate" />
        <input class="end-date" required id="endDate" type="datetime-local" v-model="endDate" />
      </div>
    </div>

    <div class="map-container">
      <CreateGameMap
        @zoomUpdated="handleZoomUpdated"
        @boundsUpdated="handleBoundsUpdated"
      />
    </div>

    <div class="create-game-btn-container">
      <button class="create-game-btn" type="submit">Create game</button>
    </div>
  </form>
</template>

<script>
import { mapState } from "vuex";
import CreateGameMap from "./CreateGameMap.vue";

export default {
  name: "CreateGameForm",
  components: {
    CreateGameMap,
  },

  data() {
    return {
      name: "",
      description: "",
      startDate: "",
      endDate: "",
      neLat: 56.88983119447765,
      neLng: 14.869823455810549,
      swLat: 56.866381305955535,
      swLng: 14.754467010498049,
      zoom: 14,
    };
  },

  computed: {
    ...mapState(["apiUrl", "token"]),
  },

  methods: {
    /**
     * Post the game to the database
     */
    handleSubmit(event) {
      event.preventDefault();

      fetch(this.apiUrl + "/game", {
        method: "POST",
        headers: {
          Authorization: "Bearer " + this.token,
          "Content-Type": "application/json",
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
          swLng: this.swLng,
          zoom: this.zoom,
        }),
      }).then((response) => {
        if (response.status >= 200 && response.status <= 299) {
          alert("Game created successfully!");
          this.$router.push("/");
        } else if (response.status === 401 || response.status === 403) {
          alert("You need to be an admin to create a game");
        } else {
          alert("Failed to create the game...");
        }
      });
    },

    handleZoomUpdated(zoom) {
      this.zoom = zoom;
    },

    handleBoundsUpdated(bounds) {
      this.neLat = bounds._northEast.lat;
      this.neLng = bounds._northEast.lng;
      this.swLat = bounds._southWest.lat;
      this.swLng = bounds._southWest.lng;
    },
  },
};
</script>

<style scoped>
.form-container{
  display: flex;
  justify-content: center;
  align-items: center;
  margin: auto;
}

.input-container {
  width: 51%;
  text-align: left;
  display: flex;
  flex-direction: column;
}
.input-container input{
    margin: 6px
}
.input-label {
  width: 40%;
  display: flex;
  text-align: right;
  flex-direction: column;
  margin-top: 27px;
}
.input-label label{
  margin-bottom: 25px;
}
.end-date, .name, .start-date, .description {
  background-color: #834fd6;
  padding: 8px;
  border: none;
  outline: none;
  color: lime;
  border-radius: 8px;
  margin-right: 5px;
  font-size: 14px;
  width: 200px;
} 
.name::placeholder {
  color: lime;
}
.description::placeholder {
  color: lime;
}
.create-game-btn {
  width: 350px;
  padding: 8px;
  border: none;
  border-radius: 8px;
  font-family: ZombieCarshel;
  transition: 0.3s;
  background-color: rgba(53, 223, 53, 0.479);
  color: #fff;
  margin-bottom: 50px;
  margin-top: 30px;
  font-size: 30px;
}
.create-game-btn-container{
    display: flex;
    justify-content: center;
    align-items: center;
}
.create-game-btn:hover {
  transition: 0.3s;
  cursor: pointer;
  background-color: rgba(161, 255, 161, 0.479);
  transition: 0.3s;
}
.create-game-container{
    display: flex;
    justify-content: center;
    align-items: center;
}

.create-game-header {
  font-family: plasdrip;
  font-size: 60px;
  text-align: center;
}

 @media screen and (max-width: 400px) {
    .create-game-btn, .end-date, .name, .start-date, .description {
      width: 90%;
    }

    .input-label {
      width: 20%;
    }

    .input-container {
      width: 70%;
    }

    .create-game-header {
      font-size: 40px;
    }
  }

   @media screen and (max-width: 500px) and (min-width: 400px) {
    .input-label {
      width: 30%;
    }
    .input-container {
      width: 65%;
    }
    .create-game-header {
      font-size: 40px;
    }
  }

   @media screen and (max-width: 700px) and (min-width: 500px) {
    .input-label {
      width: 35%;
    }
    .input-container {
      width: 60%;
    }
  }

</style>