<template>
  <div class="app-container">
    <!--搜索-->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公寓编号" prop="apartmentId">
        <el-input
          v-model="queryParams.apartment_name"
          placeholder="请输入公寓编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房客姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入房客姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房客手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入房客手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合同失效时间" prop="end_time">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择合同失效时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!--按钮-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['apartment:tenant:add']"
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
          v-hasPermi="['apartment:tenant:update']"
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
          v-hasPermi="['apartment:tenant:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['apartment:tenant:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!--表格-->
    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="房客编号" align="center" prop="id" />
      <el-table-column label="房客姓名" align="center" prop="name" />
      <el-table-column label="房间号" align="center" prop="room_id" />
      <el-table-column label="房客手机号" align="center" prop="phone" />
      <el-table-column label="公寓昵称" align="center" prop="apartment_name" />
      <el-table-column label="房客身份证号" align="center" prop="id_number" />
      <el-table-column label="合同失效时间" align="center" prop="end_time" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.end_time, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:info:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改房客信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公寓编号" prop="apartment_id">
          <el-input v-model="form.apartment_id" placeholder="请输入公寓编号" />
        </el-form-item>
        <el-form-item label="房租编号" prop="room_id">
          <el-input v-model="form.room_id" placeholder="请输入房租编号" />
        </el-form-item>
        <el-form-item label="房客姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入房客姓名" />
        </el-form-item>
        <el-form-item label="房客手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入房客手机号" />
        </el-form-item>
        <el-form-item label="房客身份证号" prop="id_number">
          <el-input v-model="form.id_number" placeholder="请输入房客身份证号" />
        </el-form-item>
        <el-form-item label="合同失效时间" prop="end_time">
          <el-date-picker clearable
            v-model="form.end_time"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择合同失效时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/apartment/tenant";

export default {
  name: "Info",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 房客信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        apartment_name: null,
        room_id: null,
        name: null,
        phone: null,
        id_number: null,
        end_time: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        apartment_id: [
          { required: true, message: "公寓编号不能为空", trigger: "blur" }
        ],
        room_id: [
          { required: true, message: "房租编号不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "房客姓名不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "房客手机号不能为空", trigger: "blur" }
        ],
        id_number: [
          { required: true, message: "房客身份证号不能为空", trigger: "blur" }
        ],
        create_time: [
          { required: true, message: "合同生效时间不能为空", trigger: "blur" }
        ],
        end_time: [
          { required: true, message: "合同失效时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询房客信息列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        apartment_id: null,
        room_id: null,
        name: null,
        phone: null,
        id_number: null,
        create_time: null,
        end_time: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加房客信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改房客信息";
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
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除房客信息编号为"' + ids + '"的数据项？').then(function() {
        return delInfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('apartment:tenant:export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
