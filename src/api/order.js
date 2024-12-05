import request from '@/utils/request.js'
//展示用户订单接口
export const orderUsershowService=()=>{
   return request.get('/order/usershow')
}
//订单状态改变接口
export const orderStateService=(orderid)=>{
    return request.get('/order/changestate?id='+orderid)
}
//管理员所有订单接口
export const orderAdminshowService=()=>{
    return request.get('/order/adminshow')
}
//订单数据统计接口
export const orderStatisticService=()=>{
    return request.get('/order/ordersta')
}
//删除订单接口
export const orderDeleteService=(orderid)=>{
    return request.get('/order/delete?id='+orderid)
}
//修改订单信息接口
export const orderAlterService=(orderData)=>{
    return request.put('/order/alter',orderData)
}
//管理员订单搜索接口
export const orderAdminSearchService=(username,goodsname)=>{
    const Params=new URLSearchParams();
    Params.append("username",username);
    Params.append("goodsname",goodsname)
    return request.post('/order/adminsearch',Params)
}
//用户订单搜索接口
export const orderUserSearchService=(goodsname)=>{
    return request.get('/order/usersearch?goodsname='+goodsname)
}