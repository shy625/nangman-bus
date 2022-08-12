<template>
  홈화면
  <Header /> 헤더
  <Nickname /> 닉네임
  <Busnow /> 버스나우
  <Footer /> 푸터
  홈화면
</template>
<script setup>
  import { useStore } from 'vuex'
  import { ref, onMounted } from 'vue'

  import Header from '../components/Header.vue'
  import Footer from '../components/Footer.vue'


  import Nickname from './components/Nickname.vue'
  import Busnow from './components/BusNow.vue'


  const store = useStore()
  onMounted(() => {
  })
  const dataNameExample = ref({
    
  })
  const fName = function (event) {
    
  }
</script>