/**
 * 科技局统一提交控制类
 */
package io.renren.modules.technology.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import io.renren.common.utils.R;
import io.renren.common.utils.UuidUtil;
import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.apply.entity.DevOpeHistoryEntity;
import io.renren.modules.apply.service.DevApplyService;
import io.renren.modules.apply.service.DevOpeHistoryService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.technology.service.TeChnologyDbService;

/**
 * @author hawkbird date 2018-10-01
 *
 */
@RestController
@RequestMapping("technologyDb/unisubmit")
public class TechnologyUniSubmit extends AbstractController {
	@Autowired
	private DevApplyService devApplyService;
	@Autowired
	private DevOpeHistoryService devOpeHistoryService;
	@Autowired
	private TeChnologyDbService teChnologyDbService;
	/**
	 * 初审待提交及已提交统计
	 */
	@RequestMapping("/prelcount")
	public R prelCount() {		
		R r=R.ok();
		r.put("preldtjcnt", 0);
		r.put("prelytjcnt", 0);
		//
		try {
			EntityWrapper<DevApplyEntity> wrapper = new EntityWrapper<DevApplyEntity>();
			DevApplyEntity apply = new DevApplyEntity();
			apply.setApplystatus("1X");
			wrapper.setEntity(apply);
			r.put("preldtjcnt", devApplyService.selectCount(wrapper));
			apply.setApplystatus("1");			
			r.put("prelytjcnt", devApplyService.selectCount(wrapper));
			return r;
		}catch(Exception ex) {
			return r;
		}		
	}	
	/**
	 * 初审统一提交
	 */
	@RequestMapping("/prelsubmit")
	public R prelSubmit() {
		R r = R.ok();
		try {
			EntityWrapper<DevApplyEntity> wrapper = new EntityWrapper<DevApplyEntity>();
			DevApplyEntity entity1 = new DevApplyEntity();
			entity1.setApplystatus("1X");
			wrapper.setEntity(entity1);
			List<DevApplyEntity> ls = devApplyService.selectList(wrapper);
			devApplyService.update("1X", "1");			
			for(DevApplyEntity apply:ls) {
				DevOpeHistoryEntity entity=new DevOpeHistoryEntity();
		    	entity.setId(UuidUtil.get32UUID());
		    	entity.setApplyid(apply.getId());
		    	entity.setOpetype("1");
		    	entity.setOpetime(new Date());
		    	entity.setOpeorg(this.getUser().getOrgId());
		    	entity.setOpeuserid(this.getUser().getUserId().toString());
		    	entity.setOpinion("市科技局初审统一提交");
		    	devOpeHistoryService.insert(entity);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return R.error("保存失败");
		}
		return r;
	}
	/**
	 * 复审待提交及已提交统计
	 */
	@RequestMapping("/retrialcount")
	public R retrialCount() {
		R r=R.ok();
		r.put("retrialdtjcnt", 0);
		r.put("retrialytjcnt", 0);
		//
		try {
			EntityWrapper<DevApplyEntity> wrapper = new EntityWrapper<DevApplyEntity>();
			DevApplyEntity apply = new DevApplyEntity();
			apply.setApplystatus("9X");
			wrapper.setEntity(apply);
			r.put("retrialdtjcnt", devApplyService.selectCount(wrapper));
			apply.setApplystatus("9");			
			r.put("retrialytjcnt", devApplyService.selectCount(wrapper));
			return r;
		}catch(Exception ex) {
			return r;
		}
	}
	/**
	 * 复审统一提交
	 */
	@RequestMapping("/retrialsubmit")
	public R retrialSubmit() {
		R r = R.ok();
		try {
			EntityWrapper<DevApplyEntity> wrapper = new EntityWrapper<DevApplyEntity>();
			DevApplyEntity entity1 = new DevApplyEntity();
			entity1.setApplystatus("9X");
			wrapper.setEntity(entity1);
			List<DevApplyEntity> ls = devApplyService.selectList(wrapper);			
			devApplyService.update("9X", "9");
			for(DevApplyEntity apply:ls) {
				DevOpeHistoryEntity entity=new DevOpeHistoryEntity();
		    	entity.setId(UuidUtil.get32UUID());
		    	entity.setApplyid(apply.getId());
		    	entity.setOpetype("9");
		    	entity.setOpetime(new Date());
		    	entity.setOpeorg(this.getUser().getOrgId());
		    	entity.setOpeuserid(this.getUser().getUserId().toString());
		    	entity.setOpinion("市科技局复审统一提交");
		    	devOpeHistoryService.insert(entity);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return R.error("保存失败");
		}
		return r;
	}
	/**
	 * 申请列表
	 */
	@RequestMapping("/applylist")
	public R applyList(@RequestParam Map<String, Object> params) {
		R r = R.error();
		String type = (String)params.get("type");
		String flag = (String)params.get("flag");
		String page = (String)params.get("page");
		String limit = (String)params.get("limit");
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("page", page);
		m.put("limit", limit);
		//科技局
		if("kjj".equals(type)) {
			//初审待提交-1X
			if("1".equals(flag)) {
				m.put("applystatus", "1X");
			}
			//初审已提交-1
			else if("2".equals(flag)) {
				m.put("applystatus", "1");
			}
			//复审待提交-5x
			else if("3".equals(flag)) {
				m.put("applystatus", "5X");
			}
			//复审已提交-5
			else if("4".equals(flag)) {
				m.put("applystatus", "5");
			}else
				return r;
		}
		//公安局
		else if("police".equals(type)) {
			//待提交-3X
			if("1".equals(flag)) {
				m.put("applystatus", "3X");
			}
			//已提交-4
			else if("2".equals(flag)) {
				m.put("applystatus", "4");
			}else 
				return r;
		}
		//市财政局
		else if("czj".equals(type)) {
			//初审待提交-5X
			if("1".equals(flag)) {
				m.put("applystatus", "5X");
			}
			//初审已提交-5
			else if("2".equals(flag)) {
				m.put("applystatus", "5");
			}
			//复审待提交-11X
			else if("3".equals(flag)) {
				m.put("applystatus", "11X");
			}
			//复审已提交-11
			else if("4".equals(flag)) {
				m.put("applystatus", "11");
			}else
				return r;
		}
		//区财政局
		else if("areafinance".equals(type)) {
			//初审待提交-7X
			if("1".equals(flag)) {
				m.put("applystatus", "7X");
			}//初审已提交-5
			else if("2".equals(flag)) {
				m.put("applystatus", "7");
			}
		}
		else
			return r;
		//
		Page<Map<String, Object>> p = teChnologyDbService.selectApplyByPage(m);
		//
		return R.ok().put("page", p);
	}
}
