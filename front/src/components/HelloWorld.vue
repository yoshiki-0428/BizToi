<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs12>
        <v-img
          :src="require('../assets/logo.png')"
          class="my-3"
          contain
          height="200"
        ></v-img>
      </v-flex>
      <v-flex xs12>
        <v-btn
          color="red"
          href="http://localhost:8000/api/auth/login"
          target="_self"
          dark
          >新規登録 / ログインはこちらから
        </v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>
