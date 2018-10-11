/**
 * 市财政局统一提交路由
 */
package io.renren.modules.cityfinance.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.renren.common.utils.R;
import io.renren.common.utils.UuidUtil;
import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.apply.entity.DevOpeHistoryEntity;
import io.renren.modules.apply.service.DevApplyService;
import io.renren.modules.apply.service.DevOpeHistoryService;
import io.renren.modules.sys.controller.AbstractController;

/**
 * @author hawkbird date 2018-10-02
 *
 */
@RestController
@RequestMapping("cityfinance/unisubmit")
public class DevCityFinanceUniSubmitController extends AbstractController {
	@Autowired
	private DevApplyService devApplyService;
	@Autowired
	private DevOpeHistoryService devOpeHistoryService;
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
			apply.setApplystatus("5X");
			wrapper.setEntity(apply);
			r.put("preldtjcnt", devApplyService.selectCount(wrapper));
			apply.setApplystatus("5");			
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
			entity1.setApplystatus("5X");
			wrapper.setEntity(entity1);
			List<DevApplyEntity> ls = devApplyService.selectList(wrapper);			
			devApplyService.update("5X", "5");
			for(DevApplyEntity apply:ls) {
				DevOpeHistoryEntity entity=new DevOpeHistoryEntity();
		    	entity.setId(UuidUtil.get32UUID());
		    	entity.setApplyid(apply.getId());
		    	entity.setOpetype("5");
		    	entity.setOpetime(new Date());
		    	entity.setOpeorg(this.getUser().getOrgId());
		    	entity.setOpeuserid(this.getUser().getUserId().toString());
		    	entity.setOpinion("市财政局初审统一提交");
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
			apply.setApplystatus("11X");
			wrapper.setEntity(apply);
			r.put("retrialdtjcnt", devApplyService.selectCount(wrapper));
			apply.setApplystatus("11");			
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
			entity1.setApplystatus("11X");
			wrapper.setEntity(entity1);
			List<DevApplyEntity> ls = devApplyService.selectList(wrapper);			
			devApplyService.update("11X", "11");
			for(DevApplyEntity apply:ls) {
				DevOpeHistoryEntity entity=new DevOpeHistoryEntity();
		    	entity.setId(UuidUtil.get32UUID());
		    	entity.setApplyid(apply.getId());
		    	entity.setOpetype("11");
		    	entity.setOpetime(new Date());
		    	entity.setOpeorg(this.getUser().getOrgId());
		    	entity.setOpeuserid(this.getUser().getUserId().toString());
		    	entity.setOpinion("公安局复审统一提交");
		    	devOpeHistoryService.insert(entity);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return R.error("保存失败");
		}
		return r;
	}
}
