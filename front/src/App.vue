<template>
  <v-app>
    <main-header />
    <v-content>
      <router-view />
    </v-content>
  </v-app>
</template>

<script>
import MainHeader from "./components/MainHeader";

export default {
  name: "App",
  components: {
    MainHeader
  }
};
</script>
