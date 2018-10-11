package io.renren.modules.areafinance.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.renren.common.utils.R;
import io.renren.common.utils.UuidUtil;
import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.apply.entity.DevOpeHistoryEntity;
import io.renren.modules.apply.service.DevApplyService;
import io.renren.modules.apply.service.DevOpeHistoryService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
@RestController
@RequestMapping("areafinance/unisubmit")
public class AreaFinanceUniSubmitController extends AbstractController {
	@Autowired
	private DevApplyService devApplyService;
	@Autowired
	private DevOpeHistoryService devOpeHistoryService;
	@Autowired
	private SysOrgService sysOrgService;
	/**
	 * 待提交及已提交统计
	 */
	@RequestMapping("/count")
	public R prelCount() {				
		R r=R.ok();
		r.put("dtjcnt", 0);
		r.put("ytjcnt", 0);
		//
		try {			
			String orgCode = sysOrgService.selectById(getUser().getOrgId()).getOrgCode();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("areacode", orgCode);
			params.put("status", "7X");
			int ret = devApplyService.qczjcnt(params);
			r.put("dtjcnt", ret);
			params.put("status", "7");
			ret = devApplyService.qczjcnt(params);
			r.put("ytjcnt", ret);
			return r;
		}catch(Exception ex) {
			return r;
		}		
	}	
	/**
	 * 统一提交
	 */
	@RequestMapping("/qczjunisubmit")
	public R prelSubmit() {
		R r = R.ok();
		try {
			String orgCode = sysOrgService.selectById(getUser().getOrgId()).getOrgCode();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("areacode", orgCode);
			params.put("status", "7X");
			List<Map<String,Object>> ls = devApplyService.qczjsel(params);
			devApplyService.qczjUpdate("7X", "7");
			for(Map<String,Object> apply:ls) {
				DevOpeHistoryEntity entity=new DevOpeHistoryEntity();
		    	entity.setId(UuidUtil.get32UUID());
		    	entity.setApplyid((String)apply.get("id"));
		    	entity.setOpetype("7");
		    	entity.setOpetime(new Date());
		    	entity.setOpeorg(this.getUser().getOrgId());
		    	entity.setOpeuserid(this.getUser().getUserId().toString());
		    	entity.setOpinion("区财政局初审统一提交");
		    	devOpeHistoryService.insert(entity);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return R.error("保存失败");
		}
		return r;
	}
}
