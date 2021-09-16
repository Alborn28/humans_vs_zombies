<template>
    <div>
        <button class="home-btn" type="button" @click="handleClick()">Home</button>
    </div>
</template>

<script>

export default {
    name: "HomeButton",
 
    methods: {
        /**
         * Re-directs the user to the landing page
         */
        handleClick(){
            this.$router.push("/");
        }
    }
}
</script>

<style scoped>
    .home-btn {
        width: 100px;
        padding: 8px;
        border: none;
        border-radius: 8px;
        margin-right: 30px;
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
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
</style>