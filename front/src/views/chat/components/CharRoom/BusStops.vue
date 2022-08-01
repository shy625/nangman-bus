<template>
  <div class="busstop">
    <div id="busToggle" class="bus-toggle">
      <div class="busstop-mini">
        <div class="busstop-mini-above">
          <div class="busstop-small">
            이전
          </div>
          <div class="busstop-icon">
            >>
          </div>
          <div class="busstop-big">
            현재
          </div>
          <div class="busstop-icon">
            >>
          </div>
          <div class="busstop-small">
            다음
          </div>
        </div>
        <div class="busstop-mini-below">
          <div v-if="!data.isToggled">
            펴기
          </div>
          <div v-else>
            접기
          </div>
        </div>
      </div>
    </div>
    <div id="busstopList" class="busstop-list">
      <el-scrollbar height="30vh">
        <el-timeline>
          <el-timeline-item
            v-for="(busstop, index) in busstops"
            :key="index"
            :hide-timestamp="true"
          >
            <div>{{ busstop.content }}</div>
          </el-timeline-item>
        </el-timeline>
      </el-scrollbar>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'

let data = ref({ isToggled: false })

// 더미데이터
const busstops = [
  {
    content: 'Event start',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Approved',
  },
  {
    content: 'Success',
  },
]

onMounted(() => {
  // 버스노선 collapse 구현
  const busstopList = document.querySelector('#busstopList')
  const busToggle = document.querySelector('#busToggle')
  busstopList.classList.add('collapsed')
  function busstopListToggle() {
      busstopList.classList.toggle('collapsed')
      if (data.value.isToggled === true) {
        data.value.isToggled = false
      } else {
        data.value.isToggled = true
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
  /* opacity: 0.95; */

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
</style>