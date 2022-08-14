<template>
  <div class="chatlist-cover">
    <div class="chatlist">
      <div class="chatlist-title">
        <div class="chatlist-title-text">
          버스 목록
        </div>
        <hr class="chatlist-title-line">
      </div>
      <div class="buslist">
        <div v-if="!!!data.rooms.length" class="buslist-notfound">
          <h1>주변에<br>낭만버스가<br>보이지 않아요</h1>
          <p style="font-size: 1.3rem">하단의 새로고침 버튼을 눌러보세요!</p>
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
            <div class="bus-getin" @click="clickBusGetIn(room.sessionId)">
              >
            </div>
          </div>
        </div>
      </div>
    </div>
    <img src="../../assets/refresh.png" alt="refresh" class="chatlist-title-refresh" v-on:click="getRooms(37.49797, 127.02763)">
  </div>
</template>
<script setup>
import axios from "axios"
import { ref } from 'vue'
import api from "../../api/api"
import { useStore } from "vuex"

const store = useStore()
const data = ref({
  rooms: [],
  lat: 0,
  lng: 0,
})

getLocation()
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
    axios({
      url: api.main.selectrooms(lat, lng),
      method: "get",
    })
      .then(res => {
        data.value.rooms = res.data
        busList.classList.remove('buslist-out')
        busList.classList.add('buslist-in')
        // console.log('데이터')
      })
      .catch(err => {
      console.log(err)
    })
  })
}

function getLocation() {
  if (navigator.geolocation) { // GPS를 지원하면
    navigator.geolocation.getCurrentPosition(position => {
      getRooms(position.coords.latitude, position.coords.longitude)
      data.value.lat = position.coords.latitude
      data.value.lng = position.coords.longitude
    }, error => {
      console.error(error)
    }, {
      enableHighAccuracy: false,
      maximumAge: 0,
      timeout: Infinity
    })
  } else {
    alert('GPS를 허용해 주세요.')
  }
}

const clickBusGetIn = sessionId => {
  // console.log(sessionId)
  store.dispatch('chatStore/fetchSessionId', sessionId)
}
// 위치바뀌면 감지
navigator.geolocation.watchPosition(function(position) {
  data.value.lat = position.coords.latitude
  data.value.lng = position.coords.longitude
})
//3. List형식으로 방의 정보를 받아서 저장해야되고
//4. List로 받은것을 template에 넣어줘야됨


//5. 약간 데이터 수정이 필요


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
  border-radius: 5px;
  height: 100vh;
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
  width: 70%;
  height: 1px;
  border: 0;
  border-top: 1px solid black;
}
.chatlist-title-refresh {
  width: 30px;
  position: fixed;
  left: 50%;
  transform: translateX(-50%);
  bottom: 120px;
}
.buslist {
  margin-top: 32px;
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
    transform: translateX(-50%) rotate(0deg);
  }
  to {
    transform: translateX(-50%) rotate(359deg) ;
  }
}
</style>