<template>
  <div class="container">

    <div class="function-top-btn">
      <el-button type="primary" plain size="small">新增</el-button>
      <el-button type="primary" plain size="small">导出</el-button>
      <el-button icon="el-icon-refresh" circle size="small" @click="houseList"></el-button>
    </div>

    <el-table
      :cell-style="{'text-align':'center'}"
      :header-cell-style="{'text-align':'center'}"
      :data="pageList"
      v-loading="loading"
      style="width: 100%"
      show-overflow-tooltip="true"
      :default-sort = "{prop: 'date', order: 'descending'}"
    >
      <!-- 展开表格行 -->
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="编号">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="地址">
              <span>{{ props.row.address }}</span>
            </el-form-item>
            <el-form-item label="注册日期">
              <span>{{ props.row.create_time }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column
        prop="apartment_name"
        label="公寓名称"
        sortable
        width="180">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.apartment_name }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="address"
        label="详细地址"
        width="180">
      </el-table-column>
      <el-table-column
        prop="state"
        label="状态"
        width="180">
      </el-table-column>
<!--      <el-table-column label="操作">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="danger"-->
<!--            @click="handleDelete(scope.$index, scope.row)">删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

  </div>
</template>

<script>
  import {houseList} from "@/api/apartment/house";

  export default {
    data(){
      return{
        //加载遮罩
        loading: false,
        //房源数据
        pageList: [],
      }
    },
    mounted() {
      this.houseList()
    },
    methods:{
      // 查询所有房源
      houseList(){
        this.loading = true;
        const params = {
          offset: 0,
          pagesize: 10
        }
        houseList(params).then(res =>{
          this.pageList = res.data.pageList
          this.loading = false;
        })
      },
    }
  }
</script>

<style>


</style>
