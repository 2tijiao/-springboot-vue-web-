//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = 'http://8.138.133.103:8090';
const instance = axios.create({baseURL})

import { useTokenStore } from '@/stores/token.js';
//添加请求拦截器，每次都在接口里面设置头部token很麻烦
instance.interceptors.request.use(
    (config)=>{
        //请求前的回调
        //添加token
        const tokenStore=useTokenStore();
        if(tokenStore.token){
            config.headers.Authorization=tokenStore.token
        }
        return config;
    },
    (err)=>{
        //请求错误的回调
        Promise.reject(err);
    }
)
//添加响应拦截器
instance.interceptors.response.use(
    result=>{
        //判断业务状态码
        if(result.data.code===1){
        return result.data;
    }
        //操作失败
        alert(result.data.msg?result.data.msg:'服务异常')
        //将异步操作的状态转换为失败
        return Promise.reject(result.data)
    },
    err=>{
        alert('服务异常');
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;