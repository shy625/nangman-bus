<template>
  <div class="detail-header">
    <router-link :to="{ name: 'reports' }" class="detail-header-back">
      &lt;
    </router-link>
    <div class="detail-header-cover">
      <div class="detail-header-title">
        <span class="detail-header-number">{{ busNum }}번</span> 낭만버스
      </div>
      <div class="detail-header-date">
        {{ day.substr(0, 4) }}년 
        {{ day.substr(5, 2)>9 ? day.substr(5, 2) : day.substr(5, 2)%10 }}월 
        {{ day.substr(8, 2) }}일 
        {{ time.substr(0, 2) }}시 
        {{ time.substr(3, 2) }}분
      </div>
    </div>
  </div>
  <hr class="detail-header-hr">
</template>
<script setup>
import { useStore } from 'vuex'
import { onBeforeMount } from 'vue';

onBeforeMount(() => {
  const store = useStore()
  const busNum = store.getters.reportsDetailBus
  const day = store.getters.reportsDetailCreateDay
  const time = store.getters.reportsDetailCreateTime
  console.log(busNum)
})

</script>
<style>
.detail-header {
  display: flex;
  padding-top: 20px;
  margin: 0px ;
  position: relative;
  justify-content: center;
  align-items: center;
}
.detail-header-back {
  position: absolute;
  left: 12px;
  text-decoration: none;
  padding: 0px 8px;
  font-size: 1.5rem;
  color: black;
}
.detail-header-cover {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.detail-header-title {
  font-family: BMHANNAPro;
  font-size: 1.5rem;
  color: black;
  margin-bottom: 12px;
}
.detail-header-number {
  color: #F34949;
}
.detail-header-hr {
  margin: 40px 0;
  border: none;
  background-color: black;
  height: 2px;
}
</style>