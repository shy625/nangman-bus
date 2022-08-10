<template>
  <BusStops></BusStops>
  <div id="chatRoom" class="chatroom">
    <div class="chat-list">
      <!-- 닉네임 다르면 chat-list 밑에 추가 -->
      <div class="other-chat-wrapper">
        <div class="chat-icon">O</div>
        <div class="chat-content">
          <div class="chat-nick">가우르구라</div>
            <!-- 닉네임 같으면 chat-content 밑에 추가 -->
          <div class="chat-chatting">
            <div class="chatting">안녕하세요 즐입니다요~</div>
            <div class="chatting-side">
              <!-- 이거는 연속 채팅 중 마지막 채팅에 부여할 수 있도록? 안되면 일단 전부 부여 -->
              <div class="chatting-like"></div>
              <div class="chatting-time">10:30</div>
            </div>
          </div>
          <div class="chat-chatting">
            <div class="chatting">즐즐~</div>
            <div class="chatting-side">
              <div class="chatting-like">따봉 3</div>
              <div class="chatting-time">10:30</div>
            </div>
          </div>
        </div>
      </div>
      <!-- 상대 채팅 -->
      <div class="other-chat-wrapper">
        <div class="chat-icon">O</div>
        <div class="chat-content">
          <div class="chat-nick">헤라클레스</div>
            <!-- 닉네임 같으면 chat-content 밑에 추가 -->
          <div class="chat-chatting">
            <div class="chatting">안녕하세요</div>
            <div class="chatting-side">
              <!-- 이거는 연속 채팅 중 마지막 채팅에 부여할 수 있도록? 안되면 일단 전부 부여 -->
              <div class="chatting-like"></div>
              <div class="chatting-time">10:30</div>
            </div>
          </div>
          <div class="chat-chatting">
            <div class="chatting">ㅋㅋㅋㅋ가우르구라님 즐~~~~~!!!!!!!!!!!!!!!!!!!!!!!</div>
            <div class="chatting-side">
              <div class="chatting-like">따봉3</div>
              <div class="chatting-time">10:30</div>
            </div>
          </div>
        </div>
      </div>
      <!-- 내 채팅 -->
      <div class="my-chat-wrapper">
            <div class="chatting-side">
              <div class="chatting-like">따봉3</div>
              <div class="chatting-time">10:30</div>
            </div>
          <div class="chat-chatting">
            <div class="chatting">ㅋㅋㅋㅋ가ㅋㅋㅋㅋ가ㅋㅋㅋㅋ가ㅋㅋㅋㅋ가ㅋㅋㅋㅋ가ㅋㅋㅋㅋ가!</div>
          </div>
      </div>
    </div>
    <div class="chat-input">
      <ChatEmos></ChatEmos>
      <input class="input-content" type="text" placeholder="낭만! >_<">
    </div>
  </div>
  <BanModal></BanModal>
  <!-- <EnterModal></EnterModal> -->
  <Roulette></Roulette>
</template>
<script setup>
import BusStops from './BusStops.vue'
import ChatEmos from './ChatEmos.vue'
import BanModal from './BanModal.vue'
import EnterModal from './EnterModal.vue'
import Roulette from './Roulette.vue'
import { ref, onMounted } from 'vue'

onMounted(() => {
  // 나중에 조건걸어서 ban-active할 수 있도록!
  const banModal = document.querySelector('#banModal')
  const chatRoom = document.querySelector('#chatRoom')
  // const busstop = document.querySelector('#busstop')
  // const enterModal = document.querySelector('#enterModal')

  banModal.classList.add('ban')
  chatRoom.classList.add('ban-active')
  // enterModal.classList.add('enter')
  // busstop.classList.add('ban-active')
})
</script>
<style>
.chatroom {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  margin: 0px 32px;
  height: 720px;
}
.chat-list {
  padding: 1px;
  height: 95%;
  background-color: #F5F5F5;
}
.other-chat-wrapper {
  display: flex;
  margin: 12px;
}
.my-chat-wrapper {
  display: flex;
  margin: 12px;
  justify-content: end;
}
.chat-content{
  display: flex;
  flex-direction: column;
}
.chat-nick {
  font-size: 0.8rem;
  margin-bottom: 4px;
}
.chat-chatting {
  display: flex;
  margin: 2px 0px;
}
.chatting {
  display: flex;
  align-items: center;
  padding: 4px;
  border-radius: 5px;
  font-size: .85rem;
  font-family: Pretendard;
}
.other-chat-wrapper .chatting {
  background-color: white;
}
.my-chat-wrapper .chatting {
  background-color: #FFD96A;
}
.chatting-side {
  display: flex;
  flex-direction: column;
  justify-content: end;
}
.other-chat-wrapper .chatting-side {
  margin-left: 4px;
}
.my-chat-wrapper .chatting-side {
  margin-right: 4px;
}
.chatting-like {
  font-size: 0.6rem;
}
.chatting-time {
  font-size: 0.6rem;
}
.chat-input {
  display: flex;
  flex-grow: 1;
  background-color: #FFD96A;
}
.input-content {
  margin: 4px 8px;
  padding: 0px;
  border: 2px solid #F5F5F5;
  border-radius: 10px;
  flex-grow: 1;
}
.input-content:focus {
  outline: 0;
}
.input-content::placeholder {
  text-align: center;
  font-family: BMHANNAAir;
}
.ban-active {
  overflow: hidden;
}
</style>