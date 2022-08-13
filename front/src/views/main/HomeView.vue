<template>
<div class="home">
  <el-scrollbar>
    <div class="home-welcome">
      <div class="welcome-nickname">
        <span class="welcome-bold">{{ homeData.nickname }}</span>님,<br>
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
<Roulette v-if="homeData.isRouletted"></Roulette>
</template>
<script setup>
import Roulette from './components/Roulette.vue'
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