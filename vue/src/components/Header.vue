<template>
  <div style="height:50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
  <div style="width: 200px;padding-left: 30px;font-weight: bold;color: dodgerblue">FzuJGXY</div>
  <div style="flex: 1"></div>

    <!--下拉框-->
    <div style="width:100px;text-align: center">

      <el-dropdown>
      <el-button type="primary" size="large">张三<el-icon class="el-icon--right"><arrow-down /></el-icon>
      </el-button>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>个人信息</el-dropdown-item>
          <el-dropdown-item>退出系统</el-dropdown-item>

        </el-dropdown-menu>
      </template>
    </el-dropdown>
    </div>
  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "Head",
  data(){
    return {
      register_form:{},
      register_dialogVisible:false,

    }
  },
  methods:{

    login_add(){
      this.login_dialogVisible = true
      this.login_form = {}
    },
    register_add(){
      this.register_dialogVisible = true
      this.register_form = {}
    },
    save(){
      request.post("http://localhost:9090/user/register",this.form).then(res =>{
        console.log(res)
      })
      this.register_dialogVisible = false
      alert("注册成功")
    },
  }
}
</script>

<style scoped>

</style>