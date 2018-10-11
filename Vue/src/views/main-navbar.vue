<template>
  <nav class="site-navbar" :class="'site-navbar--' + navbarLayoutType">
    <!--<div class="site-navbar__header">-->
      <!--<h1 class="site-navbar__brand" @click="$router.push({ name: 'home' })">-->
        <!--<a class="site-navbar__brand-lg" href="javascript:;">青岛市新能源汽车补贴审批系统</a>-->
        <!--<a class="site-navbar__brand-mini" href="javascript:;">新能源</a>-->
      <!--</h1>-->
    <!--</div>-->
    <div class="site-navbar__body clearfix">
      <el-menu
        class="site-navbar__menu"
        mode="horizontal">
        <el-menu-item class="site-navbar__switch" index="0" @click="sidebarFold = !sidebarFold">
          <icon-svg name="zhedie"></icon-svg>
        </el-menu-item>
      </el-menu>
      <el-menu
        class="site-navbar__menu"
        mode="horizontal">
        <el-menu-item class="site-navbar__switch" index="0" style="color:#19678E;font-weight:bold;font-size:20px;">
            青岛市新能源汽车补贴审批系统
        </el-menu-item>
      </el-menu>
      <el-menu
        class="site-navbar__menu site-navbar__menu--right"
        mode="horizontal">

        <el-menu-item class="site-navbar__avatar" index="3">
          <el-dropdown :show-timeout="0" placement="bottom">
            <span class="el-dropdown-link">
              <img src="~@/assets/img/avatar.png" :alt="userName">{{ userName }}({{orgName}})
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="updatePasswordHandle()">修改密码</el-dropdown-item>
              <!--<el-dropdown-item @click.native="logoutHandle()">退出</el-dropdown-item>-->
            </el-dropdown-menu>
          </el-dropdown>
        </el-menu-item>
        <!--<el-menu-item index="1" v-if="orgName!=null">-->
          <!--<template>{{orgName}}</template>-->
        <!--</el-menu-item>-->
        <el-menu-item index="2" @click.native="logoutHandle()">
            <a style="color:#909399;">退出系统<i style="color:#909399;" class="el-icon-arrow-right"></i></a>
        </el-menu-item>
        <!--<el-submenu index="3">-->
          <!--<template slot="title">Git源码</template>-->
          <!--<el-menu-item index="2-1"><a href="//github.com/daxiongYang/renren-fast-vue" target="_blank">前端</a></el-menu-item>-->
          <!--<el-menu-item index="2-2"><a href="//git.oschina.net/renrenio/renren-fast" target="_blank">后台</a></el-menu-item>-->
          <!--<el-menu-item index="2-3"><a href="//git.oschina.net/renrenio/renren-generator" target="_blank">代码生成器</a></el-menu-item>-->
        <!--</el-submenu>-->
      </el-menu>
    </div>
    <!-- 弹窗, 修改密码 -->
    <update-password v-if="updatePassowrdVisible" ref="updatePassowrd"></update-password>
  </nav>
</template>
<style>
  .el-menu-item i{
    color:white;
  }
  .index-title{
    float: left;
    margin-top: 12px;
    max-width: 150px;
    overflow: hidden;
    div{
      border: none;
      color:#19678e;
      font-size: 15px;
      font-weight: bold;
    }
  }
  .index-title svg{
    font-size: 20px;
    vertical-align: middle;
    margin-left: 4px;
    cursor: pointer;
    color: #999;
  }
</style>
<script>
  import UpdatePassword from './main-navbar-update-password'
  import { clearLoginInfo } from '@/utils'
  export default {
    data () {
      return {
        updatePassowrdVisible: false
      }
    },
    components: {
      UpdatePassword
    },
    computed: {
      navbarLayoutType: {
        get () { return this.$store.state.common.navbarLayoutType }
      },
      sidebarFold: {
        get () { return this.$store.state.common.sidebarFold },
        set (val) { this.$store.commit('common/updateSidebarFold', val) }
      },
      mainTabs: {
        get () { return this.$store.state.common.mainTabs },
        set (val) { this.$store.commit('common/updateMainTabs', val) }
      },
      userName: {
        get () { return this.$store.state.user.name }
      },
      orgName: {
        get () { return this.$store.state.user.orgName }
      }
    },
    methods: {
      // 修改密码
      updatePasswordHandle () {
        this.updatePassowrdVisible = true
        this.$nextTick(() => {
          this.$refs.updatePassowrd.init()
        })
      },
      // 退出
      logoutHandle () {
        this.$confirm(`确定进行[退出]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/sys/logout'),
            method: 'post',
            data: this.$http.adornData()
          }).then(({data}) => {
            if (data && data.code === 0) {
              clearLoginInfo()
              this.$router.push({ name: 'login' })
            }
          })
        }).catch(() => {})
      }
    }
  }
</script>
