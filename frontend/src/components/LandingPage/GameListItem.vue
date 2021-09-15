<template>
  <div class="game" @click="$emit('gameClicked', game.id)">
    <p>Name: {{game.name}}</p>
    <p>Game state: {{game.gameState}}</p>
    <p>Registered players: {{game.players.length}}</p>
    <p>Start date: {{startDate}} </p>
    <p>End date: {{endDate}} </p>
  </div>
</template>

<script>
export default {
    name: "GameListItem",
    props: {
        game: Object
    },
    data() {
        return {
            startDate: "",
            endDate: ""
        }
    },

    /**
     * Format the startDate and endDate
     */
    created() {
        let date = new Date(this.game.startDate);
        date.setHours(date.getHours() - 2);
        this.startDate = date.toLocaleString();

        date = new Date(this.game.endDate);
        date.setHours(date.getHours() - 2);
        this.endDate = date.toLocaleString();
    }
}
</script>

<style>
    .game {
        padding: 5px;
        margin: auto;
        margin-bottom: 10px;
        backdrop-filter: blur(25px);
        background-color: rgba(255, 255, 255, 0.1);
        box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
        border: 1px solid rgba(255, 255, 255, 0.3);
        z-index: 10;
        color: lime;
        width: 50%;
    }

    .game:hover {
        background-color: rgb(233, 233, 233, 0.2);
        cursor: pointer;
    }
</style>