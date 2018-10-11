package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-19 13:40:25
 */
@TableName("sys_dict")
public class SysDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String dictId;
	/**
	 * 字典类型
	 */
	private String dictType;
	/**
	 * 数据项code
	 */
	private String itemValue;
	/**
	 * 数据项名称
	 */
	private String itemName;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 序号
	 */
	private Integer seq;

	/**
	 * 设置：主键
	 */
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	/**
	 * 获取：主键
	 */
	public String getDictId() {
		return dictId;
	}
	/**
	 * 设置：字典类型
	 */
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	/**
	 * 获取：字典类型
	 */
	public String getDictType() {
		return dictType;
	}
	/**
	 * 设置：数据项code
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	/**
	 * 获取：数据项code
	 */
	public String getItemValue() {
		return itemValue;
	}
	/**
	 * 设置：数据项名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * 获取：数据项名称
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * 设置：备注
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：备注
	 */
	public String getNote() {
		return note;
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
}
