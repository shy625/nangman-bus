import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/main/MainView.vue'
import ReportsView from '../views/main/ReportsView.vue'
import ReportsDetailView from '../views/main/ReportsDetailView.vue'
import ChatView from '../views/chat/ChatView.vue'
import LoginView from '../views/accounts/LoginView.vue'
import SignupView from '../views/accounts/SignupView.vue'


const router = createRouter({
  history: createWebHistory(),
  routes: [
    { 
      name: 'main',
      path: '/', 
      component: MainView,
    },
    { 
      name: 'login', 
      path: '/login', 
      component: LoginView 
    },
    { 
      name: 'signup', 
      path: '/signup', 
      component: SignupView 
    },
    // { 
    //   name: 'accounts', 
    //   path: '/accounts/:userId', 
    //   component: AccountsView 
    // },
    { 
      name: 'reports', 
      path: '/reports', 
      component: ReportsView
    },
    { 
      name: 'reportsdetail',
      path: '/reports/:userId', 
      component: ReportsDetailView,
      meta: {
        transition: 'reportIn'
      },
      props: true,
    },
    { 
      name: 'chat', 
      path: '/chat/:sessionId', 
      component: ChatView, 
    },
  ]
})

export default router