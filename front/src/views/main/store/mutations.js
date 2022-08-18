// 닉네임 설정
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

export function SET_ID(state, id) {
  state.reportDetail.id = id
}

export function SET_BUS(state, bus) {
  state.reportDetail.bus = bus
}

export function SET_CREATE_DAY(state, createDay) {
  state.reportDetail.createDay = createDay
}

export function SET_CREATE_TIME(state, createTime) {
  state.reportDetail.createTime = createTime
}

export function SET_CONTENT(state, content) {
  state.reportDetail.content = content
}

export function SET_TOTAL_CHAT_COUNT(state, totalChatCount) {
  state.reportDetail.totalChatCount = totalChatCount
}

export function SET_MY_ACCESS_HOUR(state, myAccessHour) {
  state.reportDetail.myAccessHour = myAccessHour
}

export function SET_MY_ACCESS_MINUTE(state, myAccessMinute) {
  state.reportDetail.myAccessMinute = myAccessMinute
}

export function SET_TOTAL_USER_COUNT(state, totalUserCount) {
  state.reportDetail.totalUserCount = totalUserCount
}

export function SET_CHAT_PER_MINUTE(state, chatPerMinute) {
  state.reportDetail.chatPerMinute = chatPerMinute
}

export function SET_ACCUMULATE_USER_COUNT(state, accumulateUserCount) {
  state.reportDetail.accumulateUserCount = accumulateUserCount
}

export function SET_PERSONAL_COUNT(state, personalCount) {
  state.reportDetail.personalCount = personalCount
}

export function SET_BOARD_COUNT(state, boardCount) {
  state.reportDetail.boardCount = boardCount
}

export function SET_BUS_NUM_SAVE(state, reportId, busNum) {
  state.busNumSave.reportId = busNum
}

export function SET_MAIN_BUS_DATA(state, busData) {
  console.log(busData.top3, busData.recentBus)
  state.top3 = busData.top3
  state.recentBus = busData.recentBus
}