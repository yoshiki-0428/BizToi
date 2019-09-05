<template>
  <v-container>
    <v-layout text-center wrap>
      <logo />
      <login-button />
    </v-layout>
  </v-container>
</template>

<script>
import Logo from "../components/Logo";
import LoginButton from "../components/LoginButton";

export default {
  components: {
    Logo,
    LoginButton
  }
};
</script>
