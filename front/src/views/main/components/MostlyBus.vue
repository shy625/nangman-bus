<template>
  <div class="mostlybus">
    <div class="mostlybus-title">
      <div class="mostlybus-title-text">
        내가 탄 낭만버스
      </div>
      <hr class="mostlybus-title-line">
    </div>
    <div class="mostlybus-box">
      <div class="mostlybus-content">
        지금 <span class="mostlybus-data">양재IC</span> 지나는 <span class="mostlybus-data">5100번</span> 버스는요,<br>
        <span class="mostlybus-status">아주 시끌벅적하네요!</span>
      </div>
      <div class="mostlybus-content">
        지금 <span class="mostlybus-data">양재IC</span> 지나는 <span class="mostlybus-data">5100번</span> 버스는요,<br>
        <span class="mostlybus-status">아주 시끌벅적하네요!</span>
      </div>
      <div class="mostlybus-content">
        지금 <span class="mostlybus-data">양재IC</span> 지나는 <span class="mostlybus-data">5100번</span> 버스는요,<br>
        <span class="mostlybus-status">아주 시끌벅적하네요!</span>
      </div>
    </div>
  </div>
</template>
<script setup>

</script>
<style>
.mostlybus {
  margin: 32px 0;
}
.mostlybus-title {
  margin-bottom: 24px;
  display: flex;
  align-items: center;
}
.mostlybus-title-text {
  font-family: BMHANNAPro;
  font-size: 1.2rem;
}
.mostlybus-title-line {
  display: block;
  width: 50%;
  height: 1px;
  border: 0;
  border-top: 1px solid black;
}
.mostlybus-box {
  border-top: 4px solid #FFD96A;
}
.mostlybus-content {
  padding: 16px;
  border-bottom: 4px solid #FFD96A;
  font-size: 1.1rem;
}
.mostlybus-data {
  font-family: BMHANNAPro;
  font-size: 1.1rem;
  color: #F34949;
}
</style>