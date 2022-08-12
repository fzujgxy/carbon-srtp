import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
  {
    path: '/',
    name: 'Main',
    redirect:"/main",
    children:[{
      path:"/main",
      name:'Main',
      component:() => import("@/views/Main"),
    }]

  },
  {
    path:'/lasso',
    name:'Lasso',
    component:Layout,
    children:[{
      path:"/lasso",
      name:'Lasso',
      component:() => import("@/views/Lasso"),
    },

    {
      path:"/ebrb",
      name:'Ebrb',
      component:() => import("@/views/Ebrb"),
    },
    {
      path:'/backends',
      name:'Backends',
      component:() => import("@/views/Backends"),
      },
    ]
  },
  {
    path:'/register',
    name:'Register',
    component:() => import("@/views/Register"),

  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


export default router
