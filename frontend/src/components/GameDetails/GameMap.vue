<template>
  <div class="map">
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
      <l-marker v-for="(kill, index) in kills" :key="index" :lat-lng="kill.latlng" >
        <l-icon :iconUrl="'assets/skull.png'" :iconSize = "[30, 30]" />
        <l-popup>
          <p>Victim: {{kill.victim}}</p>
          <p>Killer: {{kill.killer}}</p>
          <p>Time of death: {{kill.timeOfDeath.toLocaleString()}}</p>
          <p>Story: {{kill.story}}</p>
        </l-popup>
      </l-marker>
      <div v-if="player.human"> 
        <l-marker v-if="playerCheckIn !== null" :lat-lng="playerCheckIn" >
          <l-icon :iconUrl="'assets/checkin.png'" :iconSize = "[30, 30]" />
          <l-popup>
            {{player.username}}
          </l-popup>
        </l-marker>
        <l-marker v-for="(checkin, index) in checkIns" :key="index" :lat-lng="checkin.latlng">
          <l-icon :iconUrl="'assets/checkin.png'" :iconSize = "[30, 30]" />
          <l-popup>
            <p>Player: {{checkin.squadMember}}</p>
            <p>Time of check in: {{checkin.timeOfCheckIn}}</p>
          </l-popup>
        </l-marker> 
      </div>
    </l-map>
  </div>
</template>

<script>
import { latLngBounds } from "leaflet";
import { LMap, LTileLayer, LMarker, LIcon, LPopup } from "vue2-leaflet";
import "leaflet/dist/leaflet.css";
import { mapActions, mapMutations, mapState } from "vuex";

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
    await this.fetchKills();
    
    this.maxBounds = latLngBounds([
      [this.game.swLat, this.game.swLng],
      [this.game.neLat, this.game.neLng],
    ]);
    
    this.minZoom = this.game.zoom
    this.zoom = this.game.zoom
  },
  computed: {
    ...mapState(["game", "player", "checkIns", "location", "kills", 'playerCheckIn']),
  },
  methods: {
    ...mapActions(["fetchGame", "fetchSquadCheckIns", "fetchKills"]),
    ...mapMutations(["setLocation"]),
    handleMapClick(mapObject){
      mapObject.locate();
    },
    onLocationFound(location){
      this.setLocation(location.latlng)
    },
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
    };
  },
};
</script>
<style scoped>
  .map {
    border: 1px solid black;
    margin: auto;
    width: 50%;
    border: 5px solid rgba(255, 255, 255, 0.3);
    border-radius: 8px;
    padding: 0;
    margin-top: 25px;
  }
</style>