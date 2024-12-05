//导入request.js请求工具
import request from '@/utils/request.js'

//管理员登录请求
export const adminLoginService = (adminData) => {
    //借助UrlSearchParams完成传递
    const params = new URLSearchParams()
    for (let key in adminData) {
        params.append(key, adminData[key]);
    }
    return request.post('/administer/login', params)
}