<template>
  <div class="profile-tooltip">
    이것은 무엇무엇무어어어어어어어어엇을 말합니다.
  </div>
</template>
<script setup>

</script>
<style>
.profile-tooltip {
  padding: 0;
  height: 0;
  position: absolute;
  bottom: 120%;
  left: 28px;
  font-size: .65rem;
  background-color: black;
  color: white;
  text-align: left;
  border-radius: 5px;
  max-width: 65%;
  z-index: -1;
  transition: all .25s;
}
.profile-tooltip::after {
  display: none;
  content: " ";
  position: absolute;
  border-style: solid;
  border-width: 5px;
  top: 100%;
  left: 40%;
  border-color: black transparent transparent transparent;
}
.tooltip-active {
  padding: 4px;
  z-index: 1;
  height: 30px;
  transition: all .3s ;
}
.tooltip-active.profile-tooltip::after {
  display: block;
}
</style>