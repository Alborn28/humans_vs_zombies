<template>
  <div class="map">
    <l-map id="mapId"
      style="height: 500px; width: 100%"
      :zoom="zoom"
      :center="center"
      @update:zoom="zoomUpdated"
      @update:center="centerUpdated"
      @update:bounds="boundsUpdated"
    >
      <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
      
    </l-map>
  </div>
</template>

<script>
import {LMap, LTileLayer} from 'vue2-leaflet';
import { latLngBounds } from "leaflet";
import "leaflet/dist/leaflet.css";

export default {
  components: {
    LMap,
    LTileLayer,
  },
  data () {
    return {
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      attribution:
        '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      zoom: 14,
      center: [56.87717, 14.813004],
      bounds: latLngBounds([
        [56.866381305955535, 14.754467010498049],
        [56.88983119447765, 14.869823455810549]
      ]),
    };
  },
  methods: {
    zoomUpdated (zoom) {
      this.zoom = zoom;
      this.$emit('zoomUpdated', this.zoom);
    },
    centerUpdated (center) {
      this.center = center;
    },
    boundsUpdated (bounds) {
      this.bounds = bounds;
      this.$emit('boundsUpdated', this.bounds);
    }
  }
}
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

   @media screen and (max-width: 900px) {
    .map{
      width: 90%;
    }
  }

  @media screen and (max-width: 500px) {
    #mapId {
      height: 300px !important;
    }
  }
</style>