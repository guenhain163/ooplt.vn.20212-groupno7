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
            </div>

            <el-row class="mt-3 mx-3">
              <el-button type="primary" @click="createTeacher()"
                >Create Giang Vien</el-button
              >
            </el-row>

            <div class="my-5" style="margin-left: 3rem">
              <el-table
                v-loading="isLoading"
                :data="tableDataSearch"
                style="width: 100%"
                height="450"
              >
                <el-table-column prop="stt" label="STT" width="50">
                </el-table-column>
                <el-table-column prop="codeModule" label="Mã học phần" width="120">
                </el-table-column>
                <el-table-column prop="nameModule" label="Tên học phần" width="120">
                </el-table-column>
                <el-table-column prop="classExam.note" label="Ghi chú" width="80">
                </el-table-column>
                <el-table-column prop="classExam.examGroup" label="Nhóm" width="100">
                </el-table-column>
                <el-table-column prop="classExam.openingPeriod" label="Đợt mở" width="80">
                </el-table-column>
                <el-table-column prop="classExam.week" label="Tuần" width="100">
                </el-table-column>
                <el-table-column prop="classExam.date" label="Thứ" width="100">
                </el-table-column>
                <el-table-column prop="classExam.date" label="Ngày thi" width="100">
                </el-table-column>
                <el-table-column prop="classExam.examShift" label="Kíp" width="80">
                </el-table-column>
                <el-table-column prop="numberStudent" label="SLDK" width="100">
                </el-table-column>
                <el-table-column prop="classExam.room" label="Phòng thi" width="100">
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
                      <el-link
                        style="margin-top: 10px"
                        @click="examPass(scope.$index, scope.row)"
                      >
                        <strong>Phân công trông thi</strong>
                      </el-link>
                      <br />

                      <el-link
                        style="margin-top: 10px"
                        @click="deleteData(scope.$index, scope.row)"
                      >
                        <strong>Delete</strong>
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
              <CreateDiaglogExamClasses ref="CreateDiaglogExamClasses" />
              <ExamRegister ref="examRegister" />
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
import CreateDiaglogExamClasses from '../components/examClasses/CreateDiaglogExamClasses'
import ExamRegister from '../components/examClasses/ExamRegister'

export default {
  name: 'IndexPage',
  components: {
    SlideBar,
    DiaglogExamClasses,
    CreateDiaglogExamClasses,
    ExamRegister
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
      console.log(item)
    },
    editData(index, val) {
      this.$refs[`popover${index}`].doClose()
      this.$refs.offerDetailDialog.dialogVisible = true
      this.$refs.offerDetailDialog.val = val
    },
    async deleteData(index, val) {
      this.$refs[`popover${index}`].doClose()
      await this.$axios
        .delete(`/admin/examClasses/${val.classExam.id}`)
        .then((response) => {
          this.notifycation()
          this.$router.go({
            path: '/exam-classes',
          })
        })
        .catch((e) => {
          this.errorNotification()
        })
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
          })
          this.tableData = raw
          this.tableDataSearch = this.tableData
          this.isLoading = false
          console.log(this.tableData)
        })
        .catch((erorr) => {
          console.log(erorr)
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
    examPass(index, val) {
      this.$refs[`popover${index}`].doClose()
      this.$refs.examRegister.dialogVisible = true
      this.$refs.examRegister.value = val
      this.$refs.examRegister.getlecturesExam()
      this.$refs.examRegister.setCondition(val.numberStudent)
    },
  },
}
</script>

<style scoped></style>
