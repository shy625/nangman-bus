<template>
  <div class="header">
    <router-link :to="{ name: 'main' }">
      <img src="../../assets/logo.png" alt="nangmanLogo" class="header-logo">
    </router-link>
    <router-link to="" @click="clickLogout">
      <img src="../../assets/user-yellow.png" alt="userIcon" class="header-user">
    </router-link>
  </div>  
</template>
<script setup>
import { useStore } from 'vuex'
const store = useStore()
const clickLogout = () => {
  store.dispatch('accounts/logout')
}
</script>
<style>
.header {
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  background-color: white;
  z-index: 1;
}
.header-logo {
  width: 120px;
  margin: 20px 0 20px 32px;
}
.header-user {
  width: 32px;
  margin-right: 32px;
}
</style>
