import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import VueSimpleAlert from 'vue-simple-alert'
import VuetifyDialog from 'vuetify-dialog'

Vue.prototype.$axios = axios
Vue.prototype.$store = store
Vue.config.productionTip = false

Vue.use(VueSimpleAlert)
Vue.use(VuetifyDialog)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
