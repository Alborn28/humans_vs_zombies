<template>
  <div v-if="player.id!==null">
    <div class="tabs">
      <a
        v-on:click="activetab = 1"
        v-bind:class="[activetab === 1 ? 'active' : '']"
        >Player info</a
      >
      <a
        v-on:click="activetab = 2"
        v-bind:class="[activetab === 2 ? 'active' : '']"
        >Kill</a
      >
      <a
        v-on:click="activetab = 3"
        v-bind:class="[activetab === 3 ? 'active' : '']"
        >Squad info</a
      >
    </div>

    <div class="content">
      <div v-if="activetab === 1" class="tabcontent">
        <PlayerInfo />
      </div>
      <div v-if="activetab === 2" class="tabcontent">
        <Kill />
      </div>
      <div v-if="activetab === 3" class="tabcontent">
        <SquadInfo />
      </div>
    </div>
  </div>
</template>

<script>
import PlayerInfo from "../GameDetails/PlayerInfo.vue";
import Kill from "../GameDetails/Kill.vue";
import SquadInfo from "../GameDetails/SquadInfo.vue";
import { mapActions, mapState } from 'vuex';

export default {
  data() {
    return {
      activetab: 1,
    };
  },

  computed: {
      ...mapState(["player"])
  },
  components: {
    PlayerInfo,
    Kill,
    SquadInfo,
  },
  methods: {
    handleClick(index) {
      this.selectedIndex = index;
    },
    ...mapActions(["fetchPlayer"])
  },

  async created() {
    await this.fetchPlayer();
  },

};
</script>


<style>
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
  background-color: #f1f1f1;
  border-radius: 10px 10px 0 0;
  font-weight: bold;
}
.tabs a:last-child {
  border-right: 1px solid #ccc;
}

/* Change background color of tabs on hover */
.tabs a:hover {
  background-color: #aaa;
  color: #fff;
}

/* Styling for active tab */
.tabs a.active {
  background-color: #fff;
  color: #484848;
  border-bottom: 2px solid #fff;
  cursor: default;
}

/* Style the tab content */
.tabcontent {
  padding: 30px;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 3px 3px 6px #e1e1e1;
}
</style>