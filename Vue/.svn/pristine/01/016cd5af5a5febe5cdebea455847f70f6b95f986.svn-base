<template>
  <div class="mod-config">
	<el-row :gutter="60">
		<el-col :span="24">
			<el-card class="box-card" shadow="hover">				
				<div style="text-align:center;">
					<div>
					<el-badge :value="dtjcnt" class="item">
						<el-button size="small" @click="applyList('1')">待提交申请</el-button>
					</el-badge>
					</div>
					<div>
					<el-badge :value="ytjcnt" class="item">
						<el-button size="small" @click="applyList('2')">已提交申请</el-button>
					</el-badge>
					</div>
					<div>
						<el-button type="success" :disabled="unisubmitenable" @click="uniSubmit()">统一提交</el-button>
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
		dtjcnt:0,
		ytjcnt:0,
		unisubmitenable:true		
      }
    },
	activated(){
		this.init()
	},
    methods: {
		init(){
			//查询-初审待提交数量
			this.$http({
				url:this.$http.adornUrl('/areafinance/unisubmit/count'),
				method:'post'				
			}).then(({data})=>{
				if(data&&data.code===0){
					this.dtjcnt = data.dtjcnt>0?(data.dtjcnt+'条'):'0条'
					this.ytjcnt = data.ytjcnt>0?(data.ytjcnt+'条'):'0条'
					this.unisubmitenable = data.dtjcnt<=0
				}else{
					this.dtjcnt = '0条'
					this.ytjcnt = '0条'
				}
			})
		},		
		//初审统一提交
		uniSubmit(){
			this.$http({
				url:this.$http.adornUrl('/areafinance/unisubmit/qczjunisubmit'),
				method:'post'
			}).then(({data})=>{
				if(data&&data.code===0){		
					this.$message({
						message: '提交成功！',
						type: 'success',
						duration: 1500
					})	
					this.init()
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
			//待提交列表
			if(flag=='1'){				
				if(this.dtjcnt=='0条'){
					this.$message({
						message: '没有记录！',
						type: 'success',
						duration: 1500
					})
					return
				}
				//跳转列表
				this.$router.push({path:'/unisubmit-applylist',name:"unisubmit-applylist",params: {type: 'areafinance',flag:'1'}});
			}
			//已提交列表
			else if(flag=='2'){
				if(this.ytjcnt=='0条'){
					this.$message({
						message: '没有记录！',
						type: 'success',
						duration: 1500
					})
					return
				}
				//跳转列表
				this.$router.push({path:'/unisubmit-applylist',name:"unisubmit-applylist",params: {type: 'areafinance',flag:'2'}});
			}			
		}
    }
  }
</script>
