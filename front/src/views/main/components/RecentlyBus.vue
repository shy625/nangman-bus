<template>
  <div class="mostlybus">
    <div class="mostlybus-title">
      <div class="mostlybus-title-text">
        마지막으로 탄 낭만버스
      </div>
      <hr class="recentlybus-title-line">
    </div>
    <div class="mostlybus-box">
      <div class="mostlybus-content">
        <!-- 가장 최근에 탔던 <노선> 버스에 방명록이 <갯수>개 추가됐어요! -->
        지금 <span class="mostlybus-data">양재IC</span> 지나는 <span class="mostlybus-data">5100번</span> 버스는요,<br>
        <span class="mostlybus-status">아주 시끌벅적하네요!</span>
      </div>
    </div>
  </div>
</template>
<script setup>

</script>
<style>
.recentlybus-title-line {
  display: block;
  width: 35%;
  height: 1px;
  border: 0;
  border-top: 1px solid black;
}
</style>