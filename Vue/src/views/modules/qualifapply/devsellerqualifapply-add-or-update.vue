<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
	<!--
    <el-form-item label="组织机构ID" prop="orgid">
      <el-input v-model="dataForm.orgid" placeholder="组织机构ID"></el-input>
    </el-form-item>
	-->
    <el-form-item label="企业名称" prop="entname">
      <el-input v-model="dataForm.entname" placeholder="企业名称"></el-input>
    </el-form-item>
    <el-form-item label="经销商负责人" prop="princ">
      <el-input v-model="dataForm.princ" placeholder="经销商负责人"></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="电话"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="公司地址" prop="dom">
      <el-input v-model="dataForm.dom" placeholder="公司地址"></el-input>
    </el-form-item>
	<!--
    <el-form-item label="申请时间" prop="applytime">
      <el-input v-model="dataForm.applytime" placeholder="申请时间"></el-input>
    </el-form-item>
    <el-form-item label="审核时间" prop="audittime">
      <el-input v-model="dataForm.audittime" placeholder="审核时间"></el-input>
    </el-form-item>
    <el-form-item label="审核意见" prop="auditoption">
      <el-input v-model="dataForm.auditoption" placeholder="审核意见"></el-input>
    </el-form-item>
    <el-form-item label="审核结果" prop="auditresult">
      <el-input v-model="dataForm.auditresult" placeholder="审核结果:1,同意;0,驳回"></el-input>
    </el-form-item>
    <el-form-item label="审核人" prop="auditer">
      <el-input v-model="dataForm.auditer" placeholder="审核人"></el-input>
    </el-form-item>
    <el-form-item label="预留字段1" prop="ylzd1">
      <el-input v-model="dataForm.ylzd1" placeholder="预留字段1"></el-input>
    </el-form-item>
    <el-form-item label="预留字段2" prop="ylzd2">
      <el-input v-model="dataForm.ylzd2" placeholder="预留字段2"></el-input>
    </el-form-item>
    <el-form-item label="预留字段3" prop="ylzd3">
      <el-input v-model="dataForm.ylzd3" placeholder="预留字段3"></el-input>
    </el-form-item>
	-->
	<el-form-item label="三证合一">
		<el-upload	
			multiple
			:limit=1
			:action="uploadUrl_szhy"
			:before-upload="beforeUploadHandle"
			:on-success="successHandleSzhy"
			:file-list="fileListSzhy"
			style="text-align: center;">			
			<el-button size="small" type="primary">点击上传</el-button>
			<div class="el-upload__tip" slot="tip">扫描件1张，支持jpg、png图片！</div>
		</el-upload>
	</el-form-item>
	<el-form-item label="身份证">
		<el-upload
			multiple
			:limit=2
			:action="uploadUrl_sfz"
			:before-upload="beforeUploadHandle"
			:on-success="successHandleSfz"
			:file-list="fileListSfz"
			style="text-align: center;">
			<el-button size="small" type="primary">点击上传</el-button>
			<div class="el-upload__tip" slot="tip">正反面2张，支持jpg、png图片！</div>
		</el-upload>		
	</el-form-item>
	<el-form-item label="汽修中心证明">
		<el-upload
			multiple
			:limit=5
			:action="uploadUrl_qcwxfwzxzm"
			:before-upload="beforeUploadHandle"
			:on-success="successHandleQcwxfwzxzm"
			:file-list="fileListQcwxfwzxzm"
			style="text-align: center;">
			<el-button size="small" type="primary">点击上传</el-button>
			<div class="el-upload__tip" slot="tip">支持jpg、png图片,数量不少于3张！</div>
		</el-upload>		
	</el-form-item>
	<el-form-item label="车企委托书">
		<el-upload
			multiple
			:limit=1
			:action="uploadUrl_cqwts"
			:before-upload="beforeUploadHandle"
			:on-success="successHandleCqwts"
			:file-list="fileListCqwts"
			style="text-align: center;">
			<el-button size="small" type="primary">点击上传</el-button>
			<div class="el-upload__tip" slot="tip">扫描件1张，支持jpg、png图片！</div>
		</el-upload>		
	</el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { getUUID } from '@/utils'
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: getUUID(),
          orgid: '',
          entname: '',
          princ: '',
          mobile: '',
          email: '',
          dom: ''
        },
        dataRule: {
          entname: [
            { required: true, message: '企业名称不能为空', trigger: 'blur' }
          ],
          princ: [
            { required: true, message: '经销商负责人不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '电话不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          dom: [
            { required: true, message: '公司地址不能为空', trigger: 'blur' }
          ]
        },
		fileListSzhy: [],
		fileListSfz: [],
		fileListQcwxfwzxzm: [],
		fileListCqwts: [],
		uploadUrl_szhy:'',
		uploadUrl_sfz:'',		
		uploadUrl_qcwxfwzxzm:'',
		uploadUrl_cqwts:''
      }
    },
    methods: {
      init (id) {        
        this.visible = true
		this.$nextTick(() => {
			this.$refs['dataForm'].resetFields()
			this.fileListSzhy=[]
			this.fileListSfz=[]
			this.fileListCqwts=[]
			this.fileListQcwxfwzxzm=[]
		})
		//查询企业名称
		this.$http({
			url:this.$http.adornUrl(`/qualifapply/devsellerqualifapply/orgname`),
			method:'post'
		}).then(({data})=>{
			if (data && data.code === 0) {
				this.dataForm.entname=data.entname
			}
		})
		this.uploadUrl_szhy = this.$http.adornUrl(`/qualifapply/devsellerqualifapply/upload?token=${this.$cookie.get('token')}&applyid=${this.dataForm.id}&attchmenttype=03`)
		this.uploadUrl_sfz = this.$http.adornUrl(`/qualifapply/devsellerqualifapply/upload?token=${this.$cookie.get('token')}&applyid=${this.dataForm.id}&attchmenttype=04`)
		this.uploadUrl_qcwxfwzxzm = this.$http.adornUrl(`/qualifapply/devsellerqualifapply/upload?token=${this.$cookie.get('token')}&applyid=${this.dataForm.id}&attchmenttype=05`)		
		this.uploadUrl_cqwts = this.$http.adornUrl(`/qualifapply/devsellerqualifapply/upload?token=${this.$cookie.get('token')}&applyid=${this.dataForm.id}&attchmenttype=06`)		
		/*
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/qualifapply/devsellerqualifapply/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orgid = data.devsellerqualifapply.orgid
                this.dataForm.entname = data.devsellerqualifapply.entname
                this.dataForm.princ = data.devsellerqualifapply.princ
                this.dataForm.mobile = data.devsellerqualifapply.mobile
                this.dataForm.email = data.devsellerqualifapply.email
                this.dataForm.dom = data.devsellerqualifapply.dom
                this.dataForm.applytime = data.devsellerqualifapply.applytime
                this.dataForm.audittime = data.devsellerqualifapply.audittime
                this.dataForm.auditoption = data.devsellerqualifapply.auditoption
                this.dataForm.auditresult = data.devsellerqualifapply.auditresult
                this.dataForm.auditer = data.devsellerqualifapply.auditer
                this.dataForm.ylzd1 = data.devsellerqualifapply.ylzd1
                this.dataForm.ylzd2 = data.devsellerqualifapply.ylzd2
                this.dataForm.ylzd3 = data.devsellerqualifapply.ylzd3
              }
            })
          }
        })
		*/
      },
	  // 上传之前
      beforeUploadHandle (file) {
		//console.log(file.type)
        if (file.type !== 'image/jpg' && file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'application/pdf') {
          this.$message.error('只支持jpg、png、pdf格式的图片！')
          return false
        }
        this.num++
      },
      // 上传成功(三证合一)
      successHandleSzhy (response, file, fileList) {
        this.fileListSzhy = fileList        
        if (response && response.code === 0) {          
        } else {
          this.$message.error(response.msg)
        }
      },
	  // 上传成功(身份证)
      successHandleSfz (response, file, fileList) {
        this.fileListSfz = fileList        
        if (response && response.code === 0) {          
        } else {
          this.$message.error(response.msg)
        }
      },
	  // 上传成功(汽车维修服务中心证明)
      successHandleQcwxfwzxzm (response, file, fileList) {
        this.fileListQcwxfwzxzm = fileList        
        if (response && response.code === 0) {          
        } else {
          this.$message.error(response.msg)
        }
      },
	  // 上传成功(车企委托书)
      successHandleCqwts (response, file, fileList) {
        this.fileListCqwts = fileList        
        if (response && response.code === 0) {          
        } else {
          this.$message.error(response.msg)
        }
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
		 //检测图片是否上传
		  if(!(this.fileListSzhy.length==1)){
			this.$message.error('请上传三证合一图片资料！')
		  }else if (!(this.fileListSfz.length==2)){
			this.$message.error('请上传身份证正反面图片资料！')
		  }else if(!(this.fileListQcwxfwzxzm.length>=3)){
			this.$message.error('请上传汽车维修服务中心证明图片资料，不能少于3家！')
		  }else if(!(this.fileListSzhy.length==1&&this.fileListSfz.length==2&&this.fileListCqwts.length==1&&this.fileListQcwxfwzxzm.length>=3)){
			this.$message.error('请按照要求上传相关图片资料！')
		  }else{
			if (valid) {
				this.$http({
				  url: this.$http.adornUrl(`/qualifapply/devsellerqualifapply/save`),
				  method: 'post',
				  data: this.$http.adornData({
					'id': this.dataForm.id,
					'entname': this.dataForm.entname,
					'princ': this.dataForm.princ,
					'mobile': this.dataForm.mobile,
					'email': this.dataForm.email,
					'dom': this.dataForm.dom
				  })
				}).then(({data}) => {
				  if (data && data.code === 0) {
					this.$message({
					  message: '操作成功',
					  type: 'success',
					  duration: 1500,
					  onClose: () => {
						this.visible = false
						this.$emit('refreshDataList')
					  }
					})
				  } else {
					this.$message.error(data.msg)
				  }
				})
			  }
		  }          
        })
      }
    }
  }
</script>
