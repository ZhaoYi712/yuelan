<template>

  <div class="container">

    <div class="function-top-btn">
      <el-button type="primary" plain size="small" @click="openAddHouseDialog">新增</el-button>
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
        prop="owner"
        label="负责人"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="180">
      </el-table-column>
      <el-table-column
        prop="state"
        label="状态"
        width="180">
      </el-table-column>
      <el-table-column
        prop="create_time"
        label="注册日期"
        sortable
        width="180">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 编辑房源信息 -->
    <el-dialog title="更改房源信息" :visible.sync="editDialogVisible">
      <el-form :model="editFrom">
        <el-form-item label="房源昵称" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="editFrom.apartment_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="房源地址" :label-width="formLabelWidth" style="width: 500px">
          <el-input v-model="editFrom.address" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handUpdate('cancel')">取 消</el-button>
        <el-button type="primary" @click="handUpdate('confirm')">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 新增房源信息 -->
    <el-dialog title="新增房源信息" :visible.sync="addDialogVisible">
      <el-form :model="addHouseFrom">
        <el-form-item label="房源昵称" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="addHouseFrom.apartment_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="房源地址" :label-width="formLabelWidth" style="width: 500px">
          <el-input v-model="addHouseFrom.address" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleAddHouse('cancel')">取 消</el-button>
        <el-button type="primary" @click="handleAddHouse('confirm')">确 定</el-button>
      </div>
    </el-dialog>


    <!--分页器-->
    <div class="pagination">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
      </el-pagination>
    </div>

  </div>

</template>

<script>
import {houseList, deleteHouse, queryHouse, updateHouseInfo, addHouseApi} from "@/api/apartment/info";

export default {
  data() {
    return {
      // 遮罩层
      loading: false,
      pageList: [],
      index: '',
      editFrom:{},
      addHouseFrom: {},
      formLabelWidth: '100px',
      editDialogVisible: false,
      addDialogVisible: false,
      dialogFormVisible: false,
    }
  },

  // 页面挂载时
  mounted() {
    this.houseList()
  },

  methods: {
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

    handleDelete(index, row){
      this.$confirm('确认删除?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(() => {
        deleteHouse(row.id).then(res =>{
          if (res.code === 200){
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          }
        })
      }).catch(()=>{
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      })
    },

    // 打开对话框并查询房源信息
    handleEdit(index, row){
      this.editDialogVisible = true;
      queryHouse(row.id).then(res =>{
        this.editFrom = res.data
      })
    },

    // 更新房源信息
    handUpdate(option){
      this.editDialogVisible = false
      if (option === 'confirm'){
        updateHouseInfo(this.editFrom).then(res =>{
          this.$message({
            message: res.msg,
            type: 'success'
          });
        })
      }
    },

    // 打开新增房源对话框
    openAddHouseDialog(){
      this.addDialogVisible = true;
      // 每次打开应当清空绑定值
      this.addHouseFrom = {}
    },

    // 新增房源
    handleAddHouse(option){
      if (option === 'confirm'){
        addHouseApi(this.addHouseFrom).then(res=>{
          this.$message({
            message: res.msg,
            type: 'success'
          });
          if (res.code === 200){
            this.addDialogVisible = false;
          }
        })
      }
      if (option === 'cancel'){
        this.addDialogVisible = false;
      }
    },

  }
}
</script>

<style>

  .container{
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
  }

  .pagination{
    margin-top: 50px;
  }

  .function-top-btn{
    margin-bottom: 20px;
    width: 100%;
  }

</style>
