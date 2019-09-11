<template>
  <v-container>
    <v-flex xs12 md12>
      <h1>学びたい書籍を選択してください</h1>
      <v-row>
        <v-col cols="12" sm="6">
          <v-text-field
            v-model="word"
            label="書籍検索キーワード"
            outlined
            rows="1"
            row-height="15"
          >
          </v-text-field>
          <v-btn class="mr-4" @click="getResult(word)">検索</v-btn>
        </v-col>
      </v-row>
      <div class="books_list">
        <v-card class="book_card" v-for="item in items">
          <v-flex>
            <img
              class="card-img"
              v-if="item.volumeInfo.imageLinks"
              v-bind:src="item.volumeInfo.imageLinks.thumbnail"
            />
          </v-flex>
          <v-flex>
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
  import _ from "lodash"

  export default {
  data() {
    return {
      word: "",
      url: "https://www.googleapis.com/books/v1/volumes",
      items: [],
      isLoading: true
    };
  },
  watch: {
    word() {
      this.isLoading = true;
      if (this.word)
        this.debouncedGetResult();
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
        .get(this.url, {params: params})
        .then(response => {
          this.items = response.data.items;
          console.log(this.items);
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
