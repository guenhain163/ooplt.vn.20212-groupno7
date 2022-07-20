<template>
  <div class="page-header-fixed page-quick-sidebar-over-content">
    <SlideBarUser @getUserCode="getUserCode" />
    <div class="page-container">
      <div class="page-content-wrapper setting">
        <div class="page-content">
          <div class="container">
              <el-row class="demo-autocomplete">
                <el-col :span="12">
                  <div class="sub-title mb-2 mx-3">Tìm thông tin</div>

                  <el-select v-model="value" filterable placeholder="Select">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    >
                    </el-option>
                  </el-select>

                  <el-input
                    v-model="getModuleName"
                    placeholder="Hãy nhập tên giảng viên"
                    class="mx-5 w-25"
                  ></el-input>
                </el-col>
              </el-row>

            <div class="m-5">
              <el-table
                :data="responseData"
                style="width: 100%"
                height="450"
              >
                <el-table-column prop="stt" label="STT" width="50">
                </el-table-column>
                <el-table-column prop="codeModule" label="Mã học phần" width="120">
                </el-table-column>
                <el-table-column prop="nameModule" label="Tên học phần" width="120">
                </el-table-column>
                <el-table-column prop="code" label="Mã lớp" width="80">
                </el-table-column>
                <el-table-column prop="examClass.examCode" label="Mã lớp thi" width="100">
                </el-table-column>

                <el-table-column
                  min-width="100"
                  class-name="text-secondary"
                  label="Action"
                >
                  <template slot-scope="scope">
                    <el-popover
                      :ref="'popover' + scope.$index"
                      placement="bottom-end"
                      trigger="click"
                    >
                      <el-link @click="register(scope.$index, scope.row)">
                        <strong>Đăng ký thi </strong>
                      </el-link>
                      <br />

                      <el-button slot="reference" size="mini">
                        Action &#x25BC;
                      </el-button>
                    </el-popover>
                  </template>
                </el-table-column>
              </el-table>
            </div>


          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SlideBarUser from '../../components/SlideBarUser'
import constant from '../../constant/constant'

export default {
  name: 'IndexPage',
  components: {
    SlideBarUser,
  },
  data() {
    return {
      responseData: [
      ],
      options: [],
      value: 20222,
      getModuleName: '',
      lectureList: [],
      id: '',
    }
  },
  watch: {
    getModuleName(value) {
      this.getModuleList(this.id)
    },
    value(value) {
      this.getModuleList(this.id)
    }
  },
  created() {
    this.options = constant
  },
  methods: {
    async getModuleList(value) {
      await this.$axios
        .get(`/user/${value}/classes?semester=${this.value}`)
        .then((response) => {
          this.responseData = []
          this.responseData = response.data
          for (let index = 0; index < this.responseData.length; index++) {
            const element = this.responseData[index];
            element.stt = index + 1
          }

        })
        .catch(() => {
        })
    },
    getUserCode(value) {
      this.id = value
      this.getModuleList(value)
    },
    async register(index, value) {
      this.$refs[`popover${index}`].doClose()
      await this.$axios
    }
  },
}
</script>

<style scoped></style>
