<template>
  <v-flex xs12 md12>
    <v-row>
      <v-col cols="12" sm="6">
        <v-autocomplete
          :items="items"
          :loading="isLoading"
          :search-input.sync="word"
          label="書籍検索キーワード"
          placeholder="Search..."
          rows="1"
          row-height="15"
          no-filter
          hide-no-data
          item-text="title"
          item-value="title"
          outlined
          clearable
        >
          <template v-slot:item="data">
            <template>
              <!-- TODO 本画像を小さく、タイトルをわかりやすく表示する -->
              <!-- TODO クリックしたら本詳細画面に飛ばす -->
              <!--
              <v-list-item-icon>
                <v-img :src="data.item.pictureUrl"/>
              </v-list-item-icon>
              -->
              <v-list-item-content>
                <v-list-item-title v-html="data.item.title"></v-list-item-title>
                <!--
                <v-list-item-subtitle class="text-justify">{{data.item.detail}}</v-list-item-subtitle>
                -->
              </v-list-item-content>
            </template>
          </template>
        </v-autocomplete>
        <v-select
          :items="targets"
          label="絞り込み検索"
          outlined
          return-object
          v-model="selected"
        ></v-select>
        <v-card>
          <v-card-text class="book-list" v-for="item in items" :key="item.id">
            <div class="book-card">
              <div class="card-img">
                <v-img :src="item.pictureUrl" />
              </div>
              <div class="card-body">
                <h5 class="card-title">{{ item.title }}</h5>
                <p class="card-text">{{ item.authors }}</p>
              </div>
              <div>
                <v-btn icon>
                  <v-icon>mdi-star</v-icon>
                </v-btn>
                <v-divider class="mx-4"></v-divider>
              </div>
            </div>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-flex>
</template>

<script>
import Book from "../model/Book";
import _ from "lodash";

export default {
  data() {
    return {
      word: "",
      items: [],
      selected: "all",
      targets: ["all", "title", "author" ],
      isLoading: false
    };
  },
  watch: {
    word() {
      if (this.word) {
        this.debouncedGetResult();
        this.isLoading = true;
      }
    }
  },
  created() {
    this.debouncedGetResult = _.debounce(this.getResult, 400);
  },
  methods: {
    getResult() {
      if (this.word.length !== 0) {
        this.$googleBookApi
          .getBooks(this.word)
          .then(res => {
            this.items = res.data.items.map(item => {
              return new Book(item.volumeInfo);
            });
            this.isLoading = false;
          })
          .catch(err => {
            this.isLoading = false;
            console.log(err.response);
          });
      }
    }
  }
};
</script>

<style scoped>
.card-img {
  width: 30%;
  text-align: center;
  margin: 0 auto;
}
.card-body {
  font-size: 3vm;
  text-align: center;
}
</style>
