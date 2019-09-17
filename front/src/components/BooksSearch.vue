<template>
  <v-container>
    <v-flex xs12 md12>
      <v-row>
        <v-col cols="12" sm="6">
          <v-autocomplete
            :items="suggestWords"
            :loading="isLoading"
            :search-input.sync="word"
            label="書籍検索キーワード"
            placeholder="Search..."
            outlined
            rows="1"
            row-height="15"
            no-filter
            hide-no-data
            hide-selected
            item-value="API"
          >
          </v-autocomplete>
          <v-btn class="mr-4" @click="getResult(word)">検索</v-btn>
        </v-col>
      </v-row>
      <div class="books_list">
        <v-card class="book_card" v-for="item in items" :key="item.id">
          <v-flex>
            <img class="card-img"
              v-if="item.volumeInfo.imageLinks"
              v-bind:src="item.volumeInfo.imageLinks.thumbnail"
            />
          </v-flex>
          <v-flex>
            <div class="card-body">
              <h5 class="card-title">{{ item.volumeInfo.title }}</h5>
              <p class="card-text">{{ item.volumeInfo.authors }}</p>
              <p class="card-text">
                <small class="text-muted">
                  {{ item.volumeInfo.publisher }}</small}}</small>
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
import _ from "lodash";
export default {
  data() {
    return {
      word: null,
      url: "https://www.googleapis.com/books/v1/volumes",
      items: [],
      suggestWords: [],
      isLoading: false
    };
  },
  watch: {
    word() {
      if (this.word) this.debouncedGetResult();
      this.isLoading = true;
    }
  },
  created() {
    this.debouncedGetResult = _.debounce(this.getResult, 400);
  },
  methods: {
    getResult: function() {
      const params = {
        q: `${this.word}`, // 検索キーワード。intitle:で書籍名が対象に
        Country: "JP",           // 国の指定。JPで日本の指定
        maxResults: 40,          // 取得する検索件数。10~40件を指定可。デフォルトは10件
        orderBy: "relevance"     // 取得する順番、relevance: 関連順 newest: 最新順
      };
      axios
        .get(this.url, { params: params })
        .then(response => {
                  this.items = response.data.items;
                  this.suggestWords = this.items.map(item => {
                    return item.volumeInfo.title;
                  });
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