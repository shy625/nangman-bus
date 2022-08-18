import axios from 'axios'
import api from '../../../api/api.js'
import router from '@/router/vue-router.js'
import * as SockJS from "sockjs-client"
import * as StompJs from "@stomp/stompjs"

export function createBoard({ commit }, credentials) {
  axios({
    url: api.chat.createBoard(),
    method: 'post',
    data: credentials,
  })
    .then(res => {
      console.log(res.data)
      if (res.data.length > 1) {
        console.log('기존')
        commit('SET_BOARDS_CREATE', res.data)
      } 
      else {
        console.log('0일때')
        commit('SET_BOARDS_NULL', res.data)
      }
      return res
    })
    .then(res => {
      const data = res.data[res.data.length-1]
      const createBoardContent = document.createElement('div')
      createBoardContent.classList.add('board-content')
      // 배경색 입력
      createBoardContent.setAttribute('style', `background-color: ${data.color}`)
      const boardContentBody = document.createElement('div')
      boardContentBody.classList.add('board-content-body')
      // 내용 입력
      boardContentBody.innerText = data.content
      const boardContentDate = document.createElement('div')
      boardContentDate.classList.add('board-content-date')
      const createDate = document.createElement('div')
      createDate.classList.add('create-date')
      // 날짜 입력
      createDate.innerText = data.createDay
      const createTime = document.createElement('div')
      createTime.classList.add('create-time')
      // 시간 입력
      createTime.innerText = data.createTime

      const boardScrollView = document.querySelector('#boardScroll .el-scrollbar__view')
      if (res.data.length > 1) {
        if (!boardScrollView.lastElementChild.lastChild.classList.contains('board-content')) { 
          console.log('지움')
          boardScrollView.removeChild(boardScrollView.lastElementChild)
          console.log(boardScrollView.lastElementChild.lastChild)
          console.log(createBoardContent)
          const boardLast = boardScrollView.lastElementChild.lastElementChild
          boardLast.addEventListener('click', () => {
            boardLast.classList.toggle('collapsed')
          })
        } 
      }

      createBoardContent.append(boardContentBody, boardContentDate)
      boardContentDate.append(createDate, createTime)
      boardScrollView.append(createBoardContent)
      createBoardContent.classList.add('submit-board')

      const boardScroll = document.querySelector('#boardScroll .el-scrollbar__wrap')
      boardScroll.scrollTo(0, boardScrollView.scrollHeight)

      createBoardContent.addEventListener('click', () => {
        createBoardContent.classList.toggle('collapsed')
      })
    })
    .catch(err => {
      console.error(err)
    })
}

export function fetchBoards({ commit }, busId) {
  axios({
    url: api.chat.getBoards(busId),
    method: 'get',
    data: busId,
  })
    .then(res => {
      commit('SET_BOARDS', res.data)
    })
    .then(() => {
      const boardScrollView = document.querySelector('#boardScroll .el-scrollbar__view')
      if (boardScrollView.lastElementChild.classList.contains('board-content')) {
        boardScrollView.removeChild(boardScrollView.lastElementChild)
      }
      const boardScroll = document.querySelector('#boardScroll .el-scrollbar__wrap')
      boardScroll.scrollTo(0, boardScrollView.scrollHeight)
      const boardContents = document.querySelectorAll('.board-content')
      if (boardContents) {
        boardContents.forEach(boardContent => {
          boardContent.addEventListener('click', () => {
            boardContent.classList.toggle('collapsed')
          })
        })
      }
    })
    .catch(err => {
      console.error(err)
    })
}

export function fetchIsAccessible({ commit }, geoData) {
  axios({
    url: api.chat.getIsAccessible(geoData.sessionId, geoData.lat, geoData.lng),
    method: 'get'
  })
    .then(res => {
      console.log('이즈 엑세서블?', res.data)
      if (res.data) {
        commit('SET_IS_ACCESSIBLE', res.data)
      } else {
        commit('SET_IS_ACCESSIBLE_CNT_PLUS')
      }
    })
}

export function fetctSessionIdFromChatList({ commit }, data) {
  console.log(data)
  axios({
    url: api.chat.getIsAccessible(data.room.sessionId, data.lat, data.lng),
    method: 'get',
  })
    .then(res => {
      if (res.data) {
        commit('SET_ROOM', data.room)
        router.push({ name: 'chat', params: { sessionId: data.room.sessionId }})
      } else {
        alert('채팅방에 입장할 수 없습니다.')
      }
    })
    .catch(err => {
      console.log(err)
    })
}

export function fetchSessionId({ dispatch }, data) {
  axios({
    url: api.chat.getIsAccessible(data.room.sessionId, data.lat, data.lng),
    method: 'get',
  })
    .then(res => {
      if (res.data) {
        // commit('SET_ROOM', data.room)
        // 채팅방 데이터 받기 코드 밑으로!
        const payload = {
          sessionId: data.room.sessionId,
          nickname: data.nickname
        }
        dispatch('fetchRoomInfo', payload)
        return true
      } else {
        return false
      }
    })
    // 채팅방 입장
    .then(res => {
      if (res) {
        // console.log(res)
        router.push({ name: 'chat', params: { sessionId: data.room.sessionId }})
      }
    })
    .catch(err => {
      console.log(err)
    })
}

