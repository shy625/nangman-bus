const state = () => ({
  userId: Number(localStorage.getItem('accountUserId')) || '',
  boards: [],
  board: '',

  room: {},
  rooms: [],
  roomInfo: {},

  sessionId: localStorage.getItem('sessionID') || '',
})

export default state