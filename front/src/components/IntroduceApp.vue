<template>
  <v-container>
    <v-layout>
      <v-flex xs12>
        <v-card class="mx-auto" max-width="100%">
          <v-img class="white--text" height="300px" src="../assets/Back.png">
            <v-card-title
              class="font-weight-bold justify-center display-3 mt-10"
            >
              ビジトイ
            </v-card-title>
            <v-card-text class="subtitle-2 mt-10">
              <span>
                <span>それはあなたにとって極上の体験</span><br />
                <span>今しかできない体験を今すぐ手に入れよう</span>
              </span>
              <v-flex 12>
                <login-button />
              </v-flex>
            </v-card-text>
          </v-img>
        </v-card>
        <br />
        <v-card class="mx-auto" max-width="100%">
          <v-img class="white--text" height="300px" src="../assets/Back.png">
            <v-card-title
              class="font-weight-bold justify-center headline mt-10"
            >
              about BizToi
            </v-card-title>
            <v-card-text class="subtitle-2 mt-10">
              <span> <span>どんなサービス？</span><br /> </span>
            </v-card-text>
          </v-img>
        </v-card>
        <br />
        <v-card class="mx-auto" max-width="auto">
          <v-img
            height="700"
            max-width="100%"
            contain
            src="../assets/howto.png"
          >
          </v-img>
          <login-button />
        </v-card>
      </v-flex>
      <br />
    </v-layout>
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
ul {
  text-align: left;
}
</style>
