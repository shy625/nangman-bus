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
// import axios from 'axios'
// import api from '../../api/api.js'
import { useStore } from 'vuex'

// 라우터 설정
const nowPage = window.location.href /*router.currentRoute.path*/
const arr = nowPage.split('/reports/') // 이 함수만큼은 쓰고싶지 않았는데...2
const reportsId = arr[1]
console.log(reportsId)

const store = useStore()

// 여기가 실제 데이터 받아오는 부분입니다.
// 비동기 처리 해결 못했습니다..
// reportDetailGetting(reportsId)
// 데이터 받아오기
// function reportDetailGetting(reportsId) {
//   axios({
//     url: api.reports.reportsDetail(reportsId),
//     method: 'get',
//   })
//     .then(res => {
//       // console.log(res.data)
//       store.commit("SET_ID", res.data.id)
//       store.commit("SET_BUS", res.data.bus)
//       store.commit("SET_CREATE_DAY", res.data.createDay)
//       store.commit("SET_CREATE_TIME", res.data.createTime)
//       store.commit("SET_CONTENT", res.data.content)
//       store.commit("SET_TOTAL_CHAT_COUNT", res.data.totalChatCount)
//       store.commit("SET_MY_ACCESS_HOUR", res.data.myAccessHour)
//       store.commit("SET_MY_ACCESS_MINUTE", res.data.myAccessMinute)
//       store.commit("SET_TOTAL_USER_COUNT", res.data.totalUserCount)
//       store.commit("SET_CHAT_PER_MINUTE", res.data.chatPerMinute)
//       store.commit("SET_ACCUMULATE_USER_COUNT", res.data.accumulateUserCount)
//       store.commit("SET_PERSONAL_COUNT", res.data.personalCount)
//       store.commit("SET_BOARD_COUNT", res.data.boardCount)
//     })
//     .catch(err => {
//       console.error(err, '낭만보고서를 가져오는 데 실패했습니다 ㅠ_ㅠ;')
//     })
// }


// 더미데이터 입니다.
// 이 부분 주석처리 해주세요
const res = {
  data: {
    id: 1, //보고서id
    bus: '1234', //버스번호
    createDay: '2022-08-11', //보고서작성날짜
    createTime: '22:24:00', //보고서작성시간
    content: '저 오늘 첫 출근이에요 힘내라고 빔 좀 쏴주세요 비이이이임~~!!!!', //좋아요많이받은채팅
    totalChatCount: 700, //총채팅수
    myAccessHour: 1, //접속시간
    myAccessMinute: 15, //접속분
    totalUserCount: 40, //이용유저수
    chatPerMinute: 10, //채팅방화력(분당)
    accumulateUserCount: 500, //여태까지의 유저수
    personalCount: 20, //버스이용수
    boardCount: 20, //버스방명록수
  }
}
store.commit("SET_ID", res.data.id)
store.commit("SET_BUS", res.data.bus)
store.commit("SET_CREATE_DAY", res.data.createDay)
store.commit("SET_CREATE_TIME", res.data.createTime)
store.commit("SET_CONTENT", res.data.content)
store.commit("SET_TOTAL_CHAT_COUNT", res.data.totalChatCount)
store.commit("SET_MY_ACCESS_HOUR", res.data.myAccessHour)
store.commit("SET_MY_ACCESS_MINUTE", res.data.myAccessMinute)
store.commit("SET_TOTAL_USER_COUNT", res.data.totalUserCount)
store.commit("SET_CHAT_PER_MINUTE", res.data.chatPerMinute)
store.commit("SET_ACCUMULATE_USER_COUNT", res.data.accumulateUserCount)
store.commit("SET_PERSONAL_COUNT", res.data.personalCount)
store.commit("SET_BOARD_COUNT", res.data.boardCount)
// 여기까지 더미데이터입니다.




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