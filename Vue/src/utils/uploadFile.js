import Vue from 'vue';
import VueCookie from 'vue-cookie';
import axios from 'axios';
// import toastr from '../assets/toastr.min';
// Vue.use(axios)


let http = {};
// let _baseURL = '/vpaas'
let _baseURL = 'http://localhost:8080/vpaas'
let ContentType = "application/json";

let uploadFileType = "multipart/form-data";
http.baseURL = _baseURL;


/**
 * 上传文件的请求
 * @param url
 * @returns {AxiosPromise}
 */
http.uploadFile = function (url, data) {
  let config = {
    //请求的接口，在请求的时候，如axios.get(url,config);这里的url会覆盖掉config中的url
    url: url,
    //基础url前缀
    baseURL: _baseURL,
    transformResponse: [function (data1) {
      var data = data1;
      if (typeof data1 == "string") {
        data = JSON.parse(data1);
      }
      //这里提前处理返回的数据;
      if (data.message && (data.data === 'login.invalid.token')) {
        window.localStorage.removeItem("access-user");
        alert("超时请重新登陆");
        window.location.href = '/';
      }
      return data;
    }],
    //请求头信息
    headers: {'access-user': window.localStorage.getItem('access-user'),  'Content-Type': uploadFileType},

    //跨域请求时是否需要使用凭证
    withCredentials: true,
    // 返回数据类型
    responseType: 'json', //default
  };
  return axios.post(url, data, config);
};

