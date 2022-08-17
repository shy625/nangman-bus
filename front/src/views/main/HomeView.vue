<template>
  <div>
    <div class="home">
      <el-scrollbar>
        <div class="home-welcome">
          <div class="welcome-nickname">
            <span class="welcome-bold">{{ homeData.nickname }}</span>님,<br>
            오늘은 어떤 낭만을<br>
            기대하세요?
          </div>
          <img src="../../assets/bus-clicked-horizon.png" alt="unclickedBus" class="welcome-chaticon-img">
        </div>
        <MostlyBus></MostlyBus>
        <RecentlyBus></RecentlyBus>
        <RandomBus></RandomBus>
      </el-scrollbar>
    </div>
    <RouletteComponent></RouletteComponent>
  </div>
</template>
<script setup>
import RouletteComponent from './components/RouletteComponent.vue'
import MostlyBus from './components/MostlyBus.vue'
import RecentlyBus from './components/RecentlyBus.vue'
import RandomBus from './components/RandomBus.vue'
import { ref, onMounted, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const homeData = ref({
  isRouletted: computed(() => store.getters['mainPage/isRouletted']),
  nickname: computed(() => store.getters['mainPage/nickname']),
  userId: computed(() => store.getters['accounts/accountUserId']),
})

onMounted(() => {
  store.dispatch('mainPage/fetchCurrentUser', homeData.value.userId)
  store.dispatch('mainPage/fetchMainPageBusData')
})
</script>
<style>
.home-blur {
  filter: blur(7px);
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

.welcome-chaticon-img {
  height: 50px;
  margin-right: 24px;
  animation: welcomeIcon 4s infinite;
}
@keyframes welcomeIcon {
  from {
    -webkit-transform: translate3d(100%, 0, 0) skewX(-30deg);
    transform: translate3d(100%, 0, 0) skewX(-30deg);
    opacity: 0;
  }
  15% {
    -webkit-transform: skewX(20deg);
    transform: skewX(20deg);
    opacity: 1;
  }
  20% {
    -webkit-transform: skewX(-5deg);
    transform: skewX(-5deg);
  }
  25% {
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
  }
}
.welcome-nickname {
  font-size: 1.5rem;
  /* font-family: Pretendard; */
}
.welcome-bold {
  font-family: BMHANNAPro;
  font-size: 1.8rem;
}
</style>