<template>
  <div class="page-header-fixed page-quick-sidebar-over-content">
    <SlideBarUser @getUserCode="getUserCode"/>
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
                :data="responseData"
                style="width: 100%"
                height="450"
              >
                <el-table-column fixed prop="stt" label="STT" width="50">
                </el-table-column>
                <el-table-column prop="codeModule" label="Mã học phần" width="120">
                </el-table-column>
                <el-table-column prop="nameModule" label="Tên học phần" width="150">
                </el-table-column>
                <el-table-column prop="classExam.classId" label="Mã lớp" width="80">
                </el-table-column>
                <el-table-column prop="classExam.week" label="Tuần" width="100">
                </el-table-column>
                <el-table-column prop="classExam.date" label="Thứ" width="100">
                </el-table-column>
                <el-table-column prop="classExam.date" label="Ngày thi" width="120">
                </el-table-column>
                <el-table-column prop="classExam.examShift" label="Kíp" width="80">
                </el-table-column>
                <el-table-column prop="classExam.examCode" label="Mã lớp thi" width="100">
                </el-table-column>

                <el-table-column prop="classExam.openingPeriod" label="Đợt mở" width="80">
                </el-table-column>
                <el-table-column prop="numberStudent" label="SLDK" width="120">
                </el-table-column>
                <el-table-column prop="classExam.room" label="Phòng thi" width="100">
                </el-table-column>
                <el-table-column prop="classExam.examClassDetailsById.cost" label="Đơn giá" width="120">
                </el-table-column>
                <el-table-column prop="hostCost" label="Chi phí tổ chức" width="120">
                </el-table-column>
                <el-table-column prop="classExam.examClassDetailsById.printingCost" label="Chi phí in ấn" width="120">
                </el-table-column>
                <el-table-column prop="classExam.examClassDetailsById.examinationCost" label="Chi phí coi thi" width="120">
                </el-table-column>
                <el-table-column prop="total" label="Tổng" width="120">
                </el-table-column>
                <el-table-column prop="classExam.note" label="Ghi chú" width="120">
                </el-table-column>
                <el-table-column prop="classExam.statusString" label="Trạng thái" width="120">
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
      responseData: [],
      options: [],
      value: 20222,
      getCodeId: '',
      id: '',
    }
  },
  watch: {},
  created() {
    this.getExamList()
    this.options = constant
  },
  methods: {
    async getExamList() {
      await this.$axios
        .get(`/user/${this.id}/examClass?role=examiner&semester=${this.value}`)
        .then((response) => {
                    this.responseData = response.data

          for (let index = 0; index < this.responseData.length; index++) {
            const element = this.responseData[index];
            element.stt = index + 1
                      this.status(element.classExam)

          }
        })
        .catch((e) => {
          console.log(e)
        })
    },

    status(data) {
      console.log(data.status)
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
    getUserCode(value) {
      this.id = value
      this.getExamList(value)
    },
  },
}
</script>

<style scoped></style>
