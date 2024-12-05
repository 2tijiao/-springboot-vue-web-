<script setup>
import {
    Document
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const categorys = ref([
    {
            "id": 1,
            "name": "lilma",
            "pwd":"666666",
            "email": "1597310170@qq.com",
            "createTime":'2024-10-16 23:22:05'
        }
])
//声明一个异步的函数
import dayjs from 'dayjs';
import { AllusersService } from '@/api/user';
const allusers=async()=>{
    let result=await AllusersService()
    if (result.data && result.data.createTime) {
    const createTimeFromBackend = result.data.createTime;
    const formattedCreateTime = dayjs(createTimeFromBackend).format('yyyy - MM - dd HH:mm:ss');
    result.data.createTime = formattedCreateTime;
  }
    categorys.value=result.data
}
allusers();

//用户记录弹窗
const dialogVisible=ref(false)
const id=ref(0)
const usercategorys = ref([
    {
            "id": 1,
            "goodsid": 1,
            "userid": 1,
            "goodsname": "Cucci",
            "goodsprice":7999.00,
            "goodsnum":1,
            "state":0
        }
])
import { userOrderService } from '@/api/user';
const showdialog=async(row)=>{
    dialogVisible.value=true
    id.value=row.id
    let result=await userOrderService(id.value)
    usercategorys.value=result.data
}
//规定表格商品状态的值（0-未收货，1-已收货）
const stateFormatter=(row, column, cellValue, index)=>{
      // 根据 cellValue 的值返回不同的字符串
      return cellValue === 0 ? '未收货' : '已收货';
    }

//搜索用户
const searchUser=ref('')
import { userSearchService } from '@/api/user';
const searchuser=async()=>{
    let result=await userSearchService(searchUser.value)
    if (result.data && result.data.createTime) {
    const createTimeFromBackend = result.data.createTime;
    const formattedCreateTime = dayjs(createTimeFromBackend).format('yyyy - MM - dd HH:mm:ss');
    result.data.createTime = formattedCreateTime;
  }
    categorys.value=result.data
    searchUser.value=''
}

</script>
<template>
    <el-card class="page-container">

        <template #header>
            <div class="header">
                <span>用户列表</span>
                <div class="search">
                    <el-input class="input" v-model="searchUser" placeholder="请输入要搜索的用户"></el-input>
                    <el-button class="button" type="primary" @click="searchuser">搜索</el-button>
                    <el-button class="button" @click="allusers">返回</el-button>
                </div>
            </div>
        </template>

        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="用户名" prop="name"> </el-table-column>
            <el-table-column label="用户邮箱" prop="email"></el-table-column>
            <el-table-column label="用户创建时间" prop="createTime"></el-table-column>
            <el-table-column label="查看" width="100">
                <template #default="{ row }">
                    <el-button :icon="Document" @click="showdialog(row)"></el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--用户记录弹窗-->
        <el-dialog v-model="dialogVisible" width="50%">
            <el-table :data="usercategorys" style="width: 100%">
                <el-table-column label="序号" width="100" type="index"> </el-table-column>
                <el-table-column label="商品名称" prop="goodsname"></el-table-column>
                <el-table-column label="商品价格" prop="goodsprice"></el-table-column>
                <el-table-column label="商品数量" prop="goodsnum"></el-table-column>
                <el-table-column label="商品状态" prop="state" :formatter="stateFormatter"></el-table-column>
            </el-table>
        </el-dialog>
        
    </el-card>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    .search{
        width: 30%;
        margin-right: 5%;
    }
    .search .input{
        height: 100%;
        width: 70%;
        float: left;
    }
    .search .button{
        width: 10%;
        float: right;
    }
    .tip{
        margin-left: 20px;
        font-size: 15px;
    }
}
</style>