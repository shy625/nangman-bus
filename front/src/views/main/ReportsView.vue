<template>
  <Header/>
  <div class="reportview-reportlist-title">
    <div class="reportview-reportlist-title-text">
    낭만보고서
    </div>
    <hr class="reportview-reportlist-title-line">
  </div>
  
  <ReportCalender/>
  <ReportItem/>
  <Footer/>
</template>
<script setup>
  import { useStore } from 'vuex'
  import { ref, onMounted } from 'vue'
  import ReportItem from './components/ReportItem.vue'
  import ReportCalender from './components/ReportCalender.vue'
  import Header from '../components/Header.vue'
  import Footer from '../components/Footer.vue'
  // import ReportItem from '../components/ReportItem.vue'/
  const store = useStore()
  onMounted(() => {
  })
  const dataNameExample = ref({
    
  })
  const fName = function (event) {
    
  }
</script>

<style>
.reportview-reportlist-title {
  display: block;
  margin: 71.75px 0 0 0;
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