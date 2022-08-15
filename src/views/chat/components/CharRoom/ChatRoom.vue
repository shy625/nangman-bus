<template>
  <BusStops></BusStops>
  <div id="chatRoom" class="chatroom">
    <div class="chat-list">
      <!-- <div class="other-chat-wrapper">
        <img src="../../../../assets/emo-default.png" class="chat-icon">
        <div class="chat-content">
          <div class="chat-nick">가우르구라</div>
          <div class="chat-chatting">
            <div class="chatting">안녕하세요 즐입니다요~</div>
            <div class="chatting-side">
              <img src="../../../../assets/like.png" alt="like" class="chatting-like">
              <div class="chatting-time">10:30</div>
            </div>
          </div>
        </div>
      </div>
      <div class="my-chat-wrapper">
        <div class="chatting-side">
          <img src="../../../../assets/like.png" alt="" class="chatting-like">
          <div class="chatting-time">10:30</div>
        </div>
        <div class="chat-chatting">
          <div class="chatting">ㅋㅋㅋㅋ가ㅋㅋㅋㅋ가ㅋㅋㅋㅋ가ㅋㅋㅋㅋ가ㅋㅋㅋㅋ가ㅋㅋㅋㅋ가!</div>
        </div>
      </div> -->
    </div>
    <div class="chat-input">
      <ChatEmos></ChatEmos>
      <input class="input-content" v-model="chatData.message" type="text" placeholder="낭만! >_<">
      <div class="chat-send">전송</div>
    </div>
  </div>
  <!-- <BanModal></BanModal> -->
  <EnterModal></EnterModal>
