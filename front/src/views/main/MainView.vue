<template>
  <div v-if="MainData.isLoggedIn">
    <HeaderComponent></HeaderComponent>
    <HomeView></HomeView>
    <FooterComponent></FooterComponent>
  </div>
  <div v-else>
    <OnBoardingView></OnBoardingView>
  </div>
</template>
<script setup>
import OnBoardingView from './OnBoardingView.vue'
import HeaderComponent from '../components/HeaderComponent.vue'
import FooterComponent from '../components/FooterComponent.vue'
import HomeView from './HomeView.vue'
import { ref, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const MainData = ref({
  isLoggedIn: computed(() => store.getters['accounts/isLoggedIn']),
})
// console.log(MainData.value.isLoggedIn)
// onMounted(() => {
//   const onBoarding = document.querySelector('.onboarding')
//   const one = document.querySelector('.onboarding-one')
//   const two = document.querySelector('.onboarding-two')
//   const three = document.querySelector('.onboarding-three')
//   const img = document.querySelector('.onboarding-img')
//   const la = document.querySelector('.onboarding-la')
// })
</script>
<style>
  
</style>
