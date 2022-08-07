import axios from 'axios'
import router from '../../../router/vue-router.js'
import api from '../../../api/api.js'

// 1. 닉네임 주는 함수
export function showNicknameFirst({ commit, getters, dispatch }) {
  axios({
    url: api.accounts.currentUserInfo(/*userid 넣어줘야 함*/),
    method: 'get',
    
  })
    .then(res => commit('SET_NICKNAME', res.data.nickname))

}
// => 닉네임 매일 04시에 서버측에서 일괄 설정하기로 되었음.
// => 설정된 닉네임을 사용자가 처음 접속했을 때 가져와서 연출로 보여주기만 하면 됨.

// 2. 오늘 최초접속 확인 함수
export function isFirstLogin({ commit, getters }) {
  const now = new Date() // 현재시간
  
  const year = now.getFullYear()
  const month = now.getMonth() + 1
  const date = now.getDate()
  const hours = now.getHours()
  const minutes = now.getMinutes()
  const seconds = now.getSeconds()

  // 현재시간 표기, 초기화 시간, 이전 방문시간
  const nowTime = year + '-' + month + '-' + date + 'T' + hours + ':' + minutes + ':' + seconds
  const todayResetTime = year + '-' + month + '-' + date + 'T' + '04:00:00'
  const lastVisited = getters.lastVisited
  
  let yesterday = date
  if (date > 1) {
    yesterday = date - 1
  } else {
    switch (month) {
      case 1:
        yesterday = 31
        break
      case 2:
        yesterday = 31
        break
      case 3:
        yesterday = 28
        break
      case 4:
        yesterday = 31
        break
      case 5:
        yesterday = 30
        break
      case 6:
        yesterday = 31
        break
      case 7:
        yesterday = 30
        break
      case 8:
        yesterday = 31
        break
      case 9:
        yesterday = 31
        break
      case 10:
        yesterday = 30
        break
      case 11:
        yesterday = 31
        break
      case 12:
        yesterday = 30
        break
    }
  }
  const yesterdayResetTime = year + '-' + month + '-' + yesterday + 'T' + '04:00:00'
  if (lastVisited > todayResetTime) {
    return 0 // 2회 이상 접속
  } else if (nowTime > todayResetTime) {
    return 1 // 첫 접속
  } else if (nowTime <= todayResetTime) {
    if (lastVisited > yesterdayResetTime) {
      return 0 // 2회 이후 접속
    } else {
      return 1 // 첫 접속
    }
  }
}

// 메인페이지 버스데이터 전달함수
export function giveBusData({}) {
  const busData = [
    {
      nowStop: '',
      busNum: '',
      participant: '',
    },
    {
      nowStop: '',
      busNum: '',
      participant: '',
    },
    {
      nowStop: '',
      busNum: '',
      participant: '',
    }
  ]
  axios({
    url: api.home.realTimeData(),
    method: 'get'
  })
    .then(res => {
      for (let i = 0; i < 3; i++) {
        busData[i].nowStop = res.data[i].busstop_name
        busData[i].busNum = res.data[i].route_no
        busData[i].participant = res.data[i].room_users
      }
    })
  return busData
}

// 낭만보고서 목록 가져오는 함수
// Vue3 infinite scroll axios
export function reportsList() {

}