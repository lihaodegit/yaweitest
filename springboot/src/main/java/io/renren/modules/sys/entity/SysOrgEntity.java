package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-18 10:01:51
 */
@TableName("sys_org")
public class SysOrgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 单位id
	 */
	@TableId
	private String orgId;
	/**
	 * 单位名称
	 */
	private String orgName;
	/**
	 * 单位代码
	 */
	private String orgCode;
	/**
	 * 创建人id
	 */
	private String createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
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
	 * 单位地址 
	 */
	private String addr;
	/**
	 * 单位类型  1.车企 2.销售商 3.市科技局 4.市公安局 5.市财政局  6.区财政局  
	 */
	private String type;

	/**
	 * 设置：单位id
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：单位id
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * 设置：单位名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：单位名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：单位代码
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：单位代码
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * 设置：创建人id
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建人id
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
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
	 * 设置：单位地址 
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 获取：单位地址 
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * 设置：单位类型  1.车企 2.销售商 3.市科技局 4.市公安局 5.市财政局  6.区财政局  
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：单位类型  1.车企 2.销售商 3.市科技局 4.市公安局 5.市财政局  6.区财政局  
	 */
	public String getType() {
		return type;
	}
}
