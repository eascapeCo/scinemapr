/**
 * vuex store
 * @author jaehankim
 * @date 2018.09.15
 */

import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        accessToken: null,
        refreshToken: null
    },
    getters: {
        getJwt: function(state) {
            return state.accessToken;
        }
    },
    mutations: {
        LOGIN_SUCCESS(state, headers) {
            console.log('muta -> ' + JSON.stringify(headers) /*+ headers*/ );
            document.cookie = 'refreshToken='+headers.tokenList.refreshToken;
            
            state.accessToken = headers.tokenList.accessToken;
        },
    },
    actions: {
        LOGIN({ commit }, { id, pwd }) {
            console.log("id -> " + id + "pwd -> " + pwd);
            return axios.post('/api/admin/login', { id, pwd })
                .then((res) => res)
                .catch((error) => error);
        }
    },
    // actions: {
    // 	LOGIN ({ commit }, {id, pwd} ) {
    // 		console.log("id -> " + id + "pwd -> " + pwd);
    // 		commit(types.LOGIN)
    // 		return fetch('/admin/login', {
    // 			method: 'POST',
    // 			headers: {
    // 				Accept: 'application/JSON'
    // 			},
    // 			body: JSON.stringify(id, pwd)
    // 		})
    // 	}
    // },
    plugins: [createPersistedState({
        storage: window.sessionStorage
    })],
});