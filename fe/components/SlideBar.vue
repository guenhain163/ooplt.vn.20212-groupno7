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
                  <li><a href="/exam-classes">Thông tin phân công trông thi </a></li>
                  <li><a href="/cost">Quản lý kinh phí </a></li>
                  <li><a href="/report">Báo cáo thống kê </a></li>
                  <li><a href="/contact">Liên hệ </a></li>
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
export default {
  name: 'SlideBar',
  auth: false,
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
      color: #061325!important;
    padding-left: 20px;
    -webkit-transition: all .3s;
    -o-transition: all .3s;
    transition: all .3s;
    background-color: #eee;
}

.nav {
  display: block;
}

.sidebar-left {
  background-color: white;
}
</style>
