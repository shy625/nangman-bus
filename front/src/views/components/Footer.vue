<template>
  <div class="footer">
    <router-link :to="{ name: 'main' }" class="footer-main-btn" @click="clickMain">
      <img src="../../assets/main.png" alt="main" class="footer-main">
      <div>홈</div>
    </router-link>
    <div class="chatlist-btn">
      <img src="../../assets/bus-unclicked.png" alt="bus-unclicked" class="footer-chatlist">
    </div>
    <router-link to="{ name: 'reports'}" class="footer-reports-btn">
      <img src="../../assets/reports.png" alt="reports" class="footer-reports">
      <div>낭만보고서</div>
    </router-link>
  </div>
</template>
<script setup>
const clickMain = () => {
  
}
</script>
<style>
.footer {
  position: fixed;
  width: 100%;
  bottom: 0;
  left: 0;
  display: flex;
  justify-content:space-around;
  padding: 4px 0 4px 0;
  border-top: 2px solid #999999;
  border-radius: 6px 6px 0 0;
}
.footer-main-btn {
  width: 65.188px;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-decoration: none;
  color: black;
  font-size: 0.8rem;
  /* filter: opacity(50%) */
}
.footer-main {
  height: 32px;
}
.chatlist-btn {
  height: 24px;
  padding: 10px;
  background-color: white;
  border: 3px solid #F34949;
  border-radius: 50%;
  position: fixed;
  left: 50%;
  transform: translateX(-50%);
  bottom: 16px;
}
.footer-chatlist {
  height: 24px;
}
.footer-reports-btn {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-decoration: none;
  color: black;
  font-size: 0.8rem;
  filter: opacity(35%);
}
.footer-reports {
  height: 32px;
}
</style>