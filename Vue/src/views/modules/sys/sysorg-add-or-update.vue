<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="单位名称" prop="orgName">
      <el-input v-model="dataForm.orgName" placeholder="单位名称"></el-input>
    </el-form-item>
    <el-form-item label="单位代码" prop="orgCode">
      <el-input v-model="dataForm.orgCode" placeholder="单位代码"></el-input>
    </el-form-item>
   <!-- <el-form-item label="创建人id" prop="createUserId">
      <el-input v-model="dataForm.createUserId" placeholder="创建人id"></el-input>
    </el-form-item> -->
   <!-- <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item> -->
   <!-- <el-form-item label="预留字段1" prop="ylzd1">
      <el-input v-model="dataForm.ylzd1" placeholder="预留字段1"></el-input>
    </el-form-item>
    <el-form-item label="预留字段2" prop="ylzd2">
      <el-input v-model="dataForm.ylzd2" placeholder="预留字段2"></el-input>
    </el-form-item>
    <el-form-item label="预留字段3" prop="ylzd3">
      <el-input v-model="dataForm.ylzd3" placeholder="预留字段3"></el-input>
    </el-form-item> -->
    <el-form-item label="单位地址 " prop="addr">
      <el-input v-model="dataForm.addr" placeholder="单位地址 "></el-input>
    </el-form-item>
    <el-form-item label="单位类型" prop="type">
			<el-select v-model="dataForm.type" placeholder="单位类型">
      <el-option label="车企" value="1"></el-option>
      <el-option label="销售商" value="2"></el-option>
			<el-option label="市科技局" value="3"></el-option>
			<el-option label="市公安局" value="4"></el-option>
			<el-option label="市财政局" value="5"></el-option>
			<el-option label="区财政局 " value="6"></el-option>
    </el-select>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
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
          orgId: 0,
          orgName: '',
          orgCode: '',
          createUserId: '',
          createTime: '',
          ylzd1: '',
          ylzd2: '',
          ylzd3: '',
          addr: '',
          type: ''
        },
        dataRule: {
          orgName: [
            { required: true, message: '单位名称不能为空', trigger: 'blur' }
          ],
          orgCode: [
            { required: false, message: '单位代码不能为空', trigger: 'blur' }
          ],
          createUserId: [
            { required: true, message: '创建人id不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          ylzd1: [
            { required: true, message: '预留字段1不能为空', trigger: 'blur' }
          ],
          ylzd2: [
            { required: true, message: '预留字段2不能为空', trigger: 'blur' }
          ],
          ylzd3: [
            { required: true, message: '预留字段3不能为空', trigger: 'blur' }
          ],
          addr: [
            { required: true, message: '单位地址 不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '单位类型', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.orgId = id || 0;
        this.visible = true;
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields();
          if (this.dataForm.orgId) {
            this.$http({
              url: this.$http.adornUrl(`/sys/sysorg/info/${this.dataForm.orgId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orgName = data.sysOrg.orgName;
                this.dataForm.orgCode = data.sysOrg.orgCode;
                this.dataForm.createUserId = data.sysOrg.createUserId;
                this.dataForm.createTime = data.sysOrg.createTime;
                this.dataForm.ylzd1 = data.sysOrg.ylzd1;
                this.dataForm.ylzd2 = data.sysOrg.ylzd2;
                this.dataForm.ylzd3 = data.sysOrg.ylzd3;
                this.dataForm.addr = data.sysOrg.addr;
                this.dataForm.type = data.sysOrg.type
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/sys/sysorg/${!this.dataForm.orgId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'orgId': this.dataForm.orgId || undefined,
                'orgName': this.dataForm.orgName,
                'orgCode': this.dataForm.orgCode,
                'createUserId': this.dataForm.createUserId,
                'createTime': this.dataForm.createTime,
                'ylzd1': this.dataForm.ylzd1,
                'ylzd2': this.dataForm.ylzd2,
                'ylzd3': this.dataForm.ylzd3,
                'addr': this.dataForm.addr,
                'type': this.dataForm.type
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false;
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
