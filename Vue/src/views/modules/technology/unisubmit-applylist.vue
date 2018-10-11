<template>
  <div class="mod-config">    
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;">
      <!--
	  <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>	  
      <el-table-column			  
        prop="id"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="baseid"
        header-align="center"
        align="center"
        label="车辆基础信息表主键">
      </el-table-column>
	-->  
	<el-table-column
		prop="platenumber"
		header-align="center"
		align="center"
		label="车牌号"
		width="200">
		</el-table-column>
		<el-table-column
			prop="org_name"
			header-align="center"
			align="center"
			label="经销商单位"
			width="200">
			</el-table-column>
	<el-table-column
		prop="applystatus"
		header-align="center"
		align="center"
		label="申请状态"
		width="200"
		>
		<template slot-scope="scope">
			<el-tag v-if="scope.row.applystatus=='1'" size="small" type="success">市科技局初审通过</el-tag>
			<el-tag v-if="scope.row.applystatus=='1X'" size="small" type="success">市科技局初审通过(待提交)</el-tag>
			<el-tag v-if="scope.row.applystatus=='2'" size="small" type="success">市科技局初审驳回</el-tag>
			<el-tag v-if="scope.row.applystatus=='3'" size="small" type="success">市公安局审核通过</el-tag>
			<el-tag v-if="scope.row.applystatus=='3X'" size="small" type="success">市公安局审核通过(待提交)</el-tag>
			<el-tag v-if="scope.row.applystatus=='4'" size="small" type="success">市公安局审核驳回</el-tag>
			<el-tag v-if="scope.row.applystatus=='5X'" size="small" type="success">市财政局初审通过(待提交) </el-tag>
			<el-tag v-if="scope.row.applystatus=='5'" size="small" type="success">市财政局初审通过</el-tag>
			<el-tag v-if="scope.row.applystatus=='6'" size="small" type="success">市财政局审核驳回</el-tag>
			<el-tag v-if="scope.row.applystatus=='7X'" size="small" type="success">区财政局初审通过</el-tag>
			<el-tag v-if="scope.row.applystatus=='7'" size="small" type="success">区财政局初审提交</el-tag>
			<el-tag v-if="scope.row.applystatus=='8'" size="small" type="success">区财政局审核驳回</el-tag>
			<el-tag v-if="scope.row.applystatus=='9'" size="small" type="success">市科技局复审提交</el-tag>
			<el-tag v-if="scope.row.applystatus=='9X'" size="small" type="success">市科技局复审通过</el-tag>
			<el-tag v-if="scope.row.applystatus=='10'" size="small" type="success">市科技局复审驳回</el-tag>
			<el-tag v-if="scope.row.applystatus=='11X'" size="small" type="success">市财政局复审通过</el-tag>
			<el-tag v-if="scope.row.applystatus=='11'" size="small" type="success">市财政局复审提交</el-tag>
			<el-tag v-if="scope.row.applystatus=='12'" size="small" type="success">市财政局复审驳回</el-tag>
			<el-tag v-if="scope.row.applystatus=='13'" size="small" type="success">区财政局付款确认</el-tag>
			<el-tag v-if="scope.row.applystatus=='14'" size="small" type="success">经销商收到钱确认</el-tag>
			<el-tag v-if="scope.row.applystatus=='15'" size="small" type="success">市科技局确认驳回</el-tag>
			
		</template>
	</el-table-column>    
      <el-table-column
        prop="assiststandard"
        header-align="center"
        align="center"
        label="补助标准(万元辆)"
				width="200">
      </el-table-column>
      <el-table-column
        prop="assistprice"
        header-align="center"
        align="center"
        label="补助资金数额（万元辆）" width="200">
      </el-table-column>
      <el-table-column
        prop="assistcityprice"
        header-align="center"
        align="center"
        label="市级财政补助资金数额（万元辆）" width="200">
      </el-table-column>
      <el-table-column
        prop="assistareaprice"
        header-align="center"
        align="center"
        label="区级财政补助资金数额（万元辆）" width="200">
      </el-table-column>
      <el-table-column
        prop="applytime"
        header-align="center"
        align="center"
        label="申请时间"
				width="200">
      </el-table-column>
      </el-table-column>      
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="50"
        label="操作">
        <template slot-scope="scope">
			<el-button type="text" size="small" @click="detail(scope.row.id,scope.row.baseid)">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
  </div>
</template>

<script>
  export default {
    data () {
      return {        
		dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
		type:this.$route.params.type,
		flag:this.$route.params.flag
      }
    },
    activated () {
      this.getDataList()
    },
    methods: {		
		// 获取数据列表
		getDataList () {
			console.log(this.$route.params.type)
			console.log(this.$route.params.flag)
			this.dataListLoading = true
			this.$http({
				url: this.$http.adornUrl('/technologyDb/unisubmit/applylist'),
				method: 'get',
				params: this.$http.adornParams({
				'page': this.pageIndex,
				'limit': this.pageSize,
				'type': this.type,
				'flag': this.flag
			})
			}).then(({data}) => {
				if (data && data.code === 0) {
				this.dataList = data.page.records
				this.totalPage = data.page.total
				} else {
				this.dataList = []
				this.totalPage = 0
				}
				this.dataListLoading = false
			})
		},
		// 每页数
		sizeChangeHandle (val) {
			this.pageSize = val
			this.pageIndex = 1
			this.getDataList()
		},
		// 当前页
		currentChangeHandle (val) {
			this.pageIndex = val
			this.getDataList()
		},      
		//详情
		detail (id,baseid) {
			 this.$router.push({path:'/technologyFs-detail',name:"technologyFs-detail",params: {  baseid: baseid,id:id}});
		},
		getSellerName (row, column, cellValue, index){
			 var name = "";
			 var token = this.$cookie.get('token')			 
			 return name
		}
    }
  }
</script>
