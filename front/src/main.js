import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./registerServiceWorker";
import vuetify from "./plugins/vuetify";
import {RepositoryFactory} from './plugins/RepositoryFactory.js';

// API
Vue.prototype.$googleBookApi = RepositoryFactory.get("googleBookApi");
Vue.prototype.$usersApi = RepositoryFactory.get("users");
Vue.prototype.$authApi = RepositoryFactory.get("auth");

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
