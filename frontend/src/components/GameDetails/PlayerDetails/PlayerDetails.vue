<template>
  <div v-if="player.id!==null">
    <div class="tabs">
      <a
        v-on:click="activetab = 1"
        v-bind:class="[activetab === 1 ? 'active' : '']"
        >Player info</a
      >
      <a
        v-if="player.human"
        v-on:click="activetab = 2"
        v-bind:class="[activetab === 2 ? 'active' : '']"
        >Squad info</a
      >
    </div>

    <div class="content">
      <div v-if="activetab === 1" class="tabcontent">
        <PlayerInfo />
      </div>
      <div v-if="activetab === 2" class="tabcontent">
        <SquadInfo />
      </div>
    </div>
  </div>
</template>

<script>
import PlayerInfo from "./PlayerInfo.vue";
import SquadInfo from "./SquadInfo.vue";
import { mapState } from 'vuex';

export default {
  data() {
    return {
      activetab: 1,
    };
  },

  computed: {
      ...mapState(["player", "game"])
  },
  components: {
    PlayerInfo,
    SquadInfo,
  },
  methods: {
    handleClick(index) {
      this.selectedIndex = index;
    }
  }
};
</script>


<style scoped>
.tabs {
  overflow: hidden;
  margin-left: 20px;
  margin-bottom: -2px;
}

.tabs ul {
  list-style-type: none;
  margin-left: 20px;
}

.tabs a {
  float: left;
  cursor: pointer;
  padding: 12px 24px;
  transition: background-color 0.2s;
  border: 1px solid #ccc;
  border-right: none;
  background-color: #540dc5;
  border-radius: 10px 10px 0 0;
  font-weight: bold;
  color: #fff;
}
.tabs a:last-child {
  border-right: 1px solid #ccc;
}

/* Change background color of tabs on hover */
.tabs a:hover {
  background-color: #834fd6;
}

/* Styling for active tab */
.tabs a.active {
  background-color: #834fd6;
  color: #fff;
  border-bottom: 2px solid #834fd6;
  cursor: default;
}

/* Style the tab content */
.tabcontent {
  padding: 15px;
  border: 5px solid rgba(255, 255, 255, 0.3);
  background-color: rgb(10, 10, 10);
  border-radius: 8px;
  min-height: 320px;
  min-width: 340px;
  max-height: 320px;
  max-width: 340px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 25px;
}

@media screen and (max-width: 400px) {
  .tabcontent {
    min-width: 0;
  }
}
</style>