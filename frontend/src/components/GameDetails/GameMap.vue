<template>

  <div class="map">
    <div>
      Marker is placed at {{ marker.lat }}, {{ marker.lng }}, bounds are
      {{ bounds }}, zoom {{zoom}}
      <br>
    </div>
    <l-map
      :zoom="zoom"
      :center="center"
      :bounds="bounds"
      :max-bounds="maxBounds"
      :min-zoom="minZoom"
      style="height: 500px; width: 100%"
    >
      <l-tile-layer
        :url="url"
        :attribution="attribution"
      />
      
    </l-map>
  </div>
</template>

<script>
import { latLngBounds, latLng } from "leaflet";
import { LMap, LTileLayer } from "vue2-leaflet";
import "leaflet/dist/leaflet.css";
import { mapState } from 'vuex';

export default {
  name: "SetBounds",
  components: {
    LMap,
    LTileLayer
  },
  created() {
    fetch(`https://hvz-experis-api.herokuapp.com/api/v1/game/${this.gameId}`)
      .then((respons) => {
        respons.json().then((data) => {
          this.maxBounds = latLngBounds([
        [data.swLat, data.swLng],
        [data.neLat, data.neLng]
        ])
        //this.zoom = data.zoom,

        });
      })
      .catch(function (err) {
        console.error("Fetch Error :-S", err);
      });
  },
  computed: {
    ...mapState(["gameId"])
  },
  data() {
    //"_southWest": { "lat": 56.866381305955535, "lng": 14.754467010498049 }, "_northEast": { "lat": 56.88983119447765, "lng": 14.869823455810549 }
    return {
      zoom: 12,
      minZoom: 14,
      center: [0, 0],
      bounds: latLngBounds([
        [56.866381305955535, 14.754467010498049],
        [56.88983119447765, 14.869823455810549]
      ]),
      maxBounds: latLngBounds([
        [56.866381305955535, 14.754467010498049],
        [56.88983119447765, 14.869823455810549]
      ]),
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      attribution:
        '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      marker: latLng(56.866381305955535, 14.754467010498049)
    };
  }
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