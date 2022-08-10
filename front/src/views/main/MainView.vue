<template>
  <!--
  메인페이지,
  1. 로그인이 안 되어있는 경우: 온보딩 페이지로 다이렉트
  2. 로그인이 되어있는 경우: 헤더, 푸터 컴포넌트 배치
  (헤더, 푸터는 스크롤에 상관없이 화면 상단, 하단 고정이어야 함)
  2-1. 메인-홈 페이지: 닉네임, 버스나우 배치
  2-2. 메인-낭만보고서 페이지: 리포트 배치 (리포트-달력, 리포트아이템 존재)
  2-3. 메인-채팅리스트 페이지: 버스리스트 배치 (버스리스트-버스아이템들 잡히는 버스만큼 배치)
  -->

  <div class="onboard">
    <p class="onboard-font">우리는 이걸</p>
    <p class="onboard-font">
      <img src="../../assets/logo.png" alt="낭만버스"
      class="logo-img"
      >라
    </p>
    <p class="onboard-font">부르기로 하였다.</p>
    <button class="button"><router-link to="signup">시작하기</router-link></button>
    <p class="login">
      이미 낭만하셨나요? <router-link to="login">로그인</router-link>
    </p>
  </div>

</template>
<script>
  // import Header from '../../views/components/Header.vue'
  // import Footer from '../../views/components/Footer.vue'
  // import Nickname from '../components/Nickname.vue'
  // import BusNow from '../components/BusNow.vue'
  // import BusList from '../components/BusList.vue'
  // import Report from '../components/Report.vue'
  import Home from './Home.vue'
  // 스토어에서 isLoggedin 가져오기
  import { useStore } from 'vuex'

  export default {
    name: 'MainView',
    components: {
      Home
    }
  }

  // setup() {
  //   const store = useStore()
  //   const isLoggedIn = store.getters('isLoggedIn',)
  // }
</script>
<style>
  .onboard {
    background: white;
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  .onboard-font {
    /* 여기서부터 작성하세요 */

  }
  .button {
    color: rgb(255, 217, 106);
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding: 0px;
    gap: 10px;
    
    position: absolute;

  }
</style>