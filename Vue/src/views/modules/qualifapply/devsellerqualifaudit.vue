<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="企业名称" clearable></el-input>
      </el-form-item>
	  <el-form-item>
		<el-select v-model="dataForm.qualifiApplyStatus" placeholder="资质申请状态" @change="getDataList()">
			<el-option
				value=""
				label="待审核">				
			</el-option>
			<el-option
				value="1"
				label="已通过">				
			</el-option>
			<el-option
				value="0"
				label="已驳回">				
			</el-option>
		</el-select>
	  </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
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
	  <!--
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="主键">
      </el-table-column>	  
      <el-table-column
        prop="orgid"
        header-align="center"
        align="center"
        label="组织机构ID">
      </el-table-column>
	  -->
      <el-table-column
        prop="entname"
        header-align="center"
        align="center"
        label="企业名称">
      </el-table-column>
      
	  <el-table-column
        prop="princ"
        header-align="center"
        align="center"
        label="经销商负责人">
      </el-table-column>  
      <el-table-column
        prop="mobile"
        header-align="center"
        align="center"
        label="电话">
      </el-table-column>
      <el-table-column
        prop="email"
        header-align="center"
        align="center"
        label="邮箱">
      </el-table-column>
      <el-table-column
        prop="dom"
        header-align="center"
        align="center"
        label="公司地址">
      </el-table-column>	 
      <el-table-column
        prop="applytime"
        header-align="center"
        align="center"
        label="申请时间">
      </el-table-column>
	   <!--
      <el-table-column
        prop="audittime"
        header-align="center"
        align="center"
        label="审核时间">	
      </el-table-column>
      <el-table-column
        prop="auditoption"
        header-align="center"
        align="center"
        label="审核意见">
      </el-table-column>
      <el-table-column
        prop="auditresult"
        header-align="center"
        align="center"
        label="审核结果">
      </el-table-column>
      <el-table-column
        prop="auditer"
        header-align="center"
        align="center"
        label="审核人">
      </el-table-column>
      <el-table-column
        prop="ylzd1"
        header-align="center"
        align="center"
        label="预留字段1">
      </el-table-column>
      <el-table-column
        prop="ylzd2"
        header-align="center"
        align="center"
        label="预留字段2">
      </el-table-column>
      <el-table-column
        prop="ylzd3"
        header-align="center"
        align="center"
        label="预留字段3">
      </el-table-column>
	  -->
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
		  <el-button type="text" size="small" @click="detailHandle(scope.row.id)">查看</el-button>
          <el-button type="text" v-if="scope.row.auditresult!='1'&&scope.row.auditresult!='0'" size="small" @click="passHandle(scope.row.id)">通过</el-button>
		  <el-button type="text" v-if="scope.row.auditresult!='1'&&scope.row.auditresult!='0'" size="small" @click="rejectHandle(scope.row.id)">驳回</el-button>
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
	<view-detail v-if="viewDetailVisible" ref="viewDetail" @refreshDataList="getDataList"></view-detail>
	<div>
		<el-dialog
			:visible="dlgVisible">
			<el-form>
				<el-form-item label="驳回意见">
					<el-input 
					placeholder="请输入驳回意见"
					v-model="auditoption">
					</el-input>					
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dlgVisible = false">取 消</el-button>
				<el-button type="primary" @click="closeDlg">提 交</el-button>
			</div>
		</el-dialog>
	</div>
  </div>
</template>

<script>
  import ViewDetail from './devsellerqualifapply-view'
  export default {
    data () {
      return {
        dataForm: {
          key: '',
		  qualifiApplyStatus: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
		viewDetailVisible: false,
		dlgVisible: false,		
		auditoption: '',
		checkid: ''
      }
    },
    components: {
	  ViewDetail
    },
    activated () {
      this.getDataList()
    },
    methods: {
	  //关闭对话框
	  closeDlg(){
		if(this.auditoption==''){
			this.$message({
					message: '请填写驳回意见！',
					type: 'success',
					duration: 1500
				  })
			return;
		}
		this.dlgVisible= false;
		var ids = [{"id":this.checkid,"auditoption":this.auditoption}];		
		//提交
		this.$http({
			url: this.$http.adornUrl('/technology/audit/sellerqualifireject'),
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
	  },	
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/technology/audit/sellerqualifilist'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key,
			'auditresult':this.dataForm.qualifiApplyStatus
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
	  //查看
	  detailHandle(id){
		this.viewDetailVisible = true
		this.$nextTick(() => {
		  this.$refs.viewDetail.init(id)
		})
	  },
      // 通过
      passHandle (id) {
        /*
		var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
		*/
		var ids = [{"id":id,"auditoption":''}];
        this.$confirm(`确定通过当前企业申请?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/technology/audit/sellerqualifipass'),
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
      },
	  //驳回
	  rejectHandle (id) {
		/*
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
		*/
        this.$confirm(`确定驳回当前企申请?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
			this.checkid = id;
			this.auditoption = '';
			this.dlgVisible = true;			
        })
      }
    }
  }
</script>
