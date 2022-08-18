<template>
  <div class="mostlybus">
    <div class="mostlybus-title">
      <div class="mostlybus-title-text">
        내가 탄 낭만버스
      </div>
      <hr class="mostlybus-title-line">
    </div>
    <div class="mostlybus-box" v-if="busData.top3.length">
      <div v-for="(bus, idx) in busData.top3" :key="idx">
        <div class="mostlybus-content">
          지금까지 <span class="mostlybus-data">{{ busData.top3Count[idx]}}</span> 탔던 <span class="mostlybus-data">{{ bus.routeNo }}</span>번 버스는 <span class="mostlybus-data">{{ bus.nodeName }}</span>에 있어요!
        </div>
      </div>
      <!-- <div class="mostlybus-content">
        지금 <span class="mostlybus-data">양재IC</span> 지나는 <span class="mostlybus-data">5100번</span> 버스는요,<br>
        <span class="mostlybus-status">아주 시끌벅적하네요!</span>
      </div>
      <div class="mostlybus-content">
        지금 <span class="mostlybus-data">양재IC</span> 지나는 <span class="mostlybus-data">5100번</span> 버스는요,<br>
        <span class="mostlybus-status">아주 시끌벅적하네요!</span>
      </div> -->
    </div>
  </div>
</template>
<script setup>
import { ref, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const busData = ref({
  top3: computed(() => store.getters['mainPage/top3']),
  top3Count: computed(() => store.getters['mainPage/top3Count'])
})
</script>
<style>
.mostlybus {
  margin: 52px 0;
}
.mostlybus-title {
  margin-bottom: 24px;
  display: flex;
  align-items: center;
}
.mostlybus-title-text {
  font-family: BMHANNAPro;
  font-size: 1.3rem;
}
.mostlybus-title-line {
  display: block;
  width: 50%;
  height: 1px;
  border: 0;
  border-top: 1px solid black;
}
.mostlybus-box {
  border-top: 4px solid #FFD96A;
}
.mostlybus-content {
  font-family: Pretendard;
  padding: 16px;
  border-bottom: 4px solid #FFD96A;
  font-size: 1rem;
}
.mostlybus-data {
  font-family: BMHANNAPro;
  font-size: 1.2rem;
  color: #F34949;
}
</style>