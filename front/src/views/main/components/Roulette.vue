<template>
  <div class="roulette-container">
    <div class="roulette-title">
      오늘의 닉네임
    </div>
    <div class="roulette-content">
      <p class="roulette-etc">츄파츕스</p>
      <p class="roulette-etc">미드가르드</p>
      <p class="roulette-etc">마나</p>
      <p class="roulette-etc">곽 The Bermyu 종환</p>
      <div class="chosen-box">
        <span class="nickname-twinkle">>> </span>
        <span class="chosen-name">{{ rouletteData.nickname }}</span>
        <span class="nickname-twinkle"> &lt;&lt;</span>
      </div>
      <p class="roulette-etc">우리곁을 떠나버린 윤병찬.. 그립읍니다..</p>
      <p class="roulette-etc">안피곤한 팀장 이현규</p>
      <p class="roulette-etc">드러눕는 김종휘</p>
      <p class="roulette-etc">소현영(남자임)</p>
      <p class="roulette-etc">수퍼 프론트 엔지니어 이우현</p>
      <p class="roulette-etc">쓰리 소사이어티스</p>
      <p class="roulette-etc">에인헤르자르</p>
      <p class="roulette-etc">파스칼</p>
      <p class="roulette-etc">스타후르츠</p>
      <p class="roulette-etc">아리스토텔레스</p>
      <p class="roulette-etc">알렉산드로스</p>
      <p class="roulette-etc">코페르니쿠스</p>
      <p class="roulette-etc">안드로아</p>
      <p class="roulette-etc">메리엘</p>
    </div>
  </div>
</template>
<script setup>
import { useStore } from 'vuex'
import { ref, computed } from 'vue'

const store = useStore()
const rouletteData = ref({
  nickname: computed(() => store.getters['mainPage/nickname'])
})
</script>
<style>
.roulette-title {
  /* font-family: Pretendard; */
  font-weight: bold;
}
.roulette-container {
  position: fixed;
  top: 20%;
  left: 50%;
  width: 95%;
  border: 3px solid #FFD96A;
  border-radius: 10px;
  height: 80px;
  overflow: hidden;
  transform: scale(0);
  /* transform: scale(1) translateX(-50%); */
  box-shadow: 0 5px 10px 0 #FFD96A,
              1px 2px 4px 0 #FFD96A,
              2px 4px 8px 0 #FFD96A,
              2px 4px 16px 0 #FFD96A;
  background-color: #f5f5f5;
}
.roulette-active {
  transform: scale(1);
  transform: translateX(-50%);
}
.roulette-content {
  text-align: center;
  font-size: 1.1rem;
  animation: roulette 3s cubic-bezier(0.1, -0.2, 0.3, 1.2);
  transform: translateY(-19%);
}
@keyframes roulette {
  from {
    transform: translateY(-80%);
  }
  to {
    transform: translateY(-18.8%);
  }
}
.chosen-box {
  margin: 0 8px;
}
.chosen-name {
  animation: twinkle .8s forwards 3;
  animation-delay: 3.1s;
}
.nickname-twinkle {
  font-family: BMHANNAPro;
  font-size: 1.5rem;
  color: #F34949;
  opacity: 0;
  animation: twinkle .8s forwards 3;
  animation-delay: 3.1s;
}
@keyframes twinkle {
  from {
    font-weight: bold;
    font-size: 1.2rem;
    opacity: 1;
  }
  50% {
    font-weight: normal;
    font-size: 1.1rem;
    opacity: 0;
  }
  to {
    font-weight: bold;
    font-size: 1.2rem;
    opacity: 1;
  }
}
.roulette-etc {
  margin: 8px;
}
.roulette-in {
  animation: rouletteIn 1s forwards;
}
@keyframes rouletteIn {
  from {
    transform: rotate3d(0, 0, 1, -45deg);
    opacity: 0;
  }
  to {
    transform: translate3d(-50%, 0, 0);
    opacity: 1;
  }
}
.roulette-out {
  animation: rouletteOut 1s normal;
}
@keyframes rouletteOut {
  from {
    opacity: 1;
  }
  to {
    transform: rotate3d(0, 0, 1, 45deg);
    opacity: 0;
  }
}
</style>