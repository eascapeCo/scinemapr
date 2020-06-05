import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '@/views/MainView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/loginForm',
    name: 'LoginForm',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/auth/LoginForm.vue')
  },
  {
    path: '/',
    name: 'MainView',
    component: MainView,
    children: [
      { path: 'grid', component: () => import(/* webpackChunkName: "about" */ '../views/sample/GirdSample.vue') }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
