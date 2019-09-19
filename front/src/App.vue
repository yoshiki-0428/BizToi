<template>
  <v-app>
    <v-layout>
      <v-flex xs12>
        <main-header />
        <v-content>
            <transition>
          <router-view />
            </transition>
        </v-content>
          <main-footer />
      </v-flex>
    </v-layout>
  </v-app>
</template>

<script>
import MainHeader from "./components/MainHeader";
import MainFooter from "./components/MainFooter";

export default {
  name: "App",
  components: {
      MainFooter,
    MainHeader
  }
};
</script>

<style>
    .v-enter {
        transform: translate(100px, 0);
        opacity: 1;
    }
    .v-enter-to {
        opacity: 2;
    }
    .v-enter-active {
        transition: all 1s 0s ease;
    }
    .v-leave {
        transform: translate(0, 0);
        opacity: 2;
    }
    .v-leave-to {
        transform: translate(100px, 0);
        opacity: 0;
    }
    .v-leave-active {
        transition: all .5s 0s ease;
    }
</style>
