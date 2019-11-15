<template lang="html">

  <section class="login">
    <div class="wrapper d-flex align-items-center auth login-full-bg">
      <div class="row w-100">
        <div class="col-lg-6 mx-auto">
          <div class="auth-form-dark text-left p-5">
            <h2>Login</h2>
            <h4 class="font-weight-light">Hello! let's get started</h4>
            <form class="pt-5">
              <form id="loginForm" @submit.prevent="sendPost()">
                <div class="form-group">
                  <label for="exampleInputEmail1">Username</label>
                  <input type="text" class="form-control" id="id" name="id" v-model="id" aria-describedby="emailHelp" placeholder="Username">
                  <i class="mdi mdi-account"></i>
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" class="form-control" id="pwd" name="pwd" v-model="pwd" placeholder="Password">
                  <i class="mdi mdi-eye"></i>
                </div>
                <div class="mt-5">
                	<input type="submit" class="btn btn-block btn-warning btn-lg font-weight-medium" value="Login">
                </div>
                <div class="mt-3 text-center">
                  <a href="#" class="auth-link text-white">Forgot password?</a>
                </div>
              </form>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>

</template>

<script>
import * as types from '../store/mutation-types'
import checkGeolocation from '../utils/check-geolocation'

export default {
  name: 'loginForm',
  data() {
    return {
      id: '',
      pwd: '',
      a: 'as',
    }
  },
  created: () => {
    if (!checkGeolocation()) {
      alert('NO GEOLOCATION, OH MY!')
    }
  },
  methods: {
    sendPost() {
      const self = this
      this.$store.dispatch(types.LOGIN, {
        id: this.id,
        pwd: this.pwd
      }).then((response) => {
        if (response.status === 200) {
          console.log(response.data.message);
            this.$store.commit(types.LOGIN_SUCCESS, {
              tokenList: response.data.info,
              id: self.id
            })
            this.$router.push('/view/s');
        } 
      })
    }
  }
}
</script>
<style scoped lang="scss">
.login {

}
</style>
