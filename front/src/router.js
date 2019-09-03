import Vue from "vue";
import Router from "vue-router";
import Company from "./components/Company.vue";
import Contact from "./components/Contact.vue";
import Lesson from "./components/Lesson.vue";
import Home from "./views/Home.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: (Home) =>
        import("./views/Home.vue")
    },
    {
      path: "/about",
      name: "about",
      component: (About) =>
        import("./views/About.vue")
    }
  ]
});
