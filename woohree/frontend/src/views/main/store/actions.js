// API
import $axios from 'axios'
// import axios from 'axios'

// 토큰 저장
export function saveToken({ commit }, token) {
  // console.log(token)
  commit('setToken', token)
  localStorage.setItem('token', token)
}
// 로그인
export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/auth/login'
  let body = payload
  return $axios.post(url, body)
}
// 회원가입
export function requestSignup({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = '/auth/signup'
  let body = payload
  return $axios.post(url, body)
}
