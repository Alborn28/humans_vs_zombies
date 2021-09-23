<template>
    <div>
        <button class="home-btn" type="button" @click="handleClick()">Home</button>
    </div>
</template>

<script>

export default {
    name: "HomeButton",
 
    methods: {
        handleClick(){
            this.$router.push("/");
        }
    }
}
</script>

<style scoped>
    .home-btn {
        width: 140px;
        padding: 8px;
        border: none;
        border-radius: 8px;
        font-size: 16px;
        font-family: ZombieCarshel;
        transition: .3s;
        background-color: rgba(95, 95, 255, 0.479);
        color: #fff;
    }
    .home-btn:hover {
        transition: .3s;
        cursor: pointer;
        background-color: rgba(132, 132, 253, 0.479);
        transition: .3s;
    }
    @media screen and (max-width: 740px) {
  .home-btn{
    width: 100%;
  }
}
</style>