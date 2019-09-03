<template>
<v-toolbar>
  <v-toolbar-title>
    <router-link tag="li" id="home-nav" to="/" exact><a>ビジトイ！</a></router-link>
  </v-toolbar-title>
  <div class="flex-grow-1"></div>
  <v-toolbar-items>
    <v-btn text>
      <router-link tag="li" id="home-nav" to="/About" exact><a>ABOUT</a></router-link>
    </v-btn>
    <v-btn text>
      <router-link tag="li" id="home-nav" to="/Lesson" exact><a>LESSON</a></router-link>
    </v-btn>
    <v-btn text>
      <router-link tag="li" id="home-nav" to="/Company" exact><a>COMPANY</a></router-link>
    </v-btn>
    <v-btn text>
      <router-link tag="li" id="home-nav" to="/Contact" exact><a>CONTACT</a></router-link>
    </v-btn>
  </v-toolbar-items>
</v-toolbar>

</template>
<style scoped>
li {
  list-style: none;
}
</style>
