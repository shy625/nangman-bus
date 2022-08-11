<template>
  <Header/>
    <div class="reportview-reportlist-cover">
      <div class="reportview-reportlist-title">
        <div class="reportview-reportlist-title-text">
        낭만보고서
        </div>
        <hr class="reportview-reportlist-title-line">
      </div>
      <ReportItem :reportList="reportList" />
      리포트 아이템을 하나하나 보여주는데 v-for? 해야될 것 같고
      리포트아이템 컴포넌트 내부에서 자기자신 클릭 시 이동할 라우터에 던져줄 리포트id 필요

    </div>
  <Footer/>
</template>
<script setup>
  import { useStore } from 'vuex'
  import { ref, onMounted } from 'vue'
  import ReportItem from './components/ReportItem.vue'
  import Header from '../components/Header.vue'
  import Footer from '../components/Footer.vue'
  // onMounted(() => {
  // })
  // const dataNameExample = ref({
    
  // })
  // const fName = function (event) {
    
  // }
  const reportList = ref([
    {
      id: '1',
      time: '2022-08-01T07:50:00',
      bus: '5100',
      chatting: '저 오늘 처음 출근...',
    },
  ])
  // 리포트 목록 가져오기
  const reportListGetting = function() {
    axios({
      url: api.reports.reportsList(),
      method: 'get',
    })
      .then(res => {
        console.log(res.data) // res 찍어볼것
        reportList = res.data
      })
      .catch(err => {
        console.error('낭만보고서가 없거나 불러오는 데 실패했습니다.')
      })
  }

  const props = {
    
  }

  // 가져올 정보가 어떤게 있냐? 정직하게 하자!
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
</style>