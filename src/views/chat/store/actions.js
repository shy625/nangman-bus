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

export function fetchSessionId({ commit, dispatch }, data) {
  axios({
    url: api.chat.getIsAccessible(data.room.sessionId, data.lat, data.lng),
    method: 'get',
  })
    .then(res => {
      if (res.data) {
        commit('SET_ROOM', data.room)
        // 채팅방 데이터 받기 코드 밑으로!
        dispatch('fetchRoomInfo', data.room.sessionId)
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

export function fetchChatLog({ commit }, log) {
  console.log('패치로그')
  commit('SET_CHAT_LOG', log)
}