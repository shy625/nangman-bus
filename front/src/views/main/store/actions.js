import axios from 'axios'
import router from '../../../router/vue-router.js'
import api from '../../../api/api.js'

// 1. 닉네임 주는 함수
export function giveNickname({ commit, getters, dispatch }) {
  axios({
    url: api.accounts.giveNickname(/*userid 넣어줘야 함*/),
    method: 'post',
    
  })
}
// => 닉네임 매일 04시에 서버측에서 일괄 설정하기로 되었음.
// => 설정된 닉네임을 사용자가 처음 접속했을 때 가져와서 연출로 보여주기만 하면 됨.