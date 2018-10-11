<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="销售机构名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('generator:devcarbasedata:save')" type="primary" @click="addOrUpdateHandle()">导入</el-button>
        <el-button v-if="isAuth('generator:devcarbasedata:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
      <!-- <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="主键">
      </el-table-column> -->
     <!-- <el-table-column
        prop="saleorgid"
        header-align="center"
        align="center"
        label="销售机构id">
      </el-table-column> -->
      <el-table-column
        prop="saleorgname"
        header-align="center"
        align="center"
        label="销售机构名称"
				width="200">
      </el-table-column>
      <el-table-column
        prop="platenumber"
        header-align="center"
        align="center"
        label="车牌号"
				width="100">
      </el-table-column>
      <el-table-column
        prop="carprop"
        header-align="center"
        align="center"
        label="车辆性质">
      </el-table-column>
      <el-table-column
        prop="carcity"
        header-align="center"
        align="center"
        label="购车城市">
      </el-table-column>
      <el-table-column
        prop="cararea"
        header-align="center"
        align="center"
        label="车辆区市信息"
				width="120">
      </el-table-column>
      <el-table-column
        prop="carorg"
        header-align="center"
        align="center"
        label="车辆运行单位"
				width="250">
      </el-table-column>
      <el-table-column
        prop="cartype"
        header-align="center"
        align="center"
        label="车辆种类">
      </el-table-column>
      <el-table-column
        prop="caruse"
        header-align="center"
        align="center"
        label="车辆用途">
      </el-table-column>
      <el-table-column
        prop="carmodel"
        header-align="center"
        align="center"
        label="车辆型号"
				width="100">
      </el-table-column>
      <el-table-column
        prop="ekgval"
        header-align="center"
        align="center"
        label="Ekg值">
      </el-table-column>
      <el-table-column
        prop="noticebatch"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="vin"
        header-align="center"
        align="center"
        label="车辆识别代码"
				width="200">
      </el-table-column>
      <el-table-column
        prop="applystandard"
        header-align="center"
        align="center"
        label="申请补助标准">
      </el-table-column>
      <el-table-column
        prop="pricereal"
        header-align="center"
        align="center"
        label="购买价格">
      </el-table-column>
      <el-table-column
        prop="price"
        header-align="center"
        align="center"
        label="车辆售价">
      </el-table-column>
      <el-table-column
        prop="receiptnum"
        header-align="center"
        align="center"
        label="发票号">
      </el-table-column>
      <el-table-column
        prop="receiptdate"
        header-align="center"
        align="center"
        label="发票日期"
				width="160">
      </el-table-column>
      <el-table-column
        prop="runcarddate"
        header-align="center"
        align="center"
        label="行驶证时间"
				width="160">
      </el-table-column>
      <el-table-column
        prop="runmiles"
        header-align="center"
        align="center"
        label="一次充满电车辆行驶里程">
      </el-table-column>
      <el-table-column
        prop="chargetime"
        header-align="center"
        align="center"
        label="车辆一次充满电所需时间（h）">
      </el-table-column>
      <el-table-column
        prop="chargepower"
        header-align="center"
        align="center"
        label="最大充电功率（Kw）">
      </el-table-column>
      <el-table-column
        prop="totalrunmiles"
        header-align="center"
        align="center"
        label="累计行驶里程（km）">
      </el-table-column>
      <el-table-column
        prop="monthrunmiles"
        header-align="center"
        align="center"
        label="月均行驶里程（Km）">
      </el-table-column>
      <el-table-column
        prop="powerconsume"
        header-align="center"
        align="center"
        label="百公里耗电量（kWh/100km）">
      </el-table-column>
      <el-table-column
        prop="locremark"
        header-align="center"
        align="center"
        label="明细附件中位置的说明备注">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="100"
        label="操作">
        <template slot-scope="scope">
          <!-- <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button> -->
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
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
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './devcarbasedata-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/generator/devcarbasedata/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
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
				this.$http({
					url: this.$http.adornUrl('/generator/devcarbasedata/getCarApplyStatus'),
					method: 'post'
				}).then(({data}) => {
					if (data && data.code === 0) {
					    this.addOrUpdateVisible = true
					    this.$nextTick(() => {
					    	this.$refs.addOrUpdate.init(id)
					    })
					} else {
						this.$message.error(data.msg)
					}
				})
       
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定进行删除操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/generator/devcarbasedata/delete'),
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
