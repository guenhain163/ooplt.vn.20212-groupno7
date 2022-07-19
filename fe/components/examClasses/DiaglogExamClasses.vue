<template>
  <div class="custom-dialog">
    <el-dialog
      title="Chỉnh sửa thông tin giáo viên"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form ref="val" :model="val" label-width="120px">
        <el-form-item label="Mã lớp thi">
          <el-input v-model="val.classExam.examCode"></el-input>
        </el-form-item>
        <el-form-item label="Ca thi">
          <el-input-number v-model="val.classExam.examShift"></el-input-number>
        </el-form-item>
        <el-form-item label="Ghi chú">
          <el-input v-model="val.classExam.note"></el-input>
        </el-form-item>
        <el-form-item label="Phòng">
          <el-input v-model="val.classExam.room"></el-input>
        </el-form-item>
        <el-form-item label="Kỳ học">
          <el-input v-model="val.classExam.openingPeriod"></el-input>
        </el-form-item>
        <el-form-item label="Trạng thái">
          <el-input-number v-model="val.classExam.status"></el-input-number>
        </el-form-item>
        <el-form-item label="Tuần">
          <el-input v-model="val.classExam.week"></el-input>
        </el-form-item>
        <el-form-item label="Nhóm">
          <el-input v-model="val.classExam.examGroup"></el-input>
        </el-form-item>
        <el-form-item label="SLDK">
          <el-input-number v-model="val.numberStudent"></el-input-number>
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
      val: {
        classExam: {},
      },
      data: {},
    }
  },
  methods: {
    async onSubmit() {
      this.data = {
        examCode: this.val.classExam.examCode,
        room: this.val.classExam.room,
        openingPeriod: this.val.classExam.openingPeriod,
        week: this.val.classExam.week,
        status: this.val.classExam.status,
        note: this.val.classExam.note,
        examGroup: this.val.classExam.examGroup,
        numberStudent: this.val.numberStudent,
        examShift: this.val.classExam.examShift,
      }
      console.log(this.data)
      await this.$axios
        .patch(`/admin/examClasses/${this.val.classExam.id}`, this.data)
        .then((response) => {
          this.notifycation()
          this.$router.go({
            path: '/exam-classes',
          })
        })
        .catch((error) => {
          console.log(error)
          this.errorNotification()
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

<style>
.el-input-number span {
  display: none !important;
}

.el-input-number input {
  padding: 0px !important;
  padding-left: 15px !important;
  text-align: justify !important;
}
</style>
