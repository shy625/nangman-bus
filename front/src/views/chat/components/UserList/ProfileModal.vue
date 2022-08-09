<template>
  <div id="profileContainer">
    <div class="modal-profile-background">
      <div class="modal-profile">
        <div class="profile-exit">X</div>
        <div class="modal-profile-title">
          <div class="modal-profile-icon">O</div>
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
            <div class="profile-detail-title">나와 함께한 시간</div>
            <div>
              <span class="profile-detail-content">10</span><span>회</span>
            </div>
            <ProfileTooltip></ProfileTooltip>
          </div>
          <div class="modal-profile-detail">
            <div class="profile-detail-title">나와 함께한 시간</div>
            <div>
              <span class="profile-detail-content">10</span><span>회</span>
            </div>
            <ProfileTooltip></ProfileTooltip>
          </div>
          <div class="modal-profile-detail">
            <div class="profile-detail-title">나와 함께한 시간</div>
            <div>
              <span class="profile-detail-content">10</span><span>회</span>
            </div>
            <ProfileTooltip></ProfileTooltip>
          </div>
          <div class="modal-profile-detail">
            <div class="profile-detail-title">나와 함께한 시간</div>
            <div>
              <span class="profile-detail-content">10</span><span>회</span>
            </div>
            <ProfileTooltip></ProfileTooltip>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import ProfileTooltip from './ProfileTooltip.vue'
import { ref, onMounted } from 'vue'

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
  margin: 12px 0px 24px 0px;
}
.modal-profile-icon {
  font-size: 2rem;
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
.profile-detail-content {
  color: #FF9090;
}
.profile-hint {
  position: absolute;
  top: 12px;
  right: 12px;
  background-color: black;
  color: white;
  border-radius: 50%;
  width: 18px;
  height: 18px;
}
.modal-profile-detail {
  position: relative;
}
.profile-exit {
  position: fixed;
  bottom: -80px;
  left: 50%;
  background-color: black;
  color: #f5f5f5;
  padding: 8px;
  border-radius: 5px;
}
.profile-exit:hover {
  cursor: pointer;
}
</style>