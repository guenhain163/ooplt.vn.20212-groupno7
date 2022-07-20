<template>
  <div class="custom-dialog">
    <el-dialog
      title="Chỉnh sửa thông tin giáo viên"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form ref="val" :model="val" label-width="150px">
        <el-form-item label="Mã lớp thi">
          <el-input v-model="val.classExam.examCode" class="w-50" disabled></el-input>
        </el-form-item>
        <el-form-item label="Mã học phần">
          <el-input v-model="val.codeModule" class="w-50" disabled></el-input>
        </el-form-item>
        <el-form-item label="Tên học phần">
          <el-input v-model="val.nameModule" class="w-50" disabled></el-input>
        </el-form-item>
        <el-form-item label="SLDK">
          <el-input-number v-model="val.numberStudent" disabled></el-input-number>
        </el-form-item>
        <el-form-item label="Chi phí 1 sinh viên">
          <el-input-number v-model="val.classExam.examClassDetailsById.cost"></el-input-number>
        </el-form-item>
        <el-form-item label="Chi phí in ấn">
          <el-input-number v-model="val.classExam.examClassDetailsById.printingCost"></el-input-number>
        </el-form-item>

        <el-form-item v-for="item in lectureData" :key="item.id" label="Cán bộ coi thi">
          <el-input v-model="item.name" class="mb-3 w-50" disabled></el-input>
          <el-input-number v-model="item.cost" :value="findCostByLecturerId(item.id)"></el-input-number>
        </el-form-item>



        <el-form-item>
          <el-button type="primary" @click="onSubmit">Lưu</el-button>
          <el-button @click="handleClose">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
      val: {
        classExam: {
          examClassDetailsById: {}
        },
      },
      data: {},
      lectureData: [],
    }
  },
  methods: {
    async onSubmit() {
      this.lectureData.forEach((element) => {
        element.lecturer_id = element.id
        delete element.id
      })
      this.data = {
        cost: this.val.classExam.examClassDetailsById.cost,
        printing_cost: this.val.classExam.examClassDetailsById.printingCost,
        examination_cost: this.lectureData
      }
      await this.$axios
        .post(`/admin/examClasses/${this.val.classExam.id}/cost`, this.data)
        .then((response) => {
          this.notifycation()
          this.$router.go({
            path: '/exam-classes',
          })
        })
        .catch(() => {
          this.errorNotification()
        })
    },
    handleClose(done) {
      this.$confirm('Bạn có chắc chắn không?')
        .then((_) => {
          done()
        })
        .catch((_) => {})
    },
    notifycation() {
      this.$notify.success({
        title: 'Success',
        message: 'Edit success',
        showClose: false,
      })
    },
    errorNotification() {
      this.$notify.error({
        title: 'Error',
        message: 'Can not edit',
      })
    },
    async getLectureData() {
      await this.$axios
        .get(`/admin/examiners?examClassId=${this.val.classExam.id}`)
        .then((response) => {
          this.lectureData = response.data
        })
        .catch(() => {
        })
      this.links = this.tableDataSearch
    },
    findCostByLecturerId(lecturerId) {
      // eslint-disable-next-line array-callback-return
      // this.val.classExam.examClassExaminerDetailsById.find((o, i) => {
      //   if (o.lecturerId === lecturerId) {

      //     return o.cost;
      //   }
      // })
    }
  },
}
</script>

<style>
.el-input-number span {
  display: none !important;
}

.el-input-number input {
  padding: 0px !important;
  padding-left: 15px !important;
  text-align: justify !important;
}
</style>
