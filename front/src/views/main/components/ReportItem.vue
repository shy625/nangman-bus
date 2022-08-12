<template>
  {{ reportList?.value[0].date }}
  {{ reportList?.value[0].bus }}
  {{ reportList?.value[0].bestMessage }}
  <!-- reportList에 값이 없어서 오류 발생함. 
  서버와 통신해서 오류 해결할 것-->
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