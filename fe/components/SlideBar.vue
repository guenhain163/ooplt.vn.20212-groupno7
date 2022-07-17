<template>
  <div class="sidebar-left">
    <div class="left_col">
      <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0"></div>
        <div class="clearfix"></div>
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
          <div class="menu_section">
            <ul class="nav side-menu">
              <p>Main Menu</p>
              <li>
                <ul>
                  <li><a href="/">Quản lý giảng viên </a></li>
                  <li><a href="/examinator">Quản lý Cán bộ coi thi </a></li>
                  <li>
                    <a href="/exam-classes">Thông tin phân công trông thi </a>
                  </li>
                  <li><a href="/cost">Quản lý kinh phí </a></li>
                  <li>
                    <p>Báo cáo</p>
                    <ul>
                      <a
                        href="/report/lectures"
                        style="margin-left: 3rem; font-size: 0.75rem !important"
                        >Báo cáo giảng viên
                      </a>
                      <a
                        href="/report/examinator"
                        style="margin-left: 3rem; font-size: 0.75rem !important"
                        >Báo cáo cán bộ coi thi
                      </a>
                      <a
                        href="/report/class"
                        style="margin-left: 3rem; font-size: 0.75rem !important"
                        >Báo cáo lớp học
                      </a>
                    </ul>
                  </li>
                  <li><a href="/contact">Liên hệ </a></li>
                  <li>
                    <a href="/#" @click="logout">Thoát phiên làm việc </a>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import nameImport from '../constant/nameImport'

export default {
  name: 'SlideBar',
  auth: false,
  data() {
    return {
      nameImport: []
    }
  },
  created() {
    this.nameImport = nameImport
  },
  methods: {
    async logout() {
      await this.$auth.logout().then(() => {
        this.$router.push('/login')
      })
    },

     onChange(event) {
      this.file = event.target.files ? event.target.files[0] : null;
      const XLSX = require('xlsx');
      if (this.file) {
        const reader = new FileReader();

        reader.readAsBinaryString(this.file);

        reader.onload = (e) => {
          /* Parse data */
          const wb = XLSX.read(e.target.result, { type: 'binary' });
          /* Get first worksheet */
          const wsname = wb.SheetNames[0];
          const ws = wb.Sheets[wsname];
          /* Convert array of arrays */
          const data = XLSX.utils.sheet_to_json(ws, { header: 1 });
          this.convertData(data)
        }
      }
    },
    convertData(data) {
      const temp = []
      data.splice(0, 3)
      data.forEach(element => {
      const classObject = Object.assign({}, element)

        for (let index = 0; index < this.nameImport.length; index++) {
          const nameChanged = this.nameImport[index];

          delete Object.assign(classObject, {[nameChanged]: classObject[index] })[index]
          delete classObject.temp
        }

        temp.push(classObject)
      });
      console.log(temp)
    }

  },
}
</script>

<style scoped>
@import '../style/css/admin.css';
@import '../style/css/style.css';
a {
  font-weight: 500;
  font-size: 14px;
  line-height: 21px;
  height: 42px;
  line-height: 18px;
  color: #061325;
  -webkit-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
  padding: 0;
  margin-bottom: 0;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  text-decoration: none;
  padding-right: 10px;
}

a:hover {
  color: #061325 !important;
  padding-left: 20px;
  -webkit-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
  background-color: #eee;
}

.nav {
  display: block;
}

.sidebar-left {
  background-color: white;
}
</style>
