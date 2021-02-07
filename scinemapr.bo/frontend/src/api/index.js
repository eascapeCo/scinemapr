import axios from 'axios'
// 인증이 안되었을 경우 리턴 로그인 url 로 라우팅 하기 위해
import router from '../router'

const DOMAIN = 'http://localhost:3000'
const UNAUTHORIZED = 401
// 인증이 안되었을 경우 로그인 하도록 라우팅 하는 함수
const onUnauthroized = () => { router.push('/login') }

export default {
  method: {
    request: (method, url, data) => {
      console.log('123')
      return axios({
        // 헤더추가
        headers: {
          Authorization: this.$store.state.access_token,
          'Content-Type': 'application/json'
          // timeout: 60000
        },
        method,
        url: DOMAIN + url,
        data
      })
        .then(result => result.data)
        .catch(result => {
          const { status } = result.response
          if (status === UNAUTHORIZED) {
            return onUnauthroized()
          }
          throw Error(result)
        })
    }
  }
}
