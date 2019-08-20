import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import cognito from './cognito';
import store from "./store";
import "./registerServiceWorker";

Vue.config.productionTip = false;

new Vue({
  router,
  cognito,
  store,
  render: h => h(App)
}).$mount("#app");
