<template>
  <div class="detail-header">
    <router-link :to="{ name: 'reports' }" class="detail-header-back">
      &lt;
    </router-link>
    <div class="detail-header-cover">
      <div class="detail-header-title">
        <span class="detail-header-number">5006번</span> 낭만버스
      </div>
      <div class="detail-header-date">
        2022년 8월 11일 오후 10시 24분
      </div>
    </div>
  </div>
  <hr class="detail-header-hr">
</template>
<script setup>

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