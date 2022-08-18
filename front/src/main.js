import { createApp, h } from 'vue'
import App from './App.vue'

import router from './router/vue-router'
import store from './store/store'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'

const app = createApp({
  render: () => h(App)
})

app.use(router)
app.use(VueAxios, axios)
app.use(store)
app.use(ElementPlus)

app.mount('#app')
