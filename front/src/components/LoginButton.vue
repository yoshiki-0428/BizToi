<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs12>
        <v-btn color="red" :href="url" target="_self" dark
          >新規登録 / ログインはこちらから
        </v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  data: () => {
    return {
      url: `${process.env.VUE_APP_API_BASE_URL}auth/login`
    };
  }
};
</script>
