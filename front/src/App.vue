<style>
li {
  list-style: none;
}
</style>

<template>
<v-app>
  </main-header>
  <v-flex xs12 md12>
    <v-spacer></v-spacer>
    <Main1></Main1>
    <Main2></Main2>
    <v-content>
      <router-view />
    </v-content>
  </v-flex>
</v-app>
</template>

<script>
import MainHeader from './components/MainHeader'
import Main1 from './components/Main1'
import Main2 from './components/Main2'

export default {
  name: "App",
  components: {
    MainHeader,
    Main1,
    Main2
  }
}
</script>
