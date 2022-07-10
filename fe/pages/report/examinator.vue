<template>
  <div class="page-header-fixed page-quick-sidebar-over-content">
    <SlideBar />
    <div class="page-container">
      <div class="page-content-wrapper setting">
        <div class="page-content">
          <div class="container">
            <div>
              <el-row class="demo-autocomplete">
                <el-col :span="12">
                  <div class="sub-title mb-2 mx-3">Tìm giảng viên</div>
                  <el-select
                    v-model="getExaminers"
                    class="mx-5"
                    filterable
                    remote
                    reserve-keyword
                    placeholder="Hãy nhập tên giảng viên"
                    :remote-method="remoteMethod"
                    :loading="loading"
                  >
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    >
                    </el-option>
                  </el-select>

                  <el-input
                    v-model="getTime"
                    placeholder="Please input semester"
                    class="mx-5 w-25"
                    :disabled="getExaminers === ''"
                  ></el-input>
                </el-col>
              </el-row>
            </div>

            <div class="border m-5 w-50">
              <h3 class="text-center mt-3">Thông tin giảng viên:</h3>
              <div class="py-2 px-5">
                <p class="mx-5">Tên: <span>{{ responseData.lecturer.name }}</span></p>
                <p class="mx-5">Email: <span>{{ responseData.lecturer.email }}</span></p>
                <p class="mx-5">Bộ môn: <span>{{ responseData.lecturer.modules?.join(", ") }}</span></p>
                <p class="mx-5">SDT: <span>{{ responseData.lecturer.phone }}</span></p>
                <p class="mx-5">Nơi làm việc: <span>{{ responseData.lecturer.workRoom }}</span></p>
              </div>
            </div>

            <div class="m-5">
              <el-table :data="responseData.classes" style="width: 100%" height="450">
                <el-table-column fixed prop="stt" label="STT" width="50">
                </el-table-column>
                <el-table-column prop="moduleCode" label="Mã học phần" width="120">
                </el-table-column>
                <el-table-column prop="moduleName" label="Tên học phần" width="150">
                </el-table-column>
                <el-table-column prop="classCode" label="Mã lớp" width="80">
                </el-table-column>
                <el-table-column prop="examCode" label="Mã lớp thi" width="100">
                </el-table-column>
                <el-table-column prop="date" label="Ngày thi" width="120">
                </el-table-column>
                <el-table-column prop="examShift" label="Ca thi" width="120">
                </el-table-column>
                <el-table-column prop="cost" label="Chi phí coi thi" width="120">
                </el-table-column>
                <el-table-column prop="note" label="Ghi chú" width="120">
                </el-table-column>
                <el-table-column prop="statusString" label="Trạng thái" width="120">
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
import SlideBar from '../../components/SlideBar'

export default {
  name: 'IndexPage',
  auth: false,
  components: {
    SlideBar,
  },
  data() {
    return {
      responseData: {
        lecturer: {},
        classes: []
      },

      options: [],
      getExaminers: [],
      getTime: 20222,
      list: [],
      loading: false,
      states: [],
      data: [],
    }
  },
  watch: {
    getExaminers(value) {
      this.getStatisticLecture(value, this.getTime)
    },
    getTime(value) {
      this.getStatisticLecture(this.getExaminers, value)
    }
  },
  created() {
    this.getExaminersList()
  },
  methods: {
    remoteMethod(query) {
      if (query !== '') {
        this.loading = true
        setTimeout(() => {
          this.loading = false
          this.options = this.list.filter((item) => {
            return item.label.toLowerCase().includes(query.toLowerCase())
          })
        }, 200)
      } else {
        this.options = []
      }
    },
    async getExaminersList() {
      await this.$axios
        .get('/admin/statistic/examiners')
        .then((response) => {
          this.data = response.data
          this.list = this.data.map((item) => {
            return { value: item.id, label: `${item.name} - ${item.email}` }
          })
        })
        .catch((e) => {
          console.log(e)
        })
    },
    async getStatisticLecture(lecture, semester) {
      await this.$axios.get(`/admin/statistic/examiners/${lecture}/${semester}`).then((response) => {
        this.responseData = response.data

        for (let index = 0; index < this.responseData.classes.length; index++) {
          const element = this.responseData.classes[index];
          element.stt = index + 1
          this.status(element)
        }
      }).catch((error) => {
        console.log(error)
      })
    },
    status(data) {
      switch (data.status) {
        case 1:
          if (Date.now() < Date.parse(data.classExam.date)) {
            data.statusString = 'Đã đăng ký thi'
          } else {
            data.statusString = 'Đã thi'
          }
          break
        case 2:
          data.statusString = 'Đã thanh toán'
          break
        case 3:
          data.statusString = 'Khóa'
          break
        default:
          data.statusString = 'Mới'
          break
      }
    },
  },
}
</script>

<style scoped></style>
