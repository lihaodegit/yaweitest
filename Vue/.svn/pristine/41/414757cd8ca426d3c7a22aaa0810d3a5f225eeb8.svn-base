<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类型" prop="dictType">
      <el-input v-model="dataForm.dictType" placeholder="类型"></el-input>
    </el-form-item>
    <el-form-item label="代码" prop="itemValue">
      <el-input v-model="dataForm.itemValue" placeholder="代码"></el-input>
    </el-form-item>
    <el-form-item label="名称" prop="itemName">
      <el-input v-model="dataForm.itemName" placeholder="名称"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="note">
      <el-input v-model="dataForm.note" placeholder="备注"></el-input>
    </el-form-item>
	<el-form-item label="序号" prop="seq">
      <el-input v-model="dataForm.seq" placeholder="序号"></el-input>
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
          dictId: 0,
          dictType: '',
          itemValue: '',
          itemName: '',
          note: '',
          seq: ''
        },
        dataRule: {
          dictType: [
            { required: true, message: '类型不能为空', trigger: 'blur' }
          ],
          itemValue: [
            { required: true, message: '代码不能为空', trigger: 'blur' }
          ],
          itemName: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
		  seq: [
            { required: true, message: '序号不能为空', trigger: 'blur' }
          ]
		}
      }
    },
    methods: {
      init (id) {
        this.dataForm.dictId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.dictId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/sysdict/info/${this.dataForm.dictId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.dictType = data.sysDict.dictType
                this.dataForm.itemValue = data.sysDict.itemValue
                this.dataForm.itemName = data.sysDict.itemName
                this.dataForm.note = data.sysDict.note
				this.dataForm.seq = data.sysDict.seq
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
              url: this.$http.adornUrl(`/generator/sysdict/${!this.dataForm.dictId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'dictId': this.dataForm.dictId || undefined,
                'dictType': this.dataForm.dictType,
                'itemValue': this.dataForm.itemValue,
                'itemName': this.dataForm.itemName,
                'note': this.dataForm.note,
				'seq': this.dataForm.seq,
              })
            }).then(({data}) => {
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
