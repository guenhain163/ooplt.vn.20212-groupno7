<template>
  <!-- <div class="justify-content-center"> -->
  <div class="row mt-5 pt-5 justify-content-center align-items-center">
    <div class="row justify-content-center align-items-center mt-5 pt-5 w-50">
      <el-form
        ref="ruleForm"
        :model="ruleForm"
        label-width="120px"
        class="demo-ruleForm w-50"
      >
        <el-form-item :rules="rules.email" label="Email" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>

        <el-form-item label="Password" prop="password" :rules="rules.password">
          <el-input
            v-model="ruleForm.password"
            type="password"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"
            >Sign In</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>

  <!-- </div> -->
</template>
<script>
export default {
  name: 'LoginPage',
  auth: 'guest',
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass')
        }
        callback()
      }
    }
    return {
      ruleForm: {
        email: '',
        password: '',
      },
      rules: {
        email: [
          {
            required: true,
            message: 'Please input email',
            trigger: 'blur',
          },
        ],

        password: [
          { validator: validatePass, trigger: 'blur' },
          {
            required: true,
            message: 'Please input password',
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
          await this.$auth
            .loginWith('local', {
              data: this.ruleForm,
            })
            .then((response) => {
              this.notifycation()
              this.middleweare(response.data.user.role)
            })
            .catch((e) => {
              console.log(e)
              this.notifycation()
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    middleweare(value) {
      switch (value) {
        case 1:
          this.$router.push({
            path: '/user',
          })
          break
        case 3:
          this.$router.push({
            path: '/',
          })
          break
      }
    },
    notifycation() {
      this.$notify.success({
        title: 'Success',
        message: 'Login success',
        showClose: false,
      })
    },
  },
}
</script>

<style scoped></style>
