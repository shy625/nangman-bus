<template>
  <div class="profile-tooltip">
    이것은 무엇무엇무어어어어어어어어엇을 말합니다.
  </div>
</template>
<script setup>

</script>
<style>
.profile-tooltip {
  /* display: hidden; */
  padding: 0;
  max-height: 0;
  position: absolute;
  top: -56px;
  left: 16px;
  font-size: .8rem;
  background-color: black;
  color: white;
  text-align: left;
  border-radius: 5px;
  /* padding: 4px; */
  max-width: 65%;
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
  /* display: block; */
  padding: 4px;
  max-height: 100px;
  transition: max-height .3s ;
}
.tooltip-active.profile-tooltip::after {
  display: block;
}
</style>