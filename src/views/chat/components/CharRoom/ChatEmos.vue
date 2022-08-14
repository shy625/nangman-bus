<template>
  <div class="emo">
    <div id="emoToggle" class="emo-toggle">
      기분
      <div id="emoList" class="emo-list">
        <div class="emo-emoji" v-for="(emo, idx) in emos"
          :key="idx"
        >
          <img :src="emo" alt="" class="emoji-img">
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { onMounted } from 'vue'
const emos = [
  `${require('../../../../assets/emo-default.png')}`,
  `${require('../../../../assets/emo-happy.png')}`,
  `${require('../../../../assets/emo-angry.png')}`,
  `${require('../../../../assets/emo-blue.png')}`,
]

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
  bottom: 40px;
  overflow: hidden;
  height: 115px;
  transition: all 0.2s;
}
.emo-list.collapsed {
  height: 0;
}
.emo-emoji {
  width: 50px;
}
.emoji-img {
  height: 25px;
}
</style>