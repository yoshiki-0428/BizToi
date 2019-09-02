import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Company from "./components/Company.vue";
import Contact from "./components/Contact.vue";
import Lesson from "./components/Lesson.vue";
import About from "./views/About.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/Home",
      name: "Home",
      component: Home
    },
    {
      path: "/lesson",
      name: "lesson",
      component: Lesson
    },
    {
      path: "/company",
      name: "company",
      component: Company
    },
    {
      path: "/contact",
      name: "contact",
      component: Contact
    },
    {
      path: "/about",
      name: "about",
      component: About
    }
  ]
});
