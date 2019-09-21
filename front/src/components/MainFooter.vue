<template>
  <v-footer padless>
    <v-col class="text-center" cols="12">
      <v-card-text>
        <v-btn icon class=mx4 v-for="item in sns" :key="item.icon" :to="item.link">
          <v-icon dark large v-text="item.icon"></v-icon>
        </v-btn>
      </v-card-text>
      {{ new Date().getFullYear() }} — <strong>ビジトイ</strong>
    </v-col>
  </v-footer>
</template>

<script>
export default {
  data() {
    return {
      sns: [
        {
          icon: "mdi-google",
          link: ""
        },
        {
          icon: "mdi-facebook-box",
          link: ""
        },
        {
          icon: "mdi-twitter",
          link: ""
        }
      ]
    };
  }
};
</script>

<style scoped>
.v-btn--icon {
  text-align: center;
  margin-right: 20px;
}
</style>
