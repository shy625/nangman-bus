export function setPlatform (state, isDesktop) {
  state.isDesktopPlatform = isDesktop
}

export function setMenuActive (state, index) {
	console.log('setMenuActive', state,index)
	const keys = Object.keys(state.menus)
	state.activeMenu = keys[index]
}

export function setMenuActiveMenuName (state, menuName) {
	state.activeMenu = menuName
}

// 토큰 저장
export function setToken(state, token) {
  console.log(token)
  state.token = token
}

// 현재 로그인한 유저
export function setCurrentUser(state, user) {
  state.currentUser = user
}
