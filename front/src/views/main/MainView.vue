<template>
  <div>
    <OnBoardingView></OnBoardingView>
  </div>
  <div>
    <!-- <Header></Header>
    <Home></Home>
    <Footer></Footer> -->
  </div>
</template>
<script setup>
import OnBoardingView from './OnBoardingView.vue'
import Header from '../components/Header.vue'
import Footer from '../components/Footer.vue'
import Home from './HomeView.vue'
import { ref, onMounted } from 'vue'

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
