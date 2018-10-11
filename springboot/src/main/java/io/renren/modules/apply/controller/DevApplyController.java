package io.renren.modules.apply.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.renren.common.utils.FreeMarkerUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.UuidUtil;
import io.renren.config.UploadConfig;
import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.apply.entity.DevOpeHistoryEntity;
import io.renren.modules.apply.service.DevApplyService;
import io.renren.modules.apply.service.DevCarBasedataService;
import io.renren.modules.apply.service.DevOpeHistoryService;
import io.renren.modules.generator.entity.SysDictEntity;
import io.renren.modules.generator.service.SysDictService;
import io.renren.modules.qualifapply.entity.DevCarQualifapplyEntity;
import io.renren.modules.qualifapply.entity.DevSellerQualifapplyEntity;
import io.renren.modules.qualifapply.service.DevSellerQualifapplyService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.upload.entity.DevUploadAttachmentEntity;
import io.renren.modules.upload.service.DevUploadAttachmentService;



/**
 * 
 *	申请模块AND经销商功能
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-25 11:33:32
 */
@RestController
@RequestMapping("apply/devapply")
public class DevApplyController extends AbstractController {
    @Autowired
    private DevApplyService devApplyService;
    
    @Autowired
    private DevOpeHistoryService devOpeHistoryService;

    @Autowired
    private DevCarBasedataService devCarBasedataService;
    
    @Autowired
    private UploadConfig uploadConfig;
    
    @Autowired
    private SysDictService sysDictService;
    
    @Autowired
    private DevUploadAttachmentService devUploadAttachmentService;
    
    @Autowired
    private DevSellerQualifapplyService devSellerQualifapplyService;
    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("apply:devapply:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = devApplyService.queryPage(params);

        return R.ok().put("page", page);
    }
    	
