<template>
  <div class="report-detail-user">
    <div class="detail-user">
      <div class="detail-user-text">
        <div class="user-text-title">
          총 채팅 수
        </div>
        <div class="user-text-content">
          {{ totalChatCount }}
        </div>
      </div>
      <img src="../../../assets/report-message.png" alt="exampleImg" class="detail-user-img">
    </div>
    <div class="detail-user">
      <img src="../../../assets/report-withTime.png" alt="exampleImg" class="detail-user-img">
      <div class="detail-user-text">
        <div class="user-text-title">
          함께한 시간
        </div>
        <div class="user-text-content">
          {{ hour }}시간 {{ minute }}분
        </div>
      </div>
    </div>
    <div class="detail-user">
      <div class="detail-user-text">
        <div class="user-text-title">
          스쳐 지나간 사람들
        </div>
        <div class="user-text-content">
          {{ totalUserCount }}명
        </div>
      </div>
      <img src="../../../assets/report-people.png" alt="exampleImg" class="detail-user-img">
    </div>
    <div class="detail-user">
      <img src="../../../assets/report-fire.png" alt="exampleImg" class="detail-user-img">
      <div class="detail-user-text">
        <div class="user-text-title">
          채팅방 화력
        </div>
        <div class="user-text-content">
          {{ chatPerMinute }}회/분
        </div>
      </div>
    </div>
  </div>
  <hr class="report-detail-hr-small">
</template>
<script setup>
import { useStore } from 'vuex'
// import { onMounted } from 'vue'


  const store = useStore()
  const totalChatCount = store.getters['mainPage/reportsDetailTotalChatCount']
  const hour = store.getters['mainPage/reportsDetailMyAccessHour']
  const minute = store.getters['mainPage/reportsDetailMyAccessMinute']
  const totalUserCount = store.getters['mainPage/reportsDetailTotalUserCount']
  const chatPerMinute = store.getters['mainPage/reportsDetailChatPerMinute']

</script>
<style>
.report-detail-user {
  margin: 0 20px;
}
.detail-user {
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin: 40px 0;
}
.detail-user-text {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.user-text-title {
  color: #FFD96A;
  font-size: 1.5rem;
}
.user-text-content {
  font-size: 2rem;
  font-family: BMHANNAPro;
}
.detail-user-img {
  width: 80px;
}
</style>