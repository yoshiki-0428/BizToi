<template>
  <v-content>
    <v-btn @click="getUser">Get User</v-btn>
    <H1>About Page!!</H1>
    <h2>User INFO: {{ userInfo }}</h2>
  </v-content>
</template>

<script>
  export default {
  data: () => {
    return {
      userInfo: Object,
    };
  },
  created() {
    const code = this.$route.query.code;
    if (code) {
      this.getToken(code);
    }
  },
  methods: {
    getToken(code) {
      this.$authApi.getTokenCode(code)
          .then(result => {
            localStorage.setItem("idToken", result.data);
            location.href = `${process.env.VUE_APP_FRONT_DOMAIN}/top`;
          });
    },
    async getUser() {
      this.userInfo = await this.$usersApi.get()
          .then(result => {
            return result.data
          });
    }
  }
};
</script>
