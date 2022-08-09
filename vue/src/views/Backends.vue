<template>
  <div style="padding :10px">

   <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="age" label="年龄"/>
      <el-table-column prop="sex" label="性别"/>
      <el-table-column prop="email" label="邮箱"/>
      <el-table-column prop="phone" label="手机号"/>


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

  },


  data(){
    return{
      currentPage:1,
      total:10,
      tableData:[]
    }
  },
}
</script>

<style scoped>

</style>