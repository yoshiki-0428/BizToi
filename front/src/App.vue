<template>
  <v-app>
      <v-layout>
          <v-flex xs12>
      <main-header />
<v-content>
      <router-view />
</v-content>
          </v-flex>
      </v-layout>
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
