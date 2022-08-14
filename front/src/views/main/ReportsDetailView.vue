<template>
<div>
  <div class="report-bg-img"></div>
  <div class="report-detail">
    <el-scrollbar>
      헤더: <ReportDetailHeader></ReportDetailHeader>
      인기 채팅: <ReportDetailMostPopularChat></ReportDetailMostPopularChat>
      유저데이터: <ReportDetailUserData></ReportDetailUserData>
      버스데이터: <ReportDetailBusData></ReportDetailBusData>
    </el-scrollbar>
  </div>
</div>
</template>
<script setup>
import ReportDetailHeader from './components/ReportDetailHeader.vue'
import ReportDetailMostPopularChat from './components/ReportDetailMostPopularChat.vue'
import ReportDetailUserData from './components/ReportDetailUserData.vue'
import ReportDetailBusData from './components/ReportDetailBusData.vue'
import { ref } from 'vue'

// 데이터 정의
const report = ref({
  reportDetail: {

  }
})

// 데이터 받아오기
// function reportDetailGetting(userId) {
//   axios({
//     url: api.reports.
//   })
// }

// 라우터 prop 테스트
// const userId = ref({ id: this.$route.params.id })
// console.log(userId.value.id)

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