import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: null,
    refreshToken: null
  },
  getters: {
    getJwt: function (state) {
      return state.accessToken
    }
  },
  mutations: {
    LOGIN_SUCCESS: (state, headers) => {
      console.log('muta -> ' + JSON.stringify(headers))
      document.cookie = 'refreshToken=' + headers.tokenList.refreshToken
      state.accessToken = headers.tokenList.accessToken
    }
  },
  actions: {
    LOGIN: ({ commit }, { id, pwd }) => {
      console.log('id -> ' + id + 'pwd -> ' + pwd)
      return axios.post('/api/admin/login', { id, pwd })
        .then((res) => {
          console.log(res)
        })
        .catch((error) => {
          if (error.request.status === 403) {
            alert('ID나 비밀번호가 다릅니다.')
          }
        })
    }
  },
  modules: {
  }
})
