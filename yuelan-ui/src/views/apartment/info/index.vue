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
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handelAllRoom(scope.row)">查看房间</el-button>
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

    <!-- 查看当前房源所有房间 -->
    <el-dialog title="所有房间" :visible.sync="roomDialogVisible">
      <el-button type="primary" plain @click="handleAddRoom()">新增房间</el-button>

      <div class="room-box" v-for="(roomItem, index) in allRoomList" :key="index">
        <div class="floor">{{roomItem.floor}} 楼</div>
        <div class="room-list" >
          <el-button class="room-item" :type="room.state === 0 ? 'success':'info'" plain @click="roomInfo(room)" v-for="room in roomItem.roomVo" :key="room.id">
            {{room.room_id}}
          </el-button>
        </div>
      </div>

      <el-empty v-if="roomEmptyVisible" description="暂无房租，请先添加"></el-empty>
    </el-dialog>

    <!-- 添加房间 -->
    <el-dialog title="添加房间" :visible.sync="roomAddDialogVisible" >
      <el-form :model="addRoomFrom">
        <el-form-item label="房间号" :required="true" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="addRoomFrom.room_id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="楼层" :required="true" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="addRoomFrom.floor" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所属" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="addRoomFrom.apartment_name" :disabled="true" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="租户" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="addRoomFrom.tenants_id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="租金" :required="true" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="addRoomFrom.rent" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="房间状态" :required="true" :label-width="formLabelWidth" style="width: 300px">
          <el-select v-model="roomState" placeholder="房间状态">
            <el-option label="已租" value="0"></el-option>
            <el-option label="空闲" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="房间类型" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="addRoomFrom.type" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="房间面积" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="addRoomFrom.area" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="当前用电量" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="addRoomFrom.power" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="当前用水量" :clearable="true" :label-width="formLabelWidth" style="width: 300px">
          <el-input v-model="addRoomFrom.water" :clearable="true" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleAddRoom('cancel')">取 消</el-button>
        <el-button type="primary" @click="handleAddRoom('confirm')">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 房租详细信息交互框 -->
    <el-dialog
      title="房间详细信息"
      :visible.sync="roomInfoDialogVisible"
      width="30%">
      <el-form :model="infoRoom" label-width="80px">
        <el-form-item label="房间号">
          <el-input v-model="infoRoom.room_id" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="租户">
          <el-input v-model="infoRoom.tenants_id" style="width: 200px"></el-input>
        </el-form-item>
      </el-form>
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
import {addHouseApi, deleteHouse, houseList, updateHouseInfo, queryHouse} from "@/api/apartment/house";
import {addRoomApi, delRoomApi, queryAllRoom} from "@/api/apartment/room";

export default {
  data() {
    return {
      //房间状态（0已租，1空闲）
      roomState: '',
      //加载遮罩
      loading: false,
      //房源数据
      pageList: [],
      //空数据组件
      roomEmptyVisible: true,
      //表单label宽度
      formLabelWidth: '100px',
      //房源编辑models
      editFrom:{},
      //房源编辑弹出框
      editDialogVisible: false,
      //房源添加models
      addHouseFrom: {},
      //房源添加弹出框
      addDialogVisible: false,
      //添加房间models
      addRoomFrom: {
        room_id: '',
        floor: '',
        apartment_id: '',
        apartment_name: '',
        tenants_id: '',
        rent: '',
        state: '',
      },
      //房间数据
      allRoomList: [],
      //添加房间弹出框
      roomAddDialogVisible: false,
      //所有房间弹出框
      roomDialogVisible: false,
      //房间信息弹出框
      roomInfoDialogVisible: false,
      //当前房间信息models
      infoRoom: '',
    }
  },

  mounted() {
    this.houseList()
  },

  methods: {
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

    // 删除指定房源
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

    // 打开房源编辑对话框
    handleEdit(index, row){
      this.editDialogVisible = true;
      this.editFrom = {};
      queryHouse(row.id).then(res =>{
        this.editFrom = res.data
      })
    },

    // 更新房源
    handUpdate(option){
      if (option === 'confirm'){
        updateHouseInfo(this.editFrom).then(res =>{
          if (res.code === 200){
            this.editDialogVisible = false;
            this.$message({
              message: res.msg,
              type: 'success'
            });
          }
          else {
            this.$message({
              message: res.msg,
              type: 'error'
            });
          }
        }).catch(err =>{
          console.log('API请求发生错误：', err);
        })
      }
      else {
        this.editDialogVisible = false;
      }
    },

    // 打开新增房源交互框
    openAddHouseDialog(){
      this.addDialogVisible = true;
      // 每次打开应当清空绑定值
      this.addHouseFrom = {};
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

    // 查询当前所有房租
    handelAllRoom(row){
      this.roomDialogVisible = true;
      this.addRoomFrom.apartment_name = row.apartment_name;
      this.addRoomFrom.apartment_id = row.id;
      const params = {
        apartment_id: row.id
      }
      queryAllRoom(params).then(res =>{
        this.allRoomList = res.data;
        this.roomEmptyVisible = res.data.length === 0;
      })
    },

    // 新增房租
    handleAddRoom(option){
      this.roomAddDialogVisible = true;
      this.addRoomFrom = {};
      if (option === 'confirm'){
        this.addRoomFrom.state = this.roomState;
        addRoomApi(this.addRoomFrom).then(res =>{
          if (res.code === 200){
            this.$message({
              message: res.msg,
              type: 'success'
            });
          }
          else {
            this.$message({
              message: res.msg,
              type: 'error'
            });
          }
        })
      }
      if (option === 'cancel'){
        this.roomAddDialogVisible = false;
      }
    },

    // 打开当前房租信息框
    roomInfo(info){
      this.roomInfoDialogVisible = !this.roomInfoDialogVisible;
      console.log('roomInfo:', info)
      this.infoRoom = info
    },

    // 删除房租
    delRoom(id){
      this.$confirm('确认删除?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(() =>{
        delRoomApi(id).then(res =>{
          if (res.code === 200){
            this.$message({
              type: 'success',
              message: res.msg
            });
          }
          else {
            this.$message({
              type: 'error',
              message: res.msg
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

  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

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
