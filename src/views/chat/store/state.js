const state = () => ({
  userId: localStorage.getItem('userId') || 1,
  busId: localStorage.getItem('busId') || 1,
  boards: [],
  board: '',
})

export default state