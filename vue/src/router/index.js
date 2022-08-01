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
    path:'/home',
    name:'Home',
    component:Layout,
    children:[{
      path:"/home",
      name:'Home',
      component:() => import("@/views/Home"),

    }]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


export default router
