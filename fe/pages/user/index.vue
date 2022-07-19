<template>
  <div class="page-header-fixed page-quick-sidebar-over-content">
    <SlideBarUser @getUserCode="getUserCode"/>
    <div class="page-container">
      <div class="page-content-wrapper setting">
        <div class="page-content">
          <div class="container">

            <div class="border m-5 w-50">
              <h3 class="text-center mt-3">Thông tin cá nhân: </h3>
              <!-- <div class="text-end" style="margin: 0px 10px 0px 0px">
                <el-button type="primary" @click="edit">Edit</el-button>
              </div> -->
              <div class="py-2 px-3">
                  <div class="row mx-5 my-4 align-items-center;">
                    <div class="col-4">Tên:</div>
                    <div class="col-8"><el-input  v-model="responseData.name" placeholder="Please input" :disabled="inputApear"></el-input></div>
                  </div>


                <div class="row mx-5 my-4">
                    <div class="col-4">Email:</div>
                    <div class="col-8"><el-input  v-model="responseData.email" placeholder="Please input" disabled=true></el-input></div>
                </div>

                <div class="row mx-5 my-4">
                    <div class="col-4">Bộ môn:</div>
                    <div class="col-8"><el-input v-model="responseData.modules" placeholder="Please input" :disabled="inputApear"></el-input></div>
                </div>


                <div class="row mx-5 my-4">
                    <div class="col-4">SDT:</div>
                    <div class="col-8"><el-input v-model="responseData.phone" placeholder="Please input" :disabled="inputApear"></el-input></div>
                </div>

                <div class="row mx-5 my-4">
                    <div class="col-4">Nơi làm việc:</div>
                    <div class="col-8"><el-input  v-model="responseData.workRoom" placeholder="Please input" :disabled="inputApear"></el-input></div>
                </div>


              </div>

              <!-- <div class="text-end" style="margin: 0px 10px 0px 0px">
                <el-button v-show="!inputApear" type="primary" @click="saveData">Save</el-button>
              </div> -->
            </div>


          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SlideBarUser from '../../components/SlideBarUser'

export default {
  name: 'IndexPage',
  components: {
    SlideBarUser,
  },
  data() {
    return {
      responseData: {
        users: {}
      },

      list: [],
      data: [],
      inputApear: true
    }
  },

  created() {
  },
  methods: {
    getUserCode(value) {
      this.getUserInfo(value)
    },
    async getUserInfo(value) {
      await this.$axios.get(`/user/${value}`).then((response) => {
        this.responseData = response.data
        console.log(this.responseData)
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
    edit() {
      this.inputApear = false
    },
    saveData() {
      console.log('hello')
    }
  },
}
</script>

<style scoped></style>
