<template>
  <div>
    <div class="map" v-if="checkInsLoaded">
      <l-map
        :zoom="zoom"
        :center="center"
        :bounds="bounds"
        :max-bounds="maxBounds"
        :min-zoom="minZoom"
        @ready="handleMapClick"
        @locationfound="onLocationFound"
        style="height: 500px; width: 100%"
      >
        <l-tile-layer :url="url" :attribution="attribution" />
        <!-- <l-marker v-for="(marker, index) in markers" :key="index" :lat-lng="marker" >
          <l-icon :iconUrl="'assets/skull.png'" :iconSize = "[30, 30]" />
        </l-marker> -->
        <l-marker v-if="currentMarker !==null" :lat-lng="currentMarker" >
          <l-icon :iconUrl="'assets/checkin.png'" :iconSize = "[30, 30]" />
          <l-popup>hej</l-popup>
        </l-marker>
        <l-marker v-for="(checkin, index) in checkIns" :key="index" :lat-lng="checkin.latlng">
          <l-icon :iconUrl="'assets/checkin.png'" :iconSize = "[30, 30]" />
        </l-marker> 
      </l-map>
    </div>
    <div v-if="this.game.gameState === 'IN_PROGRESS' && this.squadId !== null" class="check-in">
      <button @click="placeCheckIn">Check in</button>
    </div>
  </div>
</template>

<script>
import { latLngBounds } from "leaflet";
import { LMap, LTileLayer, LMarker, LIcon, LPopup } from "vue2-leaflet";
import "leaflet/dist/leaflet.css";
import { mapActions, mapState } from "vuex";

export default {
  name: "SetBounds",
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LIcon,
    LPopup,
  },
  /**
   * Fetch coordinates for a specific game
   */
  async created() {
    await this.fetchGame();
    await this.fetchSquadCheckIns();
    this.checkInsLoaded = true
    
    this.maxBounds = latLngBounds([
      [this.game.swLat, this.game.swLng],
      [this.game.neLat, this.game.neLng],
    ]);
    
    this.minZoom = this.game.zoom
    this.zoom = this.game.zoom
  },

  computed: {
    ...mapState(["game", "squadId", "player", "checkIns"]),
  },

  methods: {
    ...mapActions(["fetchGame", "postCheckIn", "fetchSquadCheckIns"]),
    handleMapClick(mapObject){
      //this.markers.push(event.latlng)
      // this.currentMarker = event.latlng;
      // this.currentMarker = mapObject.locate();
      mapObject.locate();
    },
    onLocationFound(location){
      this.location = location.latlng;
    },
    async placeCheckIn() {
      this.currentMarker = this.location;   
      await this.postCheckIn(this.location);
    }
  },

  data() {
    return {
      zoom: 0,
      minZoom: 0,
      center: [0, 0],
      bounds: latLngBounds([
        [56.866381305955535, 14.754467010498049],
        [56.88983119447765, 14.869823455810549],
      ]),
      maxBounds: latLngBounds([
        [56.866381305955535, 14.754467010498049],
        [56.88983119447765, 14.869823455810549],
      ]),
      url: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
      attribution:
        '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      markers: [],
      currentMarker: null,
      location: null,
      checkInsLoaded: false
    };
  },
};
</script>
<style scoped>
  .map {
    border: 1px solid black;
    padding: 5px;
    margin-right: 400px;
    margin-left: 400px;
    margin-top: 10px;
    margin-bottom: 10px;
  }
  .check-in {
    display: flexbox;
    justify-content: center;
    align-items: center;
  }
</style>