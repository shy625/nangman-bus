<template>
  <div class="footer">
    <router-link :to="{ name: 'main' }" class="footer-main-btn">
      <img src="../../assets/main.png" alt="main" class="footer-main">
      <div>홈</div>
    </router-link>
    <div class="chatlist-btn" @click="clickChatList">
      <img src="../../assets/bus-unclicked.png" alt="bus-unclicked" class="footer-chatlist">
    </div>
    <router-link :to="{ name: 'reports'}" class="footer-reports-btn">
      <img src="../../assets/reports.png" alt="reports" class="footer-reports">
      <div>낭만보고서</div>
    </router-link>
  </div>
  <ChatList></ChatList>
</template>
<script setup>
import ChatList from './ChatList.vue'

// 풋터 버튼 클릭할 때 색 바꾸기
// 실패... 시간만 날림 ㅜ
// const footerData = ref({
//   nowUrl: window.location.href,
// })
// onUpdated(() => {
//   console.log(footerData.value.nowUrl)
// }),
// onMounted(() => {
//   const footerReportsBtn = document.querySelector('.footer-reports-btn')
//   const footerMainBtn = document.querySelector('.footer-main-btn')
//   // console.log(footerData.value.nowUrl[footerData.value.nowUrl.length-1] === '/')
//   if (footerData.value.nowUrl[footerData.value.nowUrl.length-1] === '/') {
//     footerMainBtn.classList.add('footer-btn-active')
//     footerReportsBtn.classList.remove('footer-btn-active')
//   } else {
//     footerMainBtn.classList.remove('footer-btn-active')
//     footerReportsBtn.classList.add('footer-btn-active')
//   }
// })
// // 홈, 리포트 버튼
// const clickFooterBtn = e => {
//   const footerMainBtn = document.querySelector('.footer-main-btn')
//   const footerReportsBtn = document.querySelector('.footer-reports-btn')
//   if (e.currentTarget.classList.contains('footer-main-btn')) {
//     footerMainBtn.style.filter = 'none'
//     footerReportsBtn.style.filter = 'opacity(35%)'
//     // console.log(true)
//     // console.log(footerMainBtn.style)
//     // console.log(footerReportsBtn)
//   } else {
//     footerMainBtn.style.filter = 'opacity(35%)'
//     footerReportsBtn.style.filter = 'none'
//     // console.log(false)
//     // console.log(footerMainBtn)
//     // console.log(footerReportsBtn)
//   }
// }

// 채팅리스트 버튼
const clickChatList = () => {
  const chatList = document.querySelector('.chatlist-cover')
  if (chatList.classList.contains('chatlist-in')) {
    chatList.classList.remove('chatlist-in')
    chatList.classList.add('chatlist-out')
    setTimeout(() => {
      chatList.classList.remove('chatlist-active')
    }, 600);
  } else {
    chatList.classList.remove('chatlist-out')
    chatList.classList.add('chatlist-active')
    chatList.classList.add('chatlist-in')
  }
  const footerChatList = document.querySelector('.footer-chatlist')
  if (chatList.classList.contains('chatlist-in')) {
    footerChatList.src = `${require('../../assets/bus-clicked.png')}`
    } else {
    footerChatList.src = `${require('../../assets/bus-unclicked.png')}`
  }
}
</script>
<style>
.chatlist-active {
  transform: scale(1);
}
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
  background-color: white;
  z-index: 2;
}
.footer-main-btn {
  width: 65.188px;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-decoration: none;
  color: black;
  font-size: 0.8rem;
  /* filter: opacity(35%); */
}
/* .footer-btn-active {
  filter: none;
} */
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
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
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
  /* filter: opacity(35%); */
}
.footer-reports {
  height: 32px;
}
</style>