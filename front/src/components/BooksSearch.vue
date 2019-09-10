<template>
  <v-container>
    <v-flex xs12 md12>
      <h1>学びたい書籍を選択してください</h1>
      <v-row>
        <v-col cols="12" sm="6">
          <v-form ref="form" @submit.prevent>
            <v-text-field
              v-model="word"
              label="書籍検索キーワード"
              outlined
              rows="1"
              row-height="15"
            >
            </v-text-field>
            <v-btn class="mr-4" @click="getResult(word)">検索</v-btn>
          </v-form>
        </v-col>
      </v-row>
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
  text-align: center;
}
.book_card {
  box-sizing: border-box;
  -webkit-box-pack: center;
  width: 30%;
  height: 350px;
  margin: 10px 20px 20px 10px;
  float: left;
}
</style>
