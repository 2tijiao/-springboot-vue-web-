import './assets/main.scss'

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from '@/router/index'
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-persistedstate-plugin'
import App from './App.vue'

const app = createApp(App);
const pinia = createPinia();
const persist = createPersistedState();
pinia.use(persist)
app.use(pinia);
app.use(router);
app.use(ElementPlus);
app.mount('#app');
