<template>

  <div class="app-container">

    <!--搜索-->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公寓名称" prop="apartment_name">
        <el-input
          v-model="queryParams.apartment_name"
          placeholder="请输入公寓名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公寓负责人" prop="owner">
        <el-input
          v-model="queryParams.owner"
          placeholder="请输入公寓负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公寓地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入公寓地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公寓状态" prop="state">
        <el-input
          v-model="queryParams.state"
          placeholder="请输入公寓状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!--顶部按钮-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['apartment:house:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['apartment:house:update']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['apartment:house:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['apartment:house:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getHouseList"></right-toolbar>
    </el-row>

    <!--表格数据-->
    <el-table v-loading="loading" :data="pageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="房源编号" align="center" prop="id" />
      <el-table-column label="房源名称" align="center" prop="apartment_name" />
      <el-table-column label="房源地址" align="center" prop="address" />
      <el-table-column label="房源状态" align="center" prop="state" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="roomView(scope.row)"
            v-hasPermi="['apartment:house:list']"
          >房间</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['apartment:house:update']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['apartment:house:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改房源信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="公寓名称" prop="apartment_name">
          <el-input v-model="form.apartment_name" placeholder="请输入公寓名称" />
        </el-form-item>
        <el-form-item label="公寓地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入公寓地址" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 弹出房间交互框 -->
    <el-dialog
      :title="title"
      :visible.sync="roomCompView"
      width="30%">
      <room v-if="roomCompView" :rows="roomDate"></room>
    </el-dialog>

    <!--分页器-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getHouseList"
    />

  </div>

</template>

<script>
import {addInfo, delInfo, listInfo, updateInfo, getInfo} from "@/api/apartment/house";
import {addRoomApi, delRoomApi, loadRoomApi, queryAllRoom, updateRoomApi} from "@/api/apartment/room";
import room from "@/components/Apartment/Room/index.vue";

export default {
  name: 'house',
  components:{room},
  data() {
    return {
      roomDate: null,
      roomCompView: false,
      // 表单参数
      form: {},
      // 加载遮罩
      loading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 房源数据
      pageList: [],
      // 总条数
      total: 0,
      // 显示搜索条件
      showSearch: true,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 表单label宽度
      formLabelWidth: '100px',
      // 房源编辑models
      editFrom:{},
      // 房源编辑弹出框
      editDialogVisible: false,
      // 房源添加models
      addHouseFrom: {},
      // 房源添加弹出框
      addDialogVisible: false,
      // 添加房间models
      addRoomFrom: {
        room_id: '',
        floor: '',
        apartment_id: '',
        apartment_name: '',
        tenants_id: '',
        rent: '',
        state: '',
      },
      // 房间状态（0已租，1空闲，2欠费）
      roomState: '',
      // 房源昵称
      apartment_name: '',
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        apartment_name: null,
        owner: null,
        address: null,
        state: null,
        create_time: null
      },
      // 表单校验
      rules: {
        apartment_name: [
          { required: true, message: "公寓名称不能为空", trigger: "blur" }
        ],
        owner: [
          { required: true, message: "公寓负责人不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "公寓地址不能为空", trigger: "blur" }
        ],
        state: [
          { required: true, message: "公寓状态不能为空", trigger: "blur" }
        ],
      }
    }
  },

  mounted() {
    this.getHouseList()
  },

  methods: {

    /** 展示 roomView 组件 */
    roomView(row) {
      this.roomDate = row;
      this.roomCompView = true;
      this.title = row.apartment_name;
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getHouseList();
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.getHouseList();
    },

    // 表单重置
    reset() {
      this.form = {
        id: null,
        apartment_name: null,
        owner: null,
        address: null,
        unit: null,
        state: null,
        create_time: null
      };
      this.resetForm("form");
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    /** 多选框选中数据 */
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

    /** 新增按钮操作: 新增房源 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加公寓信息";
    },

    /** 修改按钮操作: 修改房源 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公寓信息";
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getHouseList();
            });
          } else {
            addInfo(this.form).then(res => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getHouseList();
            });
          }
        }
      });
    },

    /** 删除按钮操作: 删除指定房源 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除公寓信息编号为"' + ids + '"的数据项？').then(function() {
        return delInfo(ids);
      }).then(() => {
        this.getHouseList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
        this.$modal.msgError("网络异常");
      });
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('apartment/house/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    },

    /** 查询所有房源 */
    getHouseList(){
      this.loading = true;
      listInfo(this.queryParams).then(res =>{
        this.pageList = res.rows;
        this.total = res.total;
        this.loading = false;
      })
    },

  }
}
</script>

<style>

  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

</style>
