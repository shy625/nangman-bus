const routes = [
  {
    path: '/mvti/accounts/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/mvti/accounts/logout',
    name: 'logout',
    component: LogoutView
  },
  {
    path: '/mvti/accounts/signup',
    name: 'signup',
    component: SignupView
  },
]


router.beforeEach((to, from, next) => {
  // '/' => 'articles/1'
  // to === '/profile' => 로그인 여부를 물어보겠다. 로그인 안했다면, /login // 으로 보내버림

  // 로그인 여부 확인
  const { isLoggedIn } = store.getters

  // 현재 이동하려는 페이지에 로그인이 필요한가?
  const isAuthRequired = authPages.includes(to.name)

  // 로그인 되어있지 않다면?
  if (isAuthRequired && !isLoggedIn) {
    next({ name: 'login' })
  } else {
    next()
  }
})

export default router
