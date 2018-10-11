/**
 * 公安局统一提交路由
 */
package io.renren.modules.police.controller;

import java.util.Date;
import java.util.List;

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

/**
 * @author hawkbird
 *
 */
@RestController
@RequestMapping("police/unisubmit")
public class PoliceUniSubmitController extends AbstractController {
	@Autowired
	private DevApplyService devApplyService;
	@Autowired
	private DevOpeHistoryService devOpeHistoryService;
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
			EntityWrapper<DevApplyEntity> wrapper = new EntityWrapper<DevApplyEntity>();
			DevApplyEntity apply = new DevApplyEntity();
			apply.setApplystatus("3X");
			wrapper.setEntity(apply);
			r.put("dtjcnt", devApplyService.selectCount(wrapper));
			apply.setApplystatus("3");			
			r.put("ytjcnt", devApplyService.selectCount(wrapper));
			return r;
		}catch(Exception ex) {
			return r;
		}		
	}	
	/**
	 * 统一提交
	 */
	@RequestMapping("/policeunisubmit")
	public R prelSubmit() {
		R r = R.ok();
		try {
			EntityWrapper<DevApplyEntity> wrapper = new EntityWrapper<DevApplyEntity>();
			DevApplyEntity entity1 = new DevApplyEntity();
			entity1.setApplystatus("3X");
			wrapper.setEntity(entity1);
			List<DevApplyEntity> ls = devApplyService.selectList(wrapper);			
			devApplyService.update("3X", "3");			
			for(DevApplyEntity apply:ls) {
				DevOpeHistoryEntity entity=new DevOpeHistoryEntity();
		    	entity.setId(UuidUtil.get32UUID());
		    	entity.setApplyid(apply.getId());
		    	entity.setOpetype("3");
		    	entity.setOpetime(new Date());
		    	entity.setOpeorg(this.getUser().getOrgId());
		    	entity.setOpeuserid(this.getUser().getUserId().toString());
		    	entity.setOpinion("公安局初审统一提交");
		    	devOpeHistoryService.insert(entity);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return R.error("保存失败");
		}
		return r;
	}
}
