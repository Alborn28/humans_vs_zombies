import Vue from 'vue'
import App from './App.vue'
import store from './store'
import VueRouter from 'vue-router'
import router from './router'
import * as io from "socket.io-client";
import VueSocketIO from "vue-socket.io";

Vue.use(
  new VueSocketIO({
    connection: io('http://localhost:3000'), // options object is Optional
  })
);

Vue.config.productionTip = false

Vue.use(VueRouter)

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
