<template>
      <button @click="clickBbtn">임시버튼ㅋ</button>

  <div id="users" class="users">
    <div class="users-title">
      <div class="user-count">
        승객 {{ data.countUser }}명
      </div>
      <div class="option-whisper">
        <img class="whisper-img" src="" alt="귓">
        <div class="whisper-text">귓속말 거부</div>
      </div>
    </div>
    <div class="user-list">
      <el-scrollbar height="80vh">
        <div class="driver">
          <img class="steer" src="../../../../assets/steer.png" alt="steerImg" width="68">
          <div class="cover">
            <div class="cover-title">
              "저 이번에 내려요"
            </div>
            <div class="list-cover">
              <div class="getoff-list">
                <div class="getoff-user">
                  헤라클레스
                </div>
                <div class="getoff-user">
                  가우르구라
                </div>
                <!-- 맨위 사람과 같은 사람 한번더 넣어줘야 자연스럽게 돔 -->
                <div class="getoff-user">
                  헤라클레스
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- <div id="me" class="user">
          <div class="icon">
            O
          </div>
          <div class="profile">
            <div class="profile-nick">
              <div class="nickname">
                가우르구라
                <div class="birth">
                  o
                </div>
              </div>
              <div class="profile-me">(나)</div>
            </div>
            <div class="destination">
              흥덕지구 11단지 경남아너스빌
            </div>
          </div>
        </div> -->
        <div class="test-users">
          <div class="test-user">
            <div class="test-icon">
              <!-- <img src="../../../../assets/seat2.png" alt="seat" class="seat-icon" width="96" height="96"> -->
              <img src="../../../../assets/user-pink.png" alt="pink" class="user-icon">
            </div>
            <div class="test-name">
              헤라클레스
            </div>
          </div>
          <div class="test-user">
            <div class="test-icon">
              <img src="../../../../assets/user-yellow.png" alt="yellow" class="user-icon">
            </div>
            <div class="test-name">
              홀리싯
            </div>
          </div>
          <div class="test-user">
            <div class="test-icon">
              <img src="../../../../assets/user-red.png" alt="red" class="user-icon">
            </div>
            <div class="test-name">
              캬오오옭
            </div>
          </div>
          <div class="test-user">
            <div class="test-icon">
              <img src="../../../../assets/user-pinker.png" alt="pinker" class="user-icon">
            </div>
            <div class="test-name">
              수퍼파워현규몬스터
            </div>
          </div>
        </div>
        <!-- <div id="two" class="user">
          <div class="icon">
            O
          </div>
          <div class="profile">
            <div class="profile-nick">
              <div class="nickname">
                헤라클레스
              </div>
              <div class="birth">
                
              </div>
            </div>
            <div class="destination">
              목감 지하차도 윤병찬 생가
            </div>
          </div>
        </div> -->
      </el-scrollbar>
    </div>
  </div>
  <ProfileModal></ProfileModal>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import ProfileModal from './ProfileModal.vue'

const data = ref({
  countUser: 21,
})

onMounted(() => {
  const user = document.querySelectorAll('.test-user')
  const profileContainer = document.querySelector('#profileContainer')
  const users = document.querySelector('#users')
  const body = document.querySelector('body')
  const elCarouselArrows = document.querySelectorAll('.el-carousel__arrow')
  const profileExit = document.querySelector('.profile-exit')

  user.forEach(u => {
    u.addEventListener('click', () => {
      profileContainer.removeAttribute('class')
      profileContainer.classList.add('profile-container')
      users.removeAttribute('class')
      users.classList.add('users')
      body.classList.add('profile-active')
      elCarouselArrows.forEach(arrow => {
        arrow.disabled = true
        arrow.classList.add('arrow-profile-active')
      })
    })
  })
  profileExit.addEventListener('click', () => {
    profileContainer.classList.add('out')
    users.classList.add('out')
    body.classList.remove('profile-active')
    elCarouselArrows.forEach(arrow => {
    arrow.disabled = false
    arrow.classList.remove('arrow-profile-active')
    })
  })
})
// 유저 추가
const clickBbtn = () => {
  const addUser = document.createElement('div')
  addUser.classList.add('test-user')
  const addIcon = document.createElement('div')
  addIcon.classList.add('test-icon')
  const img = document.createElement('img')
  img.src = `/src/assets/user-yellow.png`
  img.alt = 'red'
  img.classList.add('user-icon')
  const addName = document.createElement('div')
  addName.classList.add('test-name')
  addName.innerText = '아몰라몰라'

  const testUsers = document.querySelector('.test-users')
  addUser.append(addIcon, addName)
  addIcon.append(img)
  testUsers.append(addUser)
  addUser.classList.add('add-user')
}
</script>
<style>
.add-user {
  animation: bounce 1s;
}
@keyframes bounce {
  from,
  60%,
  75%,
  90%,
  to {
    animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
  }

  from {
    opacity: 0;
    transform: translate3d(0, 3000px, 0) scaleY(5);
  }

  60% {
    opacity: 1;
    transform: translate3d(0, -20px, 0) scaleY(0.9);
  }

  75% {
    transform: translate3d(0, 10px, 0) scaleY(0.95);
  }

  90% {
    transform: translate3d(0, -5px, 0) scaleY(0.985);
  }

  to {
    transform: translate3d(0, 0, 0);
  }
}
.test-users {
  display: grid;
  grid-template-columns: 1fr 1fr;
  column-gap: 28px;
  row-gap: 20px;
  margin-top: 48px;
}
.test-user {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.test-icon {
  width: fit-content;
  position: relative;
}
.user-icon {
  width: 70px;
  height: 70px;
}
/* .seat-icon {
  position: absolute;
  top: 8%;
  left: 0;
  opacity: .3;
  z-index: -1;
} */
.test-name {
  font-size: 1.1rem;
}
.steer {
  animation: swing 4s infinite;
}
@keyframes swing {
  5% {
    -webkit-transform: rotate3d(0, 0, 1, 15deg);
    transform: rotate3d(0, 0, 1, 15deg);
  }

  10% {
    -webkit-transform: rotate3d(0, 0, 1, -10deg);
    transform: rotate3d(0, 0, 1, -10deg);
  }

  15% {
    -webkit-transform: rotate3d(0, 0, 1, 5deg);
    transform: rotate3d(0, 0, 1, 5deg);
  }

  20% {
    -webkit-transform: rotate3d(0, 0, 1, -5deg);
    transform: rotate3d(0, 0, 1, -5deg);
  }

  25% {
    -webkit-transform: rotate3d(0, 0, 1, 0deg);
    transform: rotate3d(0, 0, 1, 0deg);
  }
}
.arrow-profile-active {
  display: none !important;
}
.users {
  display: flex;
  flex-grow: 1;
  flex-direction: column;
  margin: 12px 32px 0px 32px;
  position: relative;
  z-index: 0;
}
.users-title {
  display: flex;
  justify-content: space-between;
  padding: 16px;
  background-color: #f5f5f5;
  border-radius: 10px 10px 0px 0px;
  margin-bottom: 16px;
}
.user-count {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.2rem;
}
.option-whisper {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 0.7rem;
}
.user-list {
  flex-grow: 1;
  position: relative;
}
.user-list .el-scrollbar__wrap::after {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  height: 1.5%;
  width: 98%;
  border-top: 4px solid #F34949;
  border-radius: 10px 10px 0 0;
  border-left: 4px solid #F34949;
  border-right: 4px solid #F34949;
}
.user-list .el-scrollbar__wrap {
  border-bottom: 4px solid #F34949;
  border-radius: 10px;
}
.driver {
  display: flex;
  justify-content: space-between;
  margin: 32px 40px;
}
.cover {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  padding: 12px;
  background-color: black;
  border: 2px solid #fff;
  border-radius: 5px;
  padding: 12px;
  box-shadow: 0 0 .1rem #fff,
            0 0 .1rem #fff,
            0 0 1rem #F34949,
            0 0 0.5rem #F34949,
            0 0 2rem #F34949,
            inset 0 0 1rem #F34949; 
}
.cover-title {
  margin-bottom: 8px;
  color: #FF9090;
  text-shadow:
    0 0 7px #FF9090,
    0 0 10px #FF9090,
    0 0 21px #FF9090,
    0 0 42px #F34949,
    0 0 82px #F34949,
    0 0 92px #F34949,
    0 0 102px #F34949,
    0 0 151px #F34949;
}
.list-cover {
  width: 100%;
  height: 16px;
  overflow: hidden;
  align-self: center;
}
.getoff-list {
  animation: getoffListScrollUp 4s linear infinite normal;
}
@keyframes getoffListScrollUp {
  from {
    transform: translateY(0);
  }
  to {
    transform: translateY(-67%);
  }
}
.getoff-user {
  text-align: center;
  width: 100%;
  color: #FFD96A;
  text-shadow:
    0 0 7px #FFD96A,
    0 0 90px #FFD96A,
    0 0 80px #ff8e8e,
    0 0 100px #ff8e8e,
    0 0 132px #ff8e8e;
}
.user {
  display: flex;
  margin: 16px;
}
.icon {
  margin-right: 8px;
}
.profile {
  display: flex;
  flex-direction: column;
}
.profile-nick {
  display: flex;
}
.nickname {
  font-size: 1.2rem;
  display: flex;
}
.destination {
  font-size: 0.8rem;
  margin-top: 8px;
}
.profile-me {
  margin-left: 4px;
}
.profile-active {
  overflow: hidden;
}
</style>