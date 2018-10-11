<template>
  <div class="mod-sysorg">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input  v-model="dataForm.key" placeholder="单位名称" clearable></el-input>
      </el-form-item>
			<el-form-item label="单位类型" prop="type">
				<el-select  v-model="dataForm.type" placeholder="单位类型">
				<el-option label="车企" value="1"></el-option>
				<el-option label="销售商" value="2"></el-option>
				<el-option label="市科技局" value="3"></el-option>
				<el-option label="市公安局" value="4"></el-option>
				<el-option label="市财政局" value="5"></el-option>
				<el-option label="区财政局 " value="6"></el-option>
			</el-select>
			</el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button  v-if="isAuth('generator:sysorg:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('generator:sysorg:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
        prop="orgId"
        header-align="center"
        align="center"
        label="单位id">
      </el-table-column> -->
      <el-table-column
        prop="orgName"
        header-align="center"
        align="center"
        label="单位名称"
				width="280">
      </el-table-column>
      <el-table-column
        prop="orgCode"
        header-align="center"
        align="center"
        label="单位代码">
      </el-table-column>
   <!--   <el-table-column
        prop="createUserId"
        header-align="center"
        align="center"
        label="创建人id">
      </el-table-column> -->
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间"
				width="160">
      </el-table-column>
      <el-table-column
        prop="addr"
        header-align="center"
        align="center"
        label="单位地址 "
				width="200">
      </el-table-column>
      <el-table-column
        prop="type"
        header-align="center"
        align="center"
        label="单位类型"
				:formatter="formatterType">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.orgId)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.orgId)">删除</el-button>
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
  import AddOrUpdate from './sysorg-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: '',
					type:''
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
      formatterType (row, column) {
			  switch (row.type) {
				  case '1':
					return '车企';
					break;
					case '2':
					return '销售商';
					break;
					case '3':
					return '市科技局';
					break;
					case '4':
					return '市公安局';
					case '5':
					return '市财政局';
					case '6':
					return '区财政局';
					break;
					default:
					return '未知';
				}
			},
      getDataList () {
        this.dataListLoading = true;
        this.$http({
          url: this.$http.adornUrl('/sys/sysorg/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'orgName': this.dataForm.key,
						'type':this.dataForm.type
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list;
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = [];
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val;
        this.pageIndex = 1;
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val;
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true;
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.orgId
        });
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/sys/sysorg/delete'),
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
