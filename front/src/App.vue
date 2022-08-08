<template>
  <router-view></router-view>
  <mainview/>
</template>

<script>
import MainView from './views/main/MainView.vue'
export default {
  name: 'App',

  components: {
    MainView
  },

  data: () => ({

  }),
}
</script>

<style>
@font-face {
  font-family:'BMHANNAAir';
  src: url('./assets/fonts/BMHANNAAir_ttf.ttf') format('woff');
  font-weight: normal;
}
@font-face {
  font-family:'BMHANNAPro';
  src: url('./assets/fonts/BMHANNAPro.ttf') format('woff');
  font-weight: normal;
}
body {
  margin: 0;
  font-family: BMHANNAAir, BMHANNAPro;
}
</style>