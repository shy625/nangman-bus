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
import { onMounted, ref } from 'vue'
import axios from 'axios'
import api from '../../api/api.js'
import { useStore } from 'vuex'

// 데이터 정의
const report = ref({
  reportDetail: {}
})

// vuex 선언
const store = useStore()

// 더미데이터
report.value.reportDetail = {
  id: 1, //보고서id
  bus: '1234', //버스번호
  createDay: '2022-08-11', //보고서작성날짜
  createTime: '22:24:00', //보고서작성시간
  content: '저 오늘 첫 출근이에요 힘내라고 빔 좀 쏴주세요 비이이이임~~!!!!', //좋아요많이받은채팅
  totalChatCount: 500, //총채팅수
  myAccessHour: 1, //접속시간
  myAccessMinute: 15, //접속분
  totalUserCount: 40, //이용유저수
  chatPerMinute: 10, //채팅방화력(분당)
  accumulateUserCount: 500, //여태까지의 유저수
  personalCount: 20, //버스이용수
  boardCount: 20, //버스방명록수
}

// 라우터
const nowPage = window.location.href /*router.currentRoute.path*/
const arr = nowPage.split('/reports/') // 이 함수만큼은 쓰고싶지 않았는데...2
const reportsId = arr[1]
console.log(reportsId)

// 데이터 받아오기
function reportDetailGetting(reportsId) {
  axios({
    url: api.reports.reportsDetail(reportsId),
    method: 'get',
  })
    .then(res => {
      // console.log(res.data)
      report.value.reportDetail = res.data
      store.commit("SET_ID", report.value.reportDetail.id)
      store.commit("SET_BUS", report.value.reportDetail.bus)
      store.commit("SET_CREATE_DAY", report.value.reportDetail.createDay)
      store.commit("SET_CREATE_TIME", report.value.reportDetail.createTime)
      store.commit("SET_CONTENT", report.value.reportDetail.content)
      store.commit("SET_TOTAL_CHAT_COUNT", report.value.reportDetail.totalChatCount)
      store.commit("SET_MY_ACCESS_HOUR", report.value.reportDetail.myAccessHour)
      store.commit("SET_MY_ACCESS_MINUTE", report.value.reportDetail.myAccessMinute)
      store.commit("SET_TOTAL_USER_COUNT", report.value.reportDetail.totalUserCount)
      store.commit("SET_CHAT_PER_MINUTE", report.value.reportDetail.chatPerMinute)
      store.commit("SET_ACCUMULATE_USER_COUNT", report.value.reportDetail.accumulateUserCount)
      store.commit("SET_PERSONAL_COUNT", report.value.reportDetail.personalCount)
      store.commit("SET_BOARD_COUNT", report.value.reportDetail.boardCount)
    })
    .catch(err => {
      console.error(err, '낭만보고서를 가져오는 데 실패했습니다 ㅠ_ㅠ;')
    })
}

onMounted(() => {
  reportDetailGetting(reportsId)
})

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