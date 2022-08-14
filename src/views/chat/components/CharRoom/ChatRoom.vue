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
              <img src="../../../../assets/like.png" alt="like" class="chatting-like">
              <div class="chatting-time">10:30</div>
            </div>
          </div>
          <div class="chat-chatting">
            <div class="chatting">즐즐~</div>
            <div class="chatting-side">
              <div class="side-like">
                <img src="../../../../assets/like.png" alt="like" class="chatting-like">
                <div class="like-number">5</div>
              </div>
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
              <img src="" alt="" class="chatting-like">
              <div class="chatting-time">10:30</div>
            </div>
          </div>
          <div class="chat-chatting">
            <div class="chatting">ㅋㅋㅋㅋ가우르구라님 즐~~~~~!!!!!!!!!!!!!!!!!!!!!!!</div>
            <div class="chatting-side">
              <img src="" alt="" class="chatting-like">
              <div class="chatting-time">10:30</div>
            </div>
          </div>
        </div>
      </div>
      <!-- 내 채팅 -->
      <div class="my-chat-wrapper">
            <div class="chatting-side">
              <img src="../../../../assets/like.png" alt="" class="chatting-like">
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


  <div class="chat">
    <h1>Chat Test</h1>
    <div>
      <h3>My Name : {{ data.user }}</h3>
      <h3>Chat Room : {{ data.room }}</h3>
    </div>
    <div>username : <input type="text" id="user" v-model="data.user" /></div>
    <div>chatroom : <input type="text" id="room" v-model="data.room" /></div>
    <div>
      <button id="connBtn">connect</button>
      <button id="disconnBtn">disconnect</button>
    </div>
    <div>
      message : <input type="text" id="msg" v-model="data.message" />
      <button id="sendBtn">send</button>
    </div>
    <div id="msgArea">
      <!-- 채팅 내역 -->
    </div>
  </div>
  <!-- <BanModal></BanModal> -->
  <!-- <EnterModal></EnterModal> -->
</template>
<script setup>
import BusStops from './BusStops.vue'
import ChatEmos from './ChatEmos.vue'
// import BanModal from './BanModal.vue'
// import EnterModal from './EnterModal.vue'
import * as SockJS from "sockjs-client"
import * as StompJs from "@stomp/stompjs"
import { ref, onMounted } from 'vue'
// import { useStore } from 'vuex'

// const store = useStore()
const data = ref({
  user: "",
  room: "",
  message: "",
});

onMounted(() => {
  // 나중에 조건걸어서 ban-active할 수 있도록!
  // const banModal = document.querySelector('#banModal')
  // const chatRoom = document.querySelector('#chatRoom')
  // const busstop = document.querySelector('#busstop')
  // const enterModal = document.querySelector('#enterModal')

  // banModal.classList.add('ban')
  // chatRoom.classList.add('ban-active')
  // enterModal.classList.add('enter')
  // busstop.classList.add('ban-active')

  // 소켓
  const client = new StompJs.Client({
    brokerURL: "ws://i7a704.p.ssafy.io:8080/socket",
    // brokerURL: "ws://localhost:8080/socket",
    connectHeaders: {
      login: "user",
      passcode: "password",
    },
    debug: function (str) {
      console.log(str);
    },
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
  });

  // Fallback code
  // if (typeof WebSocket !== "function") {
  // For SockJS you need to set a factory that creates a new SockJS instance
  // to be used for each (re)connect
  client.webSocketFactory = function () {
    // Note that the URL is different from the WebSocket URL
    return new SockJS("http://i7a704.p.ssafy.io:8080/socket");
    // return new SockJS("http://localhost:8080/socket");
  };
  // }

  client.onConnect = function () {
    console.log("socket connection success");
    client.subscribe(
      "/sub/chat/rooms/" + data.value.room + "/message",
      (message) => {
        const payload = JSON.parse(message.body);
        console.log(payload);
        let msgDiv = document.createElement("div");
        // msgDiv.innerHTML =
        //   "<span>[" + payload.writer + "]: </span><span>" + payload.message + "</span>";
        msgDiv.innerHTML =
          payload.userId +
          " : " +
          payload.message +
          " : " +
          payload.createdTime;
        document.getElementById("msgArea").appendChild(msgDiv);
      }
    );
  };

  const connBtn = document.getElementById("connBtn");
  connBtn.addEventListener("click", () => {
    client.activate();
  });

  const disconnBtn = document.getElementById("disconnBtn");
  disconnBtn.addEventListener("click", () => {
    client.deactivate();
  });

  const sendBtn = document.getElementById("sendBtn");
  sendBtn.addEventListener("click", () => {
    const payload = {
      userId: data.value.user,
      message: data.value.message,
    };
    console.log(payload);
    client.publish({
      destination: "/pub/chat/rooms/" + data.value.room + "/message",
      body: JSON.stringify(payload),
    });
    data.value.message = "";
  });
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
.side-like {
  display: flex;
  font-size: 0.6rem;
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
  align-items: center;
}
.other-chat-wrapper .chatting-side {
  margin-left: 4px;
}
.my-chat-wrapper .chatting-side {
  margin-right: 4px;
}
.chatting-like {
  height: 13px;
  width: 16px;
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