<template>
  <div class="report-item-report-box">
    <router-link to="{ name: 'reportDetail', params: { userid: reportList.value[0].userid } }">
      <div class="report-item-date">
        {{ reportList?.value[0].date }} <p>8월 11일</p> <p>오후 11시 33분</p>
      </div>
      <div class="report-item-bus">
        {{ reportList?.value[0].bus }} 5007번
      </div>
      <div class="report-item-best-message">
        {{ reportList?.value[0].bestMessage }} 저 오늘 처음 출근...
      </div>
    <!-- reportList에 값이 없어서 오류 발생함. 
    서버와 통신해서 오류 해결할 것-->
    </router-link>
  </div>
</template>
<script>
  import { useStore } from 'vuex'
  import { ref, onMounted } from 'vue'
  import axios from 'axios';
  // import reportListGetting from '../ReportsView.vue'
  // const reportItem = ref()
  export default {
    props: [""],
    setup(props) {
      console.log(props.reportList)
    }
  }
</script>
<style>
.report-item-report-box {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 7px 16px;
  
}
.report-item-date {
  position: absolute;
  top: 200px;

  font-family: BM HANNA Air;
  font-style: normal;
  font-weight: 400;
  font-size: 0.7rem;
  line-height: 150%;
}
.report-item-bus {
  position: absolute;
  top: 260px;

  font-family: BM HANNA Pro;
  font-style: normal;
  font-weight: 400;
  font-size: 1.9rem;
  /* font-height: 150%; */
}
.report-item-best-message {
  position: absolute;
  top: 350px;

  font-family: BM HANNA Air;
  font-style: normal;
  font-weight: 400;
  font-size: 1.2rem;
  line-height: 150%;
}
</style>