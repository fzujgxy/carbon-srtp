<template>
  <div style="height:50px;line-height: 50px;border-bottom:1px solid #ccc;display:flex">
  <div style="width: 200px;font-weight: bold;color: dodgerblue;font-size:25px">FzuJGXY</div>
  <el-image :src="url"/>

  <div style="flex: 1"></div>

    <!--下拉框-->
    <div style="width:auto;text-align: center">

      <el-dropdown>
        <span class="el-dropdown-link">
          {{user.username}}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>

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
      user:{},
      url:'http://localhost:9090/picture/logo.jpg',
    }
  },
  methods: {},
}
</script>

<style scoped>

</style>