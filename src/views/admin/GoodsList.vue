<script setup>
import {
    Select,
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import * as echarts from 'echarts'
const categorys = ref([
    {
            "id": 1,
            "goodsid": 1,
            "userid": 1,
            "username":"lilma",
            "goodsname": "Cucci",
            "goodsprice":7999.00,
            "goodsnum":1,
            "state":0
        }
])
//声明一个异步的函数
import { orderAdminshowService } from '@/api/order';
const adminshow=async()=>{
    let result=await orderAdminshowService()
    categorys.value=result.data
}
adminshow()

//规定表格商品状态的值（0-未收货，1-已收货）
const stateFormatter=(row, column, cellValue, index)=>{
      // 根据 cellValue 的值返回不同的字符串
      return cellValue === 0 ? '未收货' : '已收货';
    }

//删除订单
import { orderDeleteService } from '@/api/order';
const deleteorder=async(row)=>{
    let result=await orderDeleteService(row.id)
    adminshow()
}

//修改订单
const orderdialogVisible=ref(false)
const categoryModel = ref({
    goodsnum:'',
    state:''
})
const showDialog=(row)=>{
    orderdialogVisible.value=true
    categoryModel.value.goodsnum=row.goodsnum;
    categoryModel.value.id=row.id;
}
import { orderAlterService } from '@/api/order';
const alterorder=async()=>{
    let result=await orderAlterService(categoryModel.value)
    orderdialogVisible.value=false
    //解决数据不能及时更新问题
    window.location.reload()
    adminshow()
}

//数据报表界面
const dialogVisible=ref(false)
const stacategorys=ref([
    {
        "goodsname": "拯救者",
        "goodsnum": 2,
        "goodssum": 15999.60,
        "ratio": 0.126974
    }
])
import { orderStatisticService } from '@/api/order';
const stashow=async()=>{
    let result=await orderStatisticService()
    stacategorys.value=result.data
}
stashow()
const formatRatio=(row, column, value)=>{
      return value.toFixed(2) * 100 + '%';
}

//可视化界面
const visualizationDialogVisible=ref(false)

const initChart = () => {
  const chart = echarts.init(document.getElementById('chart'));
  const option = {
    series: [
      {
        name: '销售占比',
        type: 'pie',
        radius: '50%',
        data: stacategorys.value.map(item => ({
          value: item.ratio.toFixed(2) * 100, // 假设ratio是小数，需要转换为百分比
          name: item.goodsname
        })),
        label: {
          show: true,
          position: 'outside', // 标签的位置
          formatter: '{b}: {c}%', // 标签的内容，{b}是数据名称，{c}是数据值
        }
      }
    ]
  };
  chart.setOption(option);
}

const showVisualizationDialog=()=>{
      visualizationDialogVisible.value = true;
      setTimeout(() => {
    initChart();
  }, 0);
}

//搜索实现
const searchType = ref('username'); // 默认搜索类型为用户名
const username = ref('');
const goodsname = ref('');
import { orderAdminSearchService } from '@/api/order';
const adminsearch=async()=>{
    let result=await orderAdminSearchService(username.value,goodsname.value)
    categorys.value=result.data
    username.value=''
    goodsname.value=''
}

</script>
<template>
    <el-card class="page-container">

        <template #header>
            <div class="header">
                <span>订单列表</span>
                <div class="search">
                    <!-- 下拉框选择搜索类型 -->
                    <el-select v-model="searchType" placeholder="请选择搜索类型" class="select">
                      <el-option label="搜索用户名" value="username"></el-option>
                      <el-option label="搜索商品名" value="productname"></el-option>
                    </el-select>
                    <!-- 根据搜索类型动态显示输入框 -->
                    <el-input
                      v-if="searchType === 'username'"
                      class="input"
                      v-model="username"
                      placeholder="请输入要搜索的用户"
                    ></el-input>
                    <el-input
                      v-else
                      class="input"
                      v-model="goodsname"
                      placeholder="请输入要搜索的商品"
                    ></el-input>
                
                    <el-button class="button" type="primary" @click="adminsearch">搜索</el-button>
                    <el-button class="button" @click="adminshow">返回</el-button>
                </div>
            </div>
        </template>


        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="用户名"   prop="username"> </el-table-column>
            <el-table-column label="商品名称" prop="goodsname"></el-table-column>
            <el-table-column label="商品价格" prop="goodsprice"></el-table-column>
            <el-table-column label="商品数量" prop="goodsnum"></el-table-column>
            <el-table-column label="商品状态" prop="state" :formatter="stateFormatter"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteorder(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <el-button class="bt" @click="dialogVisible=true">销售统计报表</el-button>


        <!--数据展示弹窗-->
        <el-dialog v-model="dialogVisible" width="50%">
            <el-table :data="stacategorys" style="width: 100%">
                <el-table-column label="序号" width="100" type="index"> </el-table-column>
                <el-table-column label="商品名称" prop="goodsname"></el-table-column>
                <el-table-column label="商品数量" prop="goodsnum"></el-table-column>
                <el-table-column label="商品总销量" prop="goodssum"></el-table-column>
                <el-table-column label="销售占比" prop="ratio" :formatter="formatRatio"></el-table-column>
            </el-table>
            <el-button class="bt" @click="showVisualizationDialog">数据可视化</el-button>
        </el-dialog>

        <el-dialog v-model="visualizationDialogVisible" width="50%" title="数据可视化">
            <div id="chart" style="width: 100%; height: 400px;"></div>
        </el-dialog>

        <!--修改订单弹窗-->
        <el-dialog v-model="orderdialogVisible" width="30%">
            <el-form :model="categoryModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="商品数量">
                    <el-input v-model="categoryModel.goodsnum" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="订单状态">
                    <el-select v-model="categoryModel.state" placeholder="请选择">
                        <el-option label="已收货" value="1"></el-option>
                        <el-option label="未收货" value="0"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="orderdialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="alterorder"> 确认 </el-button>
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
        width: 50%;
        margin-right: 5%;
        .select{
            float:left;
            width:20%;
        }
        .input{
            width:50%;
        }
    }
    .search .button{
        width: 10%;
        float: right;
    }
    .tip{
        margin-left: 20px;
        font-size: 15px;
    }
    .bt{
        margin-top: 20px;
    }
}
</style>