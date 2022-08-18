<template>
  <div class="mostlybus">
    <div class="mostlybus-title">
      <div class="mostlybus-title-text">
        마지막으로 탄 낭만버스
      </div>
      <hr class="recentlybus-title-line">
    </div>
    <div class="mostlybus-box">
      <div class="mostlybus-content">
        <span class="mostlybus-data">{{ busData.recentBus.takenTime?.split(' ')[0].slice(5, 7) }}</span> 월 <span class="mostlybus-data">{{ busData.recentBus.takenTime?.split(' ')[0].slice(8, 10)}}</span> 일에 탔던
        <span class="mostlybus-data">{{ busData.recentBus.bus?.nodeName }}</span> 지나는 <span class="mostlybus-data">{{ busData.recentBus.bus?.routeNo }}</span>번 버스에요,<br>
        방명록이 <span class="mostlybus-data">{{ busData.recentBus.countBoard }}</span>개 추가됐어요!
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const busData = ref({
  recentBus: computed(() => store.getters['mainPage/recentBus'])
})

</script>
<style>
.recentlybus-title-line {
  display: block;
  width: 35%;
  height: 1px;
  border: 0;
  border-top: 1px solid black;
}
</style>