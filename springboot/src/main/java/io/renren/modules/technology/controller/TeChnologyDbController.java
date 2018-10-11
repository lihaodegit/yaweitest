package io.renren.modules.technology.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.StringUtil;
import io.renren.common.utils.UuidUtil;
import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.apply.entity.DevCarBasedataEntity;
import io.renren.modules.apply.entity.DevOpeHistoryEntity;
import io.renren.modules.apply.service.DevApplyService;
import io.renren.modules.apply.service.DevOpeHistoryService;
import io.renren.modules.carBaseData.service.DevCarBasedatasService;
import io.renren.modules.generator.entity.SysDictEntity;
import io.renren.modules.generator.service.SysDictService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.technology.service.TeChnologyDbService;
import io.renren.modules.upload.entity.DevUploadAttachmentEntity;
import io.renren.modules.upload.service.DevUploadAttachmentService;



/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-09-30 10:08:15
 */
@RestController
@RequestMapping("technologyDb/devapply")
public class TeChnologyDbController extends AbstractController{
   @Autowired
   private TeChnologyDbService teChnologyDbService;
   @Autowired
   private SysOrgService sysOrgService;
   @Autowired
   private DevUploadAttachmentService devUploadAttachmentService;
   @Autowired
   private SysDictService sysDictService;
   @Autowired
   private DevApplyService devApplyService;
   @Autowired
   private DevOpeHistoryService devOpeHistoryService;
   @Autowired
   private DevCarBasedatasService devCarBasedatasService;

   
   /**
          * 申请进度查询
    */
   @RequestMapping("/technologyProgressList")
   public R applyProgressSerachList(@RequestParam Map<String, Object> params){
       PageUtils page = teChnologyDbService.technologyProgressListDataPage(params);
       return R.ok().put("page", page);
   }
   
   
   
   /**
          * 申请进度查询
    */
   @RequestMapping("/technologyBhApplyList")
   public R technologyBhApplyList(@RequestParam Map<String, Object> params){
       PageUtils page = teChnologyDbService.technologyBhApplyListPage(params);
       return R.ok().put("page", page);
   }
   
    /**
     * 初审复审列表
     */
    @RequestMapping("/fslist")
    public R fslist(@RequestParam Map<String, Object> params){
    	Page<Map<String, Object>> page = teChnologyDbService.selectApplyByPage(params);
        return R.ok().put("page", page);
    }
    /**
	  *获取商户名称
	*/
	@RequestMapping("/getSellerNameById")
	public R getSellerNameById(@RequestParam("id")String id){
		SysOrgEntity sysorg = sysOrgService.selectById(id);
	    return R.ok().put("name", sysorg.getOrgName());
	}
	
	/**
	 *修改经销商申请状态
	 */
	@RequestMapping("/updateApplyStatus")
	public R updateApplyStatus(DevApplyEntity entity,@RequestParam(value="opinion",required=false)String opinion,@RequestParam(value="policeopinion",required=false)String policeopinion){
		System.out.println(policeopinion);
		devApplyService.updateById(entity);
		DevOpeHistoryEntity devOpeHistory = new  DevOpeHistoryEntity();
		devOpeHistory.setId(UuidUtil.get32UUID());
		devOpeHistory.setOpetype(entity.getApplystatus());
		devOpeHistory.setApplyid(entity.getId());
		devOpeHistory.setOpetime(new Date());
		devOpeHistory.setOpeorg(this.getUser().getOrgId());
		devOpeHistory.setOpeuserid(String.valueOf(this.getUserId()));
		devOpeHistory.setOpinion(opinion);
		if (!StringUtil.isEmpty(policeopinion)) {
			devOpeHistory.setPoliceopinion(policeopinion);
		}
		//市科技局复审驳回
		if("10".equals(entity.getApplystatus())) {
			DevApplyEntity entity2 = devApplyService.selectById(entity.getId());
			devCarBasedatasService.updateCarApplyNullFlag(entity2.getBaseid());
		}
		devOpeHistoryService.insert(devOpeHistory);
		return R.ok();
	}
	
	
	
	 /**
               * 市科技局对驳回件可以批量驳回给经销商
     */
    @RequestMapping("/batchBhToSeller")
    public R batchBhToSeller(@RequestBody String[] ids){
    	teChnologyDbService.updateBhApplyStatus(ids);
    	for(int i=0;i<ids.length;i++){
    		String applyid=ids[i];
    		DevOpeHistoryEntity devOpeHistory = new  DevOpeHistoryEntity();
    		devOpeHistory.setId(UuidUtil.get32UUID());
    		devOpeHistory.setOpetype("15");
    		devOpeHistory.setApplyid(applyid);
    		devOpeHistory.setOpetime(new Date());
    		devOpeHistory.setOpeorg(this.getUser().getOrgId());
    		devOpeHistory.setOpeuserid(String.valueOf(this.getUserId()));
    		devOpeHistory.setOpinion("确认驳回");
    		devOpeHistoryService.insert(devOpeHistory);   //插入节点表数据
    		Map param=new HashMap<>();
    		DevApplyEntity applyEntity =devApplyService.selectById(applyid);
    		String baseid=applyEntity.getBaseid();
    		devCarBasedatasService.updateCarApplyNullFlag(baseid); //更新车辆状态为未申请
    		
    	}
    	//////未完待续
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
	
	

    /**
             * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
			DevApplyEntity devApply = teChnologyDbService.selectById(id);

        return R.ok().put("devApply", devApply);
    }

    /**
            * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DevApplyEntity devApply){
    	teChnologyDbService.insert(devApply);

        return R.ok();
    }

    /**
            * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DevApplyEntity devApply){
    	teChnologyDbService.updateById(devApply);

        return R.ok();
    }

    /**
           * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids){
    	teChnologyDbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
