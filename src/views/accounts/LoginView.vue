<template>
<div>

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

  <!-- <account-error-list v-if="authError"></account-error-list> -->

  <form
    @submit="login"
    style="
      flex-direction: row;
      align-items: center;
      gap: 10px;
      margin: 8px 32px;
      background: #ffffff;
      /* text-align: center; */
    "
  >
    <div class="input-area">
      <div>
        <label class="loginview-input-label" for="useremail">이메일 </label>
      </div>
      <input
        class="loginview-input"
        v-model="credentials.useremail"
        id="useremail"
        type="email"
        required
      />
    </div>
    <div class="input-area" style="margin-top: 19.5px">
      <div>
        <label class="loginview-input-label" for="password">비밀번호</label>
      </div>
      <input
        class="loginview-input"
        v-model="credentials.password"
        id="password"
        type="password"
        required
      />
    </div>
    <div style="margin: 1rem; text-align: center">
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
        로그인
      </button>
    </div>
    <div style="margin: 1rem; text-align: center">
      <router-link style="text-decoration: none; color: inherit" to="signup">
        <button
          style="
            padding: 5px 15px;
            border: none;
            background: white;
            border-radius: 4px;
            text-decoration: none;
            font-family: 'BM HANNA Air';
            font-style: normal;
            font-size: 0.7rem;
          "
        >
          회원가입
        </button>
      </router-link>
    </div>
  </form>
</div>
</template>

<script setup>
import { useStore } from "vuex";
import { ref, onMounted } from "vue";
// import AccountErrorList from './AccountErrorList.vue'
const store = useStore();
onMounted(() => {});
const credentials = ref({
  useremail: "",
  password: "",
});
const login = function (event) {
  event.preventDefault();
  const credentials = {
    useremail: event.target.useremail.value,
    password: event.target.password.value,
  };
  store.dispatch("accounts/login", credentials);
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
