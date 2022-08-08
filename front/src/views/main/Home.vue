<template>
  홈화면
  <Header />
  <Nickname />
  <Busnow />
  <Footer />
  홈화면
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