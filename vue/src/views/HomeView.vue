<template>
  <div>

    <el-upload
    class="upload-demo"
    drag
    action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
    multiple>
    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
    <div class="el-upload__text">
      Drop file here or <em>click to upload</em>
    </div>
    </el-upload>


    <div style="margin:10px 0">
      <el-button type="primary" @click="add">运行</el-button>
    </div>


  </div>
</template>


<script>
export default {
  name: 'HomeView',
  components: {
  }

  methods:{

  }
}
</script>

