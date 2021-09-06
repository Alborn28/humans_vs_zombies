<template>
  <div class="game" @click="$emit('gameClicked', game.id)">
    <p>Name: {{game.name}}</p>
    <p>Game state: {{game.gameState}}</p>
    <p>Registered players: {{game.players.length}}</p>
    <p>Start date: </p>
    <p>End date: </p>
  </div>
</template>

<script>
export default {
    name: "GameListItem",
    props: {
        game: Object
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