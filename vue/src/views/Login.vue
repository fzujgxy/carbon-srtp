<template>
  <div style="width:100%;height:100%;background-color:darkslateblue;position:fixed">
    <div style="width:400px;margin:100px auto">
      <div style="color:#cccccc;font-size:30px;text-align:center;padding:30px">欢迎登陆</div>
      <el-form ref="form" :model="form" size="large">

        <el-form-item>
          <el-input v-model="form.phone"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input v-model="form.password" show-password></el-input>
        </el-form-item>

        <el-form-item>
          <el-row class="clickButton" style="margin:0px auto">
            <el-button style="width:30%" type="text">忘记密码</el-button>

             <!--注册按钮-->
          <el-button type="primary" style="width:30%;margin-right:10px" @click="register_add">注册</el-button>
            <el-dialog  v-model="register_dialogVisible" title="新用户注册" width="35%">

                <!--登录表单-->
            <el-form  dense=true :model="register_form" label-width="120px" >

            <el-form-item label="用户名" >
              <el-input v-model="register_form.username" style="width:80%" clearable/>
            </el-form-item>

            <el-form-item label="密码">
              <el-input v-model="register_form.password" style="width:80%" clearable/>
            </el-form-item>

            <el-form-item label="年龄">
              <el-input v-model="register_form.age" style="width:80%" clearable/>
            </el-form-item>

            <el-form-item label="性别">
                <el-radio v-model="register_form.sex" label="男">男</el-radio>
                <el-radio v-model="register_form.sex" label="女">女</el-radio>
                <el-radio v-model="register_form.sex" label="未知">未知</el-radio>
            </el-form-item>

            <el-form-item label="手机号">
              <el-input v-model="register_form.phone" style="width:80%" clearable/>
            </el-form-item>

            <el-form-item label="邮箱">
              <el-input v-model="register_form.email" style="width:80%" clearable/>
            </el-form-item>

            </el-form>

            <template #footer>
              <span class="register_foot">
                <el-button @click="register_dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="save">确认</el-button>
              </span>
            </template>
          </el-dialog>


            <el-button style="width:30%" type="success" @click="login">登 录</el-button>
            </el-row>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Login",
  data(){
    return{
      form:{},
      register_form:{},
      register_dialogVisible:false,
    }
  },
  methods:{
    register_add(){
      this.register_dialogVisible = true
      this.register_form = {}
    },

    save(){
      request.post("http://localhost:9090/user/register",this.register_form).then(res =>{
        console.log(res)
      })
      this.register_dialogVisible = false
      alert("注册成功")
    },

    login(){
      request.post("http://localhost:9090/user/login",this.form).then(res=>{
        if(res.code === '200'){
          this.$message({
            type:"success",
            message:"登陆成功"
          })
          this.$router.push("/home") //登录成功之后页面跳转至home
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>