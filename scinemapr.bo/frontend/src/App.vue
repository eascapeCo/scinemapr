<template lang="html">
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script lang="js">
  import store from './store';
  export default{
    name: 'app',
    mounted: function() {
      console.log('test');
  // 	console.log(store.getters.getJwt);
  // 	let isLogin = true;
  // 	if (store.getters.getJwt === null) {
  // 		isLogin = false;
  // 	}
  // 	console.log("isLogin : " + isLogin);
    },
    computed: {
      isLogin: function () {
        let isLogin = true;
        if (store.getters.getJwt === null) {
        isLogin = false;
        }
        console.log("isLogin : " + isLogin);
        return isLogin;
      },
    }
  }
</script>

<style>
  @import "../node_modules/mdi/css/materialdesignicons.min.css";
  @import "../node_modules/flag-icon-css/css/flag-icon.min.css";
  @import "../node_modules/font-awesome/css/font-awesome.min.css";
</style>

<style lang="scss">
@import "./assets/scss/style";
</style>
