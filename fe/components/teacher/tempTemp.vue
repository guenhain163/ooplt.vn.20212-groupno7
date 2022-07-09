<template>
  <div class="custom-dialog">
    <el-dialog
      title="Phân công cán bộ coi thi"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
    asdsad
      <el-form ref="val" :model="data" label-width="120px">
        <el-form-item label="Mã lớp thi">
          <el-input v-model="data.codeModule" disabled></el-input>
        </el-form-item>
        <el-form-item label="Số sinh viên">
          <el-input
            v-model="data.numberStudent" disabled
          ></el-input>
        </el-form-item>

        <el-form-item label="Cán bộ coi thi số" prop="modules">
          <el-checkbox-group v-model="data.modules">
            <el-checkbox label=1 name="modules"></el-checkbox>
            <el-checkbox
              label=2
              name="modules"
              value="1"
            ></el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">Edit</el-button>
          <el-button @click="handleClose">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
      data: {
        modules: [],
      },
    }
  },
  methods: {
    onSubmit() {
      // this.$axios
      //   .patch(`/admin/lecturers/${this.val.id}`, this.data)
      //   .then((response) => {
      //     this.notifycation()
      //     this.$router.go({
      //       path: '/',
      //     })
      //   })
      //   .catch((error) => {
      //     console.log(error)
      //     this.errorNotification()
      //   })
      console.log(this.$refs.val)
    },
    handleClose(done) {
      this.$confirm('Are you sure to close this dialog?')
        .then((_) => {
          done()
        })
        .catch((_) => {})
    },
    notifycation() {
      this.$notify.success({
        title: 'Success',
        message: 'Edit success',
        showClose: false,
      })
    },
    errorNotification() {
      this.$notify.error({
        title: 'Error',
        message: 'Can not edit',
      })
    },
  },
}
</script>