export function addUser({ commit }, payload) {
  const user = {
    emotion: 0,
    isTodayBirth: false,
    nickname: payload.nickName,
    outBusStopId: 0,
    userId: payload.userId
  }
  commit('ADD_USER', user)
}

export function fetchRoomInfo({ commit, dispatch, getters }, payload) {
  axios({
    url: api.chat.getRoomInfo(payload.sessionId),
    method: 'get',
  })
    .then(res => {
      res.data.roomUserInfoList.unshift({
        emotion: 0,
        isTodayBirth: false,
        nickName: payload.nickname,
        outBusStopId: 0,
        userId: getters.userId,
      })
      commit('SET_ROOM_INFO', res.data)
      return res.data
    })
    .then(roomInfo => {
      // console.log(roomInfo.chatRoomInfo.chatLogs, '챗')
      dispatch('fetchPreChatLogs', roomInfo.chatRoomInfo.chatLogs)
    })
}

export function fetchRooms({ commit }, data) {
  axios({
    url: api.main.selectrooms(data.lat, data.lng),
    method: "get",
  })
    .then(res => {
      // console.log(res.data)
      commit('SET_ROOMS', res.data)
    })
    .catch(err => console.log(err))
}

export function fetchChatLog({ commit }, log) {
  console.log('chatlog fetch')
  commit('SET_CHAT_LOG', log)
}

export function fetchClient({ commit }) {
  const client = new StompJs.Client({
    brokerURL: "wss://i7a704.p.ssafy.io:8080/socket",
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
    return new SockJS("https://i7a704.p.ssafy.io:8080/socket")
  }
  
  commit('SET_CLIENT', client)
}

export function fetchPreChatLogs({ getters }, chatLogs) {
  const chatList = document.querySelector('.chat-list')
  chatLogs.forEach(chatLog => {
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
    chatting.innerText = chatLog.content
    // 좋아요
    chattingLike.src = `${require('../../../assets/like-outline.png')}`
    chattingLike.alt = 'outline'
    chattingLike.addEventListener('click', e => {
      // 좋아요 pub
      if (e.target.alt === 'outline') {  // 좋아요 +1
        e.target.src = `${require('../../../assets/like-filled.png')}`
        e.target.alt = 'filled'
        const payload = {
          chatId: chatLog.chatId,
          count: null,
        }
        getters.client.publish({
          destination: '/pub/chat/rooms/' + getters.sessionId + '/like/up',
          body: JSON.stringify(payload),
        })
      } else {                           // 좋아요 -1
        e.target.src = `${require('../../../assets/like-outline.png')}`
        e.target.alt = 'outline'
        const payload = {
          chatId: chatLog.chatId,
          count: null,
        }
        getters.client.publish({
          destination: '/pub/chat/rooms/' + getters.sessionId + '/like/down',
          body: JSON.stringify(payload),
        })
      }
    })
    // 시간
    chattingTime.innerText = chatLog.createdTime.split('T')[1].slice(0, 5)
  
    if (getters.userId === Number(chatLog.userId)) {
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
      const chatIcon = document.createElement('img')
      chatIcon.classList.add('chat-icon')
      const chatContent = document.createElement('div')
      chatContent.classList.add('chat-content')
      const chatNick = document.createElement('div')
      chatNick.classList.add('chat-nick')
      
      // 기분(상태)
      chatIcon.src = `${require('../../../assets/emo-default.png')}`
      // 닉네임
      chatNick.innerText = '상대닉'

      otherChatWrapper.append(chatIcon, chatContent)
      chatContent.append(chatNick, chatChatting)
      chatChatting.append(chatting, chattingSide)
      chattingSide.append(chattingLike, chattingTime)
      chatList.append(otherChatWrapper)
    }
  })
  chatList.scrollTo(0, chatList.scrollHeight)
}

export function fetchGPS({ commit }, geoData) {
  commit('SET_GPS', geoData)
}

export function fetchProfileUser({ commit }, payload) {
  commit('SET_PROFILE_USER', payload.user)
  console.log(payload.user.userId, payload.sessionId, payload)
  axios({
    url: api.chat.getProfileUserData(),
    method: 'get',
    data: {
      userId: payload.user.userId,
      sessionId: payload.sessionId
    }
  })
    .then(res => {
      console.log(res.data)
    })
}

export function fetchRealTimeStation({ commit }, payload) {
  const busstops = document.querySelectorAll('.busstop-name')
  busstops.forEach(stop => {
    if (stop.innerText === payload.curName) {
      console.log(stop, payload.curName)
      stop.scrollIntoView({ block: 'center', behavior: 'smooth' })
      stop.classList.add('busstop-pulse-inf')
      setTimeout(() => {
        stop.classList.remove('busstop-pulse-inf')
      }, 5000)
    }
  })

  const busstopBig = document.querySelector('.busstop-big')
  busstopBig.classList.add('busstop-pulse')
  setTimeout(() => {
    busstopBig.classList.remove('busstop-pulse')
  }, 500) 
  commit('SET_REAL_TIME_STATION', payload)
}

export function fetchGetOffStation({ commit }, payload) {
  commit('SET_GET_OFF_STATION', payload)
}