import { createRouter, createWebHistory } from 'vue-router';
// import Home from '../views/Home.vue'
// import Create from '../views/Create.vue'
// import Mypage from '../views/Mypage.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { name: 'main', path: '/main', component: MainView },
    { name: 'welcome', path: '/welcome', component: WelcomeView },
    { name: 'login', path: '/login', component: LoginView },
    { name: 'signup', path: '/signup', component: SignupView },
    { name: 'accounts', path: '/accounts/:userId', component: AccountsView },
    { name: 'reports', path: '/reports/:userId', component: ReportsView },
    // { name: 'reportDetail', path: '/reports/:userId/reportId', component: ReportDetailView },
    { name: 'room', path: '/room/:roomId', component: RoomView },
    // 방명록이랑 유저목록은 컴포넌트 구조에 따라 달라질 예정
    // { name: 'board', path: '/room/board', component: BoardView },
    // { name: 'users', path: '/room/users', component: UsersView }
  ]
})
export default router;