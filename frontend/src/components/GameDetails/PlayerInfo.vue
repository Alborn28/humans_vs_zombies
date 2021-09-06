<template>

        <div>
            <ul>
                <li>
                    <h4>
                        {{player.biteCode}}
                    </h4>
                </li>
            </ul>
        </div>
</template>

<script>
export default {

    data(){
        return{
            player:{
                "biteCode":"",
                "email":""
            }
        }
    },
  async created() {
    await fetch("https://hvz-experis-api.herokuapp.com/api/v1/game/1/player/14")
      .then((respons) => {
        respons.json().then((data) => {
            this.player=data
        });
      })
      .catch(function (err) {
        console.error("Fetch Error :-S", err);
      });
  },
};
</script>

<style>
</style>