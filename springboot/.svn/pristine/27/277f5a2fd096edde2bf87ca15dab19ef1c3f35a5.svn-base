package io.renren.modules.qualifapply.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.renren.modules.qualifapply.entity.DevCarQualifapplyEntity;
import io.renren.modules.qualifapply.service.DevCarQualifapplyService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.upload.entity.DevUploadAttachmentEntity;
import io.renren.modules.upload.service.DevUploadAttachmentService;
import io.netty.util.internal.StringUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.UuidUtil;
import io.renren.config.UploadConfig;



/**
 * 车企和经销商资质申请表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-25 08:54:59
 */
@RestController
@RequestMapping("qualifapply/devcarqualifapply")
public class DevCarQualifapplyController extends AbstractController{
    @Autowired
    private DevCarQualifapplyService devCarQualifapplyService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private UploadConfig uploadConfig;
    @Autowired
    private DevUploadAttachmentService devUploadAttachmentService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("qualifapply:devcarqualifapply:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = devCarQualifapplyService.queryPage(params);        
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("qualifapply:devcarqualifapply:info")
    public R info(@PathVariable("id") String id){
		DevCarQualifapplyEntity devCarQualifapply = devCarQualifapplyService.selectById(id);			
        return R.ok().put("devCarQualifapply", devCarQualifapply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("qualifapply:devcarqualifapply:save")
    public R save(@RequestBody DevCarQualifapplyEntity devCarQualifapply){
//    	devCarQualifapply.setAuditresult(null);
    	devCarQualifapply.setApplytime(new Date());
    	devCarQualifapply.setOrgid(getUser().getOrgId());
//    	devCarQualifapply.setId(UuidUtil.get32UUID());
		devCarQualifapplyService.insert(devCarQualifapply);			
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("qualifapply:devcarqualifapply:update")
    public R update(@RequestBody DevCarQualifapplyEntity devCarQualifapply){
			devCarQualifapplyService.updateById(devCarQualifapply);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("qualifapply:devcarqualifapply:delete")
    public R delete(@RequestBody String[] ids){
			devCarQualifapplyService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 查询资质申报是否通过审核
     */
    @RequestMapping("/canadd")
    public R canAdd() {
    	SysUserEntity user = this.getUser();
    	if(user==null)
    		return R.error("请先登录！");
    	try {
    		//查询部门
        	String orgId = user.getOrgId();
        	if(StringUtil.isNullOrEmpty(orgId)) 
        		return R.error("当前用户不是车辆生产企业！");
        	SysOrgEntity org = sysOrgService.selectById(orgId);
        	if(org==null||!"1".equals(org.getType()))
        		return R.error("当前用户不是车辆生产企业！");
        	//返回值        	
        	R r = R.ok();        	
        	//判断是否申请通过
        	boolean audit = devCarQualifapplyService.isAuditOk(orgId);
        	if(audit) {
        		r.put("code", "1");
        		r.put("msg", "当前用户资质申请已通过！");
        		return r;
        	}
        	//判断是否已填写未审核
        	boolean recorded = devCarQualifapplyService.recorded(orgId);
        	if(recorded) {
        		r.put("code", "1");
        		r.put("msg", "已填写资质申请，请等待审核！");
        		return r;
        	}
        	return r;
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		return R.error();
    	}    	
    }
    /**
     * 返回当前用户部门名称
     */
    @RequestMapping("/orgname")
    public R getOrgName() {
    	String orgId = this.getUser().getOrgId();
    	try {
    		SysOrgEntity org = sysOrgService.selectById(orgId);
    		if(org==null)
    			return R.error();
    		R r = R.ok();
    		r.put("entname", org.getOrgName());
    		return r;
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		return R.error();
    	}
    }
    /**
     * 返回当前记录主键
     */
    @RequestMapping("/getid")
    public R getUuid() {
    	R r = R.ok();
    	r.put("id", UuidUtil.get32UUID());
    	return r;
    }
    /**
     * 附件上传
     */
    @RequestMapping("/upload")    
    @RequiresPermissions("qualifapply:devcarqualifapply:save")
    public R upload(HttpServletRequest request) {
    	try {
    		String root = uploadConfig.getRoot();
    		if(root==null)
    			return R.error();    		
    		String path = "devcarqualifapply";
    		
    		File fpath = new File(root+File.separator+path);
    		if(!fpath.exists()) {
    			fpath.mkdirs();
    		}
    		String attachmentType = request.getParameter("attchmenttype");
    		String applyId = request.getParameter("applyid");
    		String uuid = UuidUtil.get32UUID();
        	List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file"); 
        	if(files!=null&&files.size()>0) {
        		for(MultipartFile f:files) {
        			String fn = f.getOriginalFilename();
        			String suffix = fn.substring(fn.lastIndexOf("."));        			
        			byte[] data = f.getBytes();
        			if(data==null||data.length==0)
        				continue;
        			File f1 = new File(root + File.separator + path+File.separator+uuid+applyId+suffix);
        			FileOutputStream fos = new FileOutputStream(f1);
        			fos.write(data);
        			fos.flush();
        			fos.close();
        			//写入记录
        			DevUploadAttachmentEntity entity = new DevUploadAttachmentEntity();
        			entity.setId(uuid);
        			entity.setMasterid(applyId);
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
    //依据ID查询明细
    @RequestMapping("/detail")
//    @RequiresPermissions("qualifapply:devcarqualifapply:save")
    public R detail(@RequestParam Map<String, Object> params) {
    	try {
    		String id = (String)params.get("id");    		
    		//查询明细
    		DevCarQualifapplyEntity entity = devCarQualifapplyService.selectById(id);
    		//查询图片
    		List<DevUploadAttachmentEntity> ls = devUploadAttachmentService.queryByMasterid(id);
    		if(entity==null)
    			return R.error("查无记录！");
    		if(ls==null||ls.size()==0)
    			return R.error("缺少相关图片资料！");
    		R r = R.ok();
    		int i=0;
    		Map detail = new org.apache.commons.beanutils.BeanMap(entity);
    		r.putAll(detail);    		    		
    		for(DevUploadAttachmentEntity pic:ls) {
    			//三证合一
    			if("01".equals(pic.getType())) {
    				Map<String,String> map_szhy = new HashMap<String,String>();
    				map_szhy.put("id", pic.getId());
    				map_szhy.put("filename", pic.getFilename());
    				map_szhy.put("type", pic.getFiletype());
    				r.put("szhy", map_szhy);    				
    			}    	
    			//身份证
    			if("02".equals(pic.getType())) {    				
    				Map<String,String> map_sfz = new HashMap<String,String>();
    				map_sfz.put("id", pic.getId());
    				map_sfz.put("filename", pic.getFilename());
    				map_sfz.put("type", pic.getFiletype().toLowerCase());
    				r.put("sfz"+i, map_sfz);
    				i ++;
    			}
    		}
    		return r;
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		return R.error();
    	}    	
    }    
}
