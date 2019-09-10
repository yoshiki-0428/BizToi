<template>
  <v-container>
    <h1>LESSON</h1>
    <router-link to="/">Go to Home</router-link>
    <books-search />
  </v-container>
</template>

<script>
import BooksSearch from "../components/BooksSearch";

export default {
  components: {
    BooksSearch
  }
};
</script>
