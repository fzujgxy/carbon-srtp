<template>

  <div style="height:50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: dodgerblue">FzuJGXY</div>
    <div style="flex: 1"></div>
    <div style="width:100px;text-align: center">
      <el-dropdown>
      <el-button type="primary" size="large">张三<el-icon class="el-icon--right"><arrow-down /></el-icon>
      </el-button>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>个人信息</el-dropdown-item>
          <el-dropdown-item>退出系统</el-dropdown-item>

        </el-dropdown-menu>
      </template>
    </el-dropdown>
    </div>
  </div>

</template>

<script>
export default {
  name: "Head",

}
</script>

<style scoped>

</style>