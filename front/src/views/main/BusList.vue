<template>
  <div class="chatlist">
    <div class="chatlist-title">
      <div class="chatlist-title-text">
        버스 목록
      </div>
      <hr class="chatlist-title-line">
      <img src="../../assets/refresh.png" alt="refresh" class="chatlist-title-refresh">
    </div>
  </div>
</template>
<script setup>
  import { useStore } from 'vuex'
  import { ref, onMounted } from 'vue'
  const store = useStore()
  onMounted(() => {
  })
  const dataNameExample = ref({
    
  })
  const fName = function (event) {
    
  }
</script>
<style>
.chatlist {
  margin: 71.150px 0 45px 0;
  padding: 16px 32px;
  border: 3px solid #F34949;
  border-radius: 5px;
  overflow: hidden;
}
.chatlist-title {
  display: flex;
  align-items: center;
}
.chatlist-title-text {
  font-size: 1.2rem;
}
.chatlist-title-line {
  display: block;
  width: 55%;
  height: 1px;
  border: 0;
  border-top: 1px solid black;
}
.chatlist-title-refresh {
  width: 16px;
}
</style>