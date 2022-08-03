<template>
  <div id="banModal">
    <div class="ban-title">
      현재 승차중인 버스가 아닙니다.
    </div>
    <div class="ban-seconds">{{ data.remainingTime }}초 뒤 자동으로 퇴장합니다.</div>
    <el-button color="#FFD96A">퇴장하기</el-button>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'

const data = ref({
  remainingTime: 60,
})
const runningTime = setInterval(() => {
  data.value.remainingTime --
}, 1000)
setTimeout(() => {
  clearInterval(runningTime)
}, 60000)
</script>
<style>
.ban {
  margin: 0px 32px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>