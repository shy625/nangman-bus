<template>
  <div class="detail-header">
    <router-link :to="{ name: 'reports' }" class="detail-header-back">
      &lt;
    </router-link>
    <div class="detail-header-cover">
      <div class="detail-header-title">
        <span class="detail-header-number">{{ detailHeader.busNum }}번</span> 낭만버스
      </div>
      <div class="detail-header-date">
        {{ detailHeader.day.substr(0, 4) }}년 
        {{ detailHeader.day.substr(5, 2)>9 ? detailHeader.day.substr(5, 2) : detailHeader.day.substr(6, 1) }}월 
        {{ detailHeader.day.substr(8, 2) }}일 
        {{ detailHeader.time.substr(0, 2) }}시 
        {{ detailHeader.time.substr(3, 2) }}분
      </div>
    </div>
  </div>
  <hr class="detail-header-hr">
</template>
<script setup>
import { useStore } from 'vuex'
import { computed, ref } from 'vue';

  const store = useStore()
  // const busNum = store.getters.reportsDetailBus
  // const busNum = computed(() => store.getters['mainPage/reportsDetailBus'])
  // const day = computed(() => store.getters['mainPage/reportsDetailCreateDay'])
  // const time = computed(() => store.getters['mainPage/reportsDetailCreateTime'])

  const detailHeader = ref({
    busNum: computed(() => store.getters['mainPage/reportsDetailBus']),
    day: computed(() => store.getters['mainPage/reportsDetailCreateDay']),
    time: computed(() => store.getters['mainPage/reportsDetailCreateTime']),
  })
  // console.log(busNum)
// onBeforeMount(() => {
// })
// 만약 store.getters.뭐시기 가 안되면
// store.getters['mainPage/뭐시기'] 로 접근해야 함

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