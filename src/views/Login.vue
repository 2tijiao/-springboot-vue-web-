<script setup>
import { User, Lock,Message} from '@element-plus/icons-vue'
import { ref } from 'vue'
//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false)
//定义数据模型
const registerDate=ref({
    name:'',
    pwd:'',
    email:'',
    repwd:'' 
})
const loginData=ref({
    name:'',
    pwd:''
}
)
//校验密码的函数
const checkpwd=(value,callback)=>{
    if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== registerDate.value.pwd) {
    callback(new Error("两次密码不一致!"))
  } else {
    callback()
  }
}
//定义表单校验规则
const rules = ({
    name: [
    { required: true, message: '请输入用户名', trigger: 'blur' }],
    email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' }],
  pwd: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  repwd: [{ validator: checkpwd, trigger: 'blur' }]
})
//给设置注册按钮函数
import { userRegisterService } from '@/api/user';
import { userLoginService } from '@/api/user';
const register=async()=>{
    //registerData是一个响应式的对象，如果要获取值，需要.value
    let result=await userRegisterService(registerDate.value)
   alert(result.msg? result.msg:'注册成功');
}
//数据和校验两个表单可以复用
//登陆函数
import { useTokenStore } from '@/stores/token';
import { useRouter } from 'vue-router';
const router=useRouter();
const tokenStore=useTokenStore();
const login=async()=>{
    //调用接口，完成登录
    let result=await userLoginService(loginData.value)
   alert(result.msg? result.msg:'登录成功');
   //把得到的token存储到pinia中
   tokenStore.setToken(result.data)
   //跳转到首页，借助路由完成跳转
    router.push('/user')
}

import { adminLoginService } from '@/api/administer';
const adminlogin=async()=>{
    let result=await adminLoginService(loginData.value)
    alert(result.msg? result.msg:'登录成功');
    //把得到的token存储到pinia中
   tokenStore.setToken(result.data)
   //跳转到首页，借助路由完成跳转
    router.push('/admin')
}
//定义函数，清空数据模型的数据
const clearRegisterData=()=>{
    registerDate.value={
    name:'',
    pwd:'',
    email:'',
    repwd:'' 
    }
}
</script>

<template>
    <el-row class="login-page">
        <el-col :span="12" class="bg"></el-col>
        <el-col :span="6" :offset="3" class="form">
            <!-- 注册表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="registerDate" :rules="rules">
                <el-form-item>
                    <h1>注册</h1>
                </el-form-item>
                <el-form-item prop="name">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerDate.name"></el-input>
                </el-form-item>
                <el-form-item prop="email">
                    <el-input :prefix-icon="Message" placeholder="请输入邮箱地址" v-model="registerDate.email"></el-input>
                </el-form-item>
                <el-form-item prop="pwd">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerDate.pwd"></el-input>
                </el-form-item>
                <el-form-item prop="repwd">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入再次密码" v-model="registerDate.repwd"></el-input>
                </el-form-item>
                <!-- 注册按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="register">
                        注册
                    </el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = false">
                        ← 返回
                    </el-link>
                </el-form-item>
            </el-form>
            <!-- 登录表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-else :model="loginData" :rules="rules">
                <el-form-item>
                    <h1>登录</h1>
                </el-form-item>
                <el-form-item prop="name">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="loginData.name"></el-input>
                </el-form-item>
                <el-form-item prop="pwd">
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="loginData.pwd"></el-input>
                </el-form-item>
                <!-- 登录按钮 -->
                 <div>
                    <el-form-item class="button">
                        <el-button class="button1" type="primary" auto-insert-space @click="login">用户登录</el-button>
                        <el-button class="button2" type="primary" auto-insert-space @click="adminlogin">开发者登录</el-button>
                    </el-form-item>
                </div>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = true,clearRegisterData()">
                        注册 →
                    </el-link>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
    height: 100vh;
    background-color: #fff;

    .bg {
        background: url('@/assets/logo.jpg') no-repeat 60% center / 80% auto;
        border-radius: 0 20px 20px 0;
    }

    .form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;

        .title {
            margin: 0 auto;
        }

        .button {
            width: 100%;
        }

        .button .button1{
            float:left;
            width:30%;
            margin-left: 50px;
        }

        .button .button2{
            float:right;
            width: 30%;
            margin-left: 80px;
        }

        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }
}
</style>