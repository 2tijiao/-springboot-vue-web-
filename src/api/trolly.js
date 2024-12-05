import request from '@/utils/request.js'
//购物车展示接口
export const trollyShowService=()=>{
    return request.get('/trolly/list')
}
//完成购物接口
export const trollyFinishService=(trollyData)=>{
    return request.get('/trolly/finish?lists='+trollyData)
}
//删除购物车商品接口
export const trollyDeleteService=(id)=>{
    return request.get('/trolly/delete?id='+id)
}
//更新购物车接口
export const trollyUpdateService=(trollyData)=>{
    const param=new URLSearchParams
    for(let key in trollyData){
        param.append(key,trollyData[key])
    }
    return request.post('/trolly/update',param)
}
//购物车搜索接口
export const trollySearchService=(goodsname)=>{
    return request.get('/trolly/search?goodsname='+goodsname)
}