<template>
  <BusStops></BusStops>
  <div class="chatroom">
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
      <div class="input-state">
        <div>기쁨!</div>
        <!-- <div>2</div>
        <div>3</div>
        <div>4</div> -->
      </div>
      <input class="input-content" type="text" placeholder="낭만 >_<">
    </div>
  </div>
</template>
<script setup>
import BusStops from './BusStops.vue'
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
  border-radius: 0px 0px 20px 20px;

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
/* .chat-icon {

} */
.chat-content{
  display: flex;
  flex-direction: column;
}
.chat-nick {
  font-size: 0.9rem;
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
  font-size: 1.2rem;
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
</style>