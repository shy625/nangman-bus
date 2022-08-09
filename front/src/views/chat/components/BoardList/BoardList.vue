<template>
  <div class="board">
    <div class="board-title">방명록</div>
    <div class="board-list">
      <el-scrollbar id="boardScroll" height="75vh">
        <div id="boardContent" class="board-content">
          <div class="board-content-body">
            안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕
          </div>
          <div class="board-content-date">
            <div class="create-date">2022년 8월 1일</div>
            <div class="create-time">11:40</div>
          </div>
        </div>
        <div class="board-content">
          <div class="board-content-body">
            바이바이맨~
          </div>
          <div class="board-content-date">
            <div class="create-date">2022년 8월 1일</div>
            <div class="create-time">11:40</div>
          </div>
        </div>
      <!-- 이 밑으로 추가! -->
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
import { ref, onMounted } from 'vue'

onMounted(() => {
  const boardScrollView = document.querySelector('#boardScroll .el-scrollbar__view')
  const boardScroll = document.querySelector('#boardScroll .el-scrollbar__wrap')
  boardScroll.scrollTo(0, boardScrollView.scrollHeight)

  const boardContent = document.querySelector('#boardContent')
  function boardContentToggle() {
      boardContent.classList.toggle('collapsed')
  }
  boardContent.addEventListener('click', boardContentToggle)
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
    boardScrollView.removeChild(boardContent)
    createBtn.style.display = 'inline-flex'
    const createBoardcontent = document.createElement('div')
    createBoardcontent.classList.add('board-content')
    // 배경색 입력
    createBoardcontent.setAttribute('style', 'background-color: #FFD96A')
    const boardContentBody = document.createElement('div')
    boardContentBody.classList.add('board-content-body')
    // 내용 입력
    boardContentBody.innerText = textarea.value
    const boardContentDate = document.createElement('div')
    boardContentDate.classList.add('board-content-date')
    const createDate = document.createElement('div')
    createDate.classList.add('create-date')
    // 날짜 입력
    createDate.innerText = '2022년 8월 8일'
    const createTime = document.createElement('div')
    createTime.classList.add('create-time')
    // 시간 입력
    createTime.innerText = '17:55'

    createBoardcontent.append(boardContentBody, boardContentDate)
    // console.log(createBoardcontent)
    boardContentDate.append(createDate, createTime)
    boardScrollView.append(createBoardcontent)
    createBoardcontent.classList.add('submit-board')
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
  border-radius: 10px;
  max-height: 100px;
  /* background-color: #FFD96A; */
  transition: all 0.3s;
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
  font-family: BMHANNAPro;
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