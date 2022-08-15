const state = () => ({
  userId: Number(localStorage.getItem('accountUserId')) || '',
  boards: [],
  board: '',

  room: {},
  rooms: [],
  roomInfo: {},

  sessionId: localStorage.getItem('sessionId') || 'session_1660483514_0',
})

export default state