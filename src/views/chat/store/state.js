const state = () => ({
  userId: localStorage.getItem('userId') || 1,
  busId: localStorage.getItem('busId') || 1,
  boards: [],
  board: '',

  sessionId: localStorage.getItem('sessionId') || 'session_1660458737_3',
})

export default state