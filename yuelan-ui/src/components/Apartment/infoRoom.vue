<template>
  <!-- 房租详细信息交互框 -->
  <el-dialog
    title="房租详细信息"
    :modal="false"
    :visible.sync="roomInfoDialogVisible"
    width="30%">
    <el-descriptions>
      <el-descriptions-item label="房间号">{{infoRoom.room_id}}</el-descriptions-item>
      <el-descriptions-item label="类型">{{infoRoom.type}}</el-descriptions-item>
      <el-descriptions-item label="租金">{{infoRoom.rent}}</el-descriptions-item>
      <el-descriptions-item label="创建时间">{{infoRoom.create_time}}</el-descriptions-item>
    </el-descriptions>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="updateRoom(infoRoom.id)">编 辑</el-button>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="danger" @click="delRoom(infoRoom.id)">删 除</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {delRoomApi} from "@/api/apartment/room";

  export default {
    name: 'infoRoom',
    props:{
      infoRoom:{
        type: Object,
        required: false,
      },
      roomInfoDialogVisible: {
        type: Boolean,
        required: false,
        default: false
      }
    },
    methods: {

      delRoom(id) {
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

      updateRoom(id){
        console.log('updateRoom', this.infoRoom)
      }

    },

  }

</script>
