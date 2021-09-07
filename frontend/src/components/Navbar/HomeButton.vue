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
        width: 80px;
        padding: 8px;
        border: none;
        border-radius: 8px;
        margin-right: 30px;
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        transition: .3s;
    }
    .home-btn:hover {
        cursor: pointer;
        background-color: #498d84;
        color: #fff;
        transition: .3s;
    }
</style>