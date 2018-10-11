<template>
	  <div>
	    <el-row>
	      <el-col :span="24">
	        <el-row style="height:40px;background-color: #409EFF;border-radius: 8px;">
	          <el-col :span="24">
	            <div style="color: #fff;font-size: 18px;padding: 10px 20px;"><i class="el-icon-info"></i> &nbsp;申请信息</div>
	          </el-col>
	        </el-row>
	      
	      </el-col>
	     
	    </el-row>
	    <el-col :span="24">
	      <el-form style="margin-top: 20px;padding-right:60px;" :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="180px">
	       <el-col :span="12">
					<el-form-item label="车牌号" prop="platenumber">
	        	<el-input v-model="dataForm.platenumber"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>
				<el-col :span="12">
	        <el-form-item label="车辆性质" prop="carprop">
	        	<el-input v-model="dataForm.carprop"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>	
	       <el-col :span="12">
					<el-form-item label="车辆种类" prop="cartype">
	        	<el-input v-model="dataForm.cartype"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>	
	       <el-col :span="12"> 
					<el-form-item label="车辆用途" prop="caruse">
	        	<el-input v-model="dataForm.caruse"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>		
	       <el-col :span="12"> 
					<el-form-item label="购车城市" prop="carcity">
	        	<el-input v-model="dataForm.carcity"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>	
	       <el-col :span="12"> 
					<el-form-item label="购车区域" prop="cararea">
	        	<el-input v-model="dataForm.cararea"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>	
	       <el-col :span="12">
					<el-form-item label="车辆领域" prop="carfield">
	        	<el-input v-model="dataForm.carfield"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>	
	       <el-col :span="12">
					<el-form-item label="销售机构名称" prop="saleorgname">
	        	<el-input v-model="dataForm.saleorgname"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>	
	       <el-col :span="12">
					<el-form-item label="车辆运行单位" prop="carorg">
	        	<el-input v-model="dataForm.carorg"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>	
	       <el-col :span="12">
					<el-form-item label="车辆识别代码" prop="vin">
	        	<el-input v-model="dataForm.vin"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>
	       <el-col :span="12">
					<el-form-item label="车辆型号" prop="carmodel">
	        	<el-input v-model="dataForm.carmodel" :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>	
				 <el-col :span="12">	
	        <el-form-item label="挂牌时间" prop="boardtime">
	        	<el-input v-model="dataForm.boardtime"  :disabled="true"></el-input>
	        </el-form-item>
				 </el-col>
	       <el-col :span="12">
					<el-form-item label="申请时间" prop="applytime">
	        	<el-input v-model="dataForm.applytime"  :disabled="true"></el-input>
	        </el-form-item>
				  </el-col>	
					<el-col :span="12">
	          <el-form-item label="购买价格" prop="pricereal">
	            <el-input v-model="dataForm.pricereal"  :disabled="true"><template slot="append">万元</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="车辆售价" prop="price">
	            <el-input v-model="dataForm.price" :disabled="true"><template slot="append">万元</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="总充电量" prop="totalchargepower">
	            <el-input v-model="dataForm.totalchargepower"  :disabled="true"><template slot="append">KWh</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="累计行驶里程" prop="totalrunmiles">
	            <el-input v-model="dataForm.totalrunmiles"  :disabled="true"><template slot="append">Km</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="补助标准" prop="assiststandard">
	            <el-input v-model="dataForm.assiststandard"  :disabled="true"> <template slot="append">万元辆</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="补助资金数额" prop="assistprice">
	            <el-input v-model="dataForm.assistprice"  :disabled="true"> <template slot="append">万元辆</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="市级财政补助资金数额" prop="assistcityprice">
	            <el-input v-model="dataForm.assistcityprice"  :disabled="true"> <template slot="append">万元辆</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="区级财政补助资金数额" prop="assistareaprice">
	            <el-input v-model="dataForm.assistareaprice"  :disabled="true"> <template slot="append">万元辆</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="系统算出指导补助金额" prop="syscalc">
	            <el-input v-model="dataForm.syscalc"  :disabled="true"> <template slot="append">万元辆</template></el-input>
	          </el-form-item>
	        </el-col>
	      </el-form>
	    </el-col>
	    <el-row>
	    </el-row>
	    <el-row style="height:40px;background-color: #409EFF;border-radius: 8px;">
	      <el-col :span="24">
	        <div style="color: #fff;font-size: 18px;padding: 10px 20px;"><i class="el-icon-info"></i> &nbsp;车辆基本信息</div>
	      </el-col>
	    </el-row>
	    <el-row>
	      <el-form style="margin-top: 20px;padding-right:60px;" :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="180px">
	        <el-col :span="12">
	          <el-form-item label="申请补助标准" prop="applystandard">
	            <el-input v-model="dataForm.applystandard" placeholder="申请补助标准" :disabled="true"></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="发票号" prop="receiptnum">
	            <el-input v-model="dataForm.receiptnum"  :disabled="true"></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="发票日期" prop="receiptdate">
	            <el-input v-model="dataForm.receiptdate"  :disabled="true"></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="行驶证时间" prop="runcarddate">
	            <el-input v-model="dataForm.runcarddate" :disabled="true"></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="是否安装监控装置" prop="ismonitor">
	            <el-input v-model="dataForm.ismonitor"  :disabled="true"></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="单日运行时间" prop="dayrunhours">
	            <el-input v-model="dataForm.dayrunhours"  :disabled="true"><template slot="append">小时</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="一次充满电车辆行驶里程" prop="runmiles">
	            <el-input v-model="dataForm.runmiles"  :disabled="true"><template slot="append">Km</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="月均行驶里程" prop="monthrunmiles">
	            <el-input v-model="dataForm.monthrunmiles"  :disabled="true"><template slot="append">Km</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="车辆一次充满电所需时间" prop="chargetime">
	            <el-input v-model="dataForm.chargetime"  :disabled="true"><template slot="append">小时</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="最大充电功率" prop="chargepower">
	            <el-input v-model="dataForm.chargepower"  :disabled="true"><template slot="append">Kw</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="百公里耗电量" prop="powerconsume">
	            <el-input v-model="dataForm.powerconsume"  :disabled="true"><template slot="append">KWh/100km</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="12">
	          <el-form-item label="电池组总能量" prop="batterypower">
	            <el-input v-model="dataForm.batterypower" placeholder="电池组总能量" :disabled="true"><template slot="append">KWh</template></el-input>
	          </el-form-item>
	        </el-col>
	        <el-col :span="24">
	          <el-form-item label="明细附件中位置的说明备注" prop="locremark">
	            <el-input v-model="dataForm.locremark"  :disabled="true"></el-input>
	          </el-form-item>
	        </el-col>
					<el-col :span="24">
						<el-form-item label="国补明细附件" >
							<a :href="dataForm.cylzd1" target="_blank">国补明细附件点击查看</a>
						</el-form-item>
					</el-col>
	      </el-form>
	    </el-row>
	
	    <el-row style="height:40px;border-radius: 8px;background-color: #409EFF;">
	      <el-col :span="24">
	        <div style="color: #fff;font-size: 16px;padding: 10px 20px;"><i class="el-icon-picture-outline"></i> &nbsp;申请附件</div>
	      </el-col>
	    </el-row>
	
	    <el-row style="min-height:300px;border-radius: 8px;">
	      <el-col :span="12">
	        <div style="font-size: 16px;padding: 10px 20px;">
	          <el-form ref="form" :model="dataForm" label-width="280px">
	            <el-form-item :label="item.type" v-for="item in dataForm.fileList" :key="item.id" >
	              <img :src="item.ylzd1" style="width: 500px;height:300px;border-radius: 4px;" @click="showPic(item.ylzd1)"/>
	            </el-form-item>
	          </el-form>
	
	        </div>
	      </el-col>
	    </el-row>
	      <el-button v-show="dataForm.applystatus == '3'" type="primary" style="margin-left: 30%;margin-top: 30px;width: 140px;" @click="beforeCommit('5X')">通过</el-button>
	      <el-button v-show="dataForm.applystatus == '3'" type="primary" style="width: 140px;" @click="beforeCommit('6')">驳回</el-button>
	    <el-dialog
	    title=""
	    :visible.sync="dialogVisible"
	    width="30%"
	    >
	    <el-form ref="form" :model="dataForm" label-width="80px">
	    <el-form-item :label="dataForm.dialogTitle">
	    	<el-input type="textarea" rows="5" v-model="dataForm.opinion"></el-input>
	    </el-form-item>
	    </el-form>
	    <span slot="footer" class="dialog-footer">
	    	<el-button @click="dialogVisible = false">取 消</el-button>
	    	<el-button type="primary" @click="update()">确 定</el-button>
	    </span>
	    </el-dialog>
			<el-dialog
	      title=""
	      :visible.sync="bigPicVisible"
	      width="55%"
	    >
	      <img :src="bigPicUrl" style="width: 100%;border-radius: 4px;"/>
	      <span slot="footer" class="dialog-footer">
			 	<el-button type="primary" @click="bigPicVisible = false">确 定</el-button>
			 </span>
	    </el-dialog>
	  </div>
	
