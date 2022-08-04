<template>
  <div>
  <el-upload
    class="upload-demo"
    drag
    action="http://localhost:9090/upload/files"
    name="files"
    accept=".xlsx"

  >
    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
    <div class="el-upload__text">
      Drop file here or <em>click to upload</em>
    </div>
    <template #tip>
      <div class="el-upload__tip">
        Excel files only
      </div>
      <div style="margin:10px 0">

  <el-button type="primary" @click="run">运行</el-button>

  </div>
    </template>

  </el-upload>


  <el-steps :active="active" align-center style="margin-top:10px">

    <el-step title="最优alpha图"/>
    <el-step title="自动生成的alpha取值"/>
    <el-step title="Lasso回归分析评分"/>
    <el-step title="相关系数"/>


  </el-steps>
  <el-button @click="next">Next step</el-button>


    <el-card class="box-card" style="margin-left:auto;margin-right:auto;margin-top:30px;height:45%;width:80%" >
      <template #header>
          <span style="font-size:25px">Result</span>
      </template>



      <div v-if="stepOneVif">自动生成的alpha取值为:{{result1}}</div>
      <div v-if="stepTwoVif">Lasso回归分析评分:{{result2}}</div>
      <div v-if="stepThreeVif">相关系数:{{result3}}</div>
      <el-image v-if="stepPicVif" style="width:35%" :src="url"/>






    </el-card>


</div>

</template>


<script>

import axios from "axios";
import request from "@/utils/request";


export default {
  data(){
    return{
      result1:{},
      result2:{},
      result3:{},
      url:'http://localhost:9090/picture/alpha.jpg',
      form:{},
      active:0,
      stepOneVif:false,
      stepTwoVif:false,
      stepThreeVif:false,
      stepPicVif:false,
    }
  },

  methods: {
    run() {
      axios({
        method: 'GET',
        url: 'http://localhost:8000/lasso',
      }).then((response) => {
          this.result1=response.data[3]
          this.result2=response.data[1]
          this.result3=response.data[2]
        if(this.active==0){
          this.stepThreeVif=false
          this.stepPicVif=true
        }


      })
    },



    next(){
      this.active++
      if (this.active > 3) this.active = 0

      if(this.active==0){
          this.stepThreeVif=false
          this.stepPicVif=true
        }

      if(this.active==1){
          this.stepPicVif=false
          this.stepOneVif=true
        }

      if(this.active==2){
          this.stepOneVif=false
          this.stepTwoVif=true
        }

      if(this.active==3){
          this.stepTwoVif=false
          this.stepThreeVif=true
        }


      },
  }
}
</script>

