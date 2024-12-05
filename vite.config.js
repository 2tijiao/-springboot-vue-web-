import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  /*server:{
    proxy:{
      '/api':{//获取路劲中包含了/api的请求
        target:'http://8.138.133.103:8090',//后台服务所在的源
        changeOrigin:true,//修改源
        rewrite:(path)=>path.replace(/^\/api/,'')
        }
    }
  }*/
})
