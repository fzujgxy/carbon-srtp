<template>
  <div style="padding :10px">
    <div style="margin:10px 0">
      <el-button type="primary" @click="add">新增</el-button>
    </div>

   <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="age" label="年龄"/>
      <el-table-column prop="sex" label="性别"/>
      <el-table-column prop="email" label="邮箱"/>
      <el-table-column prop="phone" label="手机号"/>

      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button  @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>


    </el-table>

    <div style="margin:10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5,10,20]"
        :page-size=pageSize
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
         >
       </el-pagination>

      <el-dialog title="添加新用户" v-model="dialogVisible" width="30%">
        <el-form :model="form" label-width="120px">
           <el-form-item label="用户名" prop="username">
              <el-input v-model="form.username" style="width:80%" clearable/>
            </el-form-item>

            <el-form-item label="密码" prop="password">
              <el-input v-model="form.password" style="width:80%" clearable show-password/>
            </el-form-item>

            <el-form-item label="确认密码" prop="confirm">
              <el-input v-model="form.confirm" style="width:80%" clearable show-password/>
            </el-form-item>

            <el-form-item label="年龄" prop="age">
              <el-input v-model="form.age" style="width:80%" clearable/>
            </el-form-item>

              <el-form-item label="性别" prop="sex">
                <el-radio v-model="form.sex" label="男">男</el-radio>
                <el-radio v-model="form.sex" label="女">女</el-radio>
                <el-radio v-model="form.sex" label="未知">未知</el-radio>
              </el-form-item>

              <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" style="width:80%" clearable/>
            </el-form-item>

            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" style="width:80%" clearable/>
            </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确定</el-button>
          </span>
        </template>

      </el-dialog>

    </div>




  </div>
</template>

<script>
import request from "@/utils/request";

export default {
name: 'Backends',
  created(){
    this.load()
  },
  methods:{
    add(){
      this.dialogVisible = true
      this.form={}
    },

    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },

    handleDelete(id){
      console.log(id)
      request.delete("http://localhost:9090/user/" + id).then(res=>{
        if(res.code === '200'){
          this.$message({
            type:"success",
            message:"删除成功"
          })
        }else{
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()
      })
    },

    load(){
      request.get("http://localhost:9090/user/page",{
        params:
      {
        pageNum:this.currentPage,
        pageSize:this.pageSize,}
      }).then(res =>{
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },

    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },

    handleCurrentChange(pageNum){
      this.currentPage=pageNum
      this.load()
    },

    save(){
      if(this.form.id){
        request.put("http://localhost:9090/user",this.form).then(res=>{
          console.log(res)
          if(res.code==='200'){
            this.$message({
              type: "success",
              message: "更新成功"
            })
          }else{
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load() //刷新表格数据
          this.dialogVisible = false //关闭弹窗
        })
      }else{
        request.post("http://localhost:9090/user/register",this.form).then(res=>{
          console.log(res)
          if(res.code==='200'){
            this.$message({
              type: "success",
              message: "新增成功"
            })
          }else{
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load() //刷新表格数据
          this.dialogVisible = false //关闭弹窗
        })

      }
    },

  },


  data(){
    return{
      dialogVisible:false,
      form:{},
      currentPage:1,
      total:10,
      tableData:[]
    }
  },
}
</script>

<style scoped>

</style>