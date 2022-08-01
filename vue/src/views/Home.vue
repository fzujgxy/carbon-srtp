<template>
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


</template>


<script>

import axios from "axios";


export default {
  data(){
    return{
    }
  },

  methods: {
    run(){
      axios({
        method:'GET',
        url:'http://localhost:8000/lasso',
      }).then((response)=>{
        alert(response.data)
      })
    },
  },
}
</script>

