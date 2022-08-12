<template>
  <div class="onboarding">
    <div class="onboarding-one">
      우리는 이걸
      </div>
    <div class="onboarding-two">
      <img src="../../assets/logo.png" alt="onboardingImg" class="onboarding-img">
      <span class="onboarding-la">라</span>
    </div>
    <div class="onboarding-three">
      부르기로 하였다.
    </div>
    <div class="onboarding-start">
      <el-button color="#FFD96A" size="large" class="start-btn">시작하기</el-button>
      <div class="start-login">
        이미 낭만하셨나요?<span class="login-text"> 로그인</span>
      </div>
    </div>
  </div>
</template>
<script setup>

</script>
<style>
.onboarding {
  position: fixed;
  top: 25%;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 1.3rem;
}

.onboarding-one {
  margin-bottom: 20px;
  opacity: 0;
  animation: oneIn .5s forwards;
}
.onboarding-two {
  display: flex;
  align-items: flex-end;
  margin-bottom: 20px;
}
.onboarding-img {
  animation: imgIn 1s forwards;
  opacity: 0;
  animation-delay: .5s;
}
.onboarding-la {
  animation: laIn .5s forwards;
  opacity: 0;
  animation-delay: 1s;
}
.onboarding-three {
  opacity: 0;
  animation: threeIn .5s forwards;
  animation-delay: 1.5s;
}
.onboarding-start {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 65%;
  opacity: 0;
  animation: loginIn 1s forwards;
  animation-delay: 2s;
}
.start-btn {
  font-size: 1.1rem !important;
}
.start-login {
  margin-top: 12px;
  font-size: 1rem;
}
.login-text {
  color: #FFB6B9;
}
@keyframes oneIn {
  0% {
    transform: translateY(-1200px) scale(0.7);
    opacity: 0.7;
  }
  80% {
    transform: translateY(0px) scale(0.7);
    opacity: 0.7;
  }
  100% {
    transform: scale(1);
    opacity: 1;
  }
}
@keyframes imgIn {
  from {
    opacity: 0;
    transform: scale3d(0.1, 0.1, 0.1) translate3d(-1000px, 0, 0);
    animation-timing-function: cubic-bezier(0.55, 0.055, 0.675, 0.19);
  }

  60% {
    opacity: 1;
    transform: scale3d(0.475, 0.475, 0.475) translate3d(10px, 0, 0);
    animation-timing-function: cubic-bezier(0.175, 0.885, 0.32, 1);
  }
  to {
    opacity: 1;
  }
}
@keyframes threeIn {
  0% {
    -webkit-transform: translateY(1200px) scale(0.7);
    transform: translateY(1200px) scale(0.7);
    opacity: 0.7;
  }
  80% {
    -webkit-transform: translateY(0px) scale(0.7);
    transform: translateY(0px) scale(0.7);
    opacity: 0.7;
  }
  100% {
    -webkit-transform: scale(1);
    transform: scale(1);
    opacity: 1;
  }
}
@keyframes laIn {
  0% {
    transform: translateX(2000px) scale(0.7);
    opacity: 0.7;
  }
  80% {
    transform: translateX(0px) scale(0.7);
    opacity: 0.7;
  }
  100% {
    transform: scale(1);
    opacity: 1;
  }
}
@keyframes loginIn {
  from {
    opacity: 0;
    transform: translate3d(0, 100%, 0);
  }

  to {
    opacity: 1;
    transform: translate3d(0, 0, 0);
  }
}
</style>