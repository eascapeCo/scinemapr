import * as types from './mutation-types'

const mutations = {
  [types.LOGIN] (state) {
    state.auth.pending = true
  },
  [types.LOGIN_SUCCESS] (state, data) {
    const token = data.token
    const username = data.username
    console.log('data: ', data)
    state.auth.isLoggedIn = true
    state.auth.pending = false
    state.auth.token = token
    state.auth.username = username
    sessionStorage.setItem('JWT', token)
    sessionStorage.setItem('username', username)
  },
  [types.LOGIN_WRONG_CREDENTIALS] (state) {
    state.pending = false
    state.auth.isLoggedIn = false
  },
  [types.LOGIN_ERROR] (state) {
    state.pending = false
    state.auth.isLoggedIn = false
  },
  [types.LOGOUT] (state) {
    sessionStorage.removeItem('JWT')
    sessionStorage.removeItem('username')
    state.auth.isLoggedIn = false
  }
}

export default mutations
