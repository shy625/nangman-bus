<template>
  <div id="enterModal">
    <div class="enter-question">
      오늘 기분은 어떠세요?
    </div>
    <input class="enter-input" type="text" placeholder="내용을 입력해 주세요">
    <el-button 
      color="#FFD96A"
      size="small"
    >
    건너뛰기
    </el-button>
  </div>
</template>
<script setup>

</script>
<style>
.enter {
  position: fixed;
  width: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 10px;
  padding: 28px;
  background-color: #f5f5f5;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 4px 4px 3px rgba(0, 0, 0, 0.25);
}
.enter-input {
  margin: 12px 0px;
  border: 0px;
  border-radius: 5px;
  width: 75%;
  padding: 8px 0px;
}
.enter-input:focus {
  outline: none;
}
.enter-input::placeholder {
  text-align: center;
  font-family: BMHANNAAir;
}
</style>