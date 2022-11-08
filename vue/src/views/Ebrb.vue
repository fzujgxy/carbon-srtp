<template>
  <div>
    <el-upload
      class="upload-demo1"
      drag
      action="http://localhost:9090/upload/files/EbrbDatas"
      name="files"
      accept=".xlsx"
      style="width:48%"
    >
    <el-icon class="el-icon--upload1"><upload-filled /></el-icon>
    <div class="el-upload__text">click to upload TrainSet</div>
    </el-upload>

    <el-upload
      class="upload-demo2"
      drag
      action="http://localhost:9090/upload/files/EbrbTest"
      name="files"
      accept=".xlsx"
      style="width:48%"
    >
    <el-icon class="el-icon--upload2"><upload-filled /></el-icon>
    <div class="el-upload__text">click to upload TestSet</div>
    </el-upload>


    <div class="slider-demo-block">
      <el-slider v-model="N" show-input />
    </div>

  <el-button type="primary" @click="run">运行</el-button>

    <el-steps :active="active" align-center style="margin-top:10px">

      <el-step title="alpha"/>
      <el-step title="S"/>
      <el-step title="W"/>
      <el-step title="预测数据"/>
    </el-steps>

      <el-button @click="next">Next step</el-button>

     <el-card class="box-card" style="margin-left:auto;margin-right:auto;margin-top:30px;height:37%;width:80%" >
      <template #header>
          <span style="font-size:25px">Result</span>
      </template>
       <div v-if="stepFourVif">alpha:{{result1}}</div>
       <div v-if="stepOneVif">S:{{result2}}</div>
       <div v-if="stepTwoVif">W:{{result3}}</div>
       <div v-if="stepThreeVif">预测数据:{{result4}}</div>
     </el-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Ebrb",

  data(){
    return{
      result1:{},
      result2:{},
      result3:{},
      result4:{},
      form:{},
      N:0,
      active:0,
      stepOneVif:false,
      stepTwoVif:false,
      stepThreeVif:false,
      stepFourVif:false,
    }
  },

  methods: {
    run() {
      axios({
        method: 'GET',
        url: "http://localhost:8000/ebrb/?N="+this.N,
      }).then((response) => {
        this.result1 = response.data[0]
        this.result2 = response.data[1]
        this.result3 = response.data[2]
        this.result4 = response.data[3]
        if (this.active == 0) {
          this.stepThreeVif = false
          this.stepFourVif = true
        }
      })
    },

    next(){
      this.active++
      if (this.active > 3) this.active = 0

      if(this.active==0){
        this.stepThreeVif=false
        this.stepFourVif=true
      }

      if(this.active==1){
        this.stepFourVif=false
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

<style scoped>
.slider-demo-block {
  display: flex;
  align-items: center;
}
.slider-demo-block .el-slider {
  margin-top: 0;
  margin-left: 12px;
}
</style>