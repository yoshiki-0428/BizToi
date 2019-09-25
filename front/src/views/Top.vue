<template>
  <v-container>
    <book-search></book-search>
  </v-container>
</template>

<script>
  import BookSearch from "../components/BooksSearch";

  export default {
  components: {
    BookSearch
  },
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
