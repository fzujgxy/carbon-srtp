import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
  {
    path: '/',
    name: 'Login',
    redirect:"/login",
    children:[{
      path:"/login",
      name:'Login',
      component:() => import("@/views/Login"),
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
    }
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
