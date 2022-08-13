<template>
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
</template>

<script setup>
import { ref, onMounted } from "vue";
import * as SockJS from "sockjs-client";
import * as StompJs from "@stomp/stompjs";

onMounted(() => {
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
});

const data = ref({
  user: "",
  room: "",
  message: "",
});
</script>
