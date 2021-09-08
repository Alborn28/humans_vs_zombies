<template>
    <ul v-if="gamesLoaded" class="gameList">
        <li v-for="(game, index) of games" v-bind:key="index">
            <GameListItem :game="game" @gameClicked="handleGameClicked"/>
        </li>
    </ul>
</template>

<script>
import GameListItem from './GameListItem.vue'
import { mapState, mapActions, mapMutations } from 'vuex';

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

    /**
     * Fetch a list of all games
     */
    async created() {
        await this.fetchGames();
        this.gamesLoaded = true;
    },

    computed: {
        ...mapState(['games', 'authenticated'])
    },

    methods: {
        ...mapActions(['fetchGames']),
        ...mapMutations(['setGameId']),

        /**
         * If logged in, go to details for the clicked game
         */
        handleGameClicked(id) {
            if(!this.authenticated) {
                alert("You must be logged in to access game details!");
            }

            else {
                this.setGameId(id);
                this.$router.push("/game-details");
            }
        }
    }
}
</script>

<style>
    .gameList {
        list-style: none;
        width: 70%;
        margin: auto;
        margin-top: 50px;
    }
</style>