import axios from 'axios'
// 인증이 안되었을 경우 리턴 로그인 url 로 라우팅 하기 위해
// import router from '../router'

const DOMAIN = 'http://localhost:3000'
// const UNAUTHORIZED = 401
// 인증이 안되었을 경우 로그인 하도록 라우팅 하는 함수
// const onUnauthroized = () => { router.push('/login') }

export default {
  install (Vue) {
    Vue.prototype.$request = function (method, url, data, succ, fail) {
      return axios({
        // 헤더추가
        headers: {
          Authorization: this.$store.state.access_token,
          'Content-Type': 'application/json'
          // timeout: 60000
        },
        method: method,
        url: DOMAIN + url,
        data: data
      })
    }
  }
}
