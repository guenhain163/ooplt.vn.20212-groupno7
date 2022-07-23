<template>
  <div class="page-header-fixed page-quick-sidebar-over-content">
    <SlideBar />
    <div class="page-container">
      <div class="page-content-wrapper setting">
        <div class="page-content">
          <div class="container">
            <!-- <div>
              <el-row class="demo-autocomplete">
                <el-col :span="12">
                  <div class="sub-title mb-2 mx-3">Tìm giáo viên</div>
                  <el-autocomplete
                    v-model="state1"
                    class="inline-input mx-3"
                    :fetch-suggestions="querySearch"
                    placeholder="Please Input"
                    @select="handleSelect"
                  ></el-autocomplete>
                </el-col>
              </el-row>
            </div> -->



            <div class="my-5" style="margin-left: 3rem">
              <el-table
                v-loading="isLoading"
                :data="tableDataSearch"
                style="width: 100%"
                height="450"
              >
                <el-table-column prop="stt" label="STT" width="50">
                </el-table-column>
                <el-table-column
                  prop="classExam.examCode"
                  label="Mã lớp thi"
                  width="100"
                >
                </el-table-column>
                <el-table-column
                  prop="nameLecturer"
                  label="Giảng viên"
                  width="300"
                >
                </el-table-column>
                <el-table-column
                  prop="classExam.date"
                  label="Ngày thi"
                  width="100"
                >
                </el-table-column>
                <el-table-column
                  prop="nameModule"
                  label="Tên học phần"
                  width="120"
                >
                </el-table-column>
                <el-table-column prop="numberStudent" label="SLDK" width="100">
                </el-table-column>
                <el-table-column
                  prop="classExam.examClassDetailsById.cost"
                  label="Đơn giá"
                  width="80"
                >
                </el-table-column>
                <el-table-column
                  prop="cost_to_chuc"
                  label="Chi phí tổ chức"
                  width="150"
                >
                </el-table-column>
                <el-table-column
                  prop="classExam.examClassDetailsById.printingCost"
                  label="Chi phí in ấn"
                  width="150"
                >
                </el-table-column>
                <el-table-column
                  prop="classExam.examClassDetailsById.examinationCost"
                  label="Chi phí trông thi"
                  width="150"
                >
                </el-table-column>
                <el-table-column prop="totalCost" label="Tổng" width="100">
                </el-table-column>
                <el-table-column
                  prop="statusString"
                  label="Trạng thái"
                  width="150"
                >
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
                      <el-link @click="editData(scope.$index, scope.row)">
                        <strong>Edit </strong>
                      </el-link>
                      <br />

                      <el-link @click="setCost(scope.$index, scope.row)">
                        <strong>Chi phí </strong>
                      </el-link>
                      <br />

                      <el-link
                        style="margin-top: 10px"
                        @click="pay(scope.$index, scope.row)"
                      >
                        <strong>Thanh toán</strong>
                      </el-link>
                      <br />
                      <el-button slot="reference" size="mini">
                        Action &#x25BC;
                      </el-button>
                    </el-popover>
                  </template>
                </el-table-column>
              </el-table>

              <DiaglogExamClasses ref="offerDetailDialog" />
              <ExamRegister ref="examRegister" />
              <OfferSetUp ref="offerSetUp" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SlideBar from '../components/SlideBar'
import DiaglogExamClasses from '../components/examClasses/DiaglogExamClasses'
import ExamRegister from '../components/examClasses/ExamRegister'
import OfferSetUp from '../components/examClasses/OfferSetUp'

export default {
  name: 'IndexPage',
  components: {
    SlideBar,
    DiaglogExamClasses,
    ExamRegister,
    OfferSetUp
  },
  data() {
    return {
      tableData: [],
      tableDataSearch: [],
      links: [],
      state1: '',
      state2: '',
      isLoading: false,
    }
  },
  watch: {
    state1(value) {
      if (value === '') {
        this.tableDataSearch = this.tableData
      } else {
        this.tableDataSearch = this.tableData.filter(
          (tableValue) => tableValue.value === value
        )
      }
    },
  },
  mounted() {},
  created() {
    this.isLoading = true
    this.getData()
  },
  methods: {
    querySearch(queryString, cb) {
      const links = this.links
      const results = queryString
        ? links.filter(this.createFilter(queryString))
        : links
      // call callback function to return suggestions
      cb(results)
    },
    createFilter(queryString) {
      return (link) => {
        return link.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
      }
    },
    loadAll() {
      return this.tableDataSearch
    },
    handleSelect(item) {
    },
    editData(index, val) {
      this.$refs[`popover${index}`].doClose()
      this.$refs.offerDetailDialog.dialogVisible = true
      this.$refs.offerDetailDialog.val = val
    },
    setCost(index, val) {
      this.$refs[`popover${index}`].doClose()
      this.$refs.offerSetUp.dialogVisible = true
      this.$refs.offerSetUp.val = val
      // this.$refs[`popover${index}`].doClose()
      this.$refs.offerSetUp.getLectureData()
    },
    createTeacher() {
      this.$refs.CreateDiaglogExamClasses.dialogVisible = true
    },
    async getData() {
      this.isLoading = true
      await this.$axios
        .get('/admin/examClasses')
        .then((response) => {
          const raw = response.data
          for (let index = 0; index < raw.length; index++) {
            const element = raw[index]
            element.stt = index + 1
          }
          raw.forEach((data) => {
            data.value = data.nameModule

            data.cost_to_chuc =
              data.numberStudent * data.classExam.examClassDetailsById.cost
            data.totalCost =
              data.classExam.examClassDetailsById.printingCost +
              data.cost_to_chuc +
              data.classExam.examClassDetailsById.examinationCost

            this.status(data)
          })

          this.tableData = raw
          this.tableDataSearch = this.tableData

          this.isLoading = false
        })
        .catch(() => {
        })
      this.links = this.tableDataSearch
    },
    notifycation() {
      this.$notify.success({
        title: 'Success',
        message: 'Delete success',
        showClose: false,
      })
    },
    errorNotification() {
      this.$notify.error({
        title: 'Error',
        message: 'Can not delete',
      })
    },
    status(data) {
      switch (data.classExam.status) {
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
    pay(index, val) {
      this.$confirm(
        'Bạn có chắc chắn muốn thanh toán không. Tiếp tục?',
        'Warning',
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning',
        }
      )
        .then(() => {
          this.callPayAPI(val)
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: 'Bạn đã hủy thao tác',
          })
        })
    },
    callPayAPI(val) {
      this.$axios
        .post(`/admin/examClasses/${val.classExam.id}/status/paid`)
        .then((response) => {
          this.$message({
            type: 'success',
            message: 'Thanh toán thành công',
          })
          this.$router.go({
            path: '/cost',
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: 'Thanh toán thất bại',
          })
        })
    },
  },
}
</script>

<style scoped></style>
