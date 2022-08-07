<template>
<router-link :to="{ name: 'chat', params: { roomId: 1 }}">채팅방</router-link>

<router-view v-slot="{ Component, route }">
  <transition :name="route.meta.transition" :mode="route.meta.mode">
    <component :is="Component"></component>
  </transition>
</router-view>

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
@font-face {
  font-family:'Pretendard';
  src: url('./assets/fonts/PretendardVariable.ttf') format('woff');
  font-weight: normal;
}
body {
  margin: 0;
  font-family: BMHANNAAir, BMHANNAPro, Pretendard;
}
.slide-fade-enter {
  transform: translateY(-15px);
  opacity: 0;
}

.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: all .15s ease;
}

.slide-fade-leave-to {
  transform: translateY(15px);
  opacity: 0;
}
</style>