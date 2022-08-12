<template>
<router-view v-slot="{ Component, route }">
    <transition :name="route.meta.transition || 'fade'">
      <component :is="Component"></component>
    </transition>
</router-view>
</template>

<script setup>

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
a {
  text-decoration: none;
}

.fade-leave-active {
  transition: all 0.3s;
}
.fade-enter-active {
  transition: all 0.3s;
  transition-delay: 0.3s;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
.fade-enter-to,
.fade-leave-from {
  opacity: 1;
}

.reportIn-enter-from {
  opacity: 0;
  transform: scale3d(0.3, 0.3, 0.3);
}
.reportIn-leave-to {
  opacity: 0;
}
.reportIn-leave-active {
  transition: all 0.3s;
}
.reportIn-enter-active {
  transition: all 0.3s;
  transition-delay: 0.3s;
}
.reportIn-enter-to {
  opacity: 1;
  transform: scale3d(1, 1, 1);
}
.reportIn-leave-from {
  opacity: 1;
}
</style>