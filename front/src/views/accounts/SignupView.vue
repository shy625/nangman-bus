<template>
  <header>
    <div class="chat-header" style="margin: -9.5px 15px 25px 10px">
      <router-link :to="{ name: 'main' }" class="chat-header-back">
        &lt;
      </router-link>
    </div>
  </header>
  <div>
    <h3 style="margin: 32px 32px">이메일을 입력해주세요.</h3>
  </div>
  <!-- <account-error-list v-if="store.getters['accounts/authError']"></account-error-list> -->

  <!-- <form @submit.prevent="store.dispatch('accounts/signup', credentials)"> -->
  <form
    @submit="signup"
    style="
      flex-direction: row;
      align-items: center;
      gap: 10px;
      margin: 8px 32px;
      background: #ffffff;
      /* text-align: center; */
    "
  >
    <div class="input-area" style="margin: 19.5px 0 2.5rem 0">
      <div>
        <label class="loginview-input-label" for="useremail">이메일</label>
      </div>
      <div style="display: flex; flex-direction: row; align-items: center">
        <input
          class="loginview-input"
          v-model="credentials.useremail"
          id="useremail"
          type="email"
          autofocus
          required
        />
        <button
          style="
            margin-left: 10px;
            border: none;
            background: #ffd96a;
            border-radius: 4px;
            text-decoration: none;
            font-family: 'BM HANNA Air';
            font-style: normal;
            font-size: 0.2rem;
            color: black;
            width: 64px;
          "
          disabled
        >
          인증요청
        </button>
      </div>
    </div>

    <div class="input-area" style="margin: 19.5px 0 2.5rem 0">
      <div>
        <label class="loginview-input-label" for="password1">비밀번호</label>
      </div>
      <input
        class="loginview-input"
        v-model="credentials.password1"
        id="password1"
        type="password"
        required
      />
    </div>

    <div class="input-area" style="margin: 19.5px 0 2.5rem 0">
      <div>
        <label class="loginview-input-label" for="password2"
          >비밀번호 확인
        </label>
      </div>
      <input
        class="loginview-input"
        v-model="credentials.password2"
        id="password2"
        type="password"
        required
      />
    </div>

    <div class="input-area">
      <div>
        <label class="loginview-input-label" for="userbirthday"
          >생년월일 (선택)</label
        >
      </div>
      <input
        class="loginview-input"
        v-model="credentials.userbirthday"
        id="userbirthday"
        type="date"
      />
    </div>

    <div style="margin: 7rem; text-align: center">
      <button
        style="
          padding: 8px 15px;
          border: none;
          background: #ffd96a;
          border-radius: 4px;
          text-decoration: none;
          font-family: 'BM HANNA Air';
          font-style: normal;
          font-size: 0.7rem;
        "
      >
        시작하기
      </button>
    </div>
  </form>
</template>
<script setup>
import { useStore } from "vuex";
import { ref, onMounted } from "vue";
// import AccountErrorList from './AccountErrorList.vue.js'
// element-plus에 Form, Input, Datepicker로 CSS
const store = useStore();
onMounted(() => {});

const credentials = ref({
  useremail: "",
  password1: "",
  password2: "",
  userbirthday: "",
});

const signup = function (event) {
  event.preventDefault();
  const credentials = {
    useremail: event.target.useremail.value,
    password1: event.target.password1.value,
    password2: event.target.password2.value,
    userbirthday: event.target.userbirthday.value,
  };
  // console.log(event.target.useremail.value)
  store.dispatch("accounts/signup", credentials);
};
</script>
<style>
.loginview-input {
  width: 20rem;
  height: 1.2rem;
  border: none;
  border-bottom: 1px solid #bbb5b5;
  caret-color: #ffd96a;
  font-style: normal;
  font-size: 0.7rem;
}

.loginview-input:focus {
  border-color: #ffd96a;
  outline: none;
}

.loginview-input-label {
  font-family: "BM HANNA Air";
  font-style: normal;
  font-size: 0.7rem;
  color: #bbb5b5;
}

.input-area:focus-within .loginview-input-label {
  color: #ffd96a;
}
</style>
