import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import '@/assets/css/global.css';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// 用于和后台交互，发送请求
// import axios from 'axios';
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
// Vue.prototype.$ajax = axios

createApp(App).use(store).use(router).use(ElementPlus,{zhCn}).mount('#app')

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}