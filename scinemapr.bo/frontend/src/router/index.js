import Vue from 'vue';
import Router from 'vue-router';
import HelloWorld from '@/components/HelloWorld';
import LoginForm from '@/components/login';
import CreateMenuForm from '@/components/menu/CreateMenuForm';
import MainView from '@/components/mainView/MainView';

Vue.use(Router);

//Dashboard Components
import dashboard from '@/views/dashboard'

// Widgets
import widgets from '@/views/widgets'

// UI Components

import alerts from '@/views/ui-components/alerts'
import badges from '@/views/ui-components/badges'
import breadcrumbs from '@/views/ui-components/breadcrumbs'
import buttons from '@/views/ui-components/buttons'
import carousel from '@/views/ui-components/carousel'
import dropdowns from '@/views/ui-components/dropdowns'
import icons from '@/views/ui-components/icons'
import modals from '@/views/ui-components/modals'
import paginations from '@/views/ui-components/paginations'
import progress from '@/views/ui-components/progress'
import tables from '@/views/ui-components/tables'
import typography from '@/views/ui-components/typography'
import tabs from '@/views/ui-components/tabs'
import tooltips from '@/views/ui-components/tooltips'

// Form Components
import forms from '@/views/forms/forms'

// Sample Pages
import error404 from '@/views/sample-pages/error-404'
import error500 from '@/views/sample-pages/error-500'
//import login from '@/views/sample-pages/login'
import register from '@/views/sample-pages/register'

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld,
      meta: {authRequired: true},
    },
    {
      path: '/loginForm',
      name: 'LoginForm',
      component: LoginForm,
      meta: {authRequired: false},
    },
    {
        path: '/menuForm',
        name: 'CreateMenuForm',
        component: CreateMenuForm
    },
    {
    	path: '/view',
    	name: 'MainView',
    	component: MainView,
    	children: [
    		{ path: 's', component: CreateMenuForm }
    	],
    	meta: {authRequired: true},
    },
    {
      path: '/tt',
      name: 'dashboard',
      component: dashboard
    },
    {
      path: '/widgets',
      name: 'widgets',
      component: widgets
    },
    {
      path: '*',
      name: 'error-404',
      component: error404
    },
    {
      path: '/500',
      name: 'error-500',
      component: error500
    },
      {
        path: '/register',
        name: 'register',
        component: register
      },
      {
        path: '/alerts',
        name: 'alerts',
        component: alerts
      },
      {
        path: '/badges',
        name: 'badges',
        component: badges
      },
      {
        path: '/breadcrumbs',
        name: 'breadcrumbs',
        component: breadcrumbs
      },
      {
        path: '/buttons',
        name: 'buttons',
        component: buttons
      },
      {
        path: '/carousel',
        name: 'carousel',
        component: carousel
      },
      {
        path: '/dropdowns',
        name: 'dropdowns',
        component: dropdowns
      },
      {
        path: '/icons',
        name: 'icons',
        component: icons
      },
      {
        path: '/modals',
        name: 'modals',
        component: modals
      },
      {
        path: '/paginations',
        name: 'paginations',
        component: paginations
      },
      {
        path: '/progress',
        name: 'progress',
        component: progress
      },
      {
        path: '/tables',
        name: 'tables',
        component: tables
      },
      {
        path: '/typography',
        name: 'typography',
        component: typography
      },
      {
        path: '/tabs',
        name: 'tabs',
        component: tabs
      },
      {
        path: '/tooltips',
        name: 'tooltips',
        component: tooltips
      },
      {
        path: '/forms',
        name: 'forms',
        component: forms
      },
  ],
});
