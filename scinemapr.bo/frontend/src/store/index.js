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
    LOGIN_SUCCESS: function (state, headers) {
      state.loginSuccess = true
      // state.userName = headers.userName
      // state.userPass = headers.userPass
      console.log(state.accessToken)

      console.log('muta -> ' + JSON.stringify(headers))
      console.log('muta -> ' + JSON.stringify(state))
      // document.cookie = 'refreshToken=' + headers.tokenList.refreshToken
      // state.accessToken = headers.tokenList.accessToken
    },
    LOGIN_ERROR: function (state, headers) {
      state.loginError = true
      state.userName = headers.userName1
    }
  },
  actions: {
    LOGIN: ({ commit }, { id, pwd }) => {
      return new Promise((resolve, reject) => {
        console.log('id -> ' + id + 'pwd -> ' + pwd)
        return axios.post('/api/admin/login', { id, pwd })
          .then((res) => {
            console.log(res)
            console.log('accessToken: ' + res.headers.accesstoken)
            // console.log('Response: ' + response.data + 'Status Code: ' + response.status)
            if (res.status === 200) {
              console.log('Login Successful')
              commit('LOGIN_SUCCESS', {
                accessToken: 'Bearer ' + res.headers.accesstoken,
                refreshToken: 'Bearer ' + res.headers.refreshtoken
              })
            }
            resolve(res)
          })
          .catch((error) => {
            console.log('Error: ' + error)
            commit('LOGIN_ERROR', {
              userName: id
            })
            reject(new Error('Invalid credentials!'))
          })
      })
    }
  },
  modules: {
  }
})
