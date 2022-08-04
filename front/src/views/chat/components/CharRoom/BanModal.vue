<template>
  <div id="banModal">
    <div class="ban-title">
      현재 승차중인 버스가 아닙니다.
    </div>
    <div class="ban-seconds">{{ data.remainingTime }}초 뒤 자동으로 퇴장합니다.</div>
    <el-button 
      color="#FFD96A"
      size="small"
    >
    퇴장하기
    </el-button>
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
  position: fixed;
  width: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 10px;
  /* margin: 0px 32px; */
  padding: 28px;
  background-color: #f5f5f5;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 4px 4px 3px rgba(0, 0, 0, 0.25);
}
.ban-seconds {
  margin-bottom: 12px;
}
</style>