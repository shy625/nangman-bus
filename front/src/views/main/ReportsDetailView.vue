<template>
<div>
  <div class="report-bg-img"></div>
  <div class="report-detail">
    <el-scrollbar>
      <ReportDetailHeader></ReportDetailHeader>
      <ReportDetailMostPopularChat></ReportDetailMostPopularChat>
      <ReportDetailUserData></ReportDetailUserData>
      <ReportDetailBusData></ReportDetailBusData>
    </el-scrollbar>
  </div>
</div>
</template>
<script setup>
import ReportDetailHeader from './components/ReportDetailHeader.vue'
import ReportDetailMostPopularChat from './components/ReportDetailMostPopularChat.vue'
import ReportDetailUserData from './components/ReportDetailUserData.vue'
import ReportDetailBusData from './components/ReportDetailBusData.vue'
import axios from 'axios'
import api from '../../api/api.js'
import { useStore } from 'vuex'
import { onMounted } from 'vue'

// 라우터 설정
const nowPage = window.location.href /*router.currentRoute.path*/
const arr = nowPage.split('/reports/') // 이 함수만큼은 쓰고싶지 않았는데...2
const reportsId = arr[1]
const userId = localStorage.accountUserId

const store = useStore()
// const reportDetailGetting

onMounted(() => {
  reportDetailGetting(userId, reportsId)
  console.log(api.reports.reportsDetail(userId, reportsId))
})
// 데이터 받아오기
function reportDetailGetting(userId, reportsId) {
  axios({
    url: api.reports.reportsDetail(userId, reportsId),
    method: 'get',
  })
    .then(res => {
      console.log(res.data)
      
      store.commit("mainPage/SET_ID", res.data.id)
      // store.commit("mainPage/SET_BUS", res.data.routeNo )
      store.commit("mainPage/SET_CREATE_DAY", res.data.createDay)
      store.commit("mainPage/SET_CREATE_TIME", res.data.createTime)
      store.commit("mainPage/SET_CONTENT", res.data.content)
      store.commit("mainPage/SET_TOTAL_CHAT_COUNT", res.data.totalChatCount)
      store.commit("mainPage/SET_MY_ACCESS_HOUR", res.data.myAccessHour)
      store.commit("mainPage/SET_MY_ACCESS_MINUTE", res.data.myAccessMinute)
      store.commit("mainPage/SET_TOTAL_USER_COUNT", res.data.totalUserCount)
      store.commit("mainPage/SET_CHAT_PER_MINUTE", Math.round(parseFloat(res.data.chatPerMinute) * 1000) / 1000)
      store.commit("mainPage/SET_ACCUMULATE_USER_COUNT", res.data.accumulateUserCount)
      store.commit("mainPage/SET_PERSONAL_COUNT", res.data.personalCount)
      store.commit("mainPage/SET_BOARD_COUNT", res.data.boardCount)
      console.log(store.getters['mainPage/busNumSave'][0])
      const busNumList = store.getters['mainPage/busNumSave']
      console.log(res.data.id)
      for (let i=0; i<busNumList.length; i++) {
        if (busNumList[i] === res.data.id) {
          store.commit("mainPage/SET_BUS", busNumList[i+1])
        }
      }
    })
    .catch(err => {
      console.error(err, '낭만보고서를 가져오는 데 실패했습니다 ㅠ_ㅠ;')
    })
}


</script>
<style>
.report-bg-img {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  /* background-image: url('../../assets/report-bg.jpg'); */
  background-size: 100vw 100vh;
  filter: blur(10px);
  opacity: 0.6;
  z-index: -1;
  transform: scale(1.09);
}
.report-detail {
  height: 100vh;
  margin: 0 32px;
}
</style>