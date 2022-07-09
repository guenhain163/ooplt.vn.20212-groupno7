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
            multiple-limit="2"
            placeholder="Choose tags for your article"
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"
            >Create</el-button
          >
          <el-button @click="resetForm('ruleForm')">Reset</el-button>
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

      options: [
        {
          value: 'HTML',
          label: 'HTML',
        },
        {
          value: 'CSS',
          label: 'CSS',
        },
        {
          value: 'JavaScript',
          label: 'JavaScript',
        },
      ],
      value2: [],
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          alert('submit!')
          await this.$axios
            .post('/admin/lecturers', this.ruleForm)
            .then((response) => {
              this.notifycation()
              this.$router.go({
                path: '/',
              })
            })
            .catch((error) => {
              this.errorNotification()
              console.log(error)
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
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
      console.log(this.value)
      await this.$axios
        .get('/admin/examiners/free', {
          params: {
            date: this.value.classExam.date,
            examShift: this.value.classExam.examShift,
            examClassId: this.value.classExam.id,
          },
        })
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {
          console.log(error)
        })
    },
  },
}
</script>
