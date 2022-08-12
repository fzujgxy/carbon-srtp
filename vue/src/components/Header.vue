<template>
  <div style="height:50px;line-height: 50px;border-bottom:1px solid #ccc;display:flex">
  <div style="width: 200px;font-weight: bold;color: dodgerblue;font-size:25px">FzuJGXY</div>
  <el-image :src="url"/>

  <div style="flex: 1"></div>

  <!--下拉框-->
    <div style="width:auto;text-align: center;margin:5px 0">

      <el-dropdown>
      <el-button text size="large" style="font-size:20px;color:black">{{nickname}}<el-icon class="el-icon--right"><arrow-down /></el-icon>
      </el-button>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>
          <el-dropdown-item @click="$router.push('/')">退出系统</el-dropdown-item>
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
      url:'http://localhost:9090/picture/logo.jpg',
      nickname:JSON.parse(sessionStorage.getItem("user")).username,
    }
  },
  methods: {},
}
</script>

<style scoped>

</style>