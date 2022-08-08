import { createApp, h } from 'vue'
import App from './App.vue'

import router from './router/vue-router'
import store from './store/store'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'

/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

/* import specific icons */
import { faUserSecret } from '@fortawesome/free-solid-svg-icons'

/* add icons to the library */
library.add(faUserSecret)

const app = createApp({
  render: () => h(App)
})

app.use(router)
app.use(VueAxios, axios)
app.use(store)
app.use(ElementPlus)

app.component('font-awesome-icon', FontAwesomeIcon)

app.mount('#app')
