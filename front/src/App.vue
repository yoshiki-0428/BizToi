<template>
  <v-app>
  <Header></Header>
  <v-flex xs6 md12>
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
import Header from './components/Header'
import Main1 from './components/Main1'
import Main2 from './components/Main2'

export default {
  name: "App",
  components : {  Header,Main1,Main2  }
  }
</script>
<style>
li{
list-style:none;
}
</style>
