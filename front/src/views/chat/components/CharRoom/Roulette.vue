<template>
  <div class="scroll-container">
    <div class="scroll-text">
      <div class="chosen-nickname">
        <span class="nickname-twinkle">>> </span>
        <span>가우르구라</span>
        <span class="nickname-twinkle"> &lt;&lt;</span>
      </div>
      <p>헤라</p>
      <p>클레스</p>
      <p>ㅁㄴㄹ</p>
      <p>ㅌㅋㅍ</p>
      <p>ㅌㅋㅍㅌㅋㅍㅌㅋㅍ</p>
      <p>가우ㅌㅋㅍㅁㅈㅍㅁㄴ르구라</p>
      <p>헤ㅌㅋ ㅋㅌ ㅌㅋ 라</p>
      <p>클레ㅇㄴㅍㅇㄴㅍㅇㄴ스</p>
      <p>가ㅊㅌ ㅌㅋㅍㄴㅇㅍ우르구라</p>
      <p>헤라</p>
      <p>클레ㅍㄴㅇㅍ스</p>          
      <p>가우르구라</p>
      <p>헤라</p>
      <p>클레스</p>
    </div>
  </div>
</template>
<script setup>

</script>
<style>
.scroll-container {
  border: 1px solid black;
  border-radius: 5px;
  height: 80px;
  overflow: hidden;
}
.scroll-text {
  text-align: center;
  animation: roulette 1.5s cubic-bezier(0.1, -0.5, 0.5, 1.3);
}
@keyframes roulette {
  from {
    transform: translateY(-80%);
  }
  to {
    transform: translateY(0);
  }
}
.chosen-nickname {
  margin-top: 32px;
}
.nickname-twinkle {
  opacity: 0;
  animation: twinkle .8s normal 2;
  animation-delay: 1.5s;
}
@keyframes twinkle {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
</style>