<template>
  <div class="chatlist-cover">
    <div class="chatlist">
      <div class="chatlist-title">
        <div class="chatlist-title-text">
          버스 목록
        </div>
        <hr class="chatlist-title-line">
        <img src="../../assets/refresh.png" alt="refresh" class="chatlist-title-refresh" @click="getRooms(data.lat, data.lng)">
        <!-- 37.73382, 126.7316 -->
      </div>
      <div class="buslist">
        <div v-if="!!!data.rooms.length" class="buslist-notfound">
          <h1>주변에<br>낭만버스가<br>보이지 않아요</h1>
          <p style="font-size: 1.3rem">상단의 새로고침 버튼을 눌러보세요!</p>
        </div>
        <div v-for="room in data.rooms" :key="room.sessionId">
          <div class="bus-cover">
            <img src="../../assets/bus-unclicked.png" class="bus-icon">
            <div class="bus-detail">
              <div class="bus-number-dist">
                <div class="bus-number">
                  {{room.routeId}}번
                </div>
                <div class="bus-dist">
                  {{room.distance}}m
                </div>
              </div>
              <div class="bus-comment">
                <span class="bus-comment-count">{{room.inUsers}}</span>명 
                <span v-if="room.type==0" class="bus-comment-mood">조금 <span class="bus-comment-mood">어색</span>해요!</span>
                <span v-else class="bus-comment-mood"> <span class="bus-comment-mood">시끌벅적</span>해요!</span>
              </div>
            </div>
            <div class="bus-getin" @click="clickBusGetIn(room)">
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, computed } from 'vue'
import { useStore } from "vuex"

const store = useStore()
const data = ref({
  rooms: computed(() => store.getters['chatStore/rooms']),
  lat: computed(() => store.getters['chatStore/lat']),
  lng: computed(() => store.getters['chatStore/lng']),
})

// 37.49797, 127.02763
function getRooms(lat, lng) {
  const chatListRefresh = document.querySelector('.chatlist-title-refresh')
  chatListRefresh.classList.add('refresh-rotate')
  chatListRefresh.addEventListener('animationend', () => {
    chatListRefresh.classList.remove('refresh-rotate')
  })

  const busList = document.querySelector('.buslist')
  busList.classList.remove('buslist-in')
  busList.classList.add('buslist-out')
  busList.addEventListener('animationend', () => {
    const data = {
      lat: lat,
      lng: lng,
    }
    busList.classList.remove('buslist-out')
    busList.classList.add('buslist-in')
    store.dispatch('chatStore/fetchRooms', data)
  })
}

const clickBusGetIn = room => {
  console.log(room)
  const geoData = {
    room: room,
    lat: data.value.lat,
    lng: data.value.lng,
    // 강남역
    // lat: 37.496486063,
    // lng: 127.028361548,
    // 3100
    // lat: 37.73382, 
    // lng: 126.7316,
    // lat: 37.26633,
    // lng: 127.08227
  }
  store.dispatch('chatStore/fetctSessionIdFromChatList', geoData)
}

</script>
<style>
.chatlist-cover {
  position: fixed;
  top: 0;
  width: 100%;
  height: 100vh;
  overflow: hidden;
  transform: scale(0);
  z-index: 1;
}
.chatlist {
  background-color: white;
  margin: 71.150px 0 45px 0;
  padding: 16px 32px;
  border: 3px solid #F34949;
  border-bottom: none;
  border-radius: 5px;
  height: 83vh;
  overflow: scroll;
}
.chatlist-title {
  display: flex;
  align-items: center;
}
.chatlist-title-text {
  font-family: BMHANNAPro;
  font-size: 1.3rem;
}
.chatlist-title-line {
  display: block;
  width: 50%;
  height: 1px;
  border: 0;
  border-top: 1px solid black;
}
.chatlist-title-refresh {
  width: 30px;
  /* position: fixed; */
  /* left: 50%; */
  /* transform: translateX(-50%); */
  /* top: 10%; */
}
.buslist {
  margin-top: 32px;
  overflow: scroll;
}
.bus-cover {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 40px;
}
.bus-icon {
  height: 44px;
}
.bus-number-dist {
  display: flex;
  align-items: flex-end;
  margin-bottom: 8px;
}
.bus-number {
  font-size: 1.4rem;
  margin-right: 8px;
  color: #F34949;
}
.bus-getin {
  font-size: 1.5rem;
  font-family: BMHANNAPro;
}
.bus-comment-count {
  color: #F34949;
}
.bus-comment-mood {
  color: #FFB6B9;
}
.chatlist-in {
  animation: chatlistIn 1s forwards;
}
.chatlist-out {
  animation: chatlistOut 1s forwards;
}
.buslist-notfound {
  margin-top: 150px;
}
@keyframes chatlistIn {
  from,
  60%,
  75%,
  90%,
  to {
    animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
  }

  from {
    transform: translate3d(0, 3000px, 0) scaleY(5);
  }

  60% {
    transform: translate3d(0, -20px, 0) scaleY(0.9);
  }

  75% {
    transform: translate3d(0, 10px, 0) scaleY(0.95);
  }

  90% {
    transform: translate3d(0, -5px, 0) scaleY(0.985);
  }

  to {
    transform: translate3d(0, 0, 0);
  }
}
@keyframes chatlistOut {
  to {
    transform: translate3d(0, 100%, 0);
  }
}
.buslist-in {
  animation: buslistIn .75s forwards;
}
.buslist-out {
  animation: buslistOut .75s forwards;
}
@keyframes buslistIn {
  from {
    transform: translate3d(-100%, 0, 0);
    visibility: visible;
  }
  to {
    transform: translate3d(0, 0, 0);
  }
}
@keyframes buslistOut {
  from {
    transform: translate3d(0, 0, 0);
  }
  to {
    visibility: hidden;
    transform: translate3d(150%, 0, 0);
  }
}
.refresh-rotate {
  animation: refreshRotate .75s ease-out;
  animation-iteration-count: 2;
}
@keyframes refreshRotate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(359deg) ;
  }
}
</style>