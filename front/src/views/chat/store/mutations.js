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
  if (state.isAccessibleCnt > 2) {
    state.isAccessibleCnt = 0
  }
}

export function SET_CLIENT(state, client) {
  state.client = client
}

export function SET_GPS(state, geoData) {
  state.gps = geoData
}

export function SET_PROFILE_USER(state, user) {
  state.profileUser = user
}

export function SET_REAL_TIME_STATION(state, station) {
  state.realTimeStation = station
}

export function SET_GET_OFF_STATION(state, payload) {
  state.roomInfo.roomUserInfoList.forEach(user => {
    if (user.userId === payload.userId) {
      user.outBusStopId = payload.busStopId
    }
  })
}

export function ADD_USER(state, user) {
  state.roomInfo.roomUserInfoList.unshift(user)
}

export function SET_CHAT_NICKNAME(state, nickName) {
  state.chatNickName = nickName
}

export function SET_PROFILE_MODAL(state, user) {
  state.profileModal = user
}