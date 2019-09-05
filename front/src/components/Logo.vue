<template>
  <v-flex xs12>
    <v-img
      :src="require('../assets/logo.png')"
      class="my-3"
      contain
      height="200"
    ></v-img>
  </v-flex>
</template>

<script>
export default {
  data: () => {
    return {
      url: process.env.VUE_APP_API_BASE_URL + "auth/login"
    };
  }
};
</script>
