import { createRouter, createWebHistory } from 'vue-router';
import MainView from '../views/main/MainView.vue'
import ChatView from '../views/chat/ChatView.vue'
import LoginView from '../views/accounts/LoginView.vue'
import SignupView from '../views/accounts/SignupView.vue'
import TestView from '../views/components/TestView.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { 
      name: 'main',
      path: '/', 
      component: MainView,
      // meta: {
      //   transition: 'slide-fade',
      //   mode: 'out-in'
      // } 
    },
    // { name: 'welcome', path: '/welcome', component: WelcomeView },
    { name: 'login', path: '/login', component: LoginView },
    { name: 'signup', path: '/signup', component: SignupView },
    // { name: 'accounts', path: '/accounts/:userId', component: AccountsView },
    // { name: 'reports', path: '/reports/:userId', component: ReportsView },
    // { name: 'reportDetail', path: '/reports/:userId/reportId', component: ReportDetailView },
    { 
      name: 'chat', 
      path: '/chat/:roomId', 
      component: ChatView, 
      // meta: {
      //   transition: 'slide-fade',
      //   mode: 'out-in'
      // }
    },
    { name: 'test', path: '/test', component: TestView }
    // 방명록이랑 유저목록은 컴포넌트 구조에 따라 달라질 예정
    // { name: 'board', path: '/room/board', component: BoardView },
    // { name: 'users', path: '/room/users', component: UsersView }    
  ]
})

export default router