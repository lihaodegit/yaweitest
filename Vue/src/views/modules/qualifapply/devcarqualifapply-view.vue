<template>
  <el-dialog
    :title="'查看'"
    :close-on-click-modal="true"
    :visible.sync="visible">
    <el-form :model="dataForm" ref="dataForm" label-width="100px">
    <el-form-item label="企业名称" prop="entname">
      <el-input v-model="dataForm.entname" readonly="true"></el-input>
    </el-form-item>
    <el-form-item label="车企负责人" prop="princ" >
      <el-input v-model="dataForm.princ" readonly="true"></el-input>
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
      <el-button @click="visible = false">关闭</el-button>
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
				this.dataForm.audittime=data.audittime
				this.dataForm.auditoption=data.auditoption
				if(data.auditresult==1){
					this.dataForm.auditresult= '通过';
				}
				else if(data.auditresult==0){
					this.dataForm.auditresult= '驳回';
				}else{
					this.dataForm.auditresult= '待审核';
				}

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

				//
				this.visible = true
			}else{
				this.$message.error(data.msg)
			}
		})
      },
    }
  }
</script>
