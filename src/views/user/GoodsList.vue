<script setup>
import {
    Select
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const categorys = ref([
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
//声明一个异步的函数
import { orderUsershowService } from '@/api/order';
const userOrderList=async()=>{
   let result= await orderUsershowService()
   categorys.value=result.data;
}
userOrderList();

//规定表格商品状态的值（0-未收货，1-已收货）
const stateFormatter=(row, column, cellValue, index)=>{
      // 根据 cellValue 的值返回不同的字符串
      return cellValue === 0 ? '未收货' : '已收货';
    }

//确认收货弹窗
const dialogVisible=ref(false)
//商品id
const orderid=ref(1)
//弹窗
const showdialog = (row) => {
  if (row.state === 1) {
    alert("不能重复收货");
  } else {
    orderid.value = row.id;
    dialogVisible.value = true;
  }
}
//完成收货
import { orderStateService } from '@/api/order';
const changestate=async()=>{
    let result=await orderStateService(orderid.value)
    dialogVisible.value=false
    userOrderList();
}

//搜索实现
const searchGoods=ref('')
import { orderUserSearchService } from '@/api/order';
const orderSearch=async()=>{
    let result=await orderUserSearchService(searchGoods.value)
    categorys.value=result.data
    searchGoods.value=''
}
</script>
<template>
    <el-card class="page-container">

        <template #header>
            <div class="header">
                <span>订单列表</span>
                <div class="search">
                    <el-input class="input" v-model="searchGoods" placeholder="请输入要搜索的商品"></el-input>
                    <el-button class="button" type="primary" @click="orderSearch">搜索</el-button>
                    <el-button class="button" @click="userOrderList">返回</el-button>
                </div>
            </div>
        </template>

        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="商品名称" prop="goodsname"></el-table-column>
            <el-table-column label="商品价格" prop="goodsprice"></el-table-column>
            <el-table-column label="商品数量" prop="goodsnum"></el-table-column>
            <el-table-column label="商品状态" prop="state" :formatter="stateFormatter"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Select" circle plain type="primary" @click="showdialog(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!--确认收货弹窗-->
        <el-dialog v-model="dialogVisible" width="30%">
            <span class="tip">您确认已收到商品吗？</span>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="changestate"> 确认 </el-button>
                </span>
            </template>
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