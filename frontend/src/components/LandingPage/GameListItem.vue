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
        border: 1px solid black;
        padding: 5px;
        margin: auto;
        margin-bottom: 10px;
    }

    .game:hover {
        background-color: rgb(233, 233, 233);
    }
</style>