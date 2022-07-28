export function login(user) {
	return axios
		.post(api.accounts.login(), {
			username: user.username,
			password: user.password
		})
		.then(response => {
			if (response.data.accessToken) {
				localStorage.setItem('user', JSON.stringify(response.data))
			}
			return response.data
		})
}

export function logout() {
	localStorage.removeItem('user')
}

export function signup(user) {
	return axios.post(api.accounts.signup(), {
		email: user.email,
		password: user.password,
		password2: user.password2
	})
}