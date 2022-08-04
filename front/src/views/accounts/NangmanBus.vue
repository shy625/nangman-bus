<template>
  <!-- v-if router now 여부 -->
  <img src="@/assets/bus-unclicked.png" alt="bus-icon-unclicked">
  <!-- v-if router now === true -->
  <!-- <img src="@/assets/bus-clicked.png" alt="bus-icon-clicked"> -->
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