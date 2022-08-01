<template>
  <div class="emo">
    <div id="emoToggle" class="emo-toggle">
      기쁨
      <div id="emoList" class="emo-list">
        <div class="emo-emoji" v-for="(emo, idx) in emos"
          :key="idx"
        >
          {{ emo }}
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
// 더미데이터
const emos = ['기쁨', '슬픔', '취함', '우울']

// state에 현재 emo 저장해두고, 그거랑 비교해서 같은거 찾아서 toggle에 넣기
// + 그거는 emoList에 안뜨게 거르기

onMounted(() => {
  const emoList = document.querySelector('#emoList')
  const emoToggle = document.querySelector('#emoToggle')
  emoList.classList.add('collapsed')
  function emoListToggle() {
      emoList.classList.toggle('collapsed')
  }
  emoToggle.addEventListener('click', emoListToggle)
})
</script>
<style>
.emo {
  margin-left: 8px;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.emo-list {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: absolute;
  /* top: -200%; */
  bottom: 40px;
  overflow: hidden;
  height: 70px;
  transition: all 1s;
}
.emo-list.collapsed {
  height: 0;
}
.emo-emoji {
  width: 50px;
}
</style>