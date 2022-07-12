<template>
  <div class="page-header-fixed page-quick-sidebar-over-content">
    <SlideBar />
    <div class="page-container">
      <div class="page-content-wrapper setting">
        <div class="page-content">
          <div class="container mt-5 w-50">
            <el-form
              ref="ruleForm"
              :model="ruleForm"
              :rules="rules"
              label-width="120px"
              class="demo-ruleForm"
            >
              <el-form-item label="Tên" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
              </el-form-item>

              <el-form-item label="Email" prop="email">
                <el-input v-model="ruleForm.email"></el-input>
              </el-form-item>

              <el-form-item label="Zone" prop="region">
                <el-select
                  v-model="ruleForm.region"
                  placeholder="Activity zone"
                >
                  <el-option label="Zone one" value="shanghai"></el-option>
                  <el-option label="Zone two" value="beijing"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="Noi dung" prop="desc">
                <el-input v-model="ruleForm.desc" type="textarea"></el-input>
              </el-form-item>

              <el-form-item label="Activity type" prop="type">
                <el-checkbox-group v-model="ruleForm.type">
                  <el-checkbox
                    label="Please agree with our policy"
                    name="type"
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
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        name: '',
        email: '',
        region: '',
        delivery: false,
        type: [],
        desc: '',
      },
      rules: {
        name: [
          {
            required: true,
            message: 'Please input Activity name',
            trigger: 'blur',
          },
        ],

        email: [
          {
            required: true,
            message: 'Please input Activity email',
            trigger: 'blur',
          },
        ],

        region: [
          {
            required: true,
            message: 'Please select Activity zone',
            trigger: 'change',
          },
        ],
        type: [
          {
            type: 'array',
            required: true,
            message: 'Please select at least one activity type',
            trigger: 'change',
          },
        ],
        desc: [
          {
            required: true,
            message: 'Please input activity form',
            trigger: 'blur',
          },
        ],
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
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
  },
}
</script>
