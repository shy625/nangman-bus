import { createApp } from 'vue'
import App from './App.vue'

// 3rd party
import router from './router/index.js'


// lib
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App)

app.use(router)
app.use(ElementPlus)
app.mount('#app')