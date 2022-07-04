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
                  <div class="sub-title mb-2">Tìm giáo viên</div>
                  <el-autocomplete
                    v-model="state1"
                    class="inline-input"
                    :fetch-suggestions="querySearch"
                    placeholder="Please Input"
                    @select="handleSelect"
                  ></el-autocomplete>
                </el-col>
              </el-row>
            </div>

            <el-row class="mt-3">
              <el-button type="primary" @click="createTeacher()">Create Giang Vien</el-button>
            </el-row>

            <div class="m-5">
              <el-table :data="tableDataSearch" style="width: 100%" height="450">
                <el-table-column fixed prop="date" label="Date" width="150">
                </el-table-column>
                <el-table-column prop="name" label="Name" width="120">
                </el-table-column>
                <el-table-column prop="state" label="State" width="120">
                </el-table-column>
                <el-table-column prop="city" label="City" width="120">
                </el-table-column>
                <el-table-column prop="address" label="Address" width="300">
                </el-table-column>
                <el-table-column prop="zip" label="Zip" width="120">
                </el-table-column>

                <el-table-column
                  min-width="120"
                  class-name="text-secondary"
                  label="Action"
                >
                <template slot-scope="scope">
                  <el-popover :ref="'popover'+scope.$index" placement="bottom-end" trigger="click">
                    <el-link @click="editData(scope.$index, scope.row)">
                      <strong>Edit </strong>
                    </el-link>
                    <br />
                    <el-link style="margin-top: 10px" @click="deleteData(scope.$index, scope.row)">
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

              <DiaglogTeacher
                ref="offerDetailDialog"/>
              <CreateDiaglogTeacher
                ref="createDiaglogTeacher"/>

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
  auth: false,
  components: {
    SlideBar,
    DiaglogTeacher,
    CreateDiaglogTeacher,
  },
  data() {
    return {
      tableData: [
        {
          date: '2016-05-03',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value: 'Tom',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value: 'Tom2',
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value: 'Tom3',
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value: 'Tom4',
        },
        {
          date: '2016-05-08',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value: 'Tom5',
        },
        {
          date: '2016-05-06',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value: 'Tom6',
        },
        {
          date: '2016-05-07',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value: 'Tom7',
        },
      ],
      tableDataSearch: [],
      links: [],
      state1: '',
      state2: '',
    }
  },
  watch: {
    state1 (value) {
      if (value === '') {
        this.tableDataSearch = this.tableData
      } else {
        this.tableDataSearch = this.tableData.filter(tableValue => tableValue.value === value)
      }
    }
  },
  mounted() {
    this.links = this.loadAll()
  },
  created() {
    this.tableDataSearch = this.tableData
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
      return this.tableData
    },
    handleSelect(item) {
      this.tableDataSearch = item
    },
    editData(index, val) {
      this.$refs[`popover${index}`].doClose()
      console.log(this.$refs.offerDetailDialog)
      this.$refs.offerDetailDialog.dialogVisible = true
    },
    deleteData(index, val) {
      this.$refs[`popover${index}`].doClose()
      this.tableDataSearch.splice(index, 1)
    },
    createTeacher() {
      this.$refs.createDiaglogTeacher.dialogVisible = true
    },
  },
}
</script>

<style scoped></style>
