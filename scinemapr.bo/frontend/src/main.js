// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import BootstrapVue from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import axios from 'axios';
import router from './router';
import store from './store';
import App from './App';

Vue.use(BootstrapVue);
Vue.prototype.$http = axios;
Vue.config.productionTip = false;

router.beforeEach((to, form, next) => {
	let auth = to.matched.some(function (routeInfo) {return routeInfo.meta.authRequired;});
	/*
	if (auth && store.getters.getJwt === null) {
		alert('로그인을 해주세요.');
		next('/loginForm');
	} else {
		next();
	}
	*/
	next();
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>',
});
