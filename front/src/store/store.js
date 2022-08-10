import { createStore } from "vuex";
// import root from '@/views/main/store';
import accounts from '../views/accounts/store/index.js'

export default createStore({
  modules: {
    accounts
  }
});