</template>
<script setup>
import BusStops from './BusStops.vue'
import ChatEmos from './ChatEmos.vue'
// import BanModal from './BanModal.vue'
import EnterModal from './EnterModal.vue'
import * as SockJS from "sockjs-client"
import * as StompJs from "@stomp/stompjs"
import { ref, onMounted, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const chatData = ref({
  userId: computed(() => store.getters['chatStore/userId']),
  sessionId: computed(() => store.getters['chatStore/sessionId']),
  message: "",
})

onMounted(() => {
  const chatRoom = document.querySelector('#chatRoom')
  const busstop = document.querySelector('#busstop')
  // 나중에 조건걸어서 ban-active할 수 있도록!
  // const banModal = document.querySelector('#banModal')
  // const chatRoom = document.querySelector('#chatRoom')
  // const busstop = document.querySelector('#busstop')
  // banModal.classList.add('ban')
  // chatRoom.classList.add('ban-active')
  // busstop.classList.add('ban-active')
  
  // 엔터 모달
  const enterModal = document.querySelector('#enterModal')
  enterModal.classList.add('enter-in')
  enterModal.classList.add('enter')
  chatRoom.classList.add('chatroom-blur')
  busstop.classList.add('chatroom-blur')
  const enterBtns = document.querySelectorAll('.enter-btn .el-button')
  enterBtns.forEach(btn => {
    btn.addEventListener('click', () => {
      // const enterInput = document.querySelector('.enter-input')
      // const payload = {
      //   userId: chatData.value.userId,
      //   // message: enterInput.value,
      // }
      client.publish({
        destination: '/pub/chat/rooms/' + chatData.value.sessionId + '/in',
        body: chatData.value.userId,
      })
      // console.log('클릭함')
      chatRoom.classList.remove('chatroom-blur')
      busstop.classList.remove('chatroom-blur')
      enterModal.classList.add('enter-out')
      enterModal.addEventListener('animationend', () => {
        enterModal.style = 'position: fixed;transform: scale(0);'
      })
    })
  })

  // 소켓
  const client = new StompJs.Client({
    brokerURL: "ws://i7a704.p.ssafy.io:8080/socket",
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
  })

  client.webSocketFactory = function () {
    return new SockJS("http://i7a704.p.ssafy.io:8080/socket")
  }

  client.onConnect = function () {
    client.subscribe(
      "/sub/chat/rooms/" + chatData.value.sessionId + "/message",
      message => {
        const payload = JSON.parse(message.body)
        const chatList = document.querySelector('.chat-list')

        const chattingSide = document.createElement('div')
        const chattingLike = document.createElement('img')
        const chattingTime = document.createElement('div')
        const chatChatting = document.createElement('div')
        const chatting = document.createElement('div')
        chattingSide.classList.add('chatting-side')
        chattingLike.classList.add('chatting-like')
        chattingTime.classList.add('chatting-time')
        chatChatting.classList.add('chat-chatting')
        chatting.classList.add('chatting')
        // 채팅 내용
        chatting.innerText = payload.message
        // 좋아요
        chattingLike.src = `${require('../../../../assets/like-outline.png')}`
        chattingLike.alt = 'outline'
        chattingLike.addEventListener('click', e => {
          if (e.target.alt === 'outline') {  // 좋아요 +1
            e.target.src = `${require('../../../../assets/like-filled.png')}`
            e.target.alt = 'filled'
          } else {                           // 좋아요 -1
            e.target.src = `${require('../../../../assets/like-outline.png')}`
            e.target.alt = 'outline'
          }
        })
        // 시간
        chattingTime.innerText = payload.createdTime.split('T')[1].slice(0, 5)

        console.log('받음', payload)
        if (payload.userId === chatData.value.userId) {
          console.log('내꺼')
          const myChatWrapper = document.createElement('div')
          myChatWrapper.classList.add('my-chat-wrapper')

          myChatWrapper.append(chattingSide, chatChatting)
          chattingSide.append(chattingLike, chattingTime)
          chatChatting.append(chatting)
          chatList.append(myChatWrapper)
        } 
        else {
          console.log('남꺼')
          const otherChatWrapper = document.createElement('div')
          otherChatWrapper.classList.add('other-chat-wrapper')
          const chatIcon = document.createElement('div')
          chatIcon.classList.add('chat-icon')
          const chatContent = document.createElement('div')
          chatContent.classList.add('chat-content')
          const chatNick = document.createElement('div')
          chatNick.classList.add('chat-nick')
          
          // 기분(상태)
          chatIcon.innerText = 'O'
          // 닉네임
          chatNick.innerText = '상대닉'

          otherChatWrapper.append(chatIcon, chatContent)
          chatContent.append(chatNick, chatChatting)
          chatChatting.append(chatting, chattingSide)
          chattingSide.append(chattingLike, chattingTime)
          chatList.append(otherChatWrapper)
        }
        chatList.scrollTo(0, chatList.scrollHeight)
      }
    )
    client.subscribe(
      '/sub/chat/rooms/' + chatData.value.sessionId + '/user',
      message => {
        const payload = JSON.parse(message.body)
        console.log(payload)
      }
    )
  }

  // 채팅방 입장
  client.activate()

  // 퇴장
  const chatHeaderBack = document.querySelector('.chat-header-back')
  chatHeaderBack.addEventListener('click', () => {
    const payload = {
      userId: chatData.value.userId,
      message: null,
    }
    client.publish({
      destination: "/pub/chat/rooms/" + chatData.value.sessionId + "/out",
      body: JSON.stringify(payload)
    })
    client.deactivate()
  })

  // 메시지 전송
  const chatSend = document.querySelector(".chat-send");
  chatSend.addEventListener("click", () => {
    const payload = {
      userId: chatData.value.userId,
      message: chatData.value.message,
    }
    if (payload.message.length > 0) {
      console.log('전송', payload)
      client.publish({
        destination: "/pub/chat/rooms/" + chatData.value.sessionId + "/message",
        body: JSON.stringify(payload),
      })
      chatData.value.message = ""
    }
  })
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
  max-height: 700px;
  background-color: #F5F5F5;
  overflow: scroll;
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
  height: 12px;
}
.chatting-time {
  font-size: 0.6rem;
  font-family: Pretendard;
}
.chat-input {
  display: flex;
  flex-grow: 1;
  background-color: #FFD96A;
}
.chat-icon {
  height: 25px;
  margin-right: 4px;
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
.chat-send {
  display: flex;
  align-self: center;
  margin-right: 8px;
  font-family: Pretendard;
}
.ban-active {
  overflow: hidden;
}
</style>