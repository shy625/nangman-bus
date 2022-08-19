<template>
<router-view v-slot="{ Component, route }">
  <transition :name="route.meta.transition || 'fade'">
    <component :is="Component"></component>
  </transition>
</router-view>
</template>

<script setup>
import { useStore } from 'vuex'

const store = useStore()
const getGps = () => {
  if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {
        console.log(position)
        const payload = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        }
        store.dispatch('chatStore/fetchGPS', payload)
      }, err => {
        console.log(err)
      })
  } else {
    alert('GPS를 허용해 주세요.')
  }
}

getGps()
setInterval(() => {
  getGps()
}, 60000);


document.title = ("낭만버스")
// var link = document.querySelector("link[rel~='icon']");
// console.log(link)
// if (!link) {
//     link = document.createElement('link');
//     link.rel = 'icon';
//     link.type = 'image/icon'
//     document.getElementsByTagName('head')[0].appendChild(link);
// }
// link.href = require('./assets/bus-clicked-icon.ico');
// console.log(link)
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