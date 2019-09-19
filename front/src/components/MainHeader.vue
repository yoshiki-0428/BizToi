<template>
  <v-app-bar app>
    <v-toolbar-title>
      <router-link tag="li" id="home-nav1" to="/" exact>
        <a>ビジトイ</a>
      </router-link>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items class="hidden-md-and-down">
      <v-btn text v-for="item in menu" :key="item.icon" :to="item.link">
        <v-list-item-icon>
          <v-icon v-text="item.icon"></v-icon>
        </v-list-item-icon>
        {{ item.title }}
      </v-btn>
    </v-toolbar-items>
    <v-menu>
      <template v-slot:activator="{ on }">
        <v-btn text class="hidden-md-and-up" v-on="on">
          <v-app-bar-nav-icon></v-app-bar-nav-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item v-for="item in menu" :key="item.icon" :to="item.link">
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-title>
            {{ item.title }}
          </v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </v-app-bar>
</template>

<script>
export default {
  data() {
    return {
      menu: [
        {
          icon: "mdi-tooltip-text",
          title: "ABOUT",
          link: "/about"
        },
        {
          icon: "mdi-book-open-page-variant",
          title: "LESSON",
          link: "/lesson"
        },
        {
          icon: "mdi-account-card-details",
          title: "ACCOUNT",
          link: "/account"
        }
      ]
    };
  }
};
</script>

<style scoped>
li {
  list-style: none;
}
a {
  text-decoration: none;
  text-align: center;
}
</style>
