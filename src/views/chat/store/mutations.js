export function SET_BOARDS(state, boards) { 
  state.board = boards[0]
  state.boards = boards.reverse()
}

export function SET_BOARDS_CREATE(state, boards) {
  state.board = boards[0]
  state.boards = boards.reverse().slice(0, -1)
}

export function SET_BOARDS_NULL(state, boards) {
  state.board = boards[0]
  state.boards = []
}

export function SET_ROOM(state, room) {
  state.sessionId = room.sessionId
  localStorage.setItem('sessionID', room.sessionId)
  state.room = room
}

export function SET_ROOMS(state, rooms) {
  state.rooms = rooms
}

export function SET_ROOM_INFO(state, roomInfo) {
  state.roomInfo = roomInfo
}

export function SET_CHAT_LOG(state, log) {
  state.roomInfo.chatRoomInfo.chatLogs.push(log)
}

export function SET_IS_ACCESSIBLE(state, data) {
  state.isAccessible = data
  state.isAccessibleCnt = 0
}

export function SET_IS_ACCESSIBLE_CNT_PLUS(state) {
  state.isAccessibleCnt += 1
}

export function SET_CLIENT(state, client) {
  state.client = client
}