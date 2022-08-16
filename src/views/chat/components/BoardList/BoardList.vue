<template>
  <div class="board">
    <div class="board-title">방명록</div>
    <div class="board-list">
      <el-scrollbar id="boardScroll" height="75vh">
        <div 
          v-for="board in boardData.boards" 
          :key="board.boardId"
        >
          <div class="board-content" :style="`background-color: ${board.color}`">
            <div class="board-content-body">
              {{ board.content }}
            </div>
            <div class="board-content-date">
              <div class="create-date">{{ board.createDay }}</div>
              <div class="create-time">{{ board.createTime }}</div>
            </div>
          </div>
        </div>
      </el-scrollbar>
    </div>
  </div>
  <el-button 
    class="create-btn"
    @click="clickCreateBtn"
    color="#FFD96A" 
    round
  >
  낭만 남기기
  </el-button>
</template>
<script setup>
import { useStore } from 'vuex'
import { ref, onMounted, computed } from 'vue'

const store = useStore()
const boardData = ref({
  busId: computed(() => store.getters['chatStore/busId']),
  userId: computed(() => store.getters['chatStore/userId']),
  boards: computed(() => store.getters['chatStore/boards']),
  board: computed(() => store.getters['chatStore/board']),
  boardColor: computed(() => store.getters['chatStore/boardColor']),
  boardDate: computed(() => store.getters['chatStore/boardDate']),
  boardTime: computed(() => store.getters['chatStore/boardTime']),
})

onMounted(() => {
  const boardContent = document.querySelector('#boardContent')
  function boardContentToggle() {
    boardContent.classList.toggle('collapsed')
  }
  if (boardContent) {
    boardContent.addEventListener('click', boardContentToggle, false)
  }
})

const clickCreateBtn = () => {
  // 컴포넌트 생성
  const boardContent = document.createElement('div')
  boardContent.classList.add('board-content')
  boardContent.classList.add('add-board')
  boardContent.style = 'background-color: #f5f5f5;'
  const form = document.createElement('form')
  form.classList.add('board-create-form')
  const textarea = document.createElement('textarea')
  textarea.classList.add('board-create-content')
  textarea.placeholder = '당신의 낭만을 남겨주세요.'

  const createSubmitBtn = document.createElement('button')
  createSubmitBtn.classList = ('el-button is-round')
  createSubmitBtn.setAttribute('aria-disabled', 'false')
  createSubmitBtn.setAttribute('type', 'button')
  createSubmitBtn.setAttribute('style', '--el-button-bg-color:#f5f5f5; --el-button-text-color:var(--el-color-black); --el-button-border-color:#f5f5f5; --el-button-hover-bg-color:rgb(255, 228, 151); --el-button-hover-text-color:var(--el-color-black); --el-button-hover-border-color:rgb(255, 228, 151); --el-button-active-bg-color:rgb(208, 178, 89); --el-button-active-border-color:rgb(208, 178, 89);')
  const submitBtnSpan = document.createElement('span')
  submitBtnSpan.innerText = '등록'
  createSubmitBtn.append(submitBtnSpan)

  const createDelBtn = document.createElement('button')
  createDelBtn.classList = ('el-button is-round')
  createDelBtn.setAttribute('aria-disabled', 'false')
  createDelBtn.setAttribute('type', 'button')
  createDelBtn.setAttribute('style', '--el-button-bg-color:#f5f5f5; --el-button-text-color:var(--el-color-black); --el-button-border-color:#f5f5f5; --el-button-hover-bg-color:rgb(255, 228, 151); --el-button-hover-text-color:var(--el-color-black); --el-button-hover-border-color:rgb(255, 228, 151); --el-button-active-bg-color:rgb(208, 178, 89); --el-button-active-border-color:rgb(208, 178, 89);')
  const delBtnSpan = document.createElement('span')
  delBtnSpan.innerText = '취소'
  createDelBtn.append(delBtnSpan)

  boardContent.append(form)
  form.append(textarea, createSubmitBtn, createDelBtn, )
  
  const boardScrollView = document.querySelector('#boardScroll .el-scrollbar__view')
  boardScrollView.append(boardContent)
  const boardScroll = document.querySelector('#boardScroll .el-scrollbar__wrap')
  boardScroll.scrollTo(0, boardScrollView.scrollHeight)

  // 새로 만들기 없애기
  const createBtn = document.querySelector('.create-btn')
  createBtn.style.display = 'none'

  // 취소버튼 이벤트
  createDelBtn.addEventListener('click', () => {
    boardContent.classList.add('remove-board')
    boardContent.addEventListener('animationend', () => {
      boardScrollView.removeChild(boardContent)      
    })
    createBtn.style.display = 'inline-flex'
  })

  // 등록버튼 이벤트
  createSubmitBtn.addEventListener('click', () => {
    const credentials = {
      busId: boardData.value.busId,
      content: textarea.value,
      userId: boardData.value.userId,
    }
    boardScrollView.removeChild(boardContent)
    createBtn.style.display = 'inline-flex'
    store.dispatch('chatStore/createBoard', credentials)
  })
}
</script>
<style>
.board {
  display: flex;
  flex-direction: column;
  margin: 12px 32px 0px 32px;
  flex-grow: 1;
}
.board-title {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 12px;
  background-color: #f5f5f5;
  font-size: 1.2rem;
  border-radius: 10px 10px 0px 0px;
}
.board-list {
  flex-grow: 1;
}
.board-content {
  display: flex;
  font-size: 1rem;
  font-family: Pretendard;
  flex-direction: column;
  margin: 20px 0px;
  padding: 16px;
  /* border-radius: 10px; */
  max-height: 100px;
  transition: all 0.3s;
  box-shadow: 0px 1.5px 1.5px rgba(0, 0, 0, 0.25);
}
.board-content.collapsed {
  max-height: 220px;
  transition: all 0.3s;
}
.board-content-body {
  overflow: hidden;
  padding-bottom: 4px;
}
.board-content-date {
  margin-top: 8px;
  align-self: flex-end;
  font-size: 0.7rem;
}
.create-time {
  text-align: end;
}
.create-btn {
  position: fixed;
  left: 50%;
  bottom: 5%;
  transform: translateX(-50%);
}
.create-btn span {
  font-family: Pretendard;
  font-weight: 600;
}
.board-create-content {
  font-family: Pretendard;
  padding: 8px;
  width: 95%;
  height: 100%;
  resize: none;
  border: 0px;
  outline: 0px;
  border-radius: 10px;
  background-color: #f5f5f5;
}
.board-create-content::placeholder {
  text-align: center;
  color: rgba(0, 0, 0, 0.452)
}
.board-create-content::-webkit-scrollbar {
  display: none;
}
.create-form-btn {
  display: flex;
  justify-content: center;
}
#boardScroll .el-scrollbar__view {
  transition: all 1s;
}
.add-board {
  animation: addBoard .2s linear;
}
@keyframes addBoard {
  from {
    transform: scale(0);
    opacity: 0;
  }
  to {
    transform: scale(1);
    opacity: 1;
  }
}
.remove-board {
  animation: removeBoard .2s linear;
}
@keyframes removeBoard {
  from {
    transform: scale(1);
    opacity: 1;
  }
  to {
    transform: scale(0);
    opacity: 0;
  }
}
.submit-board {
  animation: submitBoard .2s linear;
}
@keyframes submitBoard {
  from {
    opacity: 0;
    transform: translateY(20%);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>