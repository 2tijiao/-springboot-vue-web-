import request from '@/utils/request.js'
import { useTokenStore } from '@/stores/token.js';
//商品列表接口
export const goodsCategoryListService=()=>{
    //在发送请求之前就给头部添加token
    //const tokenStore=useTokenStore();
    //return request.get('/goods/show',{headers:{'Authorization':tokenStore.token}});
    return request.get('/goods/show')
}

//增加商品接口
export const goodsAddService=(goodsData)=>{
    return request.post('/goods/add',goodsData)
}
//商品更新接口
export const goodsUpdateService=(goodsData)=>{
    return request.put('/goods/category',goodsData)
}
//删除商品接口
export const goodsDeleteService=(id)=>{
    return request.get('goods/delete?id='+id)
}
//搜索商品接口
export const goodsSearchService=(name)=>{
    const params=new URLSearchParams()
    params.append("name",name)
    return request.post('goods/test',params)
}
//加入购物车接口
export const addtotrolly=(trollydata)=>{
    const params=new URLSearchParams()
    for(let key in trollydata){
        params.append(key,trollydata[key])
    }
    return request.post('/goods/addtotrolly',params)
}