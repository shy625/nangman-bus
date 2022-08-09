<template>
  <router-link to="main">
    <!-- <img src="@/assets/logo.png" alt="낭만버스 로고"> -->
    홈
  </router-link>
  <a href="https://127.0.0.1:8000/">
    <font-awesome-icon icon="fa-solid fa-circle-user" />
  </a>
</template>
<script setup>
  import { useStore } from 'vuex'
  import { ref, onMounted } from 'vue'
  const store = useStore()
  onMounted(() => {
  })
  const dataNameExample = ref({
    
  })
  const fName = function (event) {
    
  }
</script>