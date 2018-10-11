package io.renren.modules.police.controller;

import java.util.ArrayList;
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
import io.renren.common.utils.UuidUtil;
import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.apply.entity.DevOpeHistoryEntity;
import io.renren.modules.apply.service.DevApplyService;
import io.renren.modules.apply.service.DevOpeHistoryService;
import io.renren.modules.generator.entity.SysDictEntity;
import io.renren.modules.generator.service.SysDictService;
import io.renren.modules.police.service.PoliceService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.technology.service.TeChnologyDbService;
import io.renren.modules.upload.entity.DevUploadAttachmentEntity;
import io.renren.modules.upload.service.DevUploadAttachmentService;
@RestController
@RequestMapping("police/devapply")
public class PoliceController extends AbstractController{
	
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
   private PoliceService policeService;
   
   
   
   
   /**
    * 公安查看车辆申请（申请进度查询）
    */
   @RequestMapping("/policeApplyProgressList")
   public R policeApplyProgressList(@RequestParam Map<String, Object> params){
   	String orgid=this.getUser().getOrgId();
   	params.put("orgid", orgid);//存入公安单位id 只有公安经手过得申请才可查看
       PageUtils page = policeService.policeProgressListDataPage(params);
       return R.ok().put("page", page);
   }
   
   /**
	   *获取驳回原因列表
	*/
	@RequestMapping("/getBhReasonList")
	public R getgetBhReasonList(){
		List<SysDictEntity> selectList = sysDictService.selectList(new EntityWrapper<SysDictEntity>().eq("dict_type", "POLICE_AUDIT_CONDITION"));
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>() ;
		for (SysDictEntity dict : selectList) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("value", dict.getItemValue());
			map.put("label", dict.getItemName());
			listMap.add(map);
		}
		return R.ok().put("data", listMap);
	}
   
   
	/**
     * 初审复审列表
     */
    @RequestMapping("/fslist")
    public R fslist(@RequestParam Map<String, Object> params){
    	Page<Map<String, Object>> page = policeService.selectPoliceApplyByPage(params);
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
