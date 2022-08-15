const state = () => ({
  userId: Number(localStorage.getItem('accountUserId')) || '',
  boards: [],
  board: '',

  room: {},
  rooms: [],
  roomInfo: {},

  sessionId: localStorage.getItem('sessionID') || 'session_1660483514_2',
  isAccessible: '',
  isAccessibleCnt: 0,
})

export default state