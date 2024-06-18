<template>
  <div class="app-container">

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['apartment:room:add']"
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
          v-hasPermi="['apartment:room:edit']"
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
          v-hasPermi="['apartment:room:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['apartment:room:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="handleReFresh"></right-toolbar>
    </el-row>

    <div class="room" v-loading="loading">
      <div class="room-box" v-for="(roomItem, index) in infoList" :key="index">
        <div class="floor">{{roomItem.floor}} 楼</div>
        <div class="room-list" >
          <el-button class="room-item" :type="room.state === 0 ? 'success':'info' " plain @click="handleUpdate(room)" v-for="room in roomItem.roomVo" :key="room.id">
            {{ room.room_id }}
          </el-button>
        </div>
      </div>
      <el-empty v-if="roomEmptyVisible" description="暂无房租，请先添加"></el-empty>
    </div>

    <!-- 添加或修改房租信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="房间编号" prop="room_id">
          <el-input v-model="form.room_id" placeholder="请输入房间编号" />
        </el-form-item>
        <el-form-item label="楼层" prop="floor">
          <el-input v-model="form.floor" placeholder="请输入楼层" />
        </el-form-item>
<!--        <el-form-item label="公寓id" prop="apartment_id">-->
<!--          <el-input v-model="form.apartment_id" placeholder="请输入公寓id" />-->
<!--        </el-form-item>-->
        <el-form-item label="租金" prop="rent">
          <el-input v-model="form.rent" placeholder="请输入租金" />
        </el-form-item>
        <el-form-item label="房间状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择房间状态">
            <el-option label="已租" value="1"></el-option>
            <el-option label="空置" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="房间面积" prop="area">
          <el-input v-model="form.area" placeholder="请输入房间面积" />
        </el-form-item>
        <el-form-item label="当前用电量" prop="power">
          <el-input v-model="form.power" placeholder="请输入当前用电量" />
        </el-form-item>
        <el-form-item label="当前用水量" prop="water">
          <el-input v-model="form.water" placeholder="请输入当前用水量" />
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
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/apartment/room";

export default {
  name: "room",
  props: ['rows'],
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
      // 房租信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 空数据组件
      roomEmptyVisible: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        room_id: null,
        floor: null,
        apartment_id: null,
        tenants_id: null,
        rent: null,
        state: null,
        type: null,
        area: null,
        power: null,
        water: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        room_id: [
          { required: true, message: "房间编号不能为空", trigger: "blur" }
        ],
        floor: [
          { required: true, message: "楼层不能为空", trigger: "blur" }
        ],
        apartment_id: [
          { required: true, message: "公寓id不能为空", trigger: "blur" }
        ],
        state: [
          { required: true, message: "房间状态不能为空", trigger: "blur" }
        ],
      }
    };
  },
  mounted() {
    this.getList(this.rows.id);
  },
  methods: {
    /** 查询房租信息列表 */
    getList(id) {
      this.roomEmptyVisible = false;
      this.loading = true;
      listInfo(id).then(res =>{
        this.infoList = res.data;
        this.loading = false;
        if (res.data.length === 0) {
          this.roomEmptyVisible = true;
        }
      })
    },

    // 刷新数据
    handleReFresh() {
      this.getList(this.rows.id)
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
        roomId: null,
        floor: null,
        apartmentId: null,
        tenantsId: null,
        rent: null,
        state: null,
        type: null,
        area: null,
        createTime: null,
        power: null,
        water: null
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
      this.title = "添加房租信息";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改房租信息";
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
      this.$modal.confirm('是否确认删除房租信息编号为"' + ids + '"的数据项？').then(function() {
        return delInfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('system/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    },

  }
};
</script>

<style>
.room-box{
  display: flex;
  flex-direction: column;
}
.floor{
  text-align: center;
  font-size: 15px;
}
.room-list{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.room-item{
  margin: 15px;
}
</style>
