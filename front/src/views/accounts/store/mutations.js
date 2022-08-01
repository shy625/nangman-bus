// export default {
//   mutations: {
//     SET_TOKEN: (state, token) => state.token = token,
//     SET_CURRENT_USER: (state, user) => state.currentUser = user,
//     SET_PROFILE: (state, profile) => state.profile = profile,
//     SET_AUTH_ERROR: (state, error) => state.authError = error
//   },
// }

export function SET_TOKEN(state, token) {
  state.token = token
}

export function SET_CURRENT_USER(state, user) {
  state.currentUser = user
}

export function SET_PROFILE(state, profile) {
  state.profile = profile
}

export function SET_AUTH_ERROR(state, error) {
  state.authError = error
}