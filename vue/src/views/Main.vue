<template>
    <el-container>
        <el-header style="height:78px;border-bottom:1px solid #ccc;display:flex">
          <el-image :src="logo" style="margin-left:10px"/>
          <div style="flex: 1"></div>
            <div style="width:auto;text-align: center">
              <el-button size="large" text  style="color:#626aef;font-weight: bold;width:100px;height:60px;margin:10px;font-size:20px" @click="aboutUs">关于我们</el-button>

              <el-dialog v-model="dialogVisible" title="成员组成" width="30%" :before-close="handleClose">

                <span slot="title" style="font-size:20px"></span>
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
           <el-card  style="width:700px;margin:80px auto;margin-left:100px;background:rgba(0,0,0,0.5);">
            <div style="font-weight: bold;font-size:25px;margin:8px 0;color:#00CDCD">LASSO简介</div>
            <div style="font-weight: bold;font-size:16px;float:left;margin:10px 0;color:#00CDCD">LASSO常用于数据维度多，指标之间存在线性相关关系的问题，能够很好地进行指标筛选。将其用于庞大指标数据下特征选择，进而突出重点指标。</div>
            <div style="font-weight: bold;font-size:25px;margin:8px 0;color:#00CDCD">EBRB的基本模型</div>
            <div style="font-weight: bold;font-size:20px;margin:8px 0;color:#00CDCD">1、模型介绍</div>
            <div style="font-weight: bold;font-size:16px;float:left;margin:10px 0;color:#00CDCD">EBRB模型是一种基于规则的专家系统,因其具有高效的规则生成和推理过程,以及理想的决策准确性,EBRB模型已被广泛用于桥梁风险评估、输油管道检测和消费者偏好等实际问题中。</div>
            <div style="font-weight: bold;font-size:20px;margin:8px 0;color:#00CDCD">2、EBRB规则表示:</div>
            <!-- <div style="font-weight: bold;font-size:18px;float:left;margin:10px 0;color:#B0C4DE">2.1 选择最优正则化参数</div> -->
            <div style="font-weight: bold;font-size:16px;float:left;margin:5px 0;color:#00CDCD">当EBRB模型应用于预测时，EBRB中的第k(k=1,2,…,L)条规则可以表示为<br>R<sub>k</sub>:<em>IFU</em><sub>1</sub>is{(A<sub>1,j</sub>,α<sup>k</sup><sub>1,j</sub>);j=1,2,…,J<sub>i</sub>}∧…∧U<sub>M</sub>is{（A<sub>M,j</sub>,α<sup>k</sup><sub>M,j</sub>);j=1,2,…,J<sub>M</sub>},THEN D is<br>{(D<sub>n</sub>,β<sup>k</sup><sub>n</sub>);n=1,2,…,N},with θ<sub>k</sub> and {δ<sub>1</sub>,δ<sub>2</sub>,…,δ<sub>M</sub>},</div>
            <div style="font-weight: bold;font-size:16px;float:left;margin:5px 0;margin-bottom:50px;color:#00CDCD">上式中{(A<sub>1,j</sub>,α<sup>k</sup><sub>1,j</sub>);j=1,2,…,J<sub>i</sub>}和{(D<sub>n</sub>,β<sup>k</sup><sub>n</sub>);n=1,2,…,N}分别表示第i个输入指标U<sub>i</sub>和输入指标D的置信框架;α<sup>k</sup><sub>i,j</sub>和β<sup>k</sup><sub>n</sub>分别表示第k条规则中评价等级A<sub>i,j</sub>和D<sub>n</sub>的置信度;θ<sub>k</sub>表示第k条规则的规则权重;δ<sub>i</sub>表示第i个输入指标的指标权重。</div>
            <!-- <div style="font-weight: bold;font-size:15px;float:left;margin:7px 0;color:#B0C4DE">首先调用python机器学习库中的sklearn.linear_model模块里的LASSO类并导入碳排放相关指标数据集，之后进行正则化参数lambda（λ）的选择（默认为1.0），在此选用交叉验证的方式选择均方误差最小的正则化参数λ,也即选择最优的λ值。</div> -->
            <!-- <div style="font-weight: bold;font-size:18px;float:left;margin:10px 0;color:#B0C4DE">2.2 筛选相关特征</div> -->
            <!-- <div style="font-weight: bold;font-size:15px;float:left;margin:7px 0;margin-bottom:50px;color:#B0C4DE">结合2.1得到的正则化参数，正则化参数越大对变量较多的线性模型的惩罚力度就越大，从而最终获得一个变量较少的模型。剔除系数被压缩为0的指标数据，即筛选出剩下的显著的变量</div> -->


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
      logo:'http://localhost:9090/picture/logo.png',
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