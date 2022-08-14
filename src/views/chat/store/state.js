const state = () => ({
  userId: localStorage.getItem('userId') || 1,
  busId: localStorage.getItem('busId') || 1,
  boards: [],
  board: '',

  rooms: [],
  roomInfo: {},

  sessionId: localStorage.getItem('sessionId') || 'session_1660483514_0',
})

export default state