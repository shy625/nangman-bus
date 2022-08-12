<template>
<div class="home">
  <el-scrollbar>
    <div class="home-welcome">
      <div class="welcome-nickname">
        <span class="welcome-bold">가우르구라</span>님,<br>
        오늘은 어떤 낭만을<br>
        기대하세요?
      </div>
      <div class="welcome-chaticon-btn">
        <img src="../../assets/bus-unclicked.png" alt="unclickedBus" class="welcome-chaticon-img">
      </div>
    </div>
    <MostlyBus></MostlyBus>
    <RecentlyBus></RecentlyBus>
    <RandomBus></RandomBus>
  </el-scrollbar>
</div>
<Roulette v-if="data.isChanged"></Roulette>
</template>
<script setup>
import Roulette from './components/Roulette.vue'
import MostlyBus from './components/MostlyBus.vue'
import RecentlyBus from './components/RecentlyBus.vue'
import RandomBus from './components/RandomBus.vue'
import { ref, onMounted } from 'vue'
 
const data = ref({
  isChanged: true,
})

onMounted(() => {
  const urlNow = window.location.href
  const footerMainBtn = document.querySelector('.footer-main-btn')
  const fotterReportsBtn = document.querySelector('.footer-reports-btn')
  if (urlNow[urlNow.length-1] === '/') {
    footerMainBtn.classList.add('footer-btn-active')
    fotterReportsBtn.classList.remove('footer-btn-active')
  }

  if (data.value.isChanged) {
    const rouletteContainer = document.querySelector('.roulette-container')
    const home = document.querySelector('.home')
    home.classList.add('home-blur')
    rouletteContainer.classList.add('roulette-active')
    rouletteContainer.classList.add('roulette-in')
    home.addEventListener('click', () => {
      home.classList.remove('home-blur')
      rouletteContainer.classList.remove('roulette-in')
      rouletteContainer.classList.add('roulette-out')
      rouletteContainer.addEventListener('animationend', () => {
        rouletteContainer.classList.remove('roulette-active')
      })
    })
  }
})
</script>
<style>
.home-blur {
  filter: blur(3px);
}
.home {
  margin: 100px 32px 51px 32px;
}
.home-welcome {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 52px;
}
.welcome-chaticon-btn {
  margin-right: 24px;
  height: 24px;
  padding: 10px;
  background-color: white;
  border: 3px solid #F34949;
  border-radius: 50%;
}
.welcome-chaticon-img {
  height: 24px;
}
.welcome-nickname {
  font-size: 1.5rem;
}
.welcome-bold {
  font-family: BMHANNAPro;
  font-size: 1.8rem;
}
</style>