<template>
  <div v-if="MainData.isLoggedIn">
    <Header></Header>
    <Home></Home>
    <Footer></Footer>
  </div>
  <div v-else>
    <OnBoardingView></OnBoardingView>
  </div>
</template>
<script setup>
import OnBoardingView from './OnBoardingView.vue'
import Header from '../components/Header.vue'
import Footer from '../components/Footer.vue'
import Home from './HomeView.vue'
import { ref, onMounted, computed } from 'vue'
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
