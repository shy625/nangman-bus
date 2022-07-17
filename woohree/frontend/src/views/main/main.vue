<template>
  <el-container class="main-wrapper">
    <main-header
      :height="`70px`"
      @openLoginDialog="onOpenLoginDialog"
      @openSignupDialog="onOpenSignupDialog"/>
    <el-container class="main-container">
      <el-aside class="hide-on-small" width="240px">
        <main-sidebar
          :width="`240px`"/>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    <main-footer :height="`110px`"/>
  </el-container>
  <login-dialog
    :open="loginDialogOpen"
    @closeLoginDialog="onCloseLoginDialog"/>
  <signup-dialog
    :open="signupDialogOpen"
    @closeSignupDialog="onCloseSignupDialog"/>
</template>
<style>
  @import "https://unpkg.com/element-plus/lib/theme-chalk/index.css";
  @import './main.css';
  @import '../../common/css/common.css';
  @import '../../common/css/element-plus.css';

</style>
<script>
import LoginDialog from './components/login-dialog'
import MainHeader from './components/main-header'
import MainSidebar from './components/main-sidebar'
import MainFooter from './components/main-footer'
import SignupDialog from './components/signup-dialog'

export default {
  name: 'Main',
  components: {
    MainHeader,
    MainSidebar,
    MainFooter,
    LoginDialog,
    SignupDialog,
  },
  data () {
    return {
      loginDialogOpen: false,
      signupDialogOpen: false,
    }
  },
  methods: {
    onOpenLoginDialog () {
      this.loginDialogOpen = true
    },
    onCloseLoginDialog () {
      this.loginDialogOpen = false
    },
    onOpenSignupDialog () {
      this.signupDialogOpen = true
    },
    onCloseSignupDialog () {
      this.signupDialogOpen = false
    }
  }
}
</script>
