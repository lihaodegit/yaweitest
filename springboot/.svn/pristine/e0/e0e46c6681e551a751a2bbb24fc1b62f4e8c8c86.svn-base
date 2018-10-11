package io.renren.modules.cityfinance.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.apply.entity.DevCarBasedataEntity;
import io.renren.modules.apply.entity.DevOpeHistoryEntity;
import io.renren.modules.apply.service.DevApplyService;
import io.renren.modules.apply.service.DevCarBasedataService;
import io.renren.modules.apply.service.DevOpeHistoryService;
import io.renren.modules.cityfinance.service.DevCityFinanceService;
import io.renren.modules.generator.entity.SysDictEntity;
import io.renren.modules.generator.service.SysDictService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.upload.entity.DevUploadAttachmentEntity;
import io.renren.modules.upload.service.DevUploadAttachmentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.UuidUtil;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-26 13:35:28
 */
@RestController
@RequestMapping("cityfinance/devapply")
public class DevCityFinanceController extends AbstractController{
    @Autowired
    private DevCarBasedataService devCarBasedataService;
    @Autowired
    private DevApplyService devApplyService;
    @Autowired
    private DevUploadAttachmentService devUploadAttachmentService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private DevOpeHistoryService devOpeHistoryService;
    @Autowired
    private DevCityFinanceService devCityFinanceService;
    
    
    /**
     * 市财政查看车辆申请（申请进度查询）
     */
    @RequestMapping("/cityFinanceApplyProgressList")
    public R cityFinanceApplyProgressList(@RequestParam Map<String, Object> params){
    	String orgid=this.getUser().getOrgId();
    	params.put("orgid", orgid);//存入公安单位id 只有市财政经手过得申请才可查看
        PageUtils page = devCityFinanceService.cityFinanceProgressListDataPage(params);
        return R.ok().put("page", page);
    }
    
    /**
     * 初审复审列表
     */
    @RequestMapping("/fslist")
    public R fslist(@RequestParam Map<String, Object> params){
    	Page<Map<String, Object>> page = devCityFinanceService.selectCityFinanceApplyByPage(params);
        return R.ok().put("page", page);
    }
   
	
	/**
	 *修改经销商申请状态
	 */
	@RequestMapping("/updateApplyStatus")
	public R updateApplyStatus(DevApplyEntity entity,String opinion){
		devApplyService.updateById(entity);
		DevOpeHistoryEntity devOpeHistory = new  DevOpeHistoryEntity();
		devOpeHistory.setId(UuidUtil.get32UUID());
		devOpeHistory.setOpetype(entity.getApplystatus());
		devOpeHistory.setApplyid(entity.getId());
		devOpeHistory.setOpetime(new Date());
		devOpeHistory.setOpeorg(entity.getOrgid());
		devOpeHistory.setOpeuserid(String.valueOf(this.getUserId()));
		devOpeHistory.setOpinion(opinion);
		devOpeHistoryService.insert(devOpeHistory);
		return R.ok();
	}
	
	
	
	/**
	 *获取申请的附件
	*/
	@RequestMapping("/getFileByApplyId")
	public R getFileByApplyId(@RequestParam("id")String id){
		List<DevUploadAttachmentEntity> list = devUploadAttachmentService.selectList(new EntityWrapper<DevUploadAttachmentEntity>().eq("masterid", id));
	    for (DevUploadAttachmentEntity devUploadAttachmentEntity : list) {
	    	SysDictEntity sysDictEntity = sysDictService.selectOne(new EntityWrapper<SysDictEntity>().eq("dict_type", "ATTACHMENT_TYPE").eq("item_value", devUploadAttachmentEntity.getType()));
	    	String name = sysDictEntity.getItemName();
	    	devUploadAttachmentEntity.setType(name);
	    }
		return R.ok().put("file", list);
	}
	
    

  

}
