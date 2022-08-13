// export default {
//   state: {
//     userId: localStorage.getItem('userId') || 1,
//     busId: localStorage.getItem('busId') || 1,
//     boards: [],
//     board: '',
//   }
// }
const state = () => ({
  userId: localStorage.getItem('userId') || 1,
  busId: localStorage.getItem('busId') || 1,
  boards: [],
  board: '',
  boardColor: '',
  boardDate: '',
  boardTime: '',
})

export default state