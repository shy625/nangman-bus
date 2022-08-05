<template>
  <router-view></router-view>
</template>

<script>

export default {
  name: 'App',

  components: {
    
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