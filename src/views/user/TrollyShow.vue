<script setup>
import {
    Edit,
    Delete,
    Goods
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const categorys = ref([
    {
        "id": 4,
        "userid": 1,
        "goodsid": 14,
        "goodsnum": 2,
        "goodsname": "iphone15 pro",
        "goodsprice": 8999.00,
        "total": 17998.00
        }
])
//声明一个异步的函数
import { trollyShowService } from '@/api/trolly';
const trollyshow=async()=>{
    let result=await trollyShowService()
   categorys.value=result.data;
}
trollyshow();

//删除购物车商品
import { trollyDeleteService } from '@/api/trolly';
const deletegoods=async(row)=>{
    let result=await trollyDeleteService(row.id)
    trollyshow();
}

//修改购物车弹窗
const dialogVisible1=ref(false)
//记录商品数量
const num=ref(1)
const updateData=ref({
    id:'',
    goodsnum:''
})
//显示修改窗口
const changegoods=(row)=>{
    dialogVisible1.value=true
    num.value=row.goodsnum
    updateData.value.id=row.id
    
}
//修改商品值
import { trollyUpdateService } from '@/api/trolly'; 
const updategoods=async()=>{
    updateData.value.goodsnum=num.value
    let result=await trollyUpdateService(updateData.value)
    dialogVisible1.value=false
    trollyshow();
}

//处理所有选中的商品,将id和价格分别记录在两个列表中
const idlist=ref([])
const pricelist=ref([])
//处理选中的值
const handleSelectionChange=(val)=>{
    idlist.value=[];
    pricelist.value=[];
    val.map((item,index) =>{
       idlist.value.push(item.id)
       pricelist.value.push(item.total)
   });  
}

//控制购买商品弹窗
const dialogVisible = ref(false)
//展示购买弹窗，并累加总价格
const bfinish=()=>{
    if(idlist.value.length==0){
        alert("您未选中商品")
    }
    else{
    sumprice.value=0;
    for(let key=0;key<pricelist.value.length;key++)
        {
            sumprice.value+=pricelist.value.at(key)
        }
    dialogVisible.value=true
}
}

import { trollyFinishService } from '@/api/trolly';
const sumprice=ref(0)
//向后端传入选择的商品id，完成购买和发邮件
const finish=async()=>{
    let result=await trollyFinishService(idlist.value)
    sumprice.value=0
    dialogVisible.value=false
    trollyshow();
}

//搜索实现
const searchGoods=ref('')
import { trollySearchService } from '@/api/trolly';
const trollySearch=async()=>{
    let result=await trollySearchService(searchGoods.value)
    categorys.value=result.data
    searchGoods.value=''
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>购物车列表</span>
                <div class="search">
                    <el-input class="input" v-model="searchGoods" placeholder="请输入要搜索的商品"></el-input>
                    <el-button class="button" type="primary" @click="trollySearch()">搜索</el-button>
                    <el-button class="button" @click="trollyshow">返回</el-button>
                </div>
            </div>
        </template>
        <el-table ref="multipleTable" :data="categorys" style="width: 100%" @selection-change="handleSelectionChange" row-key="id">
            <el-table-column type="selection" width="60"></el-table-column>
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="商品名称" prop="goodsname"></el-table-column>
            <el-table-column label="商品价格" prop="goodsprice"></el-table-column>
            <el-table-column label="商品数量" prop="goodsnum"></el-table-column>
            <el-table-column label="总价格" prop="total"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="changegoods(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deletegoods(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
        <el-button :icon="Goods" circle plain type="primary" @click="bfinish" class="buybt"></el-button>

        <!--修改商品弹窗-->
        <el-dialog v-model="dialogVisible1" width="30%">
            <el-input-number v-model="num" :min="1"/>
            <span class="tip">请输入要新的商品购买数量</span>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible1 = false">取消</el-button>
                    <el-button type="primary" @click="updategoods"> 确认 </el-button>
                </span>
            </template>
        </el-dialog>

        <!--完成购买弹窗-->
        <el-dialog v-model="dialogVisible" width="30%">
            <span class="tip">请您确认购买物品，总价格为{{sumprice}}，点击确认完成购买</span>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="finish"> 确认 </el-button>
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
    }
    .buybt{
        width: 50px;
        height:50px;
        font-size: 30px;
        margin-top: 15px;
        float:right;
    }
}
</style>