<template>
  <div>
      <button class="reg-btn" type="button" @click="ordentligt()">Registration button</button>
  </div>
</template>

<script>
export default {

    methods:{

        ordentligt(){
            console.log("vad ordentligt");
        }

    }

}
</script>

<style>
.reg-btn{
  padding: 5px;
  align-items: center;
  margin-right: 400px;
  margin-left: 400px;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>