<template>
  <div class="roulette-container">
    <div class="roulette-content">
      <p class="roulette-etc">헤라</p>
      <p class="roulette-etc">클레스</p>
      <p class="roulette-etc">ㅁㄴㄹ</p>
      <p class="roulette-etc">ㅌㅋㅍ</p>
      <div class="chosen-box">
        <span class="nickname-twinkle">>> </span>
        <span class="chosen-name">가우르구라</span>
        <span class="nickname-twinkle"> &lt;&lt;</span>
      </div>
      <p class="roulette-etc">헤라</p>
      <p class="roulette-etc">클레스</p>
      <p class="roulette-etc">ㅁㄴㄹ</p>
      <p class="roulette-etc">ㅌㅋㅍ</p>
      <p class="roulette-etc">ㅌㅋㅍㅌㅋㅍㅌㅋㅍ</p>
      <p class="roulette-etc">가우ㅌㅋㅍㅁㅈㅍㅁㄴ르구라</p>
      <p class="roulette-etc">헤ㅌㅋ ㅋㅌ ㅌㅋ 라</p>
      <p class="roulette-etc">클레ㅇㄴㅍㅇㄴㅍㅇㄴ스</p>
      <p class="roulette-etc">가ㅊㅌ ㅌㅋㅍㄴㅇㅍ우르구라</p>
      <p class="roulette-etc">헤라</p>
      <p class="roulette-etc">클레ㅍㄴㅇㅍ스</p>
      <p class="roulette-etc">가우르구라</p>
      <p class="roulette-etc">헤라</p>
      <p class="roulette-etc">클레스</p>
    </div>
  </div>
</template>
<script setup>

</script>
<style>
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
    opacity: 1;
  }
  50% {
    opacity: 0;
  }
  to {
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