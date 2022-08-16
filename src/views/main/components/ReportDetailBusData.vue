<template>
  <div class="report-detail-bus">
    <div class="bus-withuser">
      <img src="../../../assets/report-withuser.png" alt="withUserImg" class="withuser-img">
      <div class="withuser-content">
        <div class="withuser-title">
          지금까지<br>이 버스를<br>거쳐간 승객 수
        </div>
        <div class="withuser-sub">
          500{{ store.getters.reportsDetailAccumulateUserCount }}명
        </div>
      </div>
    </div>
    <div class="bus-data-cover">
      <div class="bus-mycount">
        <img src="../../../assets/report-countuser.png" alt="mycountImg" class="withuser-img">
        <div class="withuser-title">
          내가<br>이 버스를<br>탄 횟수
        </div>
        <div class="withuser-sub">
          20{{ store.getters.reportsDetailPersonalCount }}회
        </div>
      </div>
      <div class="bus-mycount">
        <img src="../../../assets/report-countboard.png" alt="mycountImg" class="withuser-img">
        <div class="withuser-title">
          이 버스의<br>방명록<br>갯수
        </div>
        <div class="withuser-sub">
          20{{ store.getters.reportsDetailBoardCount }}개
        </div>
      </div>
    </div>
  </div>
  <hr class="report-detail-hr-small">
</template>
<script setup>
import { useStore } from 'vuex'
import { onBeforeMount } from 'vue'

const store = useStore()

</script>
<style>
.report-detail-bus {
  margin: 0 20px;
  display: flex;
  flex-direction: column;
}
.bus-withuser {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin-bottom: 40px;
}
.withuser-img {
  width: 80px;
}
.withuser-content {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.withuser-title {
  color: #FFB6B9;
  font-size: 1.5rem;
}
.withuser-sub {
  font-size: 2rem;
  font-family: BMHANNAPro;
}
.bus-data-cover {
  display: flex;
  justify-content: space-around;
}
</style>
