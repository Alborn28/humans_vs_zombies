<template>
    <ul v-if="gamesLoaded" class="gameList">
        <li v-for="(game, index) of games" v-bind:key="index">
            <GameListItem :game="game" @gameClicked="handleGameClicked"/>
        </li>
    </ul>
</template>

<script>
import GameListItem from './GameListItem.vue'
import { mapState, mapActions } from 'vuex';

export default {
    name: "GameList",
    components: {
        GameListItem
    },

    data() {
        return {
            gamesLoaded: false
        }
    },

    async created() {
        await this.fetchGames();
        this.gamesLoaded = true;
    },

    computed: {
        ...mapState(['games', 'authenticated'])
    },

    methods: {
        ...mapActions(['fetchGames']),

        handleGameClicked(id) {
            console.log("Game clicked with id: " + id);
            
            if(!this.authenticated) {
                alert("You must be logged in to access game details!");
            }

            else {
                //Route to game details for that game!
            }
        }
    }
}
</script>

<style>
    .gameList {
        list-style-type: none;
        width: 70%;
        margin: auto;
        margin-top: 50px;
    }
</style>