<!-- 
  룰렛을 돌렸는지 안 돌렸는지
  4시 기준으로 초기화 할 때 접속을 했을때 기준 확인
  하루에 두 번째 접속부터는 닉네임 GET으로 조회
  store에 닉네임을 저장...
 -->
<template>
  <!-- 닉네임 진한 글씨체(볼드X) -->
  님
  <!-- 환영 문구 -->
  오늘도 낭만을 찾아 떠나볼까요?
  <!-- NangmanBus.vue 컴포넌트 -->
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