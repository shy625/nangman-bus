<template>
  <!-- flex -->
  <div class="busstops">
    <div class="busstops-small">
      이전
    </div>
    <div class="busstops-icon">
      >>
    </div>
    <div class="busstops-big">
      현재
    </div>
    <div class="busstops-icon">
      >>
    </div>
    <div class="busstops-small">
      다음
    </div>
  </div>
</template>
<script setup>

</script>
<style>
.busstops {
  display: flex;
  margin-left: 21px;
  width: 100%;
  justify-content: space-evenly;
}
.busstops-small {
  font-size: 0.9rem;
  line-height: 3.5;
}
.busstops-big {
  font-size: 1.5rem;
}
</style>