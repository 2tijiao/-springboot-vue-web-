//导入request.js请求工具
import request from '@/utils/request.js'
//提供调用注册接口的函数
export const userRegisterService=(registerDate)=>{
    //借助UrlSearchParams完成传递
    const params=new URLSearchParams()
    for(let key in registerDate){
        params.append(key,registerDate[key]);
    }
    return request.post('/user/register',params)
}
//提供调用登录接口的函数
export const userLoginService = (loginDate) => {
    //借助UrlSearchParams完成传递
    const params = new URLSearchParams()
    for (let key in loginDate) {
        params.append(key, loginDate[key]);
    }
    return request.post('/user/login', params)
}
//用户注销接口
export const userDeleteService=()=>{
    return request.post('/user/delete')
}
//展示所有用户的接口
export const AllusersService=()=>{
    return request.get('/user/allusers')
}
//展示用户订单接口
export const userOrderService=(userid)=>{
    return request.get('/user/usershow?id='+userid)
}
//搜索用户接口
export const userSearchService=(username)=>{
    return request.get('/user/search?name='+username)
}