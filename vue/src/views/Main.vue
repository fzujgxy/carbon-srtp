<template>
    <el-container>
        <el-header style="height:78px;border-bottom:1px solid #ccc;display:flex">
          <el-image :src="logo"/>
          <div style="flex: 1"></div>
            <div style="width:auto;text-align: center">
              <el-button size="large" text  style="color:#626aef;font-weight: bold;width:100px;height:60px;margin:10px;font-size:20px" @click="aboutUs">关于我们</el-button>

              <el-dialog v-model="dialogVisible" title="成员组成" width="30%" :before-close="handleClose">

                <span slot="title" style="font-size=20px"></span>
              <div style="font-weight: bold;font-size:22px;margin:10px 0">指导老师</div>
              <div style="font-size:18px;margin:10px 0">杨隆浩&nbsp&nbsp副教授</div>
              <div style="font-size:18px;margin:10px 0">吴海东&nbsp&nbsp工程师</div>
              <div style="font-weight: bold;font-size:22px;margin:10px 0">团队成员</div>
              <div style="font-size:18px;margin:10px 0">潘丽梅</div>
              <div style="font-size:18px;margin:10px 0">徐可楹</div>
              <div style="font-size:18px;margin:10px 0">李裕普</div>
              <div style="font-size:18px;margin:10px 0">王士萌</div>
              <div style="font-size:18px;margin:10px 0">高奕人</div>

              <template #footer>
                <span class="dialog-footer">
                  <el-button type="primary" @click="dialogVisible = false">确定</el-button>
                </span>
              </template>
            </el-dialog>

            </div>
        </el-header>


      <div class="background">
      <el-container >
        <el-main>
         <el-card  style="width:500px;margin:100px auto;margin-right:0px; background:rgba(0,0,0,0.5);">
         <div style="color:#00CDCD;font-size:30px;padding:50px 0">欢迎使用Lasso分析模型</div>
         <el-form ref="form" :model="form" size="large" :rules="rules" label-width="150px">

          <el-form-item prop="phone">
            <el-input :prefix-icon="Avatar" style="width:70%" v-model="form.phone" placeholder="请输入账号"></el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input :prefix-icon="Lock" style="width:70%" v-model="form.password" show-password placeholder="请输入密码"></el-input>
          </el-form-item>

          <el-form-item>
            <div style="display:flex">
              <el-input :prefix-icon="Key" style="width:50%" v-model="form.validCode" placeholder="请输入验证码"></el-input>
              <ValidCode @input="createValidCode" />
            </div>
          </el-form-item>

     </el-form>
          <el-form-item size="large" style="margin-left:80px;margin-top:50px">
            <el-button style="width: 40%;float:left" type="success" @click="$router.push('/register')">注 册</el-button>
            <el-button style="width: 40%;float:right" type="primary" @click="login">登 录</el-button>
          </el-form-item>

        </el-card>
        </el-main>

        <el-main>
          <el-card style="width:700px;margin:100px auto;margin-left:100px;background:rgba(0,0,0,0.5);">
            <div style="font-weight: bold;font-size:25px;margin:10px 0;color:#B0C4DE">LASSO的基本模型</div>
            <div style="font-weight: bold;font-size:20px;margin:10px 0;color:#B0C4DE">一、模型构造依据</div>
            <div style="font-weight: bold;font-size:15px;float:left;margin:10px 0;color:#B0C4DE">由于数据维度多，指标之间存在线性相关关系的问题，我们选用LASSO回归模型进行指标筛选。即回归拟合后的误差和系数的绝对值之和。将其用于庞大指标数据下特征选择，进而突出重点指标。</div>
            <div style="font-weight: bold;font-size:20px;margin:10px 0;color:#B0C4DE">二、LASSO代码实现:</div>
            <div style="font-weight: bold;font-size:18px;float:left;margin:10px 0;color:#B0C4DE">2.1 选择最优正则化参数</div>
            <div style="font-weight: bold;font-size:15px;float:left;margin:7px 0;color:#B0C4DE">首先调用python机器学习库中的sklearn.linear_model模块里的LASSO类并导入碳排放相关指标数据集，之后进行正则化参数lambda（λ）的选择（默认为1.0），在此选用交叉验证的方式选择均方误差最小的正则化参数λ,也即选择最优的λ值。</div>
            <div style="font-weight: bold;font-size:18px;float:left;margin:10px 0;color:#B0C4DE">2.2 筛选相关特征</div>
            <div style="font-weight: bold;font-size:15px;float:left;margin:7px 0;margin-bottom:50px;color:#B0C4DE">结合2.1得到的正则化参数，正则化参数越大对变量较多的线性模型的惩罚力度就越大，从而最终获得一个变量较少的模型。剔除系数被压缩为0的指标数据，即筛选出剩下的显著的变量</div>


          </el-card>
        </el-main>
      </el-container>

        <el-footer style="margin:-5% auto">
          <div>
            <div style="font-weight: bold;font-size:25px;color:#FFE4E1">友情链接</div>
            <el-link href="https://www.fzu.edu.cn/" style="font-weight: bold;font-size:20px;color:#FFE4E1">福州大学&nbsp&nbsp&nbsp&nbsp</el-link>
            <el-link href="https://jgxy.fzu.edu.cn/" style="font-weight: bold;font-size:20px;color:#FFE4E1">福州大学经济与管理学院&nbsp&nbsp&nbsp&nbsp</el-link>
            <el-link href="https://dsi.fzu.edu.cn/" style="font-weight: bold;font-size:20px;color:#FFE4E1">福州大学决策科学研究所</el-link>
          </div>
        </el-footer>
      </div>
      </el-container>
</template>

<script>
import request from "@/utils/request";
import {Avatar,Lock,Key} from "@element-plus/icons-vue";
import ValidCode from "@/components/ValidCode";
export default {
  name: "Main",
  components:{
    ValidCode
  },
  data(){
    return{
      validCode:'',
      dialogVisible:false,
      logo:'http://localhost:9090/picture/logo.jpg',
      form:{},
      rules:{
        password:[{required: true, message: '请输入密码', trigger: 'blur'},],
        phone:[{required: true, message: '请输入账号', trigger: 'blur'},],
      }
    }
  },
  setup(){
    return{
      Avatar,
      Lock,
      Key
    }
  },
  methods:{
    aboutUs(){
      this.dialogVisible = true
    },
    // 接收验证码组件提交的4位验证码
    createValidCode(data){
      this.validCode = data
    },
    login(){
      this.$refs['form'].validate((valid) =>{
        if(valid){
          if(!this.form.validCode){
            this.$message.error("请填写验证码")
            return
          }
          if(this.form.validCode.toLowerCase()!==this.validCode.toLowerCase()){
            this.$message.error("验证码错误")
            return
          }
          request.post("http://localhost:9090/user/login",this.form).then(res=>{
            if(res.code === '200'){
              this.$message({
                type:"success",
                message:"登陆成功"
              })
              sessionStorage.setItem("user",JSON.stringify(res.data))
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
.background{
  background:url("../assets/background.png");
  width: 100%;
  height: 100%;
  position:fixed;
  margin-top: 5%;/*上边距*/
  margin-left: -0px;/*左边距*/
  background-size:100% 100%;
}

</style>