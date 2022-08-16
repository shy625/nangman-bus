<template>
  <div id="profileContainer">
    <div class="modal-profile-background">
      <div class="modal-profile">
        <div class="profile-exit">X</div>
        <div class="modal-profile-title">
          <img src="../../../../assets/user-pink.png" class="modal-profile-icon" style="height: 80px;width:80px">
          <div class="modal-profile-nick">
            <div class="modal-profile-nickname">헤라클레스</div>
            <div class="modal-profile-birth"></div>
          </div>
        </div>
        <div class="modal-profile-content">
          <div class="profile-hint">
            ?
          </div>
          <div class="modal-profile-detail">
            <div class="profile-detail-title">나와<br>함께한 시간</div>
            <div>
              <span class="profile-detail-content">{{ profileData.countNumTogether}}</span><span>회</span>
            </div>
            <div class="profile-tooltip">
              함께 낭만채팅을 이용한 횟수에요.
            </div>
          </div>
          <div class="modal-profile-detail">
            <div class="profile-detail-title">한달 간<br>이 버스를 탄 횟수</div>
            <div>
              <span class="profile-detail-content">{{ profileData.countMonthlyUsed }}</span><span>회</span>
            </div>
            <div class="profile-tooltip">
              한달 간 이 버스에서 낭만채팅을 이용한 횟수에요.
            </div>
          </div>
          <div class="modal-profile-detail">
            <div class="profile-detail-title">지금<br>이 버스에 있던 시간</div>
            <div>
              <!-- v-if 시간 값 없으면 안나오게 -->
              <span class="profile-detail-content">
                {{ profileData.inHour }}
              </span>
              <span>시간</span>
              <span class="profile-detail-content">
                {{ profileData.inMinute }}
              </span>
              <span>분</span>
            </div>
            <div class="profile-tooltip">
              지금 이 버스에서 낭만채팅을 이용한 누적 시간이에요.
            </div>          
          </div>
          <div class="modal-profile-detail">
            <div class="profile-detail-title">내릴<br>정류장</div>
            <div>
              <span v-if="!profileData.getoffRoute" class="profile-detail-content" style="font-style: italic">선택하지 않았어요</span>
              <span v-else class="profile-detail-content">{{ profileData.getoffRoute }}</span>
            </div>
            <div class="profile-tooltip">
              선택한 하차 정류장이에요.
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
// import { useStore } from 'vuex'

// const store = useStore()
const profileData = ref({
  countNumTogether: 13,
  countMonthlyUsed: 19,
  inHour: 1,
  inMinute: 23,
  getoffRoute: '경남아너스빌 11단지',
})

onMounted(() => {
  const profileHint = document.querySelector('.profile-hint')
  const profileTooltips = document.querySelectorAll('.profile-tooltip')
  profileHint.addEventListener('click', () => {
    profileTooltips.forEach(tooltip => {
      tooltip.classList.toggle('tooltip-active')
    })
  })
  profileTooltips.forEach(tooltip => {
    tooltip.addEventListener('click', () => {
      profileTooltips.forEach(tooltip => {
        tooltip.classList.toggle('tooltip-active')
      })
    })
  })
})
</script>
<style>
#profileContainer {
  position: fixed;
	left: 0;
	top: 0;
	display: table;
	height: 100%;
	width: 100%;
	z-index: 2;
	transform: scale(0);
}
.modal-profile-background {
	display: table-cell;
	text-align: center;
	vertical-align: middle;
}
.modal-profile {
	display: block;
	position: relative;
  background-color: #f5f5f5;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 4px 4px 3px rgba(0, 0, 0, 0.25);
}
#profileContainer.profile-container {
	transform: scale(1);
}
#profileContainer.profile-container .modal-profile-background {
	background: transparent;
	animation: fadeIn 0.5s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}
#profileContainer.profile-container .modal-profile-background .modal-profile {
	transform: translateX(-1500px);
	animation: roadRunnerIn 0.3s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}
#profileContainer.profile-container.out {
	animation: quickScaleDown 0s .5s linear forwards;
}
#profileContainer.profile-container.out .modal-profile-background {
	animation: fadeOut 0.5s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}
#profileContainer.profile-container.out .modal-profile-background .modal-profile {
	animation: roadRunnerOut 0.5s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}
@keyframes roadRunnerIn {
	0% {
		transform: translateX(-1500px) skewX(30deg) scaleX(1.3);
	}
	70% {
		transform: translateX(50px) skewX(0deg) scaleX(0.9);
	}
	100% {
		transform: translateX(0px) skewX(0deg) scaleX(1);
	}
}
@keyframes roadRunnerOut {
	0% {
		transform: translateX(0px) skewX(0deg) scaleX(1);
	}
	30% {
		transform: translateX(-100px) skewX(-5deg) scaleX(0.9);
	}
	100% {
		transform: translateX(1500px) skewX(30deg) scaleX(1.3);
	}
}
@keyframes fadeIn {
	0% {
		background: transparent;
	}
	100% {
    backdrop-filter: blur(3px);
	}
}
@keyframes quickScaleDown {
	0% {
		transform: scale(1);
	}
	99.9% {
		transform: scale(1);
	}
	100% {
		transform: scale(0);
	}
}
@keyframes fadeOut {
	0% {
    backdrop-filter: blur(3px);
	}
	100% {
		background: transparent;
	}
}
.modal-profile-title {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 12px 0px 24px 0px;
}
.modal-profile-icon {
  animation: profileIconShake 5s infinite;
}
@keyframes profileIconShake {
  from,
  25% {
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
  }
  3%,
  8%,
  13%,
  18%,
  23% {
    -webkit-transform: translate3d(0, -10px, 0);
    transform: translate3d(0, -10px, 0);
  }
  5%,
  10%,
  15%,
  20% {
    -webkit-transform: translate3d(0, 10px, 0);
    transform: translate3d(0, 10px, 0);
  }
}
.modal-profile-nick {
  font-size: 1.3rem;
}
.modal-profile-content {
  background-color: white;
  border-radius: 10px;
  padding: 64px 32px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  row-gap: 80px;
  font-size: 1.1rem;
  position: relative;
}
.profile-detail-title {
  margin-bottom: 12px;
}
.profile-detail-content {
  color: #FF9090;
}
.profile-hint {
  position: absolute;
  top: 12px;
  right: 12px;
  background-color: #FFD96A;
  color: white;
  border-radius: 50%;
  width: 18px;
  height: 18px;
}
.modal-profile-detail {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: relative;
}
.profile-exit {
  position: fixed;
  bottom: 30px;
  left: 50%;
  background-color: #f5f5f5;
  color: black;
  font-weight: bold;
  padding: 6px 8px;
  border-radius: 5px;
  z-index: 3;
  
}
.profile-exit:hover {
  cursor: pointer;
}
.profile-tooltip {
  padding: 0;
  height: 0;
  position: absolute;
  bottom: 120%;
  left: 28px;
  font-size: .7rem;
  background-color: #FFD96A;
  color: black;
  text-align: left;
  border-radius: 5px;
  max-width: 65%;
  z-index: -1;
  transition: all .25s;
  font-family: Pretendard;
}
.profile-tooltip::after {
  display: none;
  content: " ";
  position: absolute;
  border-style: solid;
  border-width: 5px;
  top: 100%;
  left: 40%;
  border-color: #FFD96A transparent transparent transparent;
}
.tooltip-active {
  padding: 4px;
  z-index: 1;
  height: 45px;
  transition: all .3s ;
}
.tooltip-active.profile-tooltip::after {
  display: block;
}
</style>