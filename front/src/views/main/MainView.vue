<template>
  <!--
  메인페이지,
  1. 로그인이 안 되어있는 경우: 온보딩 페이지로 다이렉트
  2. 로그인이 되어있는 경우: 헤더, 푸터 컴포넌트 배치
  (헤더, 푸터는 스크롤에 상관없이 화면 상단, 하단 고정이어야 함)
  -->

  <!-- <div v-if="!isLoggedIn">
    <p>우리는 이걸</p>
    <img src="../../assets/logo.png" alt="낭만버스">라
    <p>부르기로 하였다.</p>
    <button><router-link to="signup">시작하기</router-link></button>
    이미 낭만하셨나요? <router-link to="login">로그인</router-link>
  </div> -->

  <div>
    <Header></Header>
    <Home></Home>
    <Footer></Footer>
  </div>
</template>

<script setup>
import Header from '../components/Header.vue'
import Footer from '../components/Footer.vue'
import Home from './HomeView.vue'

</script>
<style>
  
</style>
