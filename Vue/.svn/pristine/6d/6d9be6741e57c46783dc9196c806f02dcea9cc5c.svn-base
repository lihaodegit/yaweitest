<template>
  <el-dialog
    :title="!dataForm.id ? '导入' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form v-loading="loading" element-loading-text="正在上传中" :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
       <el-select v-model="upLoadData.sellerId" filterable placeholder="请选择销售商" style="width:80%;">
				<el-option
					v-for="item in options"
					:key="item.value"
					:label="item.label"
					:value="item.value">
				</el-option>
			 </el-select>
			 <el-upload
					class="upload-demo"
					drag
					multiple
					ref="upload"
					:action="uploadUrl"
					:file-list="fileList"
					:limit="2"
					 list-type="text"
           accept=".xlsx"
					 :data="upLoadData"
					 :before-upload="beforeUploadHandler"
					>
					<i class="el-icon-upload"></i>
					<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
					<div class="el-upload__tip" slot="tip">车辆补助资金清算信息明细表和车辆运行情况表</div>
				</el-upload>
				<el-upload
					class="upload-demo"
					drag
					multiple
					ref="upload1"
					:action="uploadUrl1"
				  :before-upload="beforeUploadHandler1"
					:file-list="fileList1"
					:limit="1"
					list-type="text"
					accept=".pdf"
					:data="upLoadData"
					>
					<i class="el-icon-upload"></i>
					<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
					<div class="el-upload__tip" slot="tip">国补明细附件（PDF合为一个，仅能上传一个）</div>
				</el-upload>
				
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
				options: [],
				dataForm: {
          id: 0
				},
				dataRule: {},
				carSellerId: '',
        fileList: [],
				fileList1: [],
				uploadUrl:'',
				uploadUrl1:'',
				upLoadData:{
					uuid:'',
					sellerId:''
				},
				upexcel:0,
				upfile:0,
				files:[],
				files1:'',
				loading: false

      }
    },
    methods: {
			beforeUploadHandler (file){
					this.files.push(file)
					this.fileList.push({name:file.name,size:file.size})
					return false;
			},
			beforeUploadHandler1 (file){
					this.files1 = file
					this.fileList1.push({name:file.name,size:file.size})
					return false;
			},
		
      init (id) {
				this.upLoadData.uuid = this.getUUID()
				this.uploadUrl = this.$http.adornUrl(`/generator/devcarbasedata/upload?token=${this.$cookie.get('token')}`)
				this.uploadUrl1 = this.$http.adornUrl(`/generator/devcarbasedata/submitUpload?token=${this.$cookie.get('token')}`)
				this.dataForm.id = id || 0
        this.visible = true
				//获取经销商下拉列表
				this.$http({
					url: this.$http.adornUrl(`/generator/devcarbasedata/getSellerList`),
					method: 'get',
					params: this.$http.adornParams()
				}).then(({data}) => {
					if (data && data.code === 0) {
						this.options = data.data
					}
				})
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/generator/devcarbasedata/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                
              }
            })
          }
        })
			
      },
      // 表单提交
      dataFormSubmit () {
				if(this.upLoadData.sellerId == ''){
							this.$message({
									message: '请选择销售商',
									type: 'warning'
								});
								return
						}
					if(this.fileList.length != 2){
						 this.$message({
								message: '请选择车辆补助资金清算信息明细表和车辆运行情况表',
								type: 'warning'
							});
							return
					}
					if(this.fileList1.length < 1){
						this.$message({
								message: '请选择国补明细附件文件',
								type: 'warning'
							});
							return
					}
					this.loading = true
          this.$refs.upload.submit();
					 event.preventDefault();//取消默认行为
					 //创建 formData 对象
					let formData = new FormData();
					// 向 formData 对象中添加文件
					console.log(this.files)
					formData.append('file',this.files[0]);
					formData.append('file',this.files[1])
					formData.append('file1',this.files1);;
					formData.append('uuid',this.upLoadData.uuid);
					formData.append('sellerId',this.upLoadData.sellerId);
					var options = {  // 设置axios的参数    
							 url: this.uploadUrl,   
							 data: formData,  
							  method: 'post',    
							  headers: { 
							    'Content-Type': 'multipart/form-data',
							},
							}

          // 发送请求
					this.axios(options).then((res) => {
						if ( res.data.code == 0) {
							 this.loading = false
							 this.$message({
							 	message: '操作成功',
							 	type: 'success',
							 	duration: 1500,
							 	onClose: () => {
									this.visible = false
									this.fileList = []
									this.fileList1 = []
							 		this.$emit('refreshDataList')
							 	}
							 })
						}else{
							this.loading = false
							var msgLists = res.data.errMsg;
							var errmsg = "<p>";
							for(var i = 0;i< msgLists.length;i++){
								var msgList = msgLists[i]
								errmsg +=  msgList.fileName + "</br>";
								for(var ii = 0;ii< msgList.list.length;ii++){
									var msg =  msgList.list[ii]
										if(msg.rows != null){
											errmsg += "--第" + msg.rows+"行--"+msg.errMsg + "</br>"
										}
								}
							}
							errmsg = errmsg + "</p>"
							this.$alert(errmsg, {
								dangerouslyUseHTMLString: true
							});
							
						}
					}) 
					
      }
    }
  }
</script>

<style>
	 .upload-demo{margin-top: 20px;}
	 .el-message-box{overflow-y: scroll;height: 150px;}
	
</style>
