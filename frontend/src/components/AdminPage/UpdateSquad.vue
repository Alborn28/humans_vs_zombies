<template>
  <div class="update-squad-container">
    <form class="update-squad-form">
      <h3>Edit Squad</h3>
      <div>
        <label><strong>Name: </strong></label>
        <input v-model="squad.name" class="select-component" />
      </div>
      <button type="button" @click="updateSquad">Update Squad</button>
    </form>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
    props:{
        squad:Object,
        gameId:Number,
    },
  computed: {
    ...mapState(["apiUrl", "token"]),
  },

  methods: {
    async updateSquad() {
        console.log(this.gameId, "-__" , this.squad);
      await fetch(this.apiUrl + "/game/" + this.gameId+"/squad/"+this.squad.id, {
        method: "PATCH",
        headers: {
          Authorization: "Bearer " + this.token,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: this.squad.id,
          name: this.squad.name,
          
        }),
      });
    },
  },
};
</script>

<style scoped>
.update-squad-container{
    
}

.select-component {
  margin-top: 10px;
  background-color: lime;
  color: #834fd6;
  width: 150px;
  border: none;
  text-align-last: center;

  border-radius: 8px;
}
.update-squad-form {
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
  backdrop-filter: blur(25px);
  background-color: rgba(255, 255, 255, 0.1);
  border: 5px solid rgba(255, 255, 255, 0.3);
  box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
  z-index: 10;
  padding: 8px;
  color: aliceblue;
  min-height: 260px;
  max-height: 260px;
  min-width: 290px;
  max-width: 290px;
}

.update-squad-form button {
  margin-top: 10px;
  width: 150px;
  border-radius: 8px;
  border: none;
  color: lime;
  background-color: rgba(95, 95, 255, 0.479);
  transition: .3s;
}


.update-squad-form button:hover {
    cursor: pointer;
    background-color: rgba(132, 132, 253, 0.479);
    transition: .3s;
  }

</style>