<template>
  <v-container>
    <v-card class="mx-auto" max-width="100%">
      <v-img class="white--text" height="300px" src="../assets/Back.png">
        <v-card-title class="align-start font-weight-bold">
          ビジトイって？
        </v-card-title>
      </v-img>
      <v-card-text>
        <span class="text--primary">
          <span>それはあなたにとって極上の体験</span><br />
          <span>今しかできない体験を今すぐ手に入れよう</span>
        </span>
        <v-flex xs12>
          <login-button />
        </v-flex>
      </v-card-text>
    </v-card>
    <br />
  </v-container>
</template>

<script>
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
