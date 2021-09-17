<template>
  <div class="squad" v-if="squadLoaded">
    <div class="create-squad-container" v-if="squadId === null">
      <h3 class="reg-squad-title">CREATE SQUAD</h3>
      <input class="name" placeholder="Name" type="name" v-model="name" />
      <button class="reg-squad-btn" @click="registerNewSquad()">register squad</button>
    </div>
    <div v-if="squadId !== null">
      <h3 class="title"> {{ squad.name }} </h3>

      <ul>
        <li class="member" v-for="(member, index) in squadMembers" :key="index">
          <p><strong>User: </strong>{{ member.username }}</p>
          <p><strong>Rank: </strong><span class="capitalize">{{ squad.squadMembers[index].rank.toLowerCase() }}</span></p>
          <p><strong>Status: </strong>{{ member.human ? "Alive" : "Dead" }}</p>
        </li>
      </ul>
    <div class="btn-container">
      <button class="leave-squad" @click="leaveSquad">Leave squad</button>
    </div>
    </div>
  </div>
</template>


<script>
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      name: "",
      squadLoaded: false,
    };
  },

  computed: {
    ...mapState(["squadId", "squad", "squadMembers"]),
  },
  async created() {
    await this.fetchSquad();
    this.squadLoaded = true;
  },

  methods: {
    ...mapActions(["registerSquad", "fetchSquad", "leaveSquad"]),

    async registerNewSquad() {
      await this.registerSquad(this.name);
    },
  },
};
</script>

<style scoped>
.create-squad-container{
   display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    border: 5px solid rgba(255, 255, 255, 0.3);
    border-radius: 8px;
    backdrop-filter: blur(25px);
    background-color: rgba(255, 255, 255, 0.1);
    box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
    z-index: 10;
    padding: 10px;

}
.create-squad-container *{
  margin-top: 10px;
}
.create-squad-container input:focus::placeholder{
  color: transparent;
}

.create-squad-container input::placeholder{
  color: lime;
}
  .squad {
    color: rgba(178, 58, 248, 0.781);
    font-size: 1.4em;
    width: 90%;
   
  }

  .squad ul {
    margin: 10px;
    list-style: none;
    text-align:center;
    overflow: scroll;
    overflow-x: hidden;
    max-height: 180px;
    max-width: 300px;
    
  }

  .squad ul::-webkit-scrollbar {
  display: none;
}

  .title {
    margin-bottom: 10px;
    text-align: center;
  }

  .capitalize {
    text-transform: capitalize;
  }

  .member {
    padding: 8px;
    margin: 10px;
    margin-bottom: 10px;
    border: 5px solid rgba(255, 255, 255, 0.3);
    border-radius: 8px;
    backdrop-filter: blur(25px);
    background-color: rgba(255, 255, 255, 0.1);
    box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
    z-index: 10;
  }

  .leave-squad {
    width: 100px;
    padding: 8px;
    border: none;
    border-radius: 8px;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    transition: .3s;
    background-color: rgba(211, 30, 45, 0.479);
    color: #fff;
  }
  .leave-squad:hover {
    transition: .3s;
    cursor: pointer;
    background-color: rgba(230, 100, 111, 0.479);
    transition: .3s;
  }
  .btn-container {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .reg-squad-btn{    
    border: none;
    border-radius: 8px;
    font-family: ZombieCarshel;
    transition: .3s;
    background-color: rgba(95, 95, 255, 0.479);
    color: lime; 
    padding: 8px;
    padding-left: 12px;    
    padding-right: 12px;
    font-size: 14px;    
  }
  .reg-squad-btn:hover {
    transition: .3s;
    cursor: pointer;
    background-color: rgba(132, 132, 253, 0.479);
    transition: .3s;
  }
  .name{
  background-color: #834fd6;
  padding: 8px;
  border: none;
  outline: none;
  color: lime;
  border-radius: 8px;
  margin-right: 5px;
  font-size: 14px;
  width: 200px;
  }

</style>