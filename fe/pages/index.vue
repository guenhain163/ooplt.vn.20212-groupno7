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

            <div class="m-5">
              <el-table
                v-loading="isLoading"
                :data="tableDataSearch"
                style="width: 100%"
                height="450"
              >
                <el-table-column prop="stt" label="STT" width="50">
                </el-table-column>
                <el-table-column prop="name" label="Name" width="270">
                </el-table-column>
                <el-table-column prop="moduleGroup" label="Bộ môn" width="270">
                </el-table-column>
                <el-table-column prop="phone" label="Điện thoại" width="120">
                </el-table-column>
                <el-table-column prop="email" label="Email" width="200">
                </el-table-column>
                <el-table-column
                  prop="workRoom"
                  label="Phòng làm việc"
                  width="200"
                >
                </el-table-column>

                <el-table-column
                  min-width="120"
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

              <DiaglogTeacher ref="offerDetailDialog" />
              <CreateDiaglogTeacher ref="createDiaglogTeacher" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SlideBar from '../components/SlideBar'
import DiaglogTeacher from '../components/teacher/DiaglogTeacher'
import CreateDiaglogTeacher from '../components/teacher/CreateDiaglogTeacher'

export default {
  name: 'IndexPage',
  components: {
    SlideBar,
    DiaglogTeacher,
    CreateDiaglogTeacher,
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
      this.$refs.offerDetailDialog.id = index
      this.$refs.offerDetailDialog.val = val

      this.$refs.offerDetailDialog.data.name = val.name
      this.$refs.offerDetailDialog.data.phone = val.phone
      this.$refs.offerDetailDialog.data.workRoom = val.workRoom
    },
    async deleteData(index, val) {
      this.$refs[`popover${index}`].doClose()
      await this.$axios
        .delete(`/admin/lecturers/${val.id}`)
        .then((response) => {
          this.notifycation()
        })
        .catch((e) => {
          this.errorNotification()
        })
    },
    createTeacher() {
      this.$refs.createDiaglogTeacher.dialogVisible = true
    },
    async getData() {
      this.isLoading = true
      await this.$axios
        .get('/admin/lecturers')
        .then((response) => {
          const raw = response.data
          for (let index = 0; index < raw.length; index++) {
            const element = raw[index]
            element.stt = index + 1
            element.moduleGroup = element.modules?.join(", ")
          }
          raw.forEach((data) => {
            data.value = data.name
          })
          this.tableData = raw
          this.tableDataSearch = this.tableData
          this.isLoading = false
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
  },
}
</script>

<style scoped></style>
