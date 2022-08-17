const state = () => ({
  token: localStorage.getItem('token') || '' ,
  currentUser: {},
  profile: {},
  authError: null,
})

export default state