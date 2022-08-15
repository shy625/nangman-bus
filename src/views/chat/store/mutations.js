export function SET_BOARDS(state, boards) { 
  state.board = boards[0]
  state.boards = boards.reverse()
}

export function SET_BOARDS_CREATE(state, boards) {
  state.board = boards[0]
  state.boards = boards.reverse().slice(0, -1)
}

export function SET_SESSION_ID(state, sessionId) {
  state.sessionId = sessionId
  localStorage.setItem('sessionID', sessionId)
}

export function SET_ROOMS(state, rooms) {
  state.rooms = rooms
}

export function SET_ROOM_INFO(state, roomInfo) {
  state.roomInfo = roomInfo
}
