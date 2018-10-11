package io.renren.modules.upload.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-26 14:27:43
 */
@TableName("dev_upload_attachment")
public class DevUploadAttachmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 文件名
	 */
	private String filename;
	/**
	 * 路径
	 */
	private String path;
	/**
	 * 文件大小
	 */
	private String filesize;
	/**
	 * 文件类型
	 */
	private String filetype;
	/**
	 * 关联主表的主键值
	 */
	private String masterid;
	/**
	 * 上传人id
	 */
	private String createid;
	/**
	 * 上传时间
	 */
	private Date createtime;
	/**
	 * 序号
	 */
	private Integer seq;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 预留字段2
	 */
	private String ylzd1;
	/**
	 * 
	 */
	private String ylzd2;
	/**
	 * 
	 */
	private String ylzd3;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：文件名
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * 获取：文件名
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * 设置：路径
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取：路径
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 设置：文件大小
	 */
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	/**
	 * 获取：文件大小
	 */
	public String getFilesize() {
		return filesize;
	}
	/**
	 * 设置：文件类型
	 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	/**
	 * 获取：文件类型
	 */
	public String getFiletype() {
		return filetype;
	}
	/**
	 * 设置：关联主表的主键值
	 */
	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	/**
	 * 获取：关联主表的主键值
	 */
	public String getMasterid() {
		return masterid;
	}
	/**
	 * 设置：上传人id
	 */
	public void setCreateid(String createid) {
		this.createid = createid;
	}
	/**
	 * 获取：上传人id
	 */
	public String getCreateid() {
		return createid;
	}
	/**
	 * 设置：上传时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：上传时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：序号
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：序号
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：预留字段2
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * 获取：预留字段2
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * 设置：
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * 获取：
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * 设置：
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * 获取：
	 */
	public String getYlzd3() {
		return ylzd3;
	}
}
