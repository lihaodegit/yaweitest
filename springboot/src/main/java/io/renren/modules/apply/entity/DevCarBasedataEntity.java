package io.renren.modules.apply.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-26 13:35:28
 */
@TableName("dev_car_basedata")
public class DevCarBasedataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 销售机构id
	 */
	private String saleorgid;
	/**
	 * 销售机构名称
	 */
	private String saleorgname;
	/**
	 * 车牌号
	 */
	private String platenumber;
	/**
	 * 车辆性质
	 */
	private String carprop;
	/**
	 * 车辆性质code
	 */
	private String carpropCode;
	/**
	 * 购车城市
	 */
	private String carcity;
	/**
	 * 车辆区市信息（excel中追加字段）
	 */
	private String cararea;
	/**
	 * 区域信息code 参见数据字典
	 */
	private String carareaCode;
	/**
	 * 车辆运行单位
	 */
	private String carorg;
	/**
	 * 车辆种类
	 */
	private String cartype;
	/**
	 * 车辆种类code
	 */
	private String cartypeCode;
	/**
	 * 车辆用途
	 */
	private String caruse;
	/**
	 * 车辆用途code
	 */
	private String caruseCode;
	/**
	 * 车辆型号
	 */
	private String carmodel;
	/**
	 * Ekg值
	 */
	private Float ekgval;
	/**
	 * 
	 */
	private String noticebatch;
	/**
	 * 车辆识别代码
	 */
	private String vin;
	/**
	 * 申请补助标准
	 */
	private Float applystandard;
	/**
	 * 购买价格（实际价格j即成交价）
	 */
	private Float pricereal;
	/**
	 * 车辆售价，须与市场公允价相符，其实就是指导价（excel追加字段）
	 */
	private Float price;
	/**
	 * 发票号
	 */
	private String receiptnum;
	/**
	 * 发票日期
	 */
	private Date receiptdate;
	/**
	 * 行驶证时间
	 */
	private Date runcarddate;
	/**
	 * 一次充满电车辆行驶里程
	 */
	private Float runmiles;
	/**
	 * 车辆一次充满电所需时间（h）
	 */
	private Float chargetime;
	/**
	 * 最大充电功率（Kw）
	 */
	private Float chargepower;
	/**
	 * 累计行驶里程（km）
	 */
	private Float totalrunmiles;
	/**
	 * 月均行驶里程（Km）
	 */
	private Float monthrunmiles;
	/**
	 * 百公里耗电量（kWh/100km）
	 */
	private Float powerconsume;
	/**
	 * 总充电量（累计燃料加注量中的一项）
	 */
	private Float totalchargepower;
	/**
	 * 单日运行时间
	 */
	private Float dayrunhours;
	/**
	 * 是否安装监控装置
	 */
	private String ismonitor;
	/**
	 * 是否安装监控装置code
	 */
	private String ismonitorCode;
	/**
	 * 监控运行单位
	 */
	private String monitororg;
	/**
	 * 明细附件中位置的说明备注（excel追加字段）
	 */
	private String locremark;
	/**
	 * 电池组型号
	 */
	private String batterysinglemodel;
	/**
	 * 电池组单体生产企业
	 */
	private String batterysingleorg;
	/**
	 * 电池组成箱型号
	 */
	private String batteryboxmodel;
	/**
	 * 电池组总能量（KWh）
	 */
	private Float batterypower;
	/**
	 * 电池组生产企业
	 */
	private String batteryorg;
	/**
	 * 电池组系统价格
	 */
	private Float batteryprice;
	/**
	 * 电池组质保年限
	 */
	private Integer batteryqualityyear;
	/**
	 * 驱动电机型号
	 */
	private String machinemodel;
	/**
	 * 额定功率（kw）
	 */
	private Float machinepower;
	/**
	 * 驱动电机生产企业
	 */
	private String machineorg;
	/**
	 * 驱动电机系统价格
	 */
	private Float machineprice;
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
	 * 设置：销售机构id
	 */
	public void setSaleorgid(String saleorgid) {
		this.saleorgid = saleorgid;
	}
	/**
	 * 获取：销售机构id
	 */
	public String getSaleorgid() {
		return saleorgid;
	}
	/**
	 * 设置：销售机构名称
	 */
	public void setSaleorgname(String saleorgname) {
		this.saleorgname = saleorgname;
	}
	/**
	 * 获取：销售机构名称
	 */
	public String getSaleorgname() {
		return saleorgname;
	}
	/**
	 * 设置：车牌号
	 */
	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}
	/**
	 * 获取：车牌号
	 */
	public String getPlatenumber() {
		return platenumber;
	}
	/**
	 * 设置：车辆性质
	 */
	public void setCarprop(String carprop) {
		this.carprop = carprop;
	}
	/**
	 * 获取：车辆性质
	 */
	public String getCarprop() {
		return carprop;
	}
	/**
	 * 设置：车辆性质code
	 */
	public void setCarpropCode(String carpropCode) {
		this.carpropCode = carpropCode;
	}
	/**
	 * 获取：车辆性质code
	 */
	public String getCarpropCode() {
		return carpropCode;
	}
	/**
	 * 设置：购车城市
	 */
	public void setCarcity(String carcity) {
		this.carcity = carcity;
	}
	/**
	 * 获取：购车城市
	 */
	public String getCarcity() {
		return carcity;
	}
	/**
	 * 设置：车辆区市信息（excel中追加字段）
	 */
	public void setCararea(String cararea) {
		this.cararea = cararea;
	}
	/**
	 * 获取：车辆区市信息（excel中追加字段）
	 */
	public String getCararea() {
		return cararea;
	}
	/**
	 * 设置：区域信息code 参见数据字典
	 */
	public void setCarareaCode(String carareaCode) {
		this.carareaCode = carareaCode;
	}
	/**
	 * 获取：区域信息code 参见数据字典
	 */
	public String getCarareaCode() {
		return carareaCode;
	}
	/**
	 * 设置：车辆运行单位
	 */
	public void setCarorg(String carorg) {
		this.carorg = carorg;
	}
	/**
	 * 获取：车辆运行单位
	 */
	public String getCarorg() {
		return carorg;
	}
	/**
	 * 设置：车辆种类
	 */
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	/**
	 * 获取：车辆种类
	 */
	public String getCartype() {
		return cartype;
	}
	/**
	 * 设置：车辆种类code
	 */
	public void setCartypeCode(String cartypeCode) {
		this.cartypeCode = cartypeCode;
	}
	/**
	 * 获取：车辆种类code
	 */
	public String getCartypeCode() {
		return cartypeCode;
	}
	/**
	 * 设置：车辆用途
	 */
	public void setCaruse(String caruse) {
		this.caruse = caruse;
	}
	/**
	 * 获取：车辆用途
	 */
	public String getCaruse() {
		return caruse;
	}
	/**
	 * 设置：车辆用途code
	 */
	public void setCaruseCode(String caruseCode) {
		this.caruseCode = caruseCode;
	}
	/**
	 * 获取：车辆用途code
	 */
	public String getCaruseCode() {
		return caruseCode;
	}
	/**
	 * 设置：车辆型号
	 */
	public void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}
	/**
	 * 获取：车辆型号
	 */
	public String getCarmodel() {
		return carmodel;
	}
	/**
	 * 设置：Ekg值
	 */
	public void setEkgval(Float ekgval) {
		this.ekgval = ekgval;
	}
	/**
	 * 获取：Ekg值
	 */
	public Float getEkgval() {
		return ekgval;
	}
	/**
	 * 设置：
	 */
	public void setNoticebatch(String noticebatch) {
		this.noticebatch = noticebatch;
	}
	/**
	 * 获取：
	 */
	public String getNoticebatch() {
		return noticebatch;
	}
	/**
	 * 设置：车辆识别代码
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	/**
	 * 获取：车辆识别代码
	 */
	public String getVin() {
		return vin;
	}
	/**
	 * 设置：申请补助标准
	 */
	public void setApplystandard(Float applystandard) {
		this.applystandard = applystandard;
	}
	/**
	 * 获取：申请补助标准
	 */
	public Float getApplystandard() {
		return applystandard;
	}
	/**
	 * 设置：购买价格（实际价格j即成交价）
	 */
	public void setPricereal(Float pricereal) {
		this.pricereal = pricereal;
	}
	/**
	 * 获取：购买价格（实际价格j即成交价）
	 */
	public Float getPricereal() {
		return pricereal;
	}
	/**
	 * 设置：车辆售价，须与市场公允价相符，其实就是指导价（excel追加字段）
	 */
	public void setPrice(Float price) {
		this.price = price;
	}
	/**
	 * 获取：车辆售价，须与市场公允价相符，其实就是指导价（excel追加字段）
	 */
	public Float getPrice() {
		return price;
	}
	/**
	 * 设置：发票号
	 */
	public void setReceiptnum(String receiptnum) {
		this.receiptnum = receiptnum;
	}
	/**
	 * 获取：发票号
	 */
	public String getReceiptnum() {
		return receiptnum;
	}
	/**
	 * 设置：发票日期
	 */
	public void setReceiptdate(Date receiptdate) {
		this.receiptdate = receiptdate;
	}
	/**
	 * 获取：发票日期
	 */
	public Date getReceiptdate() {
		return receiptdate;
	}
	/**
	 * 设置：行驶证时间
	 */
	public void setRuncarddate(Date runcarddate) {
		this.runcarddate = runcarddate;
	}
	/**
	 * 获取：行驶证时间
	 */
	public Date getRuncarddate() {
		return runcarddate;
	}
	/**
	 * 设置：一次充满电车辆行驶里程
	 */
	public void setRunmiles(Float runmiles) {
		this.runmiles = runmiles;
	}
	/**
	 * 获取：一次充满电车辆行驶里程
	 */
	public Float getRunmiles() {
		return runmiles;
	}
	/**
	 * 设置：车辆一次充满电所需时间（h）
	 */
	public void setChargetime(Float chargetime) {
		this.chargetime = chargetime;
	}
	/**
	 * 获取：车辆一次充满电所需时间（h）
	 */
	public Float getChargetime() {
		return chargetime;
	}
	/**
	 * 设置：最大充电功率（Kw）
	 */
	public void setChargepower(Float chargepower) {
		this.chargepower = chargepower;
	}
	/**
	 * 获取：最大充电功率（Kw）
	 */
	public Float getChargepower() {
		return chargepower;
	}
	/**
	 * 设置：累计行驶里程（km）
	 */
	public void setTotalrunmiles(Float totalrunmiles) {
		this.totalrunmiles = totalrunmiles;
	}
	/**
	 * 获取：累计行驶里程（km）
	 */
	public Float getTotalrunmiles() {
		return totalrunmiles;
	}
	/**
	 * 设置：月均行驶里程（Km）
	 */
	public void setMonthrunmiles(Float monthrunmiles) {
		this.monthrunmiles = monthrunmiles;
	}
	/**
	 * 获取：月均行驶里程（Km）
	 */
	public Float getMonthrunmiles() {
		return monthrunmiles;
	}
	/**
	 * 设置：百公里耗电量（kWh/100km）
	 */
	public void setPowerconsume(Float powerconsume) {
		this.powerconsume = powerconsume;
	}
	/**
	 * 获取：百公里耗电量（kWh/100km）
	 */
	public Float getPowerconsume() {
		return powerconsume;
	}
	/**
	 * 设置：总充电量（累计燃料加注量中的一项）
	 */
	public void setTotalchargepower(Float totalchargepower) {
		this.totalchargepower = totalchargepower;
	}
	/**
	 * 获取：总充电量（累计燃料加注量中的一项）
	 */
	public Float getTotalchargepower() {
		return totalchargepower;
	}
	/**
	 * 设置：单日运行时间
	 */
	public void setDayrunhours(Float dayrunhours) {
		this.dayrunhours = dayrunhours;
	}
	/**
	 * 获取：单日运行时间
	 */
	public Float getDayrunhours() {
		return dayrunhours;
	}
	/**
	 * 设置：是否安装监控装置
	 */
	public void setIsmonitor(String ismonitor) {
		this.ismonitor = ismonitor;
	}
	/**
	 * 获取：是否安装监控装置
	 */
	public String getIsmonitor() {
		return ismonitor;
	}
	/**
	 * 设置：是否安装监控装置code
	 */
	public void setIsmonitorCode(String ismonitorCode) {
		this.ismonitorCode = ismonitorCode;
	}
	/**
	 * 获取：是否安装监控装置code
	 */
	public String getIsmonitorCode() {
		return ismonitorCode;
	}
	/**
	 * 设置：监控运行单位
	 */
	public void setMonitororg(String monitororg) {
		this.monitororg = monitororg;
	}
	/**
	 * 获取：监控运行单位
	 */
	public String getMonitororg() {
		return monitororg;
	}
	/**
	 * 设置：明细附件中位置的说明备注（excel追加字段）
	 */
	public void setLocremark(String locremark) {
		this.locremark = locremark;
	}
	/**
	 * 获取：明细附件中位置的说明备注（excel追加字段）
	 */
	public String getLocremark() {
		return locremark;
	}
	/**
	 * 设置：电池组型号
	 */
	public void setBatterysinglemodel(String batterysinglemodel) {
		this.batterysinglemodel = batterysinglemodel;
	}
	/**
	 * 获取：电池组型号
	 */
	public String getBatterysinglemodel() {
		return batterysinglemodel;
	}
	/**
	 * 设置：电池组单体生产企业
	 */
	public void setBatterysingleorg(String batterysingleorg) {
		this.batterysingleorg = batterysingleorg;
	}
	/**
	 * 获取：电池组单体生产企业
	 */
	public String getBatterysingleorg() {
		return batterysingleorg;
	}
	/**
	 * 设置：电池组成箱型号
	 */
	public void setBatteryboxmodel(String batteryboxmodel) {
		this.batteryboxmodel = batteryboxmodel;
	}
	/**
	 * 获取：电池组成箱型号
	 */
	public String getBatteryboxmodel() {
		return batteryboxmodel;
	}
	/**
	 * 设置：电池组总能量（KWh）
	 */
	public void setBatterypower(Float batterypower) {
		this.batterypower = batterypower;
	}
	/**
	 * 获取：电池组总能量（KWh）
	 */
	public Float getBatterypower() {
		return batterypower;
	}
	/**
	 * 设置：电池组生产企业
	 */
	public void setBatteryorg(String batteryorg) {
		this.batteryorg = batteryorg;
	}
	/**
	 * 获取：电池组生产企业
	 */
	public String getBatteryorg() {
		return batteryorg;
	}
	/**
	 * 设置：电池组系统价格
	 */
	public void setBatteryprice(Float batteryprice) {
		this.batteryprice = batteryprice;
	}
	/**
	 * 获取：电池组系统价格
	 */
	public Float getBatteryprice() {
		return batteryprice;
	}
	/**
	 * 设置：电池组质保年限
	 */
	public void setBatteryqualityyear(Integer batteryqualityyear) {
		this.batteryqualityyear = batteryqualityyear;
	}
	/**
	 * 获取：电池组质保年限
	 */
	public Integer getBatteryqualityyear() {
		return batteryqualityyear;
	}
	/**
	 * 设置：驱动电机型号
	 */
	public void setMachinemodel(String machinemodel) {
		this.machinemodel = machinemodel;
	}
	/**
	 * 获取：驱动电机型号
	 */
	public String getMachinemodel() {
		return machinemodel;
	}
	/**
	 * 设置：额定功率（kw）
	 */
	public void setMachinepower(Float machinepower) {
		this.machinepower = machinepower;
	}
	/**
	 * 获取：额定功率（kw）
	 */
	public Float getMachinepower() {
		return machinepower;
	}
	/**
	 * 设置：驱动电机生产企业
	 */
	public void setMachineorg(String machineorg) {
		this.machineorg = machineorg;
	}
	/**
	 * 获取：驱动电机生产企业
	 */
	public String getMachineorg() {
		return machineorg;
	}
	/**
	 * 设置：驱动电机系统价格
	 */
	public void setMachineprice(Float machineprice) {
		this.machineprice = machineprice;
	}
	/**
	 * 获取：驱动电机系统价格
	 */
	public Float getMachineprice() {
		return machineprice;
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
