<template>
  <!-- flex -->
  <div id="busToggle" class="bus-toggle">
    <div class="busstop-mini">
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
  </div>
  <div id="busstopList" class="busstop-list">
    <div>1</div>
    <div>2</div>
    <div>3</div>
    <div>4</div>
  </div>

</template>
<script setup>
import { ref, onMounted } from 'vue'

onMounted(() => {
  // 버스노선 collapse 구현
  const busstopList = document.querySelector('#busstopList');
  const busToggle = document.querySelector('#busToggle');
  busstopList.classList.add('collapsed');
  function busstopListToggle() {
      busstopList.classList.toggle('collapsed');
  }
  busToggle.addEventListener('click', busstopListToggle);
})
</script>
<style>
.busstop-mini {
  display: flex;
  margin-left: 21px;
  width: 100%;
  justify-content: space-evenly;
}
.busstop-small {
  font-size: 0.9rem;
}
.busstop-big {
  font-size: 1.5rem;
}

.busstop-list {
  background-color: #ddd;
  overflow: hidden;
  max-height: 1800px;
  -webkit-transition: max-height 0.3s; 
  -moz-transition: max-height 0.3s; 
  -ms-transition: max-height 0.3s; 
  -o-transition: max-height 0.3s; 
  transition: max-height 0.3s;
}
.busstop-list.collapsed {
  max-height: 0;
}
</style>