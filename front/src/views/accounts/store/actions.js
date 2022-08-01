import axios from 'axios'
import router from '../../../router/vue-router.js'
import api from '../../../api/api.js'

// 토큰 세팅 메소드
export function saveToken({ commit }, token) {
	commit('SET_TOKEN', token)
	localStorage.setItem('token', token)
}
export function removeToken({ commit }) {
	commit('SET_TOKEN', '')
	localStorage.setItem('token', '')
}

export function login({ commit, dispatch }, credentials) {
  axios({
    url: api.accounts.login(),
    method: 'post',
    data: credentials,
  })
    .then(res => {
      const token = res.data.key
      dispatch('saveToken', token)
      dispatch('fetchCurrentUser')
      router.push({ name: 'main' })
    })
    .catch(err => {
      console.error(err.response.data)
      commit('SET_AUTH_ERROR', err.response.data)
    })
}

export function signup({ commit, dispatch }, credentials) {
  axios({
    url: api.accounts.signup(),
    method: 'post',
    data: credentials,
  })
    .then(res => {
      const token = res.data.key
      dispatch('setToken', token)
      dispatch('fetchCurrentUser')
      router.push({ name: 'main' })
    })
    .catch(err => {
      console.error(err.response.data)
      commit('SET_AUTH_ERROR', err.response.data)
    })
}

export function logout({ getters, dispatch }) {
  axios({
    url: api.accounts.logout(),
    method: 'post',
    headers: getters.authHeader,
  })
    .then(() => {
      dispatch('removeToken')
      alert('성공적으로 로그아웃되었습니다.')
      router.push({ name: 'login' })
    })
    .error(err => {
      console.error(err.response)
    })
}

export function fetchCurrentUser({ commit, getters, dispatch }, userid) {
  if (getters.isLoggedIn) {
    axios({
      url: api.accounts.currentUserInfo(userid),
      method: 'get',
      headers: getters.authHeader,
    })
      .then(res => commit('SET_CURRENT_USER', res.data))
      .catch(err => {
        if (err.response.statue === 401) {
          dispatch('removeToken')
          router.push({ name: 'login' })
        }
      })
  }
}

export function fetchProfile({ commit, getters }, { userid }) {
  axios({
    url: api.accounts.profile(userid),
    method: 'get',
    headers: getters.authHeader,
  })
    .then(res => {
      commit('SET_PROFILE', res.data)
    })
}
