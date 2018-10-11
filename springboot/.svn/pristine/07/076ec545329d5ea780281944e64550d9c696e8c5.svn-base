/**
 * 科技局资质申请控制
 */
package io.renren.modules.technology.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.technology.service.TeChnologyDbService;

/**
 * @author hawkbird date 2018-10-05
 *
 */
@RestController
@RequestMapping("technology/audit")
public class TechnologyAuditController extends AbstractController {
	@Autowired
	private TeChnologyDbService teChnologyDbService;
	/**
	 * 查询车企资质待申请资料
	 */
	@RequestMapping("/carqualifilist")
	public R carQualifiList(@RequestParam Map<String, Object> params) {
		String auditResult = (String)params.get("auditresult");
		if(auditResult==null||"".equals(auditResult))
			params.remove("auditresult");
		PageUtils page = teChnologyDbService.carQualifiList(params);
	    return R.ok().put("page", page);
	}
	/**
	 * 车企资质申请通过
	 */
	@RequestMapping("/carqualifipass")
	public R carQualifiPass(@RequestBody Map<String,Object>[] ids) {
		teChnologyDbService.updateCarQualifiApplyStatus("1", ids);
		return R.ok();
	}
	/**
	 * 车企资质申请驳回
	 */
	@RequestMapping("/carqualifireject")
	public R carQualifiReject(@RequestBody Map<String,Object>[] ids) {		
		for(Map<String,Object> map:ids) {
			map.put("auditer", this.getUser().getUsername());
		}
		teChnologyDbService.updateCarQualifiApplyStatus("0", ids);
		return R.ok();
	}
	/**
	 * 查询经销商资质申请资料
	 */
	@RequestMapping("/sellerqualifilist")
	public R sellerQualifiList(@RequestParam Map<String, Object> params) {
		String auditResult = (String)params.get("auditresult");
		if(auditResult==null||"".equals(auditResult))
			params.remove("auditresult");
		PageUtils page = teChnologyDbService.sellerQualifiList(params);
	    return R.ok().put("page", page);
	}
	/**
	 * 经销商资质申请通过
	 */
	@RequestMapping("/sellerqualifipass")
	public R sellerQualifiPass(@RequestBody Map<String,Object>[] ids) {
		for(Map<String,Object> map:ids) {
			map.put("auditer", this.getUser().getUsername());
		}
		teChnologyDbService.updateSellerQualifiApplyStatus("1", ids);
		return R.ok();
	}
	/**
	 * 经销商资质申请驳回
	 */
	@RequestMapping("/sellerqualifireject")
	public R sellerQualifiReject(@RequestBody Map<String,Object>[] ids) {
		for(Map<String,Object> map:ids) {
			map.put("auditer", this.getUser().getUsername());
		}
		teChnologyDbService.updateSellerQualifiApplyStatus("0", ids);
		return R.ok();
	}
}
