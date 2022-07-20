<template>
  <div class="custom-dialog">
    <el-dialog
      title="Chỉnh sửa thông tin giáo viên"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form ref="val" :model="data" label-width="120px">
        <el-form-item label="Name">
          <el-input v-model="data.name"></el-input>
        </el-form-item>
        <el-form-item label="Phone">
          <el-input v-model="data.phone"></el-input>
        </el-form-item>
        <el-form-item label="Email">
          <el-input v-model="val.email" disabled></el-input>
        </el-form-item>
        <el-form-item label="Phòng làm việc">
          <el-input v-model="data.workRoom"></el-input>
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
        name: '',
        phone: '',
        workRoom: '',
      },
      val: {
        name: '',
        phone: '',
        workRoom: '',
        email: '',
      }
    }
  },
  methods: {
    async onSubmit() {
      await this.$axios
        .patch(`/admin/lecturers/${this.val.id}`, this.data)
        .then((response) => {
          this.notifycation()
          this.$router.go({
            path: '/',
          })
        })
        .catch(() => {
\          this.errorNotification()
        })
    },
    handleClose(done) {
      this.$confirm('Bạn có chắc chắn không?')
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
