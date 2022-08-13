// // 닉네임 설정
// export function SET_NICKNAME(state, nickname) {
//   state.nickname = nickname
// }

export function SET_RANDOM_BUS(state, randomBus) {
  state.randomBus = randomBus
}

export function SET_CURRENT_USER(state, currentUser) {
  state.nickname = currentUser.nickname
  state.isRouletted = currentUser.is_rouletted
}

export function SET_IS_ROULETTED(state, isRouletted) {
  state.isRouletted = isRouletted  
}