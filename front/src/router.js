import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("./views/Home.vue")
    },
    {
      path: "/about",
      name: "about",
      component: () => import("./views/About.vue")
    },
    {
      path: "/account",
      name: "account",
      component: () => import("./views/Account.vue")
    },
    {
      path: "/lesson",
      name: "lesson",
      component: () => import("./views/Lesson.vue")
    }
  ]
});
