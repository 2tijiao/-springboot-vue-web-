import { createRouter,createWebHashHistory } from "vue-router";

//导入组件
import login from '@/views/Login.vue'
import userlayout from '@/views/user/Layout.vue'
import usergoodsshow from '@/views/user/GoodsShow.vue'
import usergoodslist from '@/views/user/GoodsList.vue'
import usertrollyshow from '@/views/user/TrollyShow.vue'
import admingoodsshow from '@/views/admin/GoodsShow.vue'
import admingoodslist from '@/views/admin/GoodsList.vue'
import adminlayout from '@/views/admin/Layout.vue'
import adminuserlist from '@/views/admin/UsersList.vue'

//定义路由关系
const routes=[
    {path:'/', component:login},
    //给展示区域设置子路由，用redirect属性来规定默认展示的页面
    { path: '/user', component: userlayout ,redirect:'/user/goodsshow',
        children:[
            { path: '/user/goodsshow', component: usergoodsshow },
            { path: '/user/goodslist', component: usergoodslist },
            { path: '/user/trollyshow', component: usertrollyshow}
        ]
    },
    { path: '/admin', component: adminlayout, redirect: '/admin/goodsshow',
        children: [
            { path: '/admin/goodsshow', component: admingoodsshow },
            { path: '/admin/goodslist', component: admingoodslist },
            { path: '/admin/userlist', component: adminuserlist }
        ]
    }
]

//创建路由器
const router=createRouter({
    history:createWebHashHistory(),
    routes:routes
})

export default router