</template>

<script>
	import { isURL } from '@/utils/validate'

  export default {
    data () {
      return {
        visible: false,
				dialogVisible: false,//通过驳回意见弹出框
				bigPicVisible:false,//图片弹出框
				bigPicUrl:'',//图片弹出框图片url
        dataForm: {
        	id: 0,
					opinion:'',
					dialogTitle:'',
        	baseid: '',
        	boardtime: '',
        	assiststandard: '',
        	assistprice: '',
        	assistcityprice: '',
        	assistareaprice: '',
        	syscalc: '',
        	carfield: '',
        	applystatus: '',
        	applytime: '',
        	orgid: '',
        	applyuserid: '',
					ylzd1: '',
					ylzd2: '',
					ylzd3: '',
					ylzd4: '',
					ylzd5: '',
          saleorgid: '',
          saleorgname: '',
          platenumber: '',
          carprop: '',
          carpropCode: '',
          carcity: '',
          cararea: '',
          carareaCode: '',
          carorg: '',
          cartype: '',
          cartypeCode: '',
          caruse: '',
          caruseCode: '',
          carmodel: '',
          ekgval: '',
          noticebatch: '',
          vin: '',
          applystandard: '',
          pricereal: '',
          price: '',
          receiptnum: '',
          receiptdate: '',
          runcarddate: '',
          runmiles: '',
          chargetime: '',
          chargepower: '',
          totalrunmiles: '',
          monthrunmiles: '',
          powerconsume: '',
          totalchargepower: '',
          dayrunhours: '',
          ismonitor: '',
          ismonitorCode: '',
          monitororg: '',
          locremark: '',
          batterysinglemodel: '',
          batterysingleorg: '',
          batteryboxmodel: '',
          batterypower: '',
          batteryorg: '',
          batteryprice: '',
          batteryqualityyear: '',
          machinemodel: '',
          machinepower: '',
          machineorg: '',
          machineprice: '',
          cylzd1: '',
          cylzd2: '',
          cylzd3: '',
          cylzd4: '',
          cylzd5: '',
					fileList:[],
					file:"",
					status:'',
        },
        dataRule: {
				}
         
    }
		},
		activated () {
			this.init(this.$route.params.id,this.$route.params.baseid)
		},
    methods: {
			 showPic(url) {
				  this.bigPicUrl = url
				  this.bigPicVisible = true
				
			 },
			 update() {
          var opinion = this.dataForm.opinion
          if(opinion == ''){
						this.$message.error( this.dataForm.dialogTitle+'不能为空');
					}else{
						this.dialogVisible = false
						this.$http({
							url: this.$http.adornUrl(`/technologyDb/devapply/updateApplyStatus`),
							method: 'get',
							params: this.$http.adornParams(
							{id:this.dataForm.id,applystatus:this.dataForm.status,orgid:this.dataForm.orgid,opinion:this.dataForm.opinion},
							)
						}).then(({data}) => {
							if (data && data.code === 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										
									}
								})
								this.$router.go(-1)
								this.$emit('closeTab')
							}
						})
					}
       },
       init (id,baseid) {
       	this.dataForm.id = id || 0
				this.dataForm.baseid = baseid || 0
       	this.visible = true
       	this.$nextTick(() => {
       		this.$refs['dataForm'].resetFields()
       		if (this.dataForm.id) {
       			this.$http({
       				url: this.$http.adornUrl(`/technologyDb/devapply/info/${this.dataForm.id}`),
       				method: 'get',
       				params: this.$http.adornParams()
       			}).then(({data}) => {
							console.log(data)
       				if (data && data.code === 0) {
       					this.dataForm.baseid = data.devApply.baseid
       					this.dataForm.boardtime = data.devApply.boardtime
       					this.dataForm.assiststandard = data.devApply.assiststandard
       					this.dataForm.assistprice = data.devApply.assistprice
       					this.dataForm.assistcityprice = data.devApply.assistcityprice
       					this.dataForm.assistareaprice = data.devApply.assistareaprice
       					this.dataForm.syscalc = data.devApply.syscalc
								if(data.devApply.carfield == "1"){
									this.dataForm.carfield = "个人"
								}else if(data.devApply.carfield == "2"){
									this.dataForm.carfield = "单位"
								}
									
       					this.dataForm.applystatus = data.devApply.applystatus
       					this.dataForm.applytime = data.devApply.applytime
       					this.dataForm.orgid = data.devApply.orgid
       					this.dataForm.applyuserid = data.devApply.applyuserid
       					this.dataForm.ylzd1 = data.devApply.ylzd1
       					this.dataForm.ylzd2 = data.devApply.ylzd2
       					this.dataForm.ylzd3 = data.devApply.ylzd3
       					this.dataForm.ylzd4 = data.devApply.ylzd4
       					this.dataForm.ylzd5 = data.devApply.ylzd5
								
       				}
       			})
						this.$http({
							url: this.$http.adornUrl(`/generator/devcarbasedata/info/${this.dataForm.baseid}`),
							method: 'get',
							params: this.$http.adornParams()
						}).then(({data}) => {
							console.log(data)
							if (data && data.code === 0) {
								this.dataForm.saleorgid = data.devCarBasedata.saleorgid
								this.dataForm.saleorgname = data.devCarBasedata.saleorgname
								this.dataForm.platenumber = data.devCarBasedata.platenumber
								this.dataForm.carprop = data.devCarBasedata.carprop
								this.dataForm.carpropCode = data.devCarBasedata.carpropCode
								this.dataForm.carcity = data.devCarBasedata.carcity
								this.dataForm.cararea = data.devCarBasedata.cararea
								this.dataForm.carareaCode = data.devCarBasedata.carareaCode
								this.dataForm.carorg = data.devCarBasedata.carorg
								this.dataForm.cartype = data.devCarBasedata.cartype
								this.dataForm.cartypeCode = data.devCarBasedata.cartypeCode
								this.dataForm.caruse = data.devCarBasedata.caruse
								this.dataForm.caruseCode = data.devCarBasedata.caruseCode
								this.dataForm.carmodel = data.devCarBasedata.carmodel
								this.dataForm.ekgval = data.devCarBasedata.ekgval
								this.dataForm.noticebatch = data.devCarBasedata.noticebatch
								this.dataForm.vin = data.devCarBasedata.vin
								this.dataForm.applystandard = data.devCarBasedata.applystandard
								this.dataForm.pricereal = data.devCarBasedata.pricereal
								this.dataForm.price = data.devCarBasedata.price
								this.dataForm.receiptnum = data.devCarBasedata.receiptnum
								this.dataForm.receiptdate = data.devCarBasedata.receiptdate
								this.dataForm.runcarddate = data.devCarBasedata.runcarddate
								this.dataForm.runmiles = data.devCarBasedata.runmiles
								this.dataForm.chargetime = data.devCarBasedata.chargetime
								this.dataForm.chargepower = data.devCarBasedata.chargepower
								this.dataForm.totalrunmiles = data.devCarBasedata.totalrunmiles
								this.dataForm.monthrunmiles = data.devCarBasedata.monthrunmiles
								this.dataForm.powerconsume = data.devCarBasedata.powerconsume
								this.dataForm.totalchargepower = data.devCarBasedata.totalchargepower
								this.dataForm.dayrunhours = data.devCarBasedata.dayrunhours
								this.dataForm.ismonitor = data.devCarBasedata.ismonitor
								this.dataForm.ismonitorCode = data.devCarBasedata.ismonitorCode
								this.dataForm.monitororg = data.devCarBasedata.monitororg
								this.dataForm.locremark = data.devCarBasedata.locremark
								this.dataForm.batterysinglemodel = data.devCarBasedata.batterysinglemodel
								this.dataForm.batterysingleorg = data.devCarBasedata.batterysingleorg
								this.dataForm.batteryboxmodel = data.devCarBasedata.batteryboxmodel
								this.dataForm.batterypower = data.devCarBasedata.batterypower
								this.dataForm.batteryorg = data.devCarBasedata.batteryorg
								this.dataForm.batteryprice = data.devCarBasedata.batteryprice
								this.dataForm.batteryqualityyear = data.devCarBasedata.batteryqualityyear
								this.dataForm.machinemodel = data.devCarBasedata.machinemodel
								this.dataForm.machinepower = data.devCarBasedata.machinepower
								this.dataForm.machineorg = data.devCarBasedata.machineorg
								this.dataForm.machineprice = data.devCarBasedata.machineprice
								this.dataForm.cylzd1 = data.devCarBasedata.ylzd1
								this.dataForm.cylzd2 = data.devCarBasedata.ylzd2
								this.dataForm.cylzd3 = data.devCarBasedata.ylzd3
								this.dataForm.cylzd4 = data.devCarBasedata.ylzd4
								this.dataForm.cylzd5 = data.devCarBasedata.ylzd5
								this.dataForm.cylzd1 = this.$http.adornUrl('/upload/browserpic/'+this.dataForm.cylzd1+'?token='+this.$cookie.get('token')) 

							}
						})
						
						this.$http({
							url: this.$http.adornUrl(`/technologyDb/devapply/getFileByApplyId`),
							method: 'get',
							params: this.$http.adornParams({id:this.dataForm.id})
						}).then(({data}) => {
							if (data && data.code === 0) {
								this.dataForm.fileList = data.file
								for(var i = 0;i<this.dataForm.fileList.length;i++){
									    this.dataForm.fileList[i].ylzd1 = this.$http.adornUrl('/upload/browserpic/'+this.dataForm.fileList[i].id+'?token='+this.$cookie.get('token')) 
								      console.log(this.dataForm.fileList[i].ylzd1)
								}
								console.log(this.dataForm.fileList)
							}
						})
						
						
       		}
       	})   
      },
			beforeCommit (status){
				this.dataForm.status = status 
				if(status == "5X"){
					this.dataForm.dialogTitle = "同意意见"
				}else if(status == "6"){
					this.dataForm.dialogTitle = "驳回意见"
				}
				this.dialogVisible = true
			
			},
		
		
		}
  }
</script>
