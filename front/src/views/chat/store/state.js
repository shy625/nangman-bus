const state = () => ({
  userId: Number(localStorage.getItem('accountUserId')) || '',
  boards: [],
  board: '',

  room: {},
  rooms: [],
  roomInfo: {},
  profileUser: {},
  profileModal: {},

  gps: { lat: 0, lng: 0 },

  sessionId: localStorage.getItem('sessionID') || 'session_1660483514_2',
  isAccessible: '',
  isAccessibleCnt: 0,
  client: '',
  realTimeStation: '',
  chatNickName: '',
})

export default state