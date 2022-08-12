<template>
  <div style="width:100%;height:100%;background-color:#FFC0CB;position:fixed">
    <div style="width:400px;margin:50px auto">
      <div style="color:#cccccc;margin-left:50px;font-size:30px;text-align:center;padding:30px">欢迎注册</div>
      <el-form ref="form" :model="form" size="large" :rules="rules" label-width="100px">

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

          <el-button style="width:60%;margin-left:45px" type="success" @click="save" >注 册</el-button>
            </el-form>

    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
name: "Register",

  data(){
    return{
      form:{},
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'},],
        password: [{required: true, message: '请输入密码', trigger: 'blur'},],
        confirm: [{required: true, message: '请确认密码', trigger: 'blur'},],
        age: [{required: true, message: '请输入年龄', trigger: 'blur'},],
        sex: [{required: true, message: '请输入性别', trigger: 'blur'},],
        email: [{required: true, message: '请输入邮箱', trigger: 'blur'},],
        phone: [{required: true, message: '请输入账号', trigger: 'blur'},],
      },
    }
  },

  methods: {
    save(){

      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.password != this.form.confirm) {
            this.$message({
              type: "error",
              message: '两次密码输入不一致'
            })
            return
          }

          request.post("http://localhost:9090/user/register", this.form).then(res => {
            if (res.code === '200') {
              this.$message({
                type: "success",
                message: "注册成功"
              })
              this.$router.push("/login")
            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    }
  },
}
</script>

<style scoped>

</style>


