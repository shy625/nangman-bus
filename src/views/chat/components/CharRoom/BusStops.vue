<template>
  <div id="busstop" class="busstop">
    <div id="busToggle" class="bus-toggle">
      <div class="busstop-mini">
        <div class="busstop-mini-above">
          <div class="busstop-small">
            이전
          </div>
          <div class="busstop-icon">
            <span class="busstop-icon-one">></span>
            <span class="busstop-icon-two">></span>
          </div>
          <div class="busstop-big">
            현재
          </div>
          <div class="busstop-icon">
            <span class="busstop-icon-one">></span>
            <span class="busstop-icon-two">></span>          
          </div>
          <div class="busstop-small">
            다음
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
      <el-scrollbar height="30vh">
        <el-timeline>
          <el-timeline-item
            v-for="(busstop, index) in busData.busstopInfos"
            :key="index"
            :hide-timestamp="true"
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
  busstopInfos: computed(() => store.getters['chatStore/busstopInfos'])
})

const clickBusstop = e => {
  // 하차정류장 지정 디스패치
  console.log(e.target.innerText)
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
  padding: 8px 0px;
  border-radius: 6px 6px 0px 0px;
  background-color: #FF9090;
}
.busstop-mini-above {
  display: flex;
  width: 100%;
  justify-content: space-evenly;
  align-items: center;
}
.busstop-small {
  font-size: 0.9rem;
}
.busstop-big {
  font-size: 1.5rem;
}

.busstop-list {
  border-radius: 0px 0px 6px 6px;
  background-color: #FF9090;
  position: absolute;
  width: 100%;
  overflow: hidden;
  max-height: 30vh;
  opacity: 0.97;
  /* -webkit-transition: max-height 0.3s; 
  -moz-transition: max-height 0.3s; 
  -ms-transition: max-height 0.3s; 
  -o-transition: max-height 0.3s;  */
  transition: max-height 0.25s;
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
</style>