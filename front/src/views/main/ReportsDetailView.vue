<template>
  <div class="report-bg-img"></div>
  <div class="report-detail">
    <el-scrollbar>
      <ReportDetailHeader></ReportDetailHeader>
      <ReportDetailMostPopularChat></ReportDetailMostPopularChat>
      <ReportDetailUserData></ReportDetailUserData>
      <ReportDetailBusData></ReportDetailBusData>
    </el-scrollbar>
  </div>
</template>
<script setup>
import ReportDetailHeader from './components/ReportDetailHeader.vue'
import ReportDetailMostPopularChat from './components/ReportDetailMostPopularChat.vue'
import ReportDetailUserData from './components/ReportDetailUserData.vue'
import ReportDetailBusData from './components/ReportDetailBusData.vue'
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