<template>
  <div class="mod-config">
	<el-row :gutter="60">
		<el-col :span="12">
			<el-card class="box-card" shadow="hover">
				<div slot="header" class="clearfix">
					<span>初审统一提交</span>
				</div>
				<div style="text-align:center;">
					<div>
					<el-badge :value="preldtjcnt" class="item">
						<el-button size="small" @click="applyList('1')">待提交申请</el-button>
					</el-badge>
					</div>
					<div>
					<el-badge :value="prelytjcnt" class="item">
						<el-button size="small" @click="applyList('2')">已提交申请</el-button>
					</el-badge>
					</div>
					<div>
						<el-button type="success" :disabled="prelunisubmitenable" @click="prelUniSubmit()">统一提交</el-button>
					</div>
				</div>
			</el-card>
		</el-col>
		<el-col :span="12">
			<el-card class="box-card" shadow="hover">
				<div slot="header" class="clearfix">
					<span>复审统一提交</span>
				</div>
				<div style="text-align:center;">
					<div>
					<el-badge :value="retrialdtjcnt" class="item">
						<el-button size="small" @click="applyList('3')">待提交申请</el-button>
					</el-badge>
					</div>
					<div>
					<el-badge :value="retrialytjcnt" class="item">
						<el-button size="small" @click="applyList('4')">已提交申请</el-button>
					</el-badge>
					</div>
					<div>
						<el-button type="success" :disabled="retrialunisubmitenable" @click="retrialUniSubmit()">统一提交</el-button>
					</div>
				</div>
			</el-card>
		</el-col>
	</el-row>
  </div>
</template>
<style>
  .text {
    font-size: 14px;
  }
  .item {
    margin-bottom: 18px;
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {	
    width: 100%;
	height:400px;
  }
</style>
<script>  
  export default {
    data () {
      return {
		preldtjcnt:0,
		prelytjcnt:0,
		prelunisubmitenable:true,		
		retrialdtjcnt:0,
		retrialytjcnt:0,
		retrialunisubmitenable:true
      }
    },
	activated(){
		this.init()
	},
    methods: {
		init(){
			this.prelCount()
			this.retrialCount()						
		},
		//初审查询
		prelCount(){
			//查询-初审待提交数量
			this.$http({
				url:this.$http.adornUrl('/cityfinance/unisubmit/prelcount'),
				method:'post'				
			}).then(({data})=>{
				if(data&&data.code===0){
					this.preldtjcnt = data.preldtjcnt>0?(data.preldtjcnt+'条'):'0条'
					this.prelytjcnt = data.prelytjcnt>0?(data.prelytjcnt+'条'):'0条'
					this.prelunisubmitenable = data.preldtjcnt<=0
				}else{
					this.preldtjcnt = '0条'
					this.prelytjcnt = '0条'
				}
			})
		},
		//复审查询
		retrialCount(){
			//查询复审待提交
			this.$http({
				url:this.$http.adornUrl('/cityfinance/unisubmit/retrialcount'),
				method:'post'
			}).then(({data})=>{
				if(data&&data.code===0){
					this.retrialdtjcnt = data.retrialdtjcnt>0?(data.retrialdtjcnt+'条'):'0条'
					this.retrialytjcnt = data.retrialytjcnt>0?(data.retrialytjcnt+'条'):'0条'
					this.retrialunisubmitenable = data.retrialdtjcnt<=0
				}else{
					this.retrialdtjcnt = '0条'
					this.retrialytjcnt = '0条'
				}
			})
		},
		//初审统一提交
		prelUniSubmit(){
			this.$http({
				url:this.$http.adornUrl('/cityfinance/unisubmit/prelsubmit'),
				method:'post'
			}).then(({data})=>{
				if(data&&data.code===0){		
					this.$message({
						message: '提交成功！',
						type: 'success',
						duration: 1500
					})	
					this.prelCount()
				}else{
					this.$message({
						message: data.msg,
						type: 'error',
						duration: 1500
					})
				}
			})
		},
		//复审统一提交
		retrialUniSubmit(){
			this.$http({
				url:this.$http.adornUrl('/cityfinance/unisubmit/retrialsubmit'),
				method:'post'
			}).then(({data})=>{
				if(data&&data.code===0){
					this.$message({
						message: '提交成功！',
						type: 'success',
						duration: 1500
					})
					this.retrialCount()
				}else{
					this.$message({
						message: data.msg,
						type: 'error',
						duration: 1500
					})
				}
			})
		},
		//查询申请列表
		applyList(flag){
			//初审待提交列表
			if(flag=='1'){				
				if(this.preldtjcnt=='0条'){
					this.$message({
						message: '没有记录！',
						type: 'success',
						duration: 1500
					})
					return
				}
				//跳转列表
				this.$router.push({path:'/unisubmit-applylist',name:"unisubmit-applylist",params: {type: 'czj',flag:'1'}});
			}
			//初审已提交列表
			else if(flag=='2'){
				if(this.prelytjcnt=='0条'){
					this.$message({
						message: '没有记录！',
						type: 'success',
						duration: 1500
					})
					return
				}
				//跳转列表
				this.$router.push({path:'/unisubmit-applylist',name:"unisubmit-applylist",params: {type: 'czj',flag:'2'}});
			}
			//复审待提交列表
			else if(flag=='3'){
				if(this.retrialdtjcnt=='0条'){
					this.$message({
						message: '没有记录！',
						type: 'success',
						duration: 1500
					})
					return
				}
				//跳转列表
				this.$router.push({path:'/unisubmit-applylist',name:"unisubmit-applylist",params: {type: 'czj',flag:'3'}});
			}
			//复审已提交列表
			else if(flag=='4'){
				if(this.retrialytjcnt=='0条'){
					this.$message({
						message: '没有记录！',
						type: 'success',
						duration: 1500
					})
					return
				}
				//跳转列表
				this.$router.push({path:'/unisubmit-applylist',name:"unisubmit-applylist",params: {type: 'czj',flag:'4'}});
			}
		}
    }
  }
</script>
