// // 마지막 방문 시간: 닉네임 보여주기 연출 여부 판별
// export function lastVisitedDate(state) {
//   return state.lastVisited
// }

export function nickname(state) {
  return state.nickname
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

export function reportsDetailId(state) {
  return state.reportsDetail.id
}

export function reportsDetailBus(state) {
  return state.reportsDetail.bus
}

export function reportsDetailCreateDay(state) {
  return state.reportsDetail.createDay
}

export function reportsDetailCreateTime(state) {
  return state.reportsDetail.createTime
}

export function reportsDetailContent(state) {
  return state.reportsDetail.content
}

export function reportsDetailTotalChatCount(state) {
  return state.reportsDetail.totalChatCount
}

export function reportsDetailMyAccessHour(state) {
  return state.reportsDetail.myAccessHour
}

export function reportsDetailMyAccessMinute(state) {
  return state.reportsDetail.myAccessMinute
}

export function reportsDetailTotalUserCount(state) {
  return state.reportsDetail.totalUserCount
}

export function reportsDetailChatPerMinute(state) {
  return state.reportsDetail.chatPerMinute
}

export function reportsDetailAccumulateUserCount(state) {
  return state.reportsDetail.accumulateUserCount
}

export function reportsDetailPersonalCount(state) {
  return state.reportsDetail.personalCount
}

export function reportsDetailBoardCount(state) {
  return state.reportsDetail.boardCount
}

export function busNumSave(state) {
  return state.busNumSave
}