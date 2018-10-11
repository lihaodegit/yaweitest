<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="车牌号或经销商名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('generator:devapply:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('generator:devapply:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
			  v-if="idshow"
        prop="id"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
			  v-if="idshow"
        prop="baseid"
        header-align="center"
        align="center"
        label="车辆基础信息表主键">
      </el-table-column>
			<!-- <el-table-column
				prop="applyuserid"
				header-align="center"
				align="center"
				label="申请用户"
				:formatter="getApplyName"
				width="250"> 
			</el-table-column>-->
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
			  v-if="idshow"
				prop="orgid"
				header-align="center"
				align="center"
				label="经销商单位"
				:formatter="getSellerName"
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
					<el-tag v-if="scope.row.applystatus=='3'" size="small" type="success">市公安局审核通过</el-tag>
				</template>
			</el-table-column>
    <!--  <el-table-column
        prop="boardtime"
        header-align="center"
        align="center"
        label="挂牌时间"
				width="200">
      </el-table-column> -->
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
    <!--  <el-table-column
        prop="syscalc"
        header-align="center"
        align="center"
        label="系统算出指导补助金额（万元辆）" width="200">
      </el-table-column> -->
      <el-table-column
        prop="applytime"
        header-align="center"
        align="center"
        label="申请时间"
				width="200">
      </el-table-column>
      </el-table-column>
      <!-- <el-table-column
        prop="ylzd4"
        header-align="center"
        align="center"
        label="预留字段4">
      </el-table-column>
      <el-table-column
        prop="ylzd5"
        header-align="center"
        align="center"
        label="预留字段5">
      </el-table-column> -->
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="50"
        label="操作">
        <template slot-scope="scope">
					<el-button type="text" size="small" @click="detail(scope.row.id,scope.row.baseid)">详情</el-button>
          <!-- <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button> -->
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
    <!-- 弹窗, 新增 / 修改 -->
		<!-- 详情 -->
  </div>
</template>

<script>
	import detail from './cityfinanceDb-detail'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
				idshow:false,
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
				detailVisible: false
      }
    },
    components: {
			detail
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/cityfinance/devapply/fslist'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key,
						'type':'1'
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
						console.log(data)
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
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
			//详情
			detail (id,baseid) {
				 this.$router.push({path:'/cityfinanceDb-detail',name:"cityfinanceDb-detail",params: {  baseid: baseid,id:id}});
			/* 	this.detailVisible = true
				this.$nextTick(() => {
					this.$refs.detail.init(id)
				}) */
			},
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/cityfinance/devapply/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
			
			
    }
  }
</script>
