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
                  <div class="sub-title mb-2 mx-3">Nhập kỳ học</div>

                  <el-input
                    v-model="getTime"
                    placeholder="Please input semester"
                    class="mx-5 w-25"
                  ></el-input>
                </el-col>
              </el-row>
            </div>

            <div class="m-5">
              <el-table :data="formData" style="width: 100%" height="450">
                <el-table-column fixed prop="index" label="STT" width="50">
                </el-table-column>
                <el-table-column prop="code" label="Mã lớp" width="120">
                </el-table-column>
                <el-table-column prop="codeModule" label="Mã học phần" width="120">
                </el-table-column>
                <el-table-column prop="nameModule" label="Tên học phần" width="100">
                </el-table-column>
                <el-table-column prop="examClass.note" label="Ghi chú" width="120">
                </el-table-column>
                <el-table-column prop="examClass.examGroup" label="Nhóm" width="120">
                </el-table-column>
                <el-table-column prop="examClass.openingPeriod" label="Đợt mở" width="120">
                </el-table-column>
                <el-table-column prop="examClass.week" label="Tuần" width="120">
                </el-table-column>
                <el-table-column prop="dayOfTheWeek" label="Thứ" width="120">
                </el-table-column>
                <el-table-column prop="examClass.date" label="Ngày thi" width="120">
                </el-table-column>
                <el-table-column prop="examClass.examShift" label="Kíp thi" width="120">
                </el-table-column>
                <el-table-column prop="examClass.examClassDetailsById.numberStudent" label="SLDK" width="120">
                </el-table-column>
                <el-table-column prop="examClass.room" label="Phòng thi" width="120">
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
      responseData: [],
      options: [],
      getTime: '',
      list: [],
      loading: false,
      states: [],
      data: [],
      formData: []
    }
  },
  watch: {
    getTime(value) {
      this.getStatisticLecture(value)
    }
  },
  created() {
  },
  methods: {
    async getStatisticLecture(semester) {
      await this.$axios.get(`/admin/statistic/classes/${semester}`).then((response) => {
        this.formData = []
        response.data.forEach(e => {
          this.formData.push({...e, "index": this.formData.length + 1, "dayOfTheWeek": this.formatDay(e.examClass.date)});
        })

        // for (let index = 0; index < response.data.length; index++) {
        //   const element = response.data[index];
        //   element.examClassesById.forEach(value => {
        //     // console.log(value)
        //     this.formData.push({...value, "code" : element.code, "index": this.formData.length + 1, "dayOfTheWeek": this.formatDay(value.date)});
        //   });
        // }
        console.log(this.formData);
      }).catch((error) => {
        console.log(error)
      })
    },
    formatDay(date) {
      const weekday = ["Chủ Nhật","Thứ hai","Thứ 3","Thứ 4","Thứ 5","Thứ 6","Thứ 7"];
      const dayValue = new Date(date)
      return weekday[dayValue.getDay()]
    }
  },
}
</script>

<style scoped></style>
