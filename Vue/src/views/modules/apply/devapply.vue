<template>

  <el-container>
    <el-header>
      <el-steps :active="stepIndex"  simple finish-status="success">
        <el-step title="车辆选择"></el-step>
        <el-step title="信息确认"></el-step>
        <el-step title="在线申报"></el-step>
      </el-steps>
    </el-header>
    <el-main>
      <el-row v-show="stepIndex==1">
      <el-col :span="24">
        <!--<div class="mod-config">-->
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
          <el-form-item>
            <el-input v-model="dataForm.key" placeholder="车牌号" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="getDataList()">查询</el-button>

          </el-form-item>
        </el-form>
        <el-table
          :data="dataList"
          border
          v-loading="dataListLoading"
          highlight-current-row
          @current-change="handleCurrentChange"
          style="width: 100%;">
          <el-table-column
            type="index"
            label="序号"
            width="50">
          </el-table-column>
          <el-table-column
            prop="platenumber"
            header-align="center"
            align="center"
            width="100"
            label="车牌号">
          </el-table-column>
          <el-table-column
            prop="carprop"
            header-align="center"
            align="center"
            width="100"
            label="车辆性质">
          </el-table-column>
          <el-table-column
            prop="cartype"
            header-align="center"
            align="center"
            width="100"
            label="车辆种类">
          </el-table-column>
          <el-table-column
            prop="caruse"
            header-align="center"
            align="center"
            label="车辆用途">
          </el-table-column>
          <el-table-column
            prop="cararea"
            header-align="center"
            align="center"
            label="所属区市">
          </el-table-column>
          <el-table-column
            prop="totalrunmiles"
            header-align="center"
            align="center"
            width="120"
            label="累计行驶里程">
          </el-table-column>
          <el-table-column
            prop="carorg"
            header-align="center"
            align="center"
            width="250"
            label="车辆运行单位">
          </el-table-column>

          <el-table-column
            prop="carmodel"
            header-align="center"
            align="center"
            width="140"
            label="车辆型号">
          </el-table-column>
          <el-table-column
            prop="vin"
            header-align="center"
            align="center"
            width="120"
            label="车辆识别代码">
          </el-table-column>
          <el-table-column
            prop="applystandard"
            header-align="center"
            align="center"
            width="110"
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
            width="100"
            label="车辆出厂价">
          </el-table-column>
          <el-table-column
            prop="batterypower"
            header-align="center"
            align="center"
            width="170"
            label="电池组总能量（KWh）">
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[5,10]"
          :page-size="pageSize"
          :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
        <el-row>
          <el-col :span="24"><div class="grid-content bg-purple-dark" style="text-align: center;">
            <transition name="el-zoom-in-top" style="margin:auto;">
              <el-button v-if="currCarBaseId!=null" type="primary" @click="toInfoConfirm()" style="width:200px;">
                选中车辆，开始申请
              </el-button>
            </transition>
          </div></el-col>
        </el-row>
        <!--</div>-->
      </el-col>
    </el-row>
      <el-row v-show="stepIndex==2">
        <el-row v-if="currentRow!=null">
          <el-col :span="6">
            <el-card :body-style="{ padding: '0px' }">
              <img src="~@/assets/img/car_logo.jpg" class="image">
              <!--<div style="padding: 14px;">-->
                <!--<span>好吃的汉堡</span>-->
                <!--<span>好吃的汉堡</span>-->
                <!--<div class="bottom clearfix">-->
                  <!--<time class="time"></time>-->
                  <!--<el-button type="text" class="button">操作按钮</el-button>-->
                <!--</div>-->
              <!--</div>-->
            </el-card>
          </el-col>
          <el-col :span="2"></el-col>
          <el-col :span="18">
            <el-card class="box-card" style="margin-left:10px;">
              <el-row>
                <el-form :model="currentRow" label-width="110px">
                  <el-col :span="12">
                    <el-form-item label="车牌号" size="small">
                      <el-input v-model="currentRow.platenumber" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="车辆性质" size="small">
                      <el-input v-model="currentRow.carprop" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="购车城市" size="small">
                      <el-input v-model="currentRow.carcity" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="购车区域" size="small">
                      <el-input v-model="currentRow.cararea" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="车辆种类" size="small">
                      <el-input v-model="currentRow.cartype" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="车辆用途" size="small">
                      <el-input v-model="currentRow.caruse" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="运行单位" size="small">
                      <el-input v-model="currentRow.carorg" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="车辆型号" size="small">
                      <el-input v-model="currentRow.carmodel" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="车辆别代码" size="small">
                      <el-input v-model="currentRow.vin" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="申请补助标准" size="small">
                      <el-input v-model="currentRow.applystandard" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="车辆售价" size="small">
                      <el-input v-model="currentRow.price" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="车辆购买价格" size="small">
                      <el-input v-model="currentRow.pricereal" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="行驶证时间" size="small">
                      <el-date-picker disabled style="width:100%"
                                      v-model="currentRow.runcarddate"
                                      type="date"
                                      placeholder="">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="发票日期" size="small">
                      <el-date-picker disabled style="width:100%"
                        v-model="currentRow.receiptdate"
                        type="date"
                        placeholder="">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="累计行驶里程" size="small">
                      <el-input v-model="currentRow.totalrunmiles" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="月均行驶里程" size="small">
                      <el-input v-model="currentRow.monthrunmiles" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="充满电所需时间" size="small">
                      <el-input v-model="currentRow.chargetime" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                  <el-form-item label="充满电行驶里程" size="small">
                    <el-input v-model="currentRow.runmiles" disabled></el-input>
                  </el-form-item>
                </el-col>
                  <el-col :span="12">
                    <el-form-item label="总充电量" size="small">
                      <el-input v-model="currentRow.totalchargepower" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="百公里耗电量" size="small">
                      <el-input v-model="currentRow.powerconsume" disabled></el-input>
                    </el-form-item>
                  </el-col>
                </el-form>
              </el-row>
            </el-card>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <div class="grid-content bg-purple-dark" style="text-align: center;">
            <div  style="margin:auto;margin-top:8px;">
              <el-button type="primary" @click="toBackSelect()">返回上一步</el-button>
              <el-button type="primary" @click="toApply()">确认信息，开始申请</el-button>
              <!--<el-button type="primary"  @click="toBackSelect();" style="width:200px;" >返回上一步</el-button>-->
              <!--<el-button  type="primary"  @click="toApply();" style="width:200px;" >确认信息，开始申请</el-button>-->
            </div>
          </div></el-col>
        </el-row>
      </el-row>
      <el-row v-show="stepIndex==3">
        <el-col :span="24">
          <el-card class="box-card" style="margin-left:10px;">
              <el-form :model="applyForm"  :rules="applyFormRule"  ref="applyForm"  label-width="180px">
                <el-col :span="12">
                  <el-form-item label="补助标准(万元)" style="margin-bottom:20px;">
                    <el-input-number v-model="applyForm.assiststandard" :precision="2" :step="0.1" >
                    </el-input-number>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="补助资金数额(万元)" style="margin-bottom:20px;">
                    <el-input-number v-model="applyForm.assistprice"  :precision="2" :step="0.1" >
                    </el-input-number>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="市级财政补助数额（万元）" style="margin-bottom:20px;">
                    <el-input-number v-model="applyForm.assistcityprice" :precision="2" :step="0.1" >
                    </el-input-number>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="区级财政补助数额（万元）" style="margin-bottom:20px;">
                    <el-input-number v-model="applyForm.assistareaprice"  :precision="2" :step="0.1" >
                    </el-input-number>
                  </el-form-item>
                </el-col>
                <el-col :span="21">
                  <el-form-item label="挂牌时间" style="margin-bottom:20px;" prop="boardtime">
                    <el-date-picker
                      v-model="applyForm.boardtime"
                      type="date" placeholder="选择日期">
                    </el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item label="购车领域" style="margin-bottom:20px;">
                    <el-radio-group v-model="applyForm.carfield">
                     <el-radio-button label="1">个人</el-radio-button>
                     <el-radio-button label="2">单位</el-radio-button>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <div v-show="applyForm.carfield==1">
                  <el-col :span="24">
                    <el-form-item label="身份证或暂住证" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=3
                        :action="uploadUrl_gr_sfz"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleGrSfz"
                        :file-list="fileListGrSfz"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多3张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="机动车登记证书" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=2
                        :action="uploadUrl_gr_jdcdj"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleGrJdcdj"
                        :file-list="fileListGrJdcdj"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多2张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="机动车行驶证" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=2
                        :action="uploadUrl_gr_xsz"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleGrXsz"
                        :file-list="fileListGrXsz"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多2张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="车辆买卖合同" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=2
                        :action="uploadUrl_gr_clmmht"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleGrClmmht"
                        :file-list="fileListGrClmmht"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多2张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="车辆销售发票" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=2
                        :action="uploadUrl_gr_xsfp"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleGrXsfp"
                        :file-list="fileListGrXsfp"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多2张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                </div>
                <div v-show="applyForm.carfield==2">
                  <el-col :span="24">
                    <el-form-item label="法定代表身份证明" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=3
                        :action="uploadUrl_dw_fddbsfzm"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleDwFddbsfzm"
                        :file-list="fileListDwFddbsfzm"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多3张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="单位三证合一扫描件" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=3
                        :action="uploadUrl_dw_szhy"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleDwSzhy"
                        :file-list="fileListDwSzhy"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多3张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="机动车行驶证" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=2
                        :action="uploadUrl_dw_xsz"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleDwXsz"
                        :file-list="fileListDwXsz"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多2张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="车辆买卖合同" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=2
                        :action="uploadUrl_dw_clmmht"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleDwClmmht"
                        :file-list="fileListDwClmmht"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多2张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="车辆销售发票" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=2
                        :action="uploadUrl_dw_xsfp"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleDwXsfp"
                        :file-list="fileListDwXsfp"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多2张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="拨款依据附件" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=2
                        :action="uploadUrl_dw_bkyj"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleDwBkyj"
                        :file-list="fileListDwBkyj"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多2张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="其它附件" style="width:800px;">
                      <el-upload
                        multiple
                        :limit=2
                        :action="uploadUrl_dw_qtfj"
                        :before-upload="beforeUploadHandle"
                        :on-success="successHandleDwQtfj"
                        :file-list="fileListDwQtfj"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        list-type="picture-card">
                        <i class="el-icon-plus"></i>
                        <div class="el-upload__tip" slot="tip">只支持jpg、png格式的图片(最多2张)</div>
                      </el-upload>
                    </el-form-item>
                  </el-col>
                </div>
              </el-form>
          </el-card>
        </el-col>
          <el-col :span="24">
            <div class="grid-content bg-purple-dark" style="text-align: center;">
              <div  style="margin:auto;margin-top:8px;">
                <el-button type="primary" @click="toBackConfirm()">返回上一步</el-button>
                <el-button type="primary" @click="saveApply()">提交申请</el-button>
              </div>
            </div>
          </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<style>
  .el-input.is-disabled .el-input__inner{background-color:white;}
  .el-form-item{margin-bottom:4px;}
  .el-date-editor.el-input, .el-date-editor.el-input__inner{}
  .el-card__body{padding:7px 20px}
  .el-main{padding:0px 20px}
  .el-upload-list--picture-card .el-upload-list__item{float:left;}
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
<script>
  import { getUUID } from '@/utils'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        stepIndex: 1,
        dataList: [],
        pageIndex: 1,
        pageSize: 5,
        totalPage: 0,
        dataListLoading: false,
        currentRow: null,
        currCarBaseId:null,
        applyForm:{
          id:getUUID(),  //获取主键
          baseid:'',
          boardtime:'',
          assiststandard:'',
          assistprice:'',
          assistcityprice:'',
          assistareaprice:'',
          syscalc:'',
          carfield:'1',
          applystatus:'0'
        },
        applyFormRule:{
          boardtime: [
            { required: true, message: '挂牌时间不能为空', trigger: 'blur' }
          ]
        },
        ifCanSubmit:true,
        //个人附件上传数组
        fileListGrSfz: [],  //个人身份证或暂住证
        fileListGrJdcdj: [],  //个人机动车登记
        fileListGrXsz: [],  //个人行驶证
        fileListGrClmmht: [],  //个人车辆买卖合同
        fileListGrXsfp: [],  //个人销售发票原件
        //个人附件上传url
        uploadUrl_gr_sfz:'',//个人身份证或暂住证
        uploadUrl_gr_jdcdj:'',//个人机动车登记
        uploadUrl_gr_xsz:'',//个人行驶证
        uploadUrl_gr_clmmht:'',//个人车辆买卖合同
        uploadUrl_gr_xsfp:'',//个人车辆买卖合同
        //单位附件上传数组
        fileListDwFddbsfzm: [],  //单位法定代表身份证明
        fileListDwSzhy: [],  //单位三证合一扫描件
        fileListDwXsz: [],  //单位行驶证
        fileListDwClmmht: [],  //单位车辆买卖合同
        fileListDwXsfp: [],  //单位销售发票原件
        fileListDwBkyj: [],  //单位提供拨款依据附件（客车有）
        fileListDwQtfj: [],  //单位其他附件
        //单位附件上传url
        uploadUrl_dw_fddbsfzm:'',//单位法定代表身份证明
        uploadUrl_dw_szhy:'', //单位三证合一扫描件
        uploadUrl_dw_xsz:'',//单位行驶证
        uploadUrl_dw_clmmht:'',//单位车辆买卖合同
        uploadUrl_dw_xsfp:'',//单位销售发票
        uploadUrl_dw_bkyj:'',//单位提供拨款依据附件（客车有）
        uploadUrl_dw_qtfj:''//单位其他附件
      }
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true;
        this.currentRow=null;
        this.currCarBaseId=null;
        this.$http({
          url: this.$http.adornUrl('/apply/devapply/carbaselist'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
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
      // 单选
      handleCurrentChange(val) {
        this.currentRow = val;
        this.currCarBaseId=val.id
      },
      //选中进入信息确认页面，前提是完成资质申请了
      toInfoConfirm(){
//        console.log(this.currentRow);
        this.$http({
          url: this.$http.adornUrl('/apply/devapply/getSellerApplyStatus'),
          method: 'post'
        }).then(({data}) => {
          if (data && data.code === 0) {
          this.stepIndex=2
        } else {
          this.$message.error(data.msg)
        }
      })

      },
      //返回选择页面
      toBackSelect(){
        this.stepIndex=1;
        this.getDataList();
        this.resetField()
      },
      //进入申请页面
      toApply(){
        this.stepIndex=3;
        this.applyForm.baseid=this.currCarBaseId;
        //个人附件上传url
        this.uploadUrl_gr_sfz= this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=21`);//个人身份或暂住证
        this.uploadUrl_gr_jdcdj=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=22`),//个人机动车登记
        this.uploadUrl_gr_xsz=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=23`),//个人行驶证
        this.uploadUrl_gr_clmmht=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=24`),//个人车辆买卖合同
        this.uploadUrl_gr_xsfp=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=25`);   //个人销售发票
        //单位附件上传url
        this.uploadUrl_dw_fddbsfzm=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=26`),//单位法定代表身份证明
        this.uploadUrl_dw_szhy=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=27`), //单位三证合一扫描件
        this.uploadUrl_dw_xsz=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=28`),//单位行驶证
        this.uploadUrl_dw_clmmht=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=29`),//单位车辆买卖合同
        this.uploadUrl_dw_xsfp=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=30`),//单位销售发票
        this.uploadUrl_dw_bkyj=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=31`),//单位提供拨款依据附件（客车有）
        this.uploadUrl_dw_qtfj=this.$http.adornUrl(`/apply/devapply/upload?token=${this.$cookie.get('token')}&masterid=${this.applyForm.id}&attchmenttype=32`)//单位其他附件

      },
      //返回信息确认页面
      toBackConfirm(){
        this.stepIndex=2
      },
      // 上传之前
      beforeUploadHandle (file) {
        console.log(file.type);
        if (file.type !== 'image/jpg' && file.type !== 'image/jpeg' && file.type !== 'image/png') {
          this.$message.error('只支持jpg、png格式的图片！');
          return false
        }
      },
      // 个人_身份证或暂住证上传成功
      successHandleGrSfz (response, file, fileList) {
        this.fileListGrSfz = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      // 个人_车辆登记证书
      successHandleGrJdcdj(response, file, fileList) {
        this.fileListGrJdcdj = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      // 个人_车辆行驶证
      successHandleGrXsz(response, file, fileList) {
        this.fileListGrXsz = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      // 个人_车辆买卖合同
      successHandleGrClmmht(response, file, fileList) {
        this.fileListGrClmmht = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      // 个人_车辆销售发票
      successHandleGrXsfp(response, file, fileList) {
        this.fileListGrXsfp = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      //单位法定代表身份证明
      successHandleDwFddbsfzm(response, file, fileList) {
        this.fileListDwFddbsfzm = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      //单位三证合一扫描件
      successHandleDwSzhy(response, file, fileList) {
        this.fileListDwSzhy = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      //单位行驶证
      successHandleDwXsz(response, file, fileList) {
        this.fileListDwXsz = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      //单位车辆买卖合同
      successHandleDwClmmht(response, file, fileList) {
        this.fileListDwClmmht = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      //单位销售发票原件
      successHandleDwXsfp(response, file, fileList) {
        this.fileListDwXsfp = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      //单位提供拨款依据附件（客车有）
      successHandleDwBkyj(response, file, fileList) {
        this.fileListDwBkyj = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },
      //单位其他附件
      successHandleDwQtfj(response, file, fileList) {
        this.fileListDwQtfj = fileList;
        if (response && response.code === 0) {
        } else {
          this.$message.error(response.msg)
        }
      },

      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
       console.log(file);
      },
      //申请提交
      saveApply(){
        this.$refs['applyForm'].validate((valid) => {
//          if(this.applyForm.carfield=='1'){
//              if(this.fileListGrSfz.length<=0){
//                this.ifCanSubmit=false
//                this.$message.error('请上传身份证或暂住证！')
//              }else if(this.fileListGrJdcdj.length<=0){
//                this.ifCanSubmit=false
//                this.$message.error('请上传机动车登记证明！')
//              }else if(this.fileListGrXsz.length<=0){
//                this.ifCanSubmit=false
//                this.$message.error('请上传机动车行驶证！')
//              }else if(this.fileListGrClmmht.length<=0){
//                this.ifCanSubmit=false
//                this.$message.error('请上传机动车买卖合同！')
//              }else if(this.fileListGrXsfp.length<=0){
//                this.ifCanSubmit=false
//                this.$message.error('请上传机动车销售发票！')
//              }
//          }else{
//            if(this.fileListDwFddbsfzm.length<=0){
//              this.ifCanSubmit=false
//              this.$message.error('请上传法定代表身份证明！')
//            }else if(this.fileListDwSzhy.length<=0){
//              this.ifCanSubmit=false
//              this.$message.error('请上传三证合一扫描件！')
//            }else if(this.fileListDwXsz.length<=0){
//              this.ifCanSubmit=false
//              this.$message.error('请上传机动车行驶证！')
//            }else if(this.fileListDwClmmht.length<=0){
//              this.ifCanSubmit=false
//              this.$message.error('请上传机动车买卖合同！')
//            }else if(this.fileListDwXsfp.length<=0){
//              this.ifCanSubmit=false
//              this.$message.error('请上传机动车销售发票！')
//            }else if(this.fileListDwBkyj.length<=0){
//              this.ifCanSubmit=false
//              this.$message.error('请上传拨款依据附件！')
//            }else if(this.fileListDwQtfj.length<=0){
//              this.ifCanSubmit=false
//              this.$message.error('请上传其它附件！')
//            }
//
//          }
          //提交表单
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/apply/devapply/save`),
              method: 'post',
              data: this.$http.adornData({
                'id':this.applyForm.id,
                'baseid': this.applyForm.baseid,
                'boardtime': this.applyForm.boardtime,
                'assiststandard': this.applyForm.assiststandard,
                'assistprice': this.applyForm.assistprice,
                'assistcityprice': this.applyForm.assistcityprice,
                'assistareaprice': this.applyForm.assistareaprice,
                'syscalc': this.applyForm.syscalc,
                'carfield': this.applyForm.carfield,
                'applystatus': this.applyForm.applystatus
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.currentRow.ylzd2='1';
                this.$http({
                  url: this.$http.adornUrl(`/apply/devcarbasedata/update`),
                  method: 'post',
                  data: this.currentRow
                }).then(({data}) => {
                  if (data && data.code === 0) {
//                    this.$message({
//                      message: '申请提交成功',
//                      type: 'success',
//                      duration: 1500,
//                      onClose: () => {
//                        this.visible = false
//                        this.getDataList ();
//                      }
//                    })
                    this.$notify({
                      title: '成功',
                      message: '申请提交成功',
                      type: 'success',
                      duration: 1500,
                      onClose: () => {
                        this.visible = false;
                        this.getDataList ();
                        this.stepIndex=1;
                        //数据初始化
                        this.resetField()
                        }

                      })
                  } else {
                    this.$message.error(data.msg)
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })

      },
      //字段的初始化
      resetField(){
        this.currentRow= null;
        this.currCarBaseId=null;
        this.applyForm.id=getUUID();
        this.applyForm.baseid='';
        this.applyForm.boardtime='';
        this.applyForm.assiststandard='';
        this.applyForm.assistprice='';
        this.applyForm.assistcityprice='';
        this.applyForm.assistareaprice='';
        this.applyForm.syscalc='';
        this.applyForm.assistcityprice='';
        this.ifCanSubmit=true;
        this.carfield='1';
        //个人附件上传数组
        this.fileListGrSfz=[];  //个人身份证或暂住证
        this.fileListGrJdcdj=[];  //个人机动车登记
        this.fileListGrXsz=[];  //个人行驶证
        this.fileListGrClmmht=[];  //个人车辆买卖合同
        this.fileListGrXsfp=[];  //个人销售发票原件
        //单位附件上传数组
        this.fileListDwFddbsfzm=[];  //单位法定代表身份证明
        this.fileListDwSzhy=[];  //单位三证合一扫描件
        this.fileListDwXsz=[];  //单位行驶证
        this.fileListDwClmmht=[];  //单位车辆买卖合同
        this.fileListDwXsfp=[];  //单位销售发票原件
        this.fileListDwBkyj=[];  //单位提供拨款依据附件（客车有）
        this.fileListDwQtfj= []  //单位其他附件
      }
    }
  }
</script>
