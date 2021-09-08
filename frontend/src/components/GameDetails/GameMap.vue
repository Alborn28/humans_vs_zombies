<template>
  <div class="map">
    <l-map
      :zoom="zoom"
      :center="center"
      :bounds="bounds"
      :max-bounds="maxBounds"
      :min-zoom="minZoom"
      style="height: 500px; width: 100%"
    >
      <l-tile-layer :url="url" :attribution="attribution" />
    </l-map>
  </div>
</template>

<script>
import { latLngBounds, latLng } from "leaflet";
import { LMap, LTileLayer } from "vue2-leaflet";
import "leaflet/dist/leaflet.css";
import { mapActions, mapState } from "vuex";

export default {
  name: "SetBounds",
  components: {
    LMap,
    LTileLayer,
  },
  /**
   * Fetch coordinates for a specific game
   */
  async created() {
    await this.fetchGame();

    this.maxBounds = latLngBounds([
      [this.game.swLat, this.game.swLng],
      [this.game.neLat, this.game.neLng],
    ]);
    
    this.minZoom = this.game.zoom
    this.zoom = this.game.zoom
  },

  computed: {
    ...mapState(["game"]),
  },

  methods: {
    ...mapActions(["fetchGame"])
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
      marker: latLng(56.866381305955535, 14.754467010498049),
    };
  },
};
</script>
<style>
.map {
  border: 1px solid black;
  padding: 5px;
  margin-right: 400px;
  margin-left: 400px;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>