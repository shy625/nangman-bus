<template>
  <a href="https://127.0.0.1:8000/">
    <img src="@/assets/logo.png" alt="낭만버스 로고">
  </a>
  <a href="">
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