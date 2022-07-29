import axios from 'axios'
import router from '@/router/vue-router.js'
import api from '@/api/api.js'
import getters from './getters'

// export function login(user) {
// 	return axios
// 		.post(api.accounts.login(), {
// 			useremail: user.uesremail,
// 			password: user.password
// 		})
// 		.then(response => {
// 			if (response.data.accessToken) {
// 				localStorage.setItem('user', JSON.stringify(response.data))
// 			}
// 			return response.data
// 		})
// }

// export function logout() {
// 	localStorage.removeItem('user')
// }

// export function signup(user) {
// 	return axios.post(api.accounts.signup(), {
// 		email: user.email,
// 		password: user.password,
// 		password2: user.password2
// 	})
// }

export default {
	actions: {
		// 토큰 세팅 메소드
		saveToken({ commit }, token) {
			commit('SET_TOKEN', token)
			localStorage.setItem('token', token)
		},
		removeToken({ commit }) {
			commit('SET_TOKEN', '')
			localStorage.setItem('token', '')
		},

		login({ commit, dispatch }, credentials) {
			axios({
				url: api.accounts.login(),
				method: 'post',
				data: credentials
			})
				.then(res => {
					const token = res.data.key
					dispatch('saveToken', token)
					dispatch('fetchCurrentUser')
					router.push({ name: 'main'})
				})
				.catch(err => {
					console.error(err.response.data)
					commit('SET_AUTH_ERROR', err.response.data)
				})
		},

		signup({ commit, dispatch }, credentials) {
			axios({
				url: api.accounts.signup(),
				method: 'post',
				data: credentials
			})
				.then(res => {
					const token = res.data.key
					dispatch('setToken', token)
					dispatch('fetchCurrentUser')
					router.push({ name: 'main'})
				})
				.catch(err => {
					console.error(err.response.data)
					commit('SET_AUTH_ERROR', err.response.data)
				})
		},

		logout({ getters, dispatch }) {
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
		},

		// userid 매개변수로 넣어주어야 함
		fetchCurrentUser({ commit, getters, dispatch }, userid) {
			if (getters.isLoggedIn) {
				axios({
					url: api.accounts.currentUserInfo(userid),
					method: 'get',
					headers: getters.authHeader,
				})
					.then(res => commit('SET_CURRENT_USER', res.data))
					.catch(err => {
						if (err.response.status === 401) {
							dispatch('removeToken')
							router.push({ name: 'login' })
						}
					})
			}
		},

		fetchProfile({ commit, getters }, { userid }) {
			axios({
				url: api.accounts.profile(userid),
				method: 'get',
				headers: getters.authHeader,
			})
				.then(res => {
					commit('SET_PROFILE', res.data)
				})
		}
	}
}