<template>
  <div class="mostlybus">
    <div class="mostlybus-title">
      <div class="mostlybus-title-text">
        도로 위 낭만버스
      </div>
      <hr class="mostlybus-title-line">
    </div>
    <div v-for="(bus, index) in buses.randomBus" :key="index">
      <div class="mostlybus-content">
        지금 <span class="mostlybus-data">{{bus.nodeName}}</span> 지나는 <span class="mostlybus-data">{{bus.routeNumber}}번</span> 버스는요,<br>
        <span v-if="bus.type==0" >조금 <span>어색</span>해요!</span>
        <span v-else > <span>시끌벅적</span>해요!</span>
      </div>
    </div>
  </div>
</template>
<script setup>
import axios from "axios"
import { ref, onMounted } from 'vue'
import api from "../../../api/api"
const buses = ref({
  randomBus: []
})

onMounted(() => {
  getRandomBus()
})

function getRandomBus() {
  axios({
    url: api.main.getrandombus(),
    method: "get",
  })
    .then(res => {
      buses.value.randomBus = res.data;
    })
    .catch(err => {
    console.log("error")
  })
}

</script>
<style>
  
</style>