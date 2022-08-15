<template>
  <div class="popular-chat">
    <div class="popular-chat-small">
      이날, 가장 많은 <span class="popular-chat-pink">사랑</span>을 받은 채팅은<br>
      <div class="popular-chat-big">
        "<br>
        {{ popularChat }}<br>
        "
      </div>
      였답니다!
    </div>
  </div>
  <hr class="report-detail-hr-small">
</template>
<script setup>
import { useStore } from 'vuex'
import { onBeforeMount } from 'vue';

onBeforeMount(() => {
  const store = useStore()
  const popularChat = store.getters.reportsDetailContent
})

</script>
<style>
.popular-chat {
  margin: 0 20px 0 20px;
}
.popular-chat-small {
  text-align: center;
  font-size: 1.2rem;
}
.popular-chat-pink {
  color: #F34949;
  font-size: 1.3rem;
}
.popular-chat-big {
  font-size: 1.5rem;
  font-family: BMHANNAPro;
  font-style: italic;
}
.report-detail-hr-small {
  margin: 40px;
  border: none;
  background-color: black;
  height: 2px;
}
</style>