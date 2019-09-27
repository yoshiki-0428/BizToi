<template>
  <transition name="fade">
  <v-row justify="center">
    <v-dialog v-model="dialog" fullscreen scrollable>
      <template v-slot:activator="{ on }">
        <v-btn icon v-on="on">
          <v-icon>mdi-book-search</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-toolbar>
          <v-btn icon color="black" @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title>書籍検索フォーム</v-toolbar-title>
        </v-toolbar>
        <books-search />
      </v-card>
    </v-dialog>
  </v-row>
  </transition>
</template>

  <script>
    import BooksSearch from "../components/BooksSearch";
  export default {
    components: {BooksSearch},
    data () {
      return {
        dialog: false,
        notifications: false,
        sound: true,
        widgets: false,
      }
    },
  }
</script>