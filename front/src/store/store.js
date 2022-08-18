import { createStore } from "vuex"
import accounts from '../views/accounts/store/index.js'
import mainPage from '../views/main/store/index.js'
import chatStore from "../views/chat/store/index.js"

export default createStore({
  modules: {
    accounts,
    mainPage,
    chatStore,
  }
})
