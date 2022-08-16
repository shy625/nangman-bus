export function userId() { 
  return Number(localStorage.getItem('accountUserId'))
}

export function boards(state) {
  return state.boards
}

export function board(state) {
  return state.board
}

export function boardColor(state) {
  return state.board.color
}

export function boardDate(state) {
  return state.board.createDay
}

export function boardTime(state) {
  return state.board.createTime
}

export function sessionId(state) {
  return state.sessionId
}

export function room(state) {
  return state.room
}

export function rooms(state) {
  return state.rooms
}

export function busstopInfos(state) {
  return state.roomInfo.busStopInfoList
}

export function busId(state) {
  return state.room.busId
  // return 24
}

export function busNum(state) {
  return state.roomInfo.chatRoomInfo?.routeNo
}

export function isAccessibleCnt(state) {
  return state.isAccessibleCnt
}

export function client(state) {
  return state.client
}