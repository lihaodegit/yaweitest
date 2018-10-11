<template>
  <el-dialog
    :title="'修改'"
    :close-on-click-modal="true"
    :visible.sync="visible">
    <el-form :model="dataForm" ref="dataForm" label-width="100px">
    <el-form-item label="企业名称" prop="entname">
      <el-input v-model="dataForm.entname"></el-input>
    </el-form-item>
    <el-form-item label="经销商负责人" prop="princ">
      <el-input v-model="dataForm.princ"></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="mobile">
      <el-input v-model="dataForm.mobile" ></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email"></el-input>
    </el-form-item>
    <el-form-item label="公司地址" prop="dom">
      <el-input v-model="dataForm.dom"></el-input>
    </el-form-item>
    <el-form-item label="申请时间" prop="applytime">
      <el-input v-model="dataForm.applytime"></el-input>
    </el-form-item>
    <!--<el-form-item label="审核时间" prop="audittime">
      <el-input v-model="dataForm.audittime"></el-input>
    </el-form-item>
    <el-form-item label="审核意见" prop="auditoption">
      <el-input v-model="dataForm.auditoption"></el-input>
    </el-form-item>
    <el-form-item label="审核结果" prop="auditresult">
      <el-input v-model="dataForm.auditresult"></el-input>
    </el-form-item>-->
	<el-form-item label="三证合一" prop="auditresult">
		<template v-if="szhy">
			<template v-if="szhy.type==='.pdf'">
				<a :href="sfzImg0" target="_blank">{{szhy.filename}}</a>
			</template>
			<template v-else>
				<img :src="szhyImg" width="100" height="100"/>
			</template>
		</template>
	</el-form-item>
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
	<el-form-item label="汽修中心证明" prop="auditresult">
		<template v-if="qcwxfwzxzm0">
			<template v-if="qcwxfwzxzm0.type==='.pdf'">
				<a :href="qcwxfwzxzmImg0" target="_blank">{{qcwxfwzxzm0.filename}}</a>
			</template>
			<template v-else>
				<img :src="qcwxfwzxzmImg0" width="100" height="100">
			</template>
		</template>
		<template v-if="qcwxfwzxzm1">
			<template v-if="qcwxfwzxzm1.type==='.pdf'">
				<a :href="qcwxfwzxzmImg1" target="_blank">{{qcwxfwzxzm1.filename}}</a>
			</template>
			<template v-else>
				<img :src="qcwxfwzxzmImg1" width="100" height="100">
			</template>
		</template>
		<template v-if="qcwxfwzxzm2">
			<template v-if="qcwxfwzxzm2.type==='.pdf'">
				<a :href="qcwxfwzxzmImg2" target="_blank">{{qcwxfwzxzm2.filename}}</a>
			</template>
			<template v-else>
				<img :src="qcwxfwzxzmImg2" width="100" height="100">
			</template>
		</template>
		<template v-if="qcwxfwzxzm3">
			<template v-if="qcwxfwzxzm3.type==='.pdf'">
				<a :href="qcwxfwzxzmImg3" target="_blank">{{qcwxfwzxzm3.filename}}</a>
			</template>
			<template v-else>
				<img :src="qcwxfwzxzmImg3" width="100" height="100">
			</template>
		</template>
		<template v-if="qcwxfwzxzm4">
			<template v-if="qcwxfwzxzm4.type==='.pdf'">
				<a :href="qcwxfwzxzmImg4" target="_blank">{{qcwxfwzxzm4.filename}}</a>
			</template>
			<template v-else>
				<img :src="qcwxfwzxzmImg4" width="100" height="100">
			</template>
		</template>
	</el-form-item>
	<el-form-item label="车企委托书" prop="auditresult">
		<template v-if="cqwts">
			<template v-if="cqwts.type==='.pdf'">
				<a :href="cqwtsImg" target="_blank">{{cqwts.filename}}</a>
			</template>
			<template v-else>
				<img :src="cqwtsImg" width="100" height="100"/>
			</template>
		</template>
	</el-form-item>
	</el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">关闭</el-button>
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
		qcwxfwzxzm0:null,
		qcwxfwzxzm1:null,
		qcwxfwzxzm2:null,
		qcwxfwzxzm3:null,
		qcwxfwzxzm4:null,
		cqwts:null,
		szhyImg:'',
		sfzImg0:'',
		sfzImg1:'',
		qcwxfwzxzmImg0:'',
		qcwxfwzxzmImg1:'',
		qcwxfwzxzmImg2:'',
		qcwxfwzxzmImg3:'',
		qcwxfwzxzmImg4:'',
		cqwtsImg:{}
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        console.log(id)
        //查询
        this.$http({
          url:this.$http.adornUrl(`/qualifapply/devsellerqualifapply/detail`),
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
            /*this.dataForm.audittime=data.audittime
            this.dataForm.auditoption=data.auditoption
            if(data.auditresult==1){
              this.dataForm.auditresult= '通过';
            }
            else if(data.auditresult==0){
              this.dataForm.auditresult= '驳回';
            }else{
              this.dataForm.auditresult= '待审核';
            }*/
            //三证合一
            this.szhy=data.szhy
            this.szhyImg=this.$http.adornUrl(`/upload/browserpic/${data.szhy.id}?token=${this.$cookie.get('token')}`)
            //身份证
            if(data.sfz0){
              this.sfz0=data.sfz0
              this.sfzImg0=this.$http.adornUrl(`/upload/browserpic/${data.sfz0.id}?token=${this.$cookie.get('token')}`)
            }
            if(data.sfz1){
              this.sfz1=data.sfz1
              this.sfzImg1=this.$http.adornUrl(`/upload/browserpic/${data.sfz1.id}?token=${this.$cookie.get('token')}`)
            }
            //汽车维修服务中心证明
            if(data.qcwxfwzxzm0){
              this.qcwxfwzxzm0=data.qcwxfwzxzm0
              this.qcwxfwzxzmImg0=this.$http.adornUrl(`/upload/browserpic/${data.qcwxfwzxzm0.id}?token=${this.$cookie.get('token')}`)
            }
            if(data.qcwxfwzxzm1){
              this.qcwxfwzxzm1=data.qcwxfwzxzm1
              this.qcwxfwzxzmImg1=this.$http.adornUrl(`/upload/browserpic/${data.qcwxfwzxzm1.id}?token=${this.$cookie.get('token')}`)
            }
            if(data.qcwxfwzxzm2){
              this.qcwxfwzxzm2=data.qcwxfwzxzm2
              this.qcwxfwzxzmImg2=this.$http.adornUrl(`/upload/browserpic/${data.qcwxfwzxzm2.id}?token=${this.$cookie.get('token')}`)
            }
            if(data.qcwxfwzxzm3){
              this.qcwxfwzxzm3=data.qcwxfwzxzm3
              this.qcwxfwzxzmImg3=this.$http.adornUrl(`/upload/browserpic/${data.qcwxfwzxzm3.id}?token=${this.$cookie.get('token')}`)
            }
            if(data.qcwxfwzxzm4){
              this.qcwxfwzxzm4=data.qcwxfwzxzm4
              this.qcwxfwzxzmImg4=this.$http.adornUrl(`/upload/browserpic/${data.qcwxfwzxzm4.id}?token=${this.$cookie.get('token')}`)
            }
            //车企委托书
            if(data.cqwts){
              this.cqwts=data.cqwts
              this.cqwtsImg=this.$http.adornUrl(`/upload/browserpic/${data.cqwts.id}?token=${this.$cookie.get('token')}`)
            }
            //显示
            this.visible = true
          }else{
            this.$message.error(data.msg)
          }
        })
      },
      dataFormSubmit () {
        this.$http({
          url : this.$http.adornUrl('/qualifapply/devsellerqualifapply/update'),
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
