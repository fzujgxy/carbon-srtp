<template>
  <div style="width:100%;height:100%;background-color:darkslateblue;position:fixed">
    <div style="width:400px;margin:100px auto">
      <div style="color:#cccccc;font-size:30px;margin-left:60px;padding:30px">欢迎登陆</div>
      <el-form ref="form" :model="form" size="large" :rules="rules">

        <el-form-item label="账 号:" prop="phone">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>

        <el-form-item label="密 码:" prop="password">
          <el-input v-model="form.password" show-password></el-input>
        </el-form-item>

        <el-form-item style="margin-left:50px">
        <el-button style="width: 40%;margin-left:35px" type="success" @click="$router.push('/register')">注 册</el-button>
        <el-button style="width: 40%" type="primary" @click="login">登 录</el-button>
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
      rules:{
        password:[{required: true, message: '请输入密码', trigger: 'blur'},],
        phone:[{required: true, message: '请输入账号', trigger: 'blur'},],
      }
    }
  },
  methods:{


    login(){
      this.$refs['form'].validate((valid) =>{
        if(valid){
          request.post("http://localhost:9090/user/login",this.form).then(res=>{
          if(res.code === '200'){
            this.$message({
             type:"success",
             message:"登陆成功"
            })
            this.$router.push("/lasso") //登录成功之后页面跳转至lasso
          }else {
           this.$message({
              type: "error",
              message: res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>