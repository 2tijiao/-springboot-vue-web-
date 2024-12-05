<script setup>
import {
    Edit,
    Delete
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
//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const categoryModel = ref({
    name:'',
    price:'',
    type:''
})
//添加商品表单校验
const rules = {
    name: [
        { required: true, message: '请输入商品名称', trigger: 'blur' },
    ],
    price: [
        { required: true, message: '请输入商品价格', trigger: 'blur' },
    ],
    type:[
        {required: true, message: '请输入商品种类', trigger: 'blur'},
    ]
}

//调用接口添加商品
import { goodsAddService } from '@/api/goods';
const addCategory=async()=>{
    //将title值设置为添加商品，方便后续判断
    title.value='添加商品'
    let result=await goodsAddService(categoryModel.value)
    //再调用获取所有商品的函数
    goodsCategoryList();
    dialogVisible.value=false
}

//每次点击时，数据置空
const setValue=()=>{
    categoryModel.value.name='';
    categoryModel.value.price='';
    categoryModel.value.type='';
}
//定义一个标题模型
const title=ref('')
//展示编辑弹窗
const showdialog=(row)=>{
    dialogVisible.value=true
    //将title值设置为编辑商品，方便后续判断
    title.value='编辑商品'
    //数据拷贝
    categoryModel.value.name=row.name;
    categoryModel.value.price=row.price;
    categoryModel.value.type=row.type;
    //扩展id属性，更新和删除商品需要
    categoryModel.value.id=row.id;
}

import { goodsUpdateService } from '@/api/goods';
const updateCategory=async()=>{
    //调用接口
    let result=await goodsUpdateService(categoryModel.value)
    dialogVisible.value=false
    //再调用获取所有商品的函数
    goodsCategoryList();
}

import { goodsDeleteService } from '@/api/goods';
const goodsDelete=async(row)=>{
    let result=await goodsDeleteService(row.id)
    //再调用获取所有商品的函数
    goodsCategoryList();
}

//搜索商品
const searchGoods=ref('')
import { goodsSearchService } from '@/api/goods';
const goodsSearch=async()=>{
    //调用接口
    let result=await goodsSearchService(searchGoods.value)
    //把结果直接渲染在表格上
    categorys.value=result.data;
    searchGoods.value=''
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
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible=true,title='添加商品',setValue()">添加商品</el-button>
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
                    <el-button :icon="Edit" circle plain type="primary" @click="showdialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="goodsDelete(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!--添加商品弹窗-->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="商品名称" prop="name">
                    <el-input v-model="categoryModel.name" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                 <el-form-item label="商品价格" prop="price">
                    <el-input v-model="categoryModel.price" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="商品种类" prop="type">
                    <el-input v-model="categoryModel.type" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title=='添加商品'?addCategory():updateCategory()"> 确认 </el-button>
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
        margin-right: 15%;
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
    
}
</style>