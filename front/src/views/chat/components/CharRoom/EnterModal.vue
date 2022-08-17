<template>
  <div id="enterModal" class="enter-modal">
    <div class="enter-cover"></div>
    <div class="enter-content">
      <div class="enter-question">
        오늘 기분은 어떠세요?
      </div>
      <input class="enter-input" type="text" placeholder="답변을 입력해 주세요">
      <div class="enter-btn">
        <el-button 
          color="#FFD96A"
          size="small"
        >
          건너뛰기
        </el-button>
        <el-button
          color="#FFD96A"
          size="small"
        >
          보내기
        </el-button>
      </div>
    </div>
  </div>
</template>
<script setup>

</script>
<style>
.enterModal {
  position: fixed;
  font-size: 1.2rem;
  transform: scale(0);
  width: 100%;
  top: 50%;
  left: 50%;
  border-radius: 10px;
  padding: 28px;
  background-color: #f5f5f5;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 4px 4px 3px rgba(0, 0, 0, 0.25);
}
.chatroom-blur {
  filter: blur(3px);
}
.enter {
  transform: scale(1) translate(-50%, -50%);
  position: fixed;
  width: 100%;
  top: 50%;
  left: 50%;
  border-radius: 10px;
  padding: 28px;
  background-color: #f5f5f5;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 4px 4px 3px rgba(0, 0, 0, 0.25);
}
.enter-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  z-index: 5;
}
.enter-cover {
  position: fixed;
  top: -1000px;
  left: 0;
  width: 1000px;
  height: 2000px;
  z-index: 4;
}
.enter-input {
  margin: 12px 0px;
  border: 0px;
  border-radius: 5px;
  width: 250px;
  padding: 8px 0px;
}
.enter-input:focus {
  outline: none;
}
.enter-input::placeholder {
  text-align: center;
  font-size: 0.72rem;
}
.enter-btn .el-button span {
  font-family: Pretendard;
}
.enter-in {
  animation: enterIn 2s forwards;
}
@keyframes enterIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
.enter-out {
  animation: enterOut 1s forwards;
}
@keyframes enterOut {
  from {
    opacity: 1;
  }

  to {
    opacity: 0;
  }
}
</style>