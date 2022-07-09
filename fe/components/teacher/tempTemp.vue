<template>
  <div class="custom-dialog">
    <el-dialog
      title="Tạo mới thông tin giáo viên"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="120px" class="demo-form">
        <el-form-item label="Name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>

        <el-form-item label="Phone">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>

        <el-form-item label="email">
          <el-input v-model="form.email"></el-input>
        </el-form-item>

        <el-form-item label="Work Room">
          <el-input v-model="form.workRoom"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('form')">Create</el-button>
          <el-button @click="resetForm('form')">Reset</el-button>
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
      form: {
        name: '',
        phone: '',
        email: '',
        workRoom: '',
      },

      rules: {
        name: [
          {
            required: true,
            message: 'Please input name',
            trigger: 'blur',
          },
          {
            min: 3,
            max: 5,
            message: 'Length should be 3 to 5',
            trigger: 'blur',
          },
        ],

        phone: [
          {
            required: true,
            message: 'Please input phone',
            trigger: 'blur',
          },
          {
            min: 8,
            max: 12,
            message: 'Length should be 3 to 5',
            trigger: 'blur',
          },
        ],

        email: [
          {
            required: true,
            message: 'Please input email',
            trigger: 'blur',
          },

          {
            type: 'email',
            message: 'Please input correct email address',
            trigger: ['blur', 'change'],
          },
        ],

        workRoom: [
          {
            required: true,
            message: 'Please input work room',
            trigger: 'blur',
          },
        ],
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          alert('submit!')
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
  },
}
</script>
