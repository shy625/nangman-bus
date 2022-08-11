<template>
  {{ reportList.value[0].date }}
  {{ reportList.value[0].bus }}
  {{ reportList.value[0].bestMessage }}
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