<template>
  <p>버스 아이콘</p>
  <p>버스 번호</p>
  <p>나와의 거리</p>
  <p>채팅방 인원수</p>
  <p>채팅방 분위기</p>
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