<template>
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
              저 다음에 내려요
            </div>
            <div class="list-cover">
              <div class="getoff-list">
                <div class="getoff-user">
                  헤라클레스
                </div>
                <div class="getoff-user">
                  가우르구라
                </div>
                <div class="getoff-user">
                  헤라클레스
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="me" class="user">
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
        </div>
        <div id="one" class="user">
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
        </div>
        <div id="two" class="user">
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
        </div>
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
  const user = document.querySelectorAll('.user')
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
</script>
<style>
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
.user-list::after {
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
.driver {
  display: flex;
  justify-content: space-around;
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