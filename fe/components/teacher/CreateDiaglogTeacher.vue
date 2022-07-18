<template>
  <div class="custom-dialog">
    <el-dialog
      title="Tạo mới thông tin giáo viên"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
      ><el-form
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
      >
        <el-form-item label="Tên" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>

        <el-form-item label="Điện thoại" prop="phone">
          <el-input v-model="ruleForm.phone"></el-input>
        </el-form-item>

        <el-form-item label="Email" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>

        <el-form-item label="Nơi làm việc" prop="workRoom">
          <el-input v-model="ruleForm.workRoom"></el-input>
        </el-form-item>

        <el-form-item label="Môn" prop="modules">
          <el-select v-model="ruleForm.modules" placeholder="Select">
            <el-option
              v-for="item in moduleData"
              :key="item.id"
              :label="item.name"
              :value="item.id"
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
  props: {
    // eslint-disable-next-line vue/require-default-prop
    moduleData: {
      type: Array,
    },
  },
  data() {
    return {
      dialogVisible: false,
      ruleForm: {
        name: '',
        modules: [],
        phone: '',
        email: '',
        workRoom: '',
      },
      rules: {
        name: [
          {
            required: true,
            message: 'Hãy nhập tên',
            trigger: 'blur',
          },
        ],

        phone: [
          {
            required: true,
            message: 'Hãy nhập số điện thoại',
            trigger: 'blur',
          },
          {
            min: 10,
            max: 12,
            message: 'Độ dài tối thiểu từ 8 đến 12 ký tự',
            trigger: 'blur',
          },
        ],

        email: [
          {
            required: true,
            message: 'Hãy nhập email',
            trigger: 'blur',
          },
          {
            type: 'email',
            message: 'Hãy nhập đúng dạng email',
            trigger: ['blur', 'change'],
          },
        ],

        workRoom: [
          {
            required: true,
            message: 'Hãy nhập nơi làm việc',
            trigger: 'blur',
          },
        ],
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const intToArray = Array.from(String(this.ruleForm.modules), Number)
          this.ruleForm.modules = intToArray
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
      this.$confirm('Bạn có chắc chắn không?')
        .then((_) => {
          done()
          this.resetForm('ruleForm')
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
  },
}
</script>
