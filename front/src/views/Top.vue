<template>
  <v-container>
    <book-search />
    <div @click="getUser">Get User</div>
    <div>{{ userInfo }}</div>
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
            location.href = "/top";
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
