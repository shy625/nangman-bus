<template>
  <header>
    <div class="chat-header">
      <div class="chat-header-back">&lt; 1</div>
      <div class="chat-header-title">5006번 버스</div>
    </div>
    <hr class="chat-header-hr">
  </header>

  <el-carousel 
  height="100vh"
  indicator-position="none" 
  :initial-index="1"
  arrow="always"
  :autoplay="false"
  :loop="false"
  >
    <el-carousel-item>
      <BoardList></BoardList>
    </el-carousel-item>
    <el-carousel-item>
      <ChatRoom></ChatRoom>
    </el-carousel-item>
    <el-carousel-item>
      <UserList></UserList>
    </el-carousel-item>
  </el-carousel>
</template>

<script setup>
  import BoardList from './components/BoardList.vue'
  import ChatRoom from './components/ChatRoom.vue'
  import UserList from './components/UserList.vue';

</script>

<style>
.el-carousel__arrow {
  top: 75% !important;
  opacity: 0.3;
  background-color: #FFD96A !important;
}
.el-carousel__arrow:hover {
  opacity: 1;
}
.chat-header {
  display: flex;
  padding: 20px 0px;
  margin: 0px 20px;
  position: relative;
  justify-content: center;
  font-size: 1.5rem;
}
.chat-header-back {
  position: absolute;
  left: 12px;
}
.chat-header-title {
  color: #F34949;
}
.chat-header-hr {
  margin: 0px 32px;
}

</style>