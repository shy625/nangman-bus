<template>
  // 현재 페이지가 어디냐에 따라 각 아이콘들 색이 바뀌어야 하고
  // 또한 페이지 이동가능/이동불가 상태가 바뀌어야 함
  <a href="https://127.0.0.1:8000/">
    <!-- 클릭 안 되어 있을 때는 좀 밝은 색으로 -->
    <font-awesome-icon icon="fa-solid fa-house" />
    홈
  </a>
  <a href="https://127.0.0.1:8000/">
    <!-- 직접 만든 아이콘 - 집에서 SVG 형식으로 만들 것 (애니메이션 넣으면 더 좋음) -->
    <!-- 안 눌렸을 때 -->
    
    <!-- 눌렸을 때 -->
    
    낭만버스
  </a>
  <a href="https://127.0.0.1:8000/">
    <!-- 클릭 안 되어 있을 때는 좀 밝은 색으로 -->
    <font-awesome-icon icon="fa-solid fa-square-list" />
    낭만보고서
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