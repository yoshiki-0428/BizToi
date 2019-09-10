<template>
  <v-container>
    <v-flex xs12 md12>
      <h1>学びたい書籍を選択してください</h1>
      <form v-on:submit.prevent="getResult(word)">
        <p>
          Search：
          <input type="search" placeholder="キーワードを入力" v-model="word" />
        </p>
      </form>
      <div class="books_list">
        <v-card class="book_card" v-for="item in items">
          <v-flex md6>
            <img
              class="card-img"
              v-bind:src="item.volumeInfo.imageLinks.thumbnail"
            />
          </v-flex>
          <v-flex md6>
            <div class="card-body">
              <h5 class="card-title">{{ item.volumeInfo.title }}</h5>
              <p class="card-text">{{ item.volumeInfo.authors }}</p>
              <p class="card-text">
                <small class="text-muted">{{
                  item.volumeInfo.publisher
                }}</small>
              </p>
            </div>
          </v-flex>
        </v-card>
      </div>
    </v-flex>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  el: "BooksSearch",
  data() {
    return {
      word: "",
      items: [],
      isLoading: true
    };
  },
  watch: {
    word() {
      this.isLoading = true;
      this.debouncedGetResult();
    }
  },
  created() {
    this.getResult();
    this.debouncedGetResult = _.debounce(this.getResult, 800);
  },
  methods: {
    getResult: function() {
      axios
        .get(
          `https://www.googleapis.com/books/v1/volumes?q=search"${this.word}`
        )
        .then(response => {
          this.items = response.data.items;
          this.isLoading = false;
        })
        .catch(err => {
          this.isLoading = false;
          console.log(err);
        });
    }
  }
};
</script>

<style>
.books_list {
  width: 100%;
}
.book_card {
  box-sizing: border-box;
  -webkit-box-pack: center;
  width: 25%;
  float: none;
}
</style>
