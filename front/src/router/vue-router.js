import { createRouter, createWebHistory } from 'vue-router';
import MainView from '../views/main/MainView.vue'
import ChatView from '../views/chat/ChatView.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { name: 'main', path: '/', component: MainView },
    // { name: 'welcome', path: '/welcome', component: WelcomeView },
    // { name: 'login', path: '/login', component: LoginView },
    // { name: 'signup', path: '/signup', component: SignupView },
    // { name: 'accounts', path: '/accounts/:userId', component: AccountsView },
    // { name: 'reports', path: '/reports/:userId', component: ReportsView },
    // { name: 'reportDetail', path: '/reports/:userId/reportId', component: ReportDetailView },
    { name: 'chat', path: '/chat/:roomId', component: ChatView },
    // 방명록이랑 유저목록은 컴포넌트 구조에 따라 달라질 예정
    // { name: 'board', path: '/room/board', component: BoardView },
    // { name: 'users', path: '/room/users', component: UsersView }
  ]
})

export default router