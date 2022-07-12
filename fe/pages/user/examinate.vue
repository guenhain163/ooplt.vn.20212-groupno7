<template>
  <div class="page-header-fixed page-quick-sidebar-over-content">
    <SlideBarUser />
    <div class="page-container">
      <div class="page-content-wrapper setting">
        <div class="page-content">
          <div class="container">
            <div>
              <el-row class="demo-autocomplete">
                <el-col :span="12">
                  <div class="sub-title mb-2 mx-3">Tìm giảng viên</div>

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
                    v-model="getCodeId"
                    placeholder="Please input semester"
                    class="mx-5 w-25"
                  ></el-input>
                </el-col>
              </el-row>
            </div>

            <div class="m-5">
              <el-table
                :data="responseData.classes"
                style="width: 100%"
                height="450"
              >
                <el-table-column prop="stt" label="STT" width="50">
                </el-table-column>
                <el-table-column prop="codeModule" label="Mã học phần" width="120">
                </el-table-column>
                <el-table-column prop="nameModule" label="Tên học phần" width="120">
                </el-table-column>
                <el-table-column prop="note" label="Ghi chú" width="80">
                </el-table-column>
                <el-table-column prop="examGroup" label="Nhóm" width="100">
                </el-table-column>
                <el-table-column prop="openingPeriod" label="Đợt mở" width="80">
                </el-table-column>
                <el-table-column prop="week" label="Tuần" width="100">
                </el-table-column>
                <el-table-column prop="date" label="Thứ" width="100">
                </el-table-column>
                <el-table-column prop="date" label="Ngày thi" width="100">
                </el-table-column>
                <el-table-column prop="examShift" label="Kíp" width="80">
                </el-table-column>
                <el-table-column prop="numberStudent" label="SLDK" width="100">
                </el-table-column>
                <el-table-column prop="room" label="Phòng thi" width="100">
                </el-table-column>
                <el-table-column prop="room" label="Trạng thái" width="100">
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
  auth: false,
  components: {
    SlideBarUser,
  },
  data() {
    return {
      responseData: {
        lecturer: {},
        classes: [],
      },

      options: [],
      value: '',
      getCodeId: '',
    }
  },
  watch: {},
  created() {
    this.getLectureList()
    this.options = constant
  },
  methods: {
    async getLectureList() {
      await this.$axios
        .get('/admin/statistic/lecturers')
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
    registeExam(index, value) {
      console.log(index, value)
    },
  },
}
</script>

<style scoped></style>
