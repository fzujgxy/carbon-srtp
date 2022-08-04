<template>
  <div>
    <el-menu
        style="width:200px;min-height: calc(100vh - 50px)"
        default-active="lasso"
        class="el-menu-vertical-demo"
        router>

      <el-menu-item index="lasso">
        <i class="el-icon-setting"></i>
        <template #title>Lasso回归分析</template>
      </el-menu-item>

      <el-menu-item index="ebrb">
        <i class="el-icon-setting"></i>
        <template #title>置信规则库分析</template>
      </el-menu-item>

    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>