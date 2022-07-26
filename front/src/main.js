import { createApp, h } from 'vue'
import App from './App.vue'

import router from './common/lib/vue-router'
import store from './common/lib/store'
import VueAxios from './common/lib/axios'
import axios from './common/lib/axios'

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
