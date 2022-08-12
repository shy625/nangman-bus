import axios from 'axios'
import router from '../../../router/vue-router.js'
import api from '../../../api/api.js'

export function getSelectRooms({ commit, getters }, lat, lng) {
  axios({
    url: api.getSelectRooms(lat, lng),
    method: 'get',
    headers: getters.authHeader,
  })
    .then(res => {
      commit('SET_PROFILE', res.data)
    })
}