<template>
  <div class="container">      
      <div class="rule-text">
          <h1>The Rules</h1>
          <p>These are the official ”Goucher-Style” Humans vs. Zombies rules as invented by Brad Sappington and Chris Weed in 2005, 
              with a few changes since to make the game more balanced and safe.</p>
          <p>Humans vs. Zombies is a game of tag. All players begin as humans, and one is randomly chosen to be the “Original Zombie.” The Original Zombie tags human players and turns them into zombies.
               Zombies must tag and eat a human every 48 hours or they starve to death and are out of the game.</p>
            <p>The Zombies win when all human players have been tagged and turned into zombies.
                The Humans win by surviving long enough for all of the zombies to starve.</p>
      </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>

.container{
    color: lime;
    display: flex;
    justify-content: center;
    align-items: center;
}
.rule-text{
  backdrop-filter: blur(25px);
  background-color: rgba(255, 255, 255, 0.1);
  box-shadow: 0 4px 20px 0 rgba(255, 255, 255, 0.1);
  border: 5px solid rgba(255, 255, 255, 0.3);
  z-index: 10;
  color: lime;
  width: 50%;
  border-radius: 8px;
}

</style>