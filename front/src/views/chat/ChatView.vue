<template>
  <div class="chat-body">
    <header>
      <div class="chat-header">
        <router-link :to="{ name: 'main' }" class="chat-header-back">
          &lt;
        </router-link>
        <div class="chat-header-title">5006번 버스</div>
      </div>
      <hr class="chat-header-hr">
    </header>
  
    <el-carousel 
    indicator-position="none" 
    :initial-index="1"
    arrow="always"
    :autoplay="false"
    :loop="false"
    >
      <el-carousel-item>
        <BoardList></BoardList>
      </el-carousel-item>
      <el-carousel-item>
        <ChatRoom></ChatRoom>
      </el-carousel-item>
      <el-carousel-item>
        <UserList></UserList>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script setup>
import BoardList from './components/BoardList/BoardList.vue'
import ChatRoom from './components/CharRoom/ChatRoom.vue'
import UserList from './components/UserList/UserList.vue'
import { ref, onMounted, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const chatViewData = ref({
  busId: computed(() => store.getters['chatStore/busId'])
})

onMounted(() => {
  const leftBtn = document.querySelector('.el-carousel__arrow--left')
  leftBtn.addEventListener('click', () => {
    store.dispatch('chatStore/fetchBoards', chatViewData.value.busId)
  })
})

function getLocation() {
  if (navigator.geolocation) { // GPS를 지원하면
    navigator.geolocation.getCurrentPosition(position => {
      console.log('위도: '
       + position.coords.latitude + ' / 경도: ' + position.coords.longitude)
    }, error => {
      console.error(error)
    }, {
      enableHighAccuracy: false,
      maximumAge: 0,
      timeout: Infinity
    })
  } else {
    alert('GPS를 지원하지 않습니다.')
  }
}
getLocation()
// 위치바뀌면 감지
// let watchId = navigator.geolocation.watchPosition(function(position) {
//   console.log(position.coords)
// })
</script>

<style>
.el-button {
  font-family: BMHANNAAir;
}
.chat-body {
  display: flex;
  flex-direction: column;
  height: 100vh;
}
.el-carousel {
  height: 100%;
}
.el-carousel__container {
  height: 100% !important;
}
.el-carousel__item {
  display: flex !important;
  flex-direction: column;
}
.el-carousel__arrow {
  top: 80% !important;
  z-index: 1;
  opacity: 0.3;
  background-color: #FFD96A !important;
  box-shadow: 1.5px 1.5px 4px rgba(0, 0, 0, 0.25);
}
.el-carousel__arrow:hover {
  opacity: 1;
}
.chat-header {
  display: flex;
  padding: 20px 0px;
  margin: 0px 20px;
  position: relative;
  justify-content: center;
  font-size: 1.5rem;
}
.chat-header-back {
  position: absolute;
  left: 12px;
  text-decoration: none;
  padding: 0px 8px;
}
.chat-header-title {
  color: #F34949;
}
.chat-header-hr {
  margin: 0px 32px;
}

</style>