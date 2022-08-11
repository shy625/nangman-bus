<template>
  <p>낭만보고서 ㅡㅡㅡㅡㅡㅡㅡ</p>
  <report-calender/>
  <ReportItem/>
</template>
<script setup>
  import { useStore } from 'vuex'
  import { ref, onMounted } from 'vue'
  import ReportItem from './components/ReportItem.vue';
  import ReportCalender from './components/ReportCalender.vue';
  const store = useStore()
  onMounted(() => {
  })
  const dataNameExample = ref({
    
  })
  const fName = function (event) {
    
  }
</script>