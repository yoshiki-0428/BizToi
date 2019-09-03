<template>
  <v-app-bar app>
    <v-toolbar-title>
      <router-link tag="li" id="home-nav1" to="/" exact>
        <a>ビジトイ</a>
      </router-link>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn text>
        <router-link tag="li" id="home-nav2" to="/About" exact>
          <a>ABOUT</a>
        </router-link>
      </v-btn>
      <v-btn text>
        <router-link tag="li" id="home-nav3" to="/Lesson" exact>
          <a>LESSON</a>
        </router-link>
      </v-btn>
      <v-btn text>
        <router-link tag="li" id="home-nav4" to="/Company" exact>
          <a>COMPANY</a>
        </router-link>
      </v-btn>
      <v-btn text>
        <router-link tag="li" id="home-nav5" to="/Contact" exact>
          <a>CONTACT</a>
        </router-link>
      </v-btn>
    </v-toolbar-items>
  </v-app-bar>
</template>

<style scoped>
li {
  list-style: none;
}
</style>