    /**
     * 获取经销商申请状态
     *      
     *      * */
    @RequestMapping("/getSellerApplyStatus")
    public R getSellerApplyStatus(){
    	String orgId = this.getUser().getOrgId();
    	DevSellerQualifapplyEntity entity = devSellerQualifapplyService.selectOne(
			new EntityWrapper<DevSellerQualifapplyEntity>().eq("orgid", orgId).isNotNull("auditresult"));
    	if(entity != null) {
    		Integer status = entity.getAuditresult();
    		if(status == 1) {
    			return R.ok();
			}else if(status == 0) {
				return R.error("资质申请被驳回");
			}else {
				return R.error("资质申请没有通过");
			}
    	}else {
    		return R.error("资质申请没有通过");
    	}
	
 
}
    
    
    /**
     * 我的申请（申请进度查询）
     */
    @RequestMapping("/progressList")
    public R applyProgressSerachList(@RequestParam Map<String, Object> params){
    	String saleorgid=this.getUser().getOrgId();
    	params.put("saleorgid", saleorgid);//存入经销商id 只显示该经销商的审批进度
        PageUtils page = devApplyService.progressListDataPage(params);
        return R.ok().put("page", page);
    }
    
    
    /**
            * 我的申请（申请进度查询）
     */
    @RequestMapping("/exportWord")
    public void exportWord(HttpServletRequest request,HttpServletResponse response){
    	String orgId = this.getUser().getOrgId();
    	List<DevApplyEntity> list = devApplyService.selectList(new EntityWrapper<DevApplyEntity>().eq("orgid", orgId).eq("applystatus", "14"));
    	float money = 0;
    	if(list != null && list.size() > 0) {
    		for (DevApplyEntity devApplyEntity : list) {
        		Float assistprice = devApplyEntity.getAssistprice();
        		money += assistprice;
    		}
    	}
    	Map<String, Object> dataMap = new HashMap<String, Object>();
    	dataMap.put("count", list.size()); 
    	dataMap.put("money", money);
    	dataMap.put("name", "");
    	String value = FreeMarkerUtil.getFreeMarkerFile("jingxiaoshang.ftl", dataMap);
    	System.out.println(value);
    	try {
    		response.setCharacterEncoding("UTF-8");
		    response.setHeader("content-Type", "application/msword");
		    response.setHeader("Content-Disposition", "attachment;filename=经销商申报.doc");
			response.getWriter().write(value);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
     * 申请信息查询
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("apply:devapply:info")
    public R info(@PathVariable("id") String id){
			DevApplyEntity devApply = devApplyService.selectById(id);

        return R.ok().put("devApply", devApply);
    }
    
    
    /**
     * 流转节点数据
     */
    @RequestMapping("/lznodedata")
    public R getOpeHistoryNodeList(@RequestParam Map<String, Object> params){
		List<Map> list=devOpeHistoryService.getOpeHistoryNodeList(params);
        return R.ok().put("list", list);
    }
    
    
    /**
     * 收款确认
     */
    @RequestMapping("/confirmMoney")
    public R confirmMoney(@RequestParam Map<String, Object> params){
    	String applyid=params.get("applyid").toString();
    	String opinion=params.get("opinion").toString();
    	DevOpeHistoryEntity entity=new DevOpeHistoryEntity();
    	entity.setId(UuidUtil.get32UUID());
    	entity.setApplyid(applyid);
    	entity.setOpetype("14");
    	entity.setOpetime(new Date());
    	entity.setOpeorg(this.getUser().getOrgId());
    	entity.setOpeuserid(this.getUser().getUserId().toString());
    	entity.setOpinion(opinion);
    	devOpeHistoryService.insert(entity);
    	DevApplyEntity applyEntity=new DevApplyEntity();
    	applyEntity.setId(applyid);
    	applyEntity.setApplystatus("14");
    	devApplyService.update(applyEntity, new EntityWrapper<DevApplyEntity>());
        return R.ok();
    }

    
    /**
     * 车辆基础信息表
     */
    @RequestMapping("/carbaselist")
    public R carbaselist(@RequestParam Map<String, Object> params){
    	params.put("orgid", this.getUser().getOrgId()); //补充部门信息
        PageUtils page = devCarBasedataService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("apply:devapply:save")
    public R save(@RequestBody DevApplyEntity devApply){
    	devApply.setApplytime(new Date());
    	devApply.setOrgid(this.getUser().getOrgId());
    	devApply.setApplyuserid(this.getUser().getUserId().toString());
    	DevOpeHistoryEntity history=new DevOpeHistoryEntity();
    	history.setId(UuidUtil.get32UUID());
    	history.setApplyid(devApply.getId());
    	history.setOpetype("0");
    	history.setOpetime(new Date());
    	history.setOpeorg(this.getUser().getOrgId());
    	history.setOpeuserid(this.getUser().getUserId().toString());
		devApplyService.insert(devApply);
		devOpeHistoryService.insert(history);		
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("apply:devapply:update")
    public R update(@RequestBody DevApplyEntity devApply){
			devApplyService.updateById(devApply);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids){
			devApplyService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    
    
    /**
     * 附件上传
     */
    @RequestMapping("/upload")    
    public R upload(HttpServletRequest request) {
    	try {
    		String root = uploadConfig.getRoot();
    		if(root==null)
    			return R.error();    		
    		String path = "apply";
    		
    		File fpath = new File(root+File.separator+path);
    		if(!fpath.exists()) {
    			fpath.mkdirs();
    		}
    		String attachmentType = request.getParameter("attchmenttype");
    		String masterid = request.getParameter("masterid");
    		String uuid = UuidUtil.get32UUID();
        	List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file"); 
        	if(files!=null&&files.size()>0) {
        		for(MultipartFile f:files) {
        			String fn = f.getOriginalFilename();
        			String suffix = fn.substring(fn.lastIndexOf("."));        			
        			byte[] data = f.getBytes();
        			if(data==null||data.length==0)
        				continue;
        			File f1 = new File(root + File.separator + path+File.separator+uuid+masterid+suffix);
        			FileOutputStream fos = new FileOutputStream(f1);
        			fos.write(data);
        			fos.flush();
        			fos.close();
        			//写入记录
        			DevUploadAttachmentEntity entity = new DevUploadAttachmentEntity();
        			entity.setId(uuid);
        			entity.setMasterid(masterid);
        			entity.setType(attachmentType);
        			entity.setFilename(fn);
        			entity.setFiletype(suffix);
        			entity.setFilesize(String.valueOf(data.length));
        			entity.setPath(path);
        			entity.setCreatetime(new Date());
        			devUploadAttachmentService.insert(entity);
        		}
        	}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		return R.error();
    	}
    	
    	return R.ok();
    }

}
