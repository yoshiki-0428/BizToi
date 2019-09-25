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
      path: "/top",
      name: "top",
      component: () => import("./views/Top.vue")
    },
    {
      path: "/company",
      name: "company",
      component: () => import("./views/Company.vue")
    },
    {
      path: "/contact",
      name: "contact",
      component: () => import("./views/Contact.vue")
    },
    {
      path: "/lesson",
      name: "company",
      component: () => import("./views/Lesson.vue")
    }
  ]
});
