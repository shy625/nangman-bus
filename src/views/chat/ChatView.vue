<template>
  <div class="chat-body">
    <header>
      <div class="chat-header">
        <router-link :to="{ name: 'main' }" class="chat-header-back">
          &lt;
        </router-link>
        <div class="chat-header-title">{{ chatViewData.busNum }}번<span style="color:black"> 낭만버스</span></div>
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
  busId: computed(() => store.getters['chatStore/busId']),
  busNum: computed(() => store.getters['chatStore/busNum']),
  sessionId: computed(() => store.getters['chatStore/sessionId']),
  lat: 0,
  lng: 0
})
navigator.geolocation.watchPosition(function(position) {
  // console.log(position.coords.latitude, position.coords.longitude)
  chatViewData.value.lat = position.coords.latitude
  chatViewData.value.lng = position.coords.longitude
})

onMounted(() => {
  navigator.geolocation.getCurrentPosition(position => {
    console.log(position)
    // 채팅창에서 새로고침할 경우, 채팅방 정보 다시 요청
    const payload = {
      room: { sessionId: chatViewData.value.sessionId },
      // lat: position.coords.latitude,
      // lng: position.coords.longitude,
      lat: 37.49797,
      lng: 127.02763,
    }
    console.log(payload)
    store.dispatch('chatStore/fetchSessionId', payload)
  })

  const leftBtn = document.querySelector('.el-carousel__arrow--left')
  leftBtn.addEventListener('click', () => {
    store.dispatch('chatStore/fetchBoards', chatViewData.value.busId)
  })
})
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
  top: 75% !important;
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
  font-family: BMHANNAPro;
}
.chat-header-hr {
  margin: 0px 32px;
}

</style>