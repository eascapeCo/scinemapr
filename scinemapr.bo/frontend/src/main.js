import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import VueSimpleAlert from 'vue-simple-alert'

Vue.prototype.$axios = axios
Vue.prototype.$store = store
Vue.config.productionTip = false

Vue.use(VueSimpleAlert)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
