import axios from 'axios'
import api from '../../../api/api.js'

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
      return res
    })
    .then(res => {
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