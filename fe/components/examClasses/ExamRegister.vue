<template>
  <div class="custom-dialog">
    <el-dialog
      title="Tạo mới thông tin giáo viên"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
      ><el-form ref="val" :model="val" label-width="120px" class="demo-val">
        <el-form-item label="Mã lớp thi" prop="codeModule">
          <el-input v-model="value.codeModule" disabled></el-input>
        </el-form-item>

        <el-form-item label="Số sinh viên" prop="numberStudent">
          <el-input v-model="value.numberStudent" disabled></el-input>
        </el-form-item>

        <el-form-item label="Cán bộ coi thi" prop="numberStudent">
          <el-select
            v-model="value2"
            multiple
            filterable
            default-first-option
            :multiple-limit=maxLectureExam
            placeholder="Choose tags for your article"
          >
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="`${item.name} - ${item.email}`"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('val')"
            >Create</el-button
          >
          <el-button @click="resetForm('val')">Reset</el-button>
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
      ruleForm: {
        modules: [],
      },
      value: {},
      options: [],
      value2: [],
      maxLectureExam: ''
    }
  },
  methods: {
    async submitForm(formName) {
      console.log(this.value)
      await this.$axios.post(`/admin/examClasses/${this.value.classExam.id}/examiners/division`, this.value2).then((response) => {
        this.notifycation()
        this.$router.go({
          path: '/exam-classes'
        })
      }).catch((error) => {
        console.log(error)
        this.errorNotification()
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
      this.value2 = []
    },
    handleClose(done) {
      this.$confirm('Bạn có chắc chắn không?')
        .then((_) => {
          done()
        })
        .catch((_) => {})
        this.resetForm('val')
    },
    notifycation() {
      this.$notify.success({
        title: 'Success',
        message: 'Create success',
        showClose: false,
      })
    },
    errorNotification() {
      this.$notify.error({
        title: 'Error',
        message: 'Can not create',
      })
    },
    async getlecturesExam() {
      await this.$axios
        .get('/admin/examiners/free', {
          params: {
            date: this.value.classExam.date,
            examShift: this.value.classExam.examShift,
            examClassId: this.value.classExam.id,
          },
        })
        .then((response) => {
          const data = response.data
          for (let index = 0; index < data.length; index++) {
            const element = data[index];
            element.value = element.id
          }
          this.options = data
        })
        .catch((error) => {
          console.log(error)
        })

      console.log(this.value)
        await this.$axios.get(`/admin/examiners?examClassId=${this.value.classExam.classId}`).then((response) => {
          console.log(response)
        }).catch((error) => {
          console.log(error)
        })
    },
    setCondition(value) {
      if (value >= 60) {
        this.maxLectureExam = 2
      } else this.maxLectureExam = 1
    },
  },
}
</script>
