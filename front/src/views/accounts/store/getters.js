// export default {
//   getters: {
//     isLoggedIn: state => !!state.token,
//     currentUser: state => state.currentUser,
//     profile: state => state.profile,
//     authError: state => state.authError,
//     authHeader: state => ({ Authorization: `Token ${state.token}`})
//   },
// }
export function isLoggedIn(state) {
  return !!localStorage.token
}

export function currentUser(state) {
  return state.currentUser
}

export function profile(state) {
  return state.profile
}

export function authError(state) {
  return state.authError
}

export function authHeader(state) {
  return ({ Authorization: `Token ${state.token}` })
}

export function accountUserId() {
  return localStorage.accountUserId
}