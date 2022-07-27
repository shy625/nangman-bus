<template>
  <el-carousel 
  indicator-position="none" 
  height="100vh"
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

  <!-- <div class="container">
    <div class="pages">
      <div class="board-list">
        <button @onClick="slide('next')">다음</button>
        <BoardList></BoardList>
      </div>
      <div class="chat-room">
        <button @onClick="slide('prev')">이전</button>
        <button @onClick="slide('next')">다음</button>
        <ChatRoom></ChatRoom>
      </div>
      <div class="user-list">
        <button @onClick="slide('prev')">이전</button>
        <button @onClick="slide('next')">다음</button>
        <UserList></UserList>
      </div>
    </div>
  </div> -->
</template>

<script setup>
  import BoardList from './components/BoardList.vue'
  import ChatRoom from './components/ChatRoom.vue'
  import UserList from './components/UserList.vue';

</script>

<style>
  .el-carousel {
    height: auto;
  }
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
  }
</style>