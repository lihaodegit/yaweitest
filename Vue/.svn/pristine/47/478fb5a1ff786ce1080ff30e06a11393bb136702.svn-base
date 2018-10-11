<template>
  <div class="site-wrapper site-page--login">
    <div class="site-content__wrapper">
      <div class="site-content">
        <div class="brand-info">
          <img src="~@/assets/img/logo.jpg">
        </div>
        <div class="footer">©2018 All rights reserved 青岛华通军工投资有限责任公司</div>
        <div class="login-main">
          <el-card class="is-always-shadow card-box">
            <el-tabs v-model="activeName" class="tabs-box">
              <el-tab-pane label="登陆" name="first" style="width:100%;">
                <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
                  <el-form-item prop="userName">
                    <el-input v-model="dataForm.userName" placeholder="请输入账号"></el-input>
                  </el-form-item>
                  <el-form-item prop="password">
                    <el-input v-model="dataForm.password" type="password" placeholder="请输入密码"></el-input>
                  </el-form-item>
                  <el-form-item prop="captcha">
                    <el-row :gutter="20">
                      <el-col :span="14">
                        <el-input v-model="dataForm.captcha" placeholder="验证码">
                        </el-input>
                      </el-col>
                      <el-col :span="10" class="login-captcha">
                        <img :src="captchaPath" @click="getCaptcha()" alt="">
                      </el-col>
                    </el-row>
                  </el-form-item>
                  <el-form-item>
                    <el-button class="login-btn-submit" type="primary" @click="dataFormSubmit()">登录</el-button>
                  </el-form-item>
                </el-form>
              </el-tab-pane>
              <!--<el-tab-pane label="微信登陆" name="second">微信登陆二维码-->
                <!--<div id="qrcode"></div>-->
              <!--</el-tab-pane>-->
            </el-tabs>
          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { getUUID } from '@/utils'
  export default {
    data () {
      return {
        activeName: 'first',
        dataForm: {
          userName: '',
          password: '',
          uuid: '',
          captcha: ''
        },
        dataRule: {
          userName: [
            { required: true, message: '帐号不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          captcha: [
            { required: true, message: '验证码不能为空', trigger: 'blur' }
          ]
        },
        captchaPath: ''
      }
    },
    created () {
      this.getCaptcha()
    },
    methods: {
      // 提交表单
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl('/sys/login'),
              method: 'post',
              data: this.$http.adornData({
                'username': this.dataForm.userName,
                'password': this.dataForm.password,
                'uuid': this.dataForm.uuid,
                'captcha': this.dataForm.captcha
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$cookie.set('token', data.token)
                this.$router.replace({ name: 'home' })
              } else {
                this.getCaptcha()
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
      // 获取验证码
      getCaptcha () {
        this.dataForm.uuid = getUUID()
        this.captchaPath = this.$http.adornUrl(`/captcha.jpg?uuid=${this.dataForm.uuid}`)
      }
    }
  }
</script>

<style lang="scss">

  .site-wrapper.site-page--login {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    overflow: hidden;
  &:before {
     position: fixed;
     top: 100px;
     bottom:100px;
     left: 0;
     width: 100%;
     /*height: 100%;*/
     content: "";
     background-image: url(~@/assets/img/login_bg.jpg);
     background-size: auto 100%;
     background-position: left;
     background-repeat: no-repeat;
     background-color: #fff;
   }
  .site-content__wrapper {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    padding: 0;
    margin: 0;
    overflow-x: hidden;
    overflow-y: auto;
    background-color: transparent;
  }
  .site-content {
    min-height: 100%;
  .footer{
    position: absolute;
    bottom: 40px;
    width: 100%;
    font-size: 14px;
    color:#666;
    text-align: center;
  }
  }
  .brand-info {
    color: #fff;
    padding: 5px 0 0 10px;
  }
  .login-main {
    position: absolute;
    top: 50%;
    margin-top: -200px;
    right: 40px;
    width: 670px;
  >.card-box {
     width: 350px;
     min-height: 350px;
     margin: auto;
  form{
    margin-top: 10px;
  }
  }
  }
  .login-captcha {
    overflow: hidden;
  > img {
      width: 100%;
      cursor: pointer;
    }
  }
  .login-btn-submit {
    width: 100%;
    margin-top: 18px;
  }
  .tabs-box .el-tabs__nav{
    width: 100%;
  >div{
     width: 50%;
     text-align: center;
   }
  }
  }
  .site-wrapper.site-page--login .tabs-box .el-tabs__nav > div{width:100%;}
</style>
