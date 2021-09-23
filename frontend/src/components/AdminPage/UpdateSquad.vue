<template>
  <div class="update-squad-container">
    <form class="update-squad-form">
      <h3>Edit Squad</h3>
      <div>
        <label><strong>Name: </strong></label>
        <input v-model="squad.name" class="select-component" />
      </div>
      <div v-if="squadMembers.length > 0">
        <label><strong>Kick: </strong></label>
        <select v-model="memberId" class="select-component">
          <option value="0"></option>
          <option
            v-for="(member, index) in squadMembers"
            :value="member.id"
            :key="index"
          >
            {{ member.username }}
          </option>
        </select>
      </div>
      <button class="update-btn" type="button" @click="updateSquad">
        Update Squad
      </button>
    </form>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  props: {
    squad: Object,
    gameId: Number,
  },
  data() {
    return {
      squadMembers: [],
      memberId: 0,
    };
  },
  async created() {
    for (let i = 0; i < this.squad.squadMembers.length; i++) {
      const response = await fetch(
        `https://hvz-experis-api.herokuapp.com${this.squad.squadMembers[i].player}`
      );
      const data = await response.json();
      this.squadMembers.push(data);
    }
  },
  computed: {
    ...mapState(["apiUrl", "token"]),
  },
  methods: {
    async kickPlayer() {
      if (this.memberId !== 0) {
        await fetch(
          this.apiUrl +
            "/game/" +
            this.gameId +
            "/squad/" +
            this.squad.id +
            "/leave/" +
            this.memberId,
          {
            method: "DELETE",
            headers: {
              Authorization: "Bearer " + this.token,
              "Content-Type": "application/json",
            },
          }
        );
        //Removes the squad from the database if the player being removed is the last squad member remaining.
        const response = await fetch(
          this.apiUrl +
            `/game/${this.gameId}/squad`
        );
        const data = await response.json();
        if (data[0].squadMembers.length < 1) {
          await fetch(
            this.apiUrl + "/game/" + this.gameId + "/squad/" + this.squad.id,
            {
              method: "DELETE",
              headers: {
                Authorization: "Bearer " + this.token,
                "Content-Type": "application/json",
              },
            }
          );
        }
      }
    },

    async updateSquad() {
      await fetch(
        this.apiUrl + "/game/" + this.gameId + "/squad/" + this.squad.id,
        {
          method: "PATCH",
          headers: {
            Authorization: "Bearer " + this.token,
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            id: this.squad.id,
            name: this.squad.name,
          }),
        }
      );

      await this.kickPlayer();

      alert("Squad updated!")
      this.$router.push("/")
    },
  },
};
</script>

<style scoped>
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
  min-height: 280px;
  max-height: 280px;
  min-width: 290px;
  max-width: 290px;
}

.update-btn {
  margin-top: 10px;
  width: 150px;
  border-radius: 8px;
  border: none;
  color: lime;
  background-color: rgba(95, 95, 255, 0.479);
  transition: 0.3s;
  font-size: 16px;
}

.update-btn:hover {
  cursor: pointer;
  background-color: rgba(132, 132, 253, 0.479);
  transition: 0.3s;
}
.kick-btn {
  margin-left: 10px;
  margin-top: 10px;
  width: 40px;
  border-radius: 8px;
  border: none;
  color: lime;
  background-color: rgba(211, 30, 45, 0.479);
  transition: 0.3s;
}
.kick-btn:hover {
  cursor: pointer;
  background-color: rgba(230, 100, 111, 0.479);
  transition: 0.3s;
}
.option-component {
  border-radius: 8px;
}

@media screen and (max-width: 350px) {
  .update-squad-form {
    width: 90%;
  }
}
</style>