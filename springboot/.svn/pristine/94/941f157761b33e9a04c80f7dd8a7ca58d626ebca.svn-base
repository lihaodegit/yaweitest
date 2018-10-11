package io.renren.modules.apply.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-25 11:33:32
 */
@TableName("dev_apply")
public class DevApplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 车辆基础信息表主键
	 */
	private String baseid;
	/**
	 * 挂牌时间
	 */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date boardtime;
	/**
	 * 补助标准(万元辆)
	 */
	private Float assiststandard;
	/**
	 * 补助资金数额（万元辆）
	 */
	private Float assistprice;
	/**
	 * 市级财政补助资金数额（万元辆）
	 */
	private Float assistcityprice;
	/**
	 * 区级财政补助资金数额（万元辆）
	 */
	private Float assistareaprice;
	/**
	 * 系统算出指导补助金额（万元辆）
	 */
	private Float syscalc;
	/**
	 * 车辆领域
	 */
	private String carfield;
	/**
	 * 申请状态 0发起待市科技局审核  1市科技局审核通过 2市科技局审核驳回 3公安局审核通过 4公安局审核驳回 5市财政局审核通过  6市财政局审核驳回  7区财政局审核通过 8区财政局审核驳回 9市科技局审核通过  10市科技局审核驳回 11市财政局审核通过  12市财政局审核驳回  13区财政局付款确认  14经销商收到钱确认
	 */
	private String applystatus;
	/**
	 * 申请时间
	 */
	private Date applytime;
	/**
	 * 经销商单位id
	 */
	private String orgid;
	/**
	 * 申请用户id
	 */
	private String applyuserid;
	/**
	 * 预留字段1
	 */
	private String ylzd1;
	/**
	 * 预留字段2
	 */
	private String ylzd2;
	/**
	 * 预留字段3
	 */
	private String ylzd3;
	/**
	 * 预留字段4
	 */
	private String ylzd4;
	/**
	 * 预留字段5
	 */
	private String ylzd5;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：车辆基础信息表主键
	 */
	public void setBaseid(String baseid) {
		this.baseid = baseid;
	}
	/**
	 * 获取：车辆基础信息表主键
	 */
	public String getBaseid() {
		return baseid;
	}
	/**
	 * 设置：挂牌时间
	 */
	public void setBoardtime(Date boardtime) {
		this.boardtime = boardtime;
	}
	/**
	 * 获取：挂牌时间
	 */
	public Date getBoardtime() {
		return boardtime;
	}
	/**
	 * 设置：补助标准(万元辆)
	 */
	public void setAssiststandard(Float assiststandard) {
		this.assiststandard = assiststandard;
	}
	/**
	 * 获取：补助标准(万元辆)
	 */
	public Float getAssiststandard() {
		return assiststandard;
	}
	/**
	 * 设置：补助资金数额（万元辆）
	 */
	public void setAssistprice(Float assistprice) {
		this.assistprice = assistprice;
	}
	/**
	 * 获取：补助资金数额（万元辆）
	 */
	public Float getAssistprice() {
		return assistprice;
	}
	/**
	 * 设置：市级财政补助资金数额（万元辆）
	 */
	public void setAssistcityprice(Float assistcityprice) {
		this.assistcityprice = assistcityprice;
	}
	/**
	 * 获取：市级财政补助资金数额（万元辆）
	 */
	public Float getAssistcityprice() {
		return assistcityprice;
	}
	/**
	 * 设置：区级财政补助资金数额（万元辆）
	 */
	public void setAssistareaprice(Float assistareaprice) {
		this.assistareaprice = assistareaprice;
	}
	/**
	 * 获取：区级财政补助资金数额（万元辆）
	 */
	public Float getAssistareaprice() {
		return assistareaprice;
	}
	/**
	 * 设置：系统算出指导补助金额（万元辆）
	 */
	public void setSyscalc(Float syscalc) {
		this.syscalc = syscalc;
	}
	/**
	 * 获取：系统算出指导补助金额（万元辆）
	 */
	public Float getSyscalc() {
		return syscalc;
	}
	/**
	 * 设置：车辆领域
	 */
	public void setCarfield(String carfield) {
		this.carfield = carfield;
	}
	/**
	 * 获取：车辆领域
	 */
	public String getCarfield() {
		return carfield;
	}
	/**
	 * 设置：申请状态 0发起待市科技局审核  1市科技局审核通过 2市科技局审核驳回 3公安局审核通过 4公安局审核驳回 5市财政局审核通过  6市财政局审核驳回  7区财政局审核通过 8区财政局审核驳回 9市科技局审核通过  10市科技局审核驳回 11市财政局审核通过  12市财政局审核驳回  13区财政局付款确认  14经销商收到钱确认
	 */
	public void setApplystatus(String applystatus) {
		this.applystatus = applystatus;
	}
	/**
	 * 获取：申请状态 0发起待市科技局审核  1市科技局审核通过 2市科技局审核驳回 3公安局审核通过 4公安局审核驳回 5市财政局审核通过  6市财政局审核驳回  7区财政局审核通过 8区财政局审核驳回 9市科技局审核通过  10市科技局审核驳回 11市财政局审核通过  12市财政局审核驳回  13区财政局付款确认  14经销商收到钱确认
	 */
	public String getApplystatus() {
		return applystatus;
	}
	/**
	 * 设置：申请时间
	 */
	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
	/**
	 * 获取：申请时间
	 */
	public Date getApplytime() {
		return applytime;
	}
	/**
	 * 设置：经销商单位id
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	/**
	 * 获取：经销商单位id
	 */
	public String getOrgid() {
		return orgid;
	}
	/**
	 * 设置：申请用户id
	 */
	public void setApplyuserid(String applyuserid) {
		this.applyuserid = applyuserid;
	}
	/**
	 * 获取：申请用户id
	 */
	public String getApplyuserid() {
		return applyuserid;
	}
	/**
	 * 设置：预留字段1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * 获取：预留字段1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * 设置：预留字段2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * 获取：预留字段2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * 设置：预留字段3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * 获取：预留字段3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * 设置：预留字段4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * 获取：预留字段4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * 设置：预留字段5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	/**
	 * 获取：预留字段5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
}
