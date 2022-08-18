<template>
  <div class="mostlybus">
    <div class="mostlybus-title">
      <div class="mostlybus-title-text">
        도로 위 낭만버스
      </div>
      <hr class="mostlybus-title-line">
    </div>
    <div class="mostlybus-box">
      <div v-if="!!!buses.randomBus.length" class="mostlybus-content">
        현재 운행중인 낭만버스가 없어요!
      </div>
      <div v-for="(bus, index) in buses.randomBus" :key="index">
        <div class="mostlybus-content">
          지금 <span class="mostlybus-data">{{bus.nodeName}}</span> 지나는 <span class="mostlybus-data">{{bus.routeNumber}}번</span> 버스는요,<br>
          <span v-if="bus.type==0" >조금 <span class="mostlybus-data">어색</span>해요!</span>
          <span v-else > <span class="mostlybus-data">시끌벅적</span>해요!</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
// import axios from "axios"
import { ref, onMounted, computed } from 'vue'
// import api from "../../../api/api"
import { useStore } from 'vuex'

const store = useStore()
const buses = ref({
  randomBus: computed(() => store.getters['mainPage/randomBus'])
})
onMounted(() => {
  // getRandomBus()
  store.dispatch('mainPage/fetchRandomBus')
})

// function getRandomBus() {
//   axios({
//     url: api.main.getrandombus(),
//     method: "get",
//   })
//     .then(res => {
//       buses.value.randomBus = res.data;
//     })
//     .catch(err => {
//     console.log("error")
//   })
// }

</script>
<style>
  
</style>