<template>
  <v-container>
    <v-card class="mx-auto" max-width="100%">
      <v-img class="white--text" height="300px" src="../assets/Back.png">
        <v-card-title class="font-weight-bold justify-center display-3 mt-10">
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
        <v-card-title class="font-weight-bold justify-center headline mt-10">
          about BizToi
        </v-card-title>
        <v-card-text class="subtitle-2 mt-10">
          <span>
            <span>どんなサービス？</span><br />
          </span>
        </v-card-text>
      </v-img>
    </v-card>
    <br />
    <v-card class="mx-auto" max-width="100%">
      <v-img class="white--text" height="300px" src="../assets/Back.png">
        <v-card-title class="font-weight-bold justify-center headline mt-10">
           How to use
        </v-card-title>
        <v-card-text class="subtitle-1 mt-10">
          <ul>
            <li>①学習したい本を選択する</li>
            <li>②問題集を選択する</li>
            <li>③回答を記載する</li>
            <li>④読書後に問題集に挑戦する</li>
            <li>⑤回答をシェアする</li>
          </ul>
        </v-card-text>
      </v-img>
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
  ul{
    text-align: left;
  }
</style>
