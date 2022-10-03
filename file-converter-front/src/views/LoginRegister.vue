<template>
  <main>
    <div class="login-container" >
      <div class="login-module" v-show="isLogin">
        <p>登录</p>
        <div class="login-item">
          <input type="text" value="" name="account" placeholder="请输入账号" class="account login-input">
          <input type="password" name="password" placeholder="请输入密码" class="password login-input">
          <input type="button" value="登录" class="login-btn">
          <div class="login-other">
            <a class="change-password" href="">忘记密码？</a>
            <a class="register-link" @click="changeStatus">没有账号，去注册！</a>
          </div>
        </div>
      </div>
      <div class="register-module" v-show="!isLogin">
        <p>注册</p>
        <div class="login-item">
          <input type="text" value="" name="account" placeholder="请输入手机号码" class="account login-input">
          <input type="password" name="password" placeholder="请输入密码" class="password login-input">
          <input type="password" name="confirmPassword" placeholder="请输入确认密码"
                 class="confirm-password login-input">
          <div class="captcha-module">
            <input type="text" name="captcha" placeholder="验证码" class="captcha">
            <input type="button" value="验证码" class="captcha-btn">
          </div>
          <input type="button" value="注册" class="login-btn">

          <div class="login-other">
            <a class="register-link" @click="changeStatus">已有账号，去登录！</a>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  name: "LoginRegister",
  data() {
    return {
      isLogin: true
    }
  },
  methods: {
    changeStatus() {
      this.isLogin = !this.isLogin
      console.log(this.isLogin)
    }
  }
}
</script>

<style scoped>
main {
  width: 100vw;
  height: 100vh;
  background: url("../assets/icon/login-bg.png") repeat;
  background-size: cover;
  min-width: 1170px;
}

.login-module, .register-module {
  float: right;
  margin-top: 12%;
  margin-right: 15vw;
  width: 400px;
  background: rgba(255, 255, 255, 0.8);
  border-radius: 5px;
}

.login-module p, .register-module p {
  font-family: "Microsoft YaHei UI", serif;
  font-size: 35px;
  margin: 0;
  letter-spacing: 3px;
  font-weight: 600;
  color: #666666;
  text-align: center;
  padding-bottom: 20px;
  padding-top: 30px;
  border-bottom: solid 1px #dddddd;

}

.login-item {
  width: 400px;
  padding: 20px 0;
}

.login-input {
  width: 300px;
  height: 25px;
  margin: 15px 45px;
  outline: none;
  border: solid 2px #dddddd;
  font-family: "Microsoft YaHei UI", serif;
  font-size: 16px;
}

.login-btn {
  width: 300px;
  height: 32px;
  margin: 15px 47px;
  background: #007bff;
  border: solid 1px #007bff;
  color: #ffffff;
  letter-spacing: 1px;
  border-radius: 5px;
}

.login-btn:hover {
  background: #58A7FA;
  border: solid 1px #58A7FA;
  cursor: pointer;
}

.login-other {
  width: 300px;
  margin: 20px auto;
  padding-bottom: 20px;
}

.change-password {
  float: left;
  text-decoration: none;
  color: #58A7FA;
}

.register-link {
  float: right;
  text-decoration: none;
  color: #58A7FA;
}

.captcha-module {
  width: 310px;
  margin: 20px auto;
}

.captcha {
  width: 150px;
  height: 25px;
  outline: none;
  border: solid 2px #dddddd;
  font-family: "Microsoft YaHei UI", serif;
  font-size: 16px;
  vertical-align: middle;
  margin-right: 20px;
}

.captcha-btn {
  width: 125px;
  height: 31px;
  background: #007bff;
  border: solid 1px #007bff;
  color: #ffffff;
  border-radius: 3px;
}

.captcha-btn:hover {
  background: #58A7FA;
  border: solid 1px #58A7FA;
  cursor: pointer;
}

.login-other a:hover{
  cursor: pointer;
}
</style>