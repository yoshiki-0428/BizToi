<template>
  <v-content>
    <v-btn @click="getUser">Get User</v-btn>
    <H1>About Page!!</H1>
    <h2>TokenId: {{ tokenId }}</h2>
    <br/>
    <h2>AccessToken: {{ accessToken }}</h2>
    <br/>
    <h2>RefreshToken: {{ refreshToken }}</h2>
  </v-content>
</template>

<script>
  import axios from "axios";

  export default {
  data: () => {
    return {
      tokenId: "",
      accessToken: "",
      refreshToken: ""
    };
  },
  created() {
    const _this = this;
    axios
      .get(process.env.VUE_APP_API_BASE_URL + "auth/token", {
        params: {
          code: this.$route.query.code
        }
      })
      .then(result => {
        console.log(result)
        _this.tokenId = result.data;
        localStorage.setItem("idToken", result.data);
      });
  },
  methods: {
    getUser() {
      const bearer = `Bearer ${localStorage.getItem("idToken")}`;
      axios
        .get(process.env.VUE_APP_API_BASE_URL + "users/me", {
          headers: {
            Authorization: bearer
          }
        })
        .then(result => {
          console.log(result);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>
