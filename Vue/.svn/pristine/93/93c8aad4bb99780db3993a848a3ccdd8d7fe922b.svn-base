<template>
  <el-dialog
    :title="'修改'"
    :close-on-click-modal="true"
    :visible.sync="visible">
    <el-form :model="dataForm" ref="dataForm" label-width="100px">
    <el-form-item label="企业名称" prop="entname">
      <el-input v-model="dataForm.entname" readonly="true"></el-input>
    </el-form-item>
    <el-form-item label="车企负责人" prop="princ" >
      <el-input v-model="dataForm.princ"></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="mobile">
      <el-input v-model="dataForm.mobile" readonly="true"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" readonly="true"></el-input>
    </el-form-item>
    <el-form-item label="公司地址" prop="dom">
      <el-input v-model="dataForm.dom" readonly="true"></el-input>
    </el-form-item>
    <el-form-item label="申请时间" prop="applytime">
      <el-input v-model="dataForm.applytime" readonly="true"></el-input>
    </el-form-item>
    <!--<el-form-item label="审核时间" prop="audittime">
      <el-input v-model="dataForm.audittime" readonly="true"></el-input>
    </el-form-item>
    <el-form-item label="审核意见" prop="auditoption">
      <el-input v-model="dataForm.auditoption" readonly="true"></el-input>
    </el-form-item>
    <el-form-item label="审核结果" prop="auditresult">
      <el-input v-model="dataForm.auditresult" readonly="true"></el-input>
    </el-form-item>-->
	<template v-if="szhy">
		<el-form-item label="三证合一" prop="auditresult">
			<img :src="szhyImg" width="100" height="100"/>
		</el-form-item>
	</template>
	<el-form-item label="身份证" prop="auditresult">
		<template v-if="sfz0">
			<template v-if="sfz0.type==='.pdf'">
				<a :href="sfzImg0" target="_blank">{{sfz0.filename}}</a>
			</template>
			<template v-else>
				<img :src="sfzImg0" width="100" height="100">
			</template>
		</template>
		<template v-if="sfz1">
			<template v-if="sfz1.type==='.pdf'">
				<a :href="sfzImg1" target="_blank">{{sfz1.filename}}</a>
			</template>
			<template v-else>
				<img :src="sfzImg1" width="100" height="100">
			</template>
		</template>
	</el-form-item>
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
        dataForm: {
          entname: '',
          princ: '',
          mobile: '',
          email: '',
          dom: '',
          applytime: '',
          audittime: '',
          auditoption: '',
          auditresult: ''
        },
		szhy:null,
		sfz0:null,
		sfz1:null,
		szhyImg:'',
		sfzImg0:'',
		sfzImg1:''
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
		//查询
		this.$http({
			url:this.$http.adornUrl(`/qualifapply/devcarqualifapply/detail`),
			method:'post',
			params:this.$http.adornParams({
				'id': id
			})
		}).then(({data})=>{
			if (data && data.code === 0) {
				this.dataForm.entname=data.entname
				this.dataForm.princ=data.princ
				this.dataForm.mobile=data.mobile
				this.dataForm.email=data.email
				this.dataForm.dom=data.dom
				this.dataForm.applytime=data.applytime
				// this.dataForm.audittime=data.audittime
				// this.dataForm.auditoption=data.auditoption
				// this.dataForm.auditresult=data.auditresult
				//图片
				this.szhy=data.szhy
				this.szhyImg=this.$http.adornUrl(`/upload/browserpic/${data.szhy.id}?token=${this.$cookie.get('token')}`)
				if(data.sfz0){
					this.sfz0=data.sfz0
					this.sfzImg0=this.$http.adornUrl(`/upload/browserpic/${data.sfz0.id}?token=${this.$cookie.get('token')}`)
				}
				if(data.sfz1){
					this.sfz1=data.sfz1
					this.sfzImg1=this.$http.adornUrl(`/upload/browserpic/${data.sfz1.id}?token=${this.$cookie.get('token')}`)
				}
				this.visible = true
			}else{
				this.$message.error(data.msg)
			}
		})
      },
      dataFormSubmit () {
        this.$http({
          url : this.$http.adornUrl('/qualifapply/devcarqualifapply/update'),
          method : 'post',
          data : this.$http.adornData({
            'id' : this.dataForm.id,
            'entname' : this.dataForm.entname,
            'princ' : this.dataForm.princ,
            'mobile' : this.dataForm.mobile,
            'email' : this.dataForm.email,
            'dom' : this.dataForm.dom,
            'applytime' : this.dataForm.applytime
            // 'audittime' : this.data.audittime,
            // 'auditoption' : this.data.auditoption,
            // 'auditresult' : this.data.auditresult
          })
        }).then(({data}) =>{
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
          }else {
            this.$message.error(data.msg)
          }
        })
      }
    }
  }
</script>
