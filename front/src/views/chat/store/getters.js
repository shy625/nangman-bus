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

export function lat(state) {
  return state.gps.lat
}

export function lng(state) {
  return state.gps.lng
}

export function userList(state) {
  return state.roomInfo.roomUserInfoList
}

export function userListLength(state) {
  return state.roomInfo.roomUserInfoList?.length
}

export function profileUser(state) {
  return state.profileUser
}

export function realTimeStation(state) {
  return state.realTimeStation
}

export function getOffUserList(state) {
  const getOffUserList = []
  let idx = 0
  let startUser = ''
  state.roomInfo.roomUserInfoList?.forEach(user => {
    console.log(user.outBusStopId, state.realTimeStation.nextId)
    if (user.outBusStopId === state.realTimeStation.nextId) {
      if (idx === 0) {
        startUser = user.nickName
      }
      const getOff = {
        nickName: user.nickName,
        idx: idx,
      }
      getOffUserList.push(getOff)
      idx += 1
    }
  })
  getOffUserList.push({ nickName: startUser, idx: getOffUserList.length })
  console.log(getOffUserList)
  return getOffUserList
}

export function chatNickName(state) {
  return state.chatNickName
}

export function profileModal(state) {
  return state.profileModal
}

export function profileBusStop(state) {
  const busstops = state.roomInfo.busStopInfoList
  for (let i=0; i<busstops?.length; i++) {
    if (Number(busstops[i].busStopId) === state.profileUser.outBusStopId) {
      return busstops[i].nodeName
    }
  }
  // state.roomInfo.busStopInfoList?.forEach(busstop => {
  //   console.log(Number(busstop.busStopId), state.profileUser.outBusStopId, Number(busstop.busStopId) === state.profileUser.outBusStopId)
  //   if (Number(busstop.busStopId) === state.profileUser.outBusStopId) {
  //     return busstop.nodeName
  //   }
  // })
}