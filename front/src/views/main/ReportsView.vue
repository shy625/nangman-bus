<template>
  <div>
    <Header/>
    <div class="reportview-reportlist-cover">
      <div class="reportview-reportlist-title">
        <div class="reportview-reportlist-title-text">
        낭만보고서
        </div>
        <hr class="reportview-reportlist-title-line">
      </div>
      <div v-if="reportsData"><!-- 리포트데이터가 있으면 보여줌 -->
        <div v-for="report in reportsData.reportList" :key="report.time"><!-- 시간 순서로 리포트아이템 출력 -->
          <div class="reportview-preview">
            <div class="reportview-date">
              {{ report.time.substr(5, 7) }}월
              {{ report.time.substr(8, 10) }}일
            </div>
            <div class="reportview-time">
            {{ report.time.substr(11, 13) }}시
            {{ report.time.substr(14, 16) }}분<!-- 시간 출력 -->
            </div>
          </div>
          <div class="reportview-busnum">
            {{ report.bus }}번<!-- 버스 번호 -->
          </div>
          <div class="reportview-comment">
            "{{ report.chatting.substr(0, 12) }}..."<!-- 가장 좋아요를 많이 받은 채팅(12자만 출력) -->
          </div>
        </div>
      </div>
    </div>
    <Footer/>
  </div>
</template>
<script setup>
  import { useStore } from 'vuex'
  import { ref, onMounted } from 'vue'
  import Header from '../components/Header.vue'
  import Footer from '../components/Footer.vue'
  import axios from 'axios'
  import api from '../../api/api.js'

  const reportsData = ref({
    reportList: []
  })

  onMounted(() => {
    reportListGetting()
  })

  // 리포트 목록 가져오기
  function reportListGetting() {
    axios({
      url: api.reports.reportsList(),
      method: 'get',
    })
      .then(res => {
        console.log(res.data)
        reportsData.value.reportList = res.data
      })
      .catch(err => {
        console.error('낭만보고서가 없거나 가져오는 데 실패했어요...')
      })
  }

  // 가져올 정보가 어떤게 있냐?
  // [
  //   {
  //     id
  //     날짜 + 시간,
  //     버스 번호,
  //     가장 많은 사랑을 받은 채팅,
  //   },
  //   {
  //     id
  //     날짜 + 시간,
  //     버스 번호, (reports.room.bus.no)
  //     가장 많은 사랑을 받은 채팅,
  //   },
  //   ...
  // ]
</script>

<style>
.reportview-reportlist-cover {
  position: fixed;
  top: 0;
  width: 100%;
  height: 100vh;
  /* overflow: hidden; */
  /* transform: scale(0); */
  /* z-index: 1; */
}
.reportview-reportlist-title {
  display: block;
  margin: 71.75px 0 0 32px;
}

.reportview-reportlist-title-text {
  font-size: 1.2rem;
}

.reportview-reportlist-title-line {
  display: block;
  width: 70%;
  height: 1px;
  border: 0;
  border-top: 1px solid black;
}
/* 리포트 미리보기 */
.reportview-preview {
  color: black;
}
.reportview-date {
  color: black;
}
.reportview-time {
  color: black;
}
.reportview-busnum {
  color: black;
}
.reportview-comment {
  color: black;
}
</style>