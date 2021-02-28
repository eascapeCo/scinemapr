import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import VueSimpleAlert from 'vue-simple-alert'
import api from '@/api'

const local = axios.create({
  baseURL: ''
})

Vue.prototype.$local = local

Vue.prototype.$axios = axios
Vue.prototype.$store = store

Vue.use(VueSimpleAlert)
Vue.use(api)

new Vue({
  router,
  store,
  vuetify,
  api,
  render: h => h(App)
}).$mount('#app')
