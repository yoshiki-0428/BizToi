<template>
<v-container>
  <v-layout text-center wrap>
    <main1 />
    <main2 />
  </v-layout>
</v-container>
</template>

<script>
import Logo from "../components/Logo";
import LoginButton from "../components/LoginButton";
import Main1 from "../components/Main1";
import Main2 from "../components/Main2"

export default {
  components: {
    Main1,
    Main2,
    Logo,
    LoginButton
  }
};
</script>
