<template>
<v-app>
  <main-header />
  <router-view />
</v-app>
</template>

<script>
import MainHeader from './components/MainHeader'

export default {
  name: "App",
  components: {
    MainHeader
  }
};
</script>
