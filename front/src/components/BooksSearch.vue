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
          outlined
          rows="1"
          row-height="15"
          no-filter
          hide-no-data
          filled
          item-text="title"
          item-value="title"
        >
          <template v-slot:item="data" style="height: 1000px">
            <template >
              <!-- TODO 本画像を小さく、タイトルをわかりやすく表示する -->
              <!-- TODO クリックしたら本詳細画面に飛ばす -->
              <v-list-item-icon>
                <v-img :src="data.item.pictureUrl"/>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title v-html="data.item.title"></v-list-item-title>
                <v-list-item-subtitle v-html="data.item.detail"></v-list-item-subtitle>
              </v-list-item-content>
            </template>
          </template>
        </v-autocomplete>
        <v-btn class="mr-4" @click="getResult(word)">検索</v-btn>
      </v-col>
    </v-row>
  </v-flex>
</template>

<script>
  import axios from "axios";
  import Book from "../model/Book"
  import _ from "lodash";

  export default {
  data() {
    return {
      word: "",
      url: "https://www.googleapis.com/books/v1/volumes",
      items: [],
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
    async getResult () {
      const params = {
        q: `${this.word}`, // 検索キーワード。intitle:で書籍名が対象に
        Country: "JP",           // 国の指定。JPで日本の指定
        maxResults: 40,          // 取得する検索件数。10~40件を指定可。デフォルトは10件
        orderBy: "relevance"     // 取得する順番、relevance: 関連順 newest: 最新順
      };
      await axios
        .get(this.url, { params: params })
        .then(response => {
          this.items = response.data.items.map(item => {
            return new Book(item.volumeInfo)
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
