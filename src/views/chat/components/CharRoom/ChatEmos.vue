<template>
  <div class="emo">
    <div id="emoToggle" class="emo-toggle">
      <img src="../../../../assets/emo-default.png" alt="emo" class="toggle-title">
      <div id="emoList" class="emo-list">
        <div class="emo-emoji" v-for="(emo, idx) in emosData.emos"
          :key="idx"
        >
          <img :src="emo" :alt="idx" class="emoji-img">
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const emosData = ref({
  client: computed(() => store.getters['chatStore/client']),
  userId: computed(() => store.getters['chatStore/userId']),
  emos: [  // 0: 무표정, 1: 화남, 2: 기쁨, 3: 우울
    `${require('../../../../assets/emo-default.png')}`,
    `${require('../../../../assets/emo-angry.png')}`,
    `${require('../../../../assets/emo-happy.png')}`,
    `${require('../../../../assets/emo-blue.png')}`,
  ],
  sessionId: computed(() => store.getters['chatStore/sessionId'])
})

onMounted(() => {
  const emoList = document.querySelector('#emoList')
  const emoToggle = document.querySelector('#emoToggle')
  emoList.classList.add('collapsed')
  function emoListToggle() {
      emoList.classList.toggle('collapsed')
  }
  emoToggle.addEventListener('click', emoListToggle)

  // 기본 이미지 클릭한 거로 바꾸기
  const emojis = document.querySelectorAll('.emoji-img')
  const toggleTitle = document.querySelector('.toggle-title')
  emojis.forEach(emoji => {
    emoji.addEventListener('click', e => {
      toggleTitle.src = e.target.src
      // 감정 상태 pub
      console.log('감정 번호', e.target.alt)
      const payload = {
        userId: emosData.value.userId,
        emotion: e.target.alt,
      }
      emosData.value.client.publish({
        destination: '/pub/chat/rooms/' + emosData.value.sessionId + '/emotion',
        body: JSON.stringify(payload),
      })
    })
  })
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
.emo-toggle {
  font-family: Pretendard;
}
.toggle-title {
  height: 20px;
}
.emo-emoji {
  width: 50px;
}
.emoji-img {
  height: 25px;
}
</style>