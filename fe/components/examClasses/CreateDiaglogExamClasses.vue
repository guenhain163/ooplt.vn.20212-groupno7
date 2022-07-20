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
        <el-form-item label="Name" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>

        <el-form-item label="Phone" prop="phone">
          <el-input v-model="ruleForm.phone"></el-input>
        </el-form-item>

        <el-form-item label="Email" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>

        <el-form-item label="Work room" prop="workRoom">
          <el-input v-model="ruleForm.workRoom"></el-input>
        </el-form-item>

        <el-form-item label="Modules" prop="modules">
          <el-checkbox-group v-model="ruleForm.modules">
            <el-checkbox label=1 name="modules"></el-checkbox>
            <el-checkbox
              label=4
              name="modules"
              value="1"
            ></el-checkbox>
          </el-checkbox-group>
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
            message: 'Please input name',
            trigger: 'blur',
          },
        ],

        phone: [
          {
            required: true,
            message: 'Please input Phone',
            trigger: 'blur',
          },
          {
            min: 8,
            max: 12,
            message: 'Length should be 8 to 12',
            trigger: 'blur',
          },
        ],

        email: [
          {
            required: true,
            message: 'Please input Email',
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

        modules: [
          {
            type: 'array',
            required: true,
            message: 'Please select at least one activity type',
            trigger: 'change',
          },
        ],
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          alert('submit!')
          await this.$axios.post('/admin/examiners', this.ruleForm).then((response) => {
            this.notifycation()
            this.$router.go({
            path: '/',
          })
          }).catch(() => {
            this.errorNotification()
          })
        } else {
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
