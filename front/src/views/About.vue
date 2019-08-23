<template>
  <v-content>
    <H1>About Page!!</H1>
    <h2>TokenId: {{ tokenId }}</h2>
    <br />
    <h2>AccessToken: {{ accessToken }}</h2>
    <br />
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
      .get(process.env.VUE_APP_API_BASE_URL + "/auth/token", {
        params: {
          code: this.$route.query.code
        }
      })
      .then(result => {
        _this.tokenId = result.data.id_token;
        _this.accessToken = result.data.access_token;
        _this.refreshToken = result.data.refresh_token;
      });
  }
};
</script>
