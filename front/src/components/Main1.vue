<template>
<v-container>
  <v-card class="mx-auto" max-width=100%>
    <v-img class="white--text" height=500px src="../assets/Back.png">
      <v-card-title class="font-weight-bold">ビジトイって？</v-card-title>
      <v-card-text>
        <ul>
          <li>***********</li>
          <li>***********</li>
          <li>***********</li>
        </ul>
        <v-flex xs12>
          <login-button />
        </v-flex>
      </v-card-text>
      <v-layout text-right wrap>

      </v-layout>

    </v-img>
  </v-card>
</v-container>
</template>

<script>
import Logo from "../components/Logo";
import LoginButton from "../components/LoginButton";

export default {
  components: {
    LoginButton
  }
};
</script>

<style scoped>
li {
  list-style: none;
}
</style>
