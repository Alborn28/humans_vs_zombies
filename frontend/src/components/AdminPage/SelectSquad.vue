<template>
  <div class="select-container" v-if="squads.length > 0">
    <h3>select Squad</h3>
    <select @change="handleChangeSquad" class="select-component">
      <option
        v-for="(squad, index) in squads"
        :key="index"
        :value="squad.id"
        class="option-component"
      >
        {{ squad.name }}
      </option>
    </select>
    <button @click="handleSelectSquad" class="button">Select Squad</button>
    
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
    props:{
        gameId:Number
    },

  data() {
    return {
      squads: [],
      squadId:null,
     
    };
  },
  computed: {
    ...mapState(["apiUrl" ]),
  },
  

  /**
   * Fetch a list of all the squads in the selected game.
   */
  async created() {
    const response = await fetch(this.apiUrl + `/game/${this.gameId}/squad`)
    const data = await response.json();
    this.squads=data;
    this.squadId=this.squads.length === 0 ? null : this.squads[0].id;
  },

  methods: {
    handleChangeSquad(event) {
      this.squadId = event.target.value;
    },
    async handleSelectSquad() {
      const response = await fetch(this.apiUrl + "/game/" + this.gameId + "/squad/" +this.squadId);
      const data = await response.json();
      this.$emit("squad-selected", data)
    },
  },
};
</script>

<style>
.select-container {
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
  backdrop-filter: blur(25px);
  background-color: rgba(255, 255, 255, 0.1);
  border: 5px solid rgba(255, 255, 255, 0.3);
  box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
  z-index: 10;
  min-height: 280px;
  max-height: 280px;
  min-width: 290px;
  max-width: 290px;
  padding: 20px;
}
.select-component {
  margin-top: 10px;
  background-color: lime;
  color: #834fd6;
  width: 150px;
  border: none;
  text-align-last: center;
  font-size: 16px;
  border-radius: 8px;
}
.option-component {
  border-radius: 8px;
}
.select-component:focus {
  outline: none;
}
.button {
  margin-top: 10px;
  width: 150px;
  border-radius: 8px;
  border: none;
  color: lime;
  background-color: rgba(95, 95, 255, 0.479);
  transition: .3s;
  font-size: 16px;
}

button:hover {
    cursor: pointer;
    background-color:rgba(132, 132, 253, 0.479);
    transition: .3s;
  }
  @media screen and (max-width: 350px) {
    .select-container {
      width: 90%;
    }
  }
</style>