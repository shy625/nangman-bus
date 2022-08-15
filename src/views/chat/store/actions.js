import axios from 'axios'
import api from '../../../api/api.js'
import router from '@/router/vue-router.js'

export function createBoard({ commit }, credentials) {
  axios({
    url: api.chat.createBoard(),
    method: 'post',
    data: credentials,
  })
    .then(res => {
      console.log(res.data.slice(0, -1))
      commit('SET_BOARDS_CREATE', res.data)
    })
    .then(() => {
      const boardScrollView = document.querySelector('#boardScroll .el-scrollbar__view')
      const boardScroll = document.querySelector('#boardScroll .el-scrollbar__wrap')
      boardScroll.scrollTo(0, boardScrollView.scrollHeight)

      const boardContents = document.querySelectorAll('.board-content')
      const boardContent = boardContents[boardContents.length-1]
      if (boardContent) {
        boardContent.addEventListener('click', () => {
          boardContent.classList.toggle('collapsed')
        })
      }
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
      const boardScroll = document.querySelector('#boardScroll .el-scrollbar__wrap')
      boardScroll.scrollTo(0, boardScrollView.scrollHeight)
      const boardContents = document.querySelectorAll('.board-content')
      console.log(boardContents[boardContents.length-1])
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

export function fetchSessionId({ commit, dispatch }, data) {
  axios({
    url: api.chat.getIsAccessible(data.sessionId, data.lat, data.lng),
    method: 'get',
  })
    .then(res => {
      console.log(res.data)
      if (res.data) {
        commit('SET_SESSION_ID', data.sessionId)
        // 채팅방 데이터 받기 코드 밑으로!
        dispatch('fetchRoomInfo', data.sessionId)
      }
    })
    // 채팅방 입장
    .then(() => {
      router.push({ name: 'chat', params: { sessionId: data.sessionId }})
    })
    .catch(err => {
      console.log(err)
    })
}

export function fetchRoomInfo({ commit }, sessionId) {
  axios({
    url: api.chat.getRoomInfo(sessionId),
    method: 'get',
  })
    .then(res => {
      console.log(res.data)
      commit('SET_ROOM_INFO', res.data)
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