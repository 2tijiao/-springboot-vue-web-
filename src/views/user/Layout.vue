<script setup>
import {
    Management,
    Promotion,
    UserFilled,
    User,
    Crop,
    EditPen,
    SwitchButton,
    CaretBottom
} from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
const router=useRouter();
const relogin=()=>{
    router.push('/')
}
import { userDeleteService } from '@/api/user';
const userDelete=async()=>{
    let result=await userDeleteService()
    alert(result.msg? result.msg:'注销成功');
    router.push('/')
}
</script>

<template>
    <!--element-plus中的容器-->
    <el-container class="layout-container">
        <!-- 左侧菜单 -->
        <el-aside width="200px">
            <div class="el-aside__logo" @click="relogin"></div>
            <!--菜单标签-->
            <el-menu active-text-color="#ffd04b" background-color="#232323"  text-color="#fff"
                router>
                <el-menu-item index="/user/goodsshow">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>商品列表</span>
                </el-menu-item>
                <el-menu-item index="/user/trollyshow">
                    <el-icon>
                        <Promotion />
                    </el-icon>
                    <span>购物车列表</span>
                </el-menu-item>
                <el-menu-item index="/user/goodslist">
                    <el-icon>
                        <Promotion />
                    </el-icon>
                    <span>订单列表</span>
                </el-menu-item>
                <el-menu-item @click="userDelete()">
                    <el-icon>
                        <Crop />
                    </el-icon>
                    <span>用户注销</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <!-- 右侧主区域 -->
        <el-container>
            <!-- 头部区域 -->
            <el-header>
                <div>欢迎使用：<strong>乐购</strong></div>
            </el-header>
            <!-- 中间区域 -->
            <el-main>
                <router-view></router-view>
            </el-main>
            <!-- 底部区域 -->
            <el-footer>乐购 ©2024 Created by 马镜瑶</el-footer>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
    height: 100vh;

    .el-aside {
        background-color: #232323;

        &__logo {
            height: 120px;
            background: url('@/assets/logo.jpg') no-repeat center / 120px auto;
        }

        .el-menu {
            border-right: none;
        }
    }

    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active,
            &:focus {
                outline: none;
            }
        }
    }

    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
    }
}
</style>