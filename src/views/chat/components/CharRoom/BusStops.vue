<template>
  <div id="busstop" class="busstop">
    <div id="busToggle" class="bus-toggle">
      <div class="busstop-mini">
        <div class="busstop-mini-above">
          <div class="busstop-small">
            인천시청후문
          </div>
          <div class="busstop-icon">
            <span class="busstop-icon-one">></span>
            <span class="busstop-icon-two">></span>
          </div>
          <div class="busstop-big">
            래미안아파트파이낸셜뉴스
          </div>
          <div class="busstop-icon">
            <span class="busstop-icon-one">></span>
            <span class="busstop-icon-two">></span>          
          </div>
          <div class="busstop-small">
            모래내시장역(3번출구)
          </div>
        </div>
        <div class="busstop-mini-below">
          <div v-if="!busData.isToggled">
            <img src="../../../../assets/list-up.png" alt="up">
          </div>
          <div v-else>
            <img src="../../../../assets/list-down.png" alt="down">
          </div>
        </div>
      </div>
    </div>
    <div id="busstopList" class="busstop-list collapsed">
      <div class="busstop-hint">&#34;정류장을 누르면 내릴 정류장으로 등록해요&#34;</div>
      <el-scrollbar height="30vh">
        <el-timeline>
          <el-timeline-item
            v-for="(busstop, index) in busData.busstopInfos"
            :key="index"
            :hide-timestamp="true"
            :hollow="true"
            color="#F34949"
          >
            <div 
              class="busstop-name" 
              @click="clickBusstop"
            >
              {{ busstop.nodeName }}
            </div>
          </el-timeline-item>
        </el-timeline>
      </el-scrollbar>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const busData = ref({ 
  isToggled: false,
  busstopInfos: computed(() => store.getters['chatStore/busstopInfos']),
  client: computed(() => store.getters['chatStore/client']),
  userId: computed(() => store.getters['chatStore/userId']),
  sessionId: computed(() => store.getters['chatStore/sessionId']),
})

const clickBusstop = e => {
  // 하차정류장 지정 디스패치
  // console.log(e.target.innerText)
  e.target.classList.add('busstop-pulse')
  setTimeout(() => {
    e.target.classList.remove('busstop-pulse')
  }, 500)
  e.target.scrollIntoView({ block: 'center', behavior: 'smooth' })  // 타겟 스크롤 포커스! -> 현재 정류장 기준으로!

  // 버스 하차 정류장 pub
  const payload = {
    userId: busData.value.userId,
    busStopId: 12,
  }
  console.log('하차 전송')
  busData.value.client.publish({
    destination: '/pub/chat/rooms/' + busData.value.sessionId + '/outBusStop',
    body: JSON.stringify(payload),
  })
}

onMounted(() => {
  const busstopList = document.querySelector('#busstopList')
  const busToggle = document.querySelector('#busToggle')
  // busstopList.classList.add('collapsed')
  function busstopListToggle() {
    busstopList.classList.toggle('collapsed')
    if (busData.value.isToggled === true) {
      busData.value.isToggled = false
    } else {
      busData.value.isToggled = true
    }
  }
  busToggle.addEventListener('click', busstopListToggle)
})

</script>
<style>
.busstop {
  margin: 12px 32px 0px 32px;
  position: relative;
}
.bus-toggle {
  width: 100%;
}
.busstop-mini {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 8px;
  border-radius: 6px 6px 0px 0px;
  background-color: #FF9090;
}
.busstop-mini-above {
  display: flex;
  width: 100%;
  justify-content: space-evenly;
  align-items: center;
  font-family: Pretendard;
}
.busstop-small {
  text-align: center;
  width: 60px;
  font-size: 0.75rem;
}
.busstop-big {
  text-align: center;
  width: 100px;
  font-size: 1rem;
  font-weight: bold;
}
.busstop-hint {
  margin: 4px;
  font-size: 0.7rem;
  font-style: italic;
  text-align: end;
  font-weight: bold;
  color: #F34949;
}
.busstop-list {
  border-radius: 0px 0px 6px 6px;
  background-color: #FF9090;
  position: absolute;
  width: 100%;
  overflow: hidden;
  max-height: 35vh;
  /* opacity: 0.97; */
  /* -webkit-transition: max-height 0.3s; 
  -moz-transition: max-height 0.3s; 
  -ms-transition: max-height 0.3s; 
  -o-transition: max-height 0.3s;  */
  transition: max-height 0.25s;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.25);
}
.busstop-list.collapsed {
  max-height: 0;
}

.el-timeline-item {
  padding: 8px 0px !important;
  line-height: 1.5;
}
.busstop-mini-below img {
  filter: brightness(0);
}
.busstop-name {
  font-family: Pretendard;
  color: black;
}
.busstop-icon-one {
  animation: busstopIconFadeOne 2s infinite;
}
@keyframes busstopIconFadeOne {
  from {
    opacity: 1;
  }
  50% {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
.busstop-icon-two {
  opacity: 0;
  animation: busstopIconFadeTwo 2s infinite;
}
@keyframes busstopIconFadeTwo {
  from {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
.busstop-pulse {
  animation: busstopPulse .5s;
}
@keyframes busstopPulse {
  from {
    font-weight: normal;
    transform: scale3d(1, 1, 1);
  }
  50% {
    font-weight: bold;
    transform: scale3d(1.05, 1.05, 1.05);
  }
  to {
    font-weight: normal;
    transform: scale3d(1, 1, 1);
  }
}
</style>