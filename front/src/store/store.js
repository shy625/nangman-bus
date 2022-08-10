import { createStore } from "vuex";
// import root from '@/views/main/store';
import accounts from '../views/accounts/store/index.js'
import mainPage from '../views/main/store/index.js'

export default createStore({
  modules: {
    accounts,
    mainPage
  }
});
