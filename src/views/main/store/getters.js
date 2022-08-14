// // 마지막 방문 시간: 닉네임 보여주기 연출 여부 판별
// export function lastVisitedDate(state) {
//   return state.lastVisited
// }

export function nickname(state) {
  return '오케아누스' || state.nickname
}

export function isRouletted(state) {
  return state.isRouletted
}

export function randomBus(state) {
  return state.randomBus
}

export function mostlyBus(state) {
  return state.mostlyBus
}

export function recentlyBus(state) {
  return state.recentlyBus
}