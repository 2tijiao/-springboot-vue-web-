<script setup>
import {
    CirclePlus
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const categorys = ref([
    {
            "id": 1,
            "price": 1999.00,
            "type": "clothe",
            "name": "Cucci"
        }
])
//声明一个异步的函数
import { goodsCategoryListService } from '@/api/goods';
const goodsCategoryList=async()=>{
   let result= await goodsCategoryListService();
   categorys.value=result.data;
}
goodsCategoryList();


//搜索商品
const searchGoods=ref('')
import { goodsSearchService } from '@/api/goods';
const goodsSearch=async()=>{
    let result=await goodsSearchService(searchGoods.value)
    categorys.value=result.data;
    searchGoods.value=''
}


//添加分类数据模型
const trollydata = ref({
    goodsid:'',
    num:''
})
//记录商品数量
const goodsnum=ref(1)

//控制添加分类弹窗
const dialogVisible = ref(false)
const showdialog=(row)=>{
    dialogVisible.value=true;
    trollydata.value.goodsid=row.id;
}

//添加购物车功能
import { addtotrolly } from '@/api/goods';
const totrolly=async()=>{
    trollydata.value.num=goodsnum.value;
    let result=await addtotrolly(trollydata.value)
    goodsnum.value=1
    dialogVisible.value=false
}

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>商品列表</span>
                <div class="search">
                    <el-input class="input" v-model="searchGoods" placeholder="请输入要搜索的商品"></el-input>
                    <el-button class="button" type="primary" @click="goodsSearch()">搜索</el-button>
                    <el-button class="button" @click="goodsCategoryList()">返回</el-button>
                </div>
            </div>
        </template>

        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="商品名称" prop="name"></el-table-column>
            <el-table-column label="商品价格" prop="price"></el-table-column>
            <el-table-column label="商品种类" prop="type"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="CirclePlus" circle plain type="primary" @click="showdialog(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!--添加商品至购物车弹窗-->
        <el-dialog v-model="dialogVisible" width="30%">
            <el-input-number v-model="goodsnum" :min="1"/>
            <span class="tip">请输入要添加的商品数量</span>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click=totrolly()> 确认 </el-button>
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
}
</style>