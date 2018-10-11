package io.renren.modules.carBaseData.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.renren.common.utils.CombineBeansUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.UuidUtil;
import io.renren.config.UploadConfig;
import io.renren.modules.carBaseData.entity.DevCarBasedatasEntity;
import io.renren.modules.carBaseData.entity.ExcelErrList;
import io.renren.modules.carBaseData.service.DevCarBasedatasService;
import io.renren.modules.qualifapply.entity.DevCarQualifapplyEntity;
import io.renren.modules.qualifapply.entity.DevSellerQualifapplyEntity;
import io.renren.modules.qualifapply.service.DevCarQualifapplyService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.upload.entity.DevUploadAttachmentEntity;
import io.renren.modules.upload.service.DevUploadAttachmentService;


/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-09-25 09:24:09
 */
@RestController
@RequestMapping("generator/devcarbasedata")
public class DevCarBasedatasController extends AbstractController {
    @Autowired
    private DevCarBasedatasService devCarBasedatasService;
    @Autowired
    private UploadConfig uploadConfig;
    @Autowired
    private DevUploadAttachmentService devUploadAttachmentService;
    @Autowired
    private SysOrgService sysorgService;
    @Autowired
    private DevCarQualifapplyService devCarQualifapplyService ;
    /**
            * 列表
     */
    @RequestMapping("/list")
   /* @RequiresPermissions("generator:devcarbasedata:list")*/
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = devCarBasedatasService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
           * 车企资质审核是否通过
     */
    @RequestMapping("/getCarApplyStatus")
   /* @RequiresPermissions("generator:devcarbasedata:list")*/
    public R getSellerApplyStatus(){
    	String orgId = this.getUser().getOrgId();
    	DevCarQualifapplyEntity entity = devCarQualifapplyService.selectOne(
    			new EntityWrapper<DevCarQualifapplyEntity>().eq("orgid", orgId).isNotNull("auditresult"));
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
     * 车企查看车辆申请进度（申请进度查询）
     */
    @RequestMapping("/carApplyProgressList")
    public R carApplyProgressList(@RequestParam Map<String, Object> params){
    	String carcompanyid=this.getUser().getOrgId();
    	params.put("carcompanyid", carcompanyid);//存入车企id 只显示该车企录入车辆的申请进度
        PageUtils page = devCarBasedatasService.carApplyprogressListDataPage(params);
        return R.ok().put("page", page);
    }
    
    
    /**
            *获取经销商列表
     */
    @RequestMapping("/getSellerList")
//    @RequiresPermissions("generator:devcarbasedata:list")
    public R getSellerList(){
    	List<SysOrgEntity> list = sysorgService.selectList(new EntityWrapper<SysOrgEntity>().eq("type", "2"));
        List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>() ;
    	for (SysOrgEntity sysorg : list) {
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("value", sysorg.getOrgId());
    		map.put("label", sysorg.getOrgName());
    		listMap.add(map);
		}
        return R.ok().put("data", listMap);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("generator:devcarbasedata:info")
    public R info(@PathVariable("id") String id){
		DevCarBasedatasEntity devCarBasedata = devCarBasedatasService.selectById(id);
        return R.ok().put("devCarBasedata", devCarBasedata);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:devcarbasedata:save")
    public R save(@RequestBody DevCarBasedatasEntity devCarBasedata){
        devCarBasedatasService.insert(devCarBasedata);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:devcarbasedata:update")
    public R update(@RequestBody DevCarBasedatasEntity devCarBasedata){
        devCarBasedatasService.updateById(devCarBasedata);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:devcarbasedata:delete")
    public R delete(@RequestBody String[] ids){
        devCarBasedatasService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
             * 附件上传
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/upload")    
//    @RequiresPermissions("qualifapply:devcarqualifapply:save")
    public R upload(HttpServletRequest request) {
    	try {
    		String uuid = request.getParameter("uuid");
            String sellerId = request.getParameter("sellerId");//销售商id
            String carId = String.valueOf(this.getUserId());//车企id
            boolean flag = true;
            List<ExcelErrList> errmsgList = new ArrayList<ExcelErrList>();//excel导入时产生的报错信息
            List<DevCarBasedatasEntity> carbaseList = new ArrayList<DevCarBasedatasEntity>();//两个excel通过车牌号合成完整数据的集合
        	List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");//车辆补助资金清算信息明细表和车辆运行情况表
        	List<MultipartFile> file1 = ((MultipartHttpServletRequest) request).getFiles("file1"); //国补明细附件文件
        	List<DevCarBasedatasEntity> ListOn = new ArrayList<DevCarBasedatasEntity>(); //两个excel其中一个产生的数据
   		    List<DevCarBasedatasEntity> ListTw = new ArrayList<DevCarBasedatasEntity>(); //两个excel其中一个产生的数据
   		    if(file1 != null && files.size() > 0) {
   		    	MultipartFile f = file1.get(0);
   		    	String root = uploadConfig.getRoot();
   	    		if(root==null)
   	    			return R.error();    		
   	    		String path = "devcarBaseData";
   	    		File fpath = new File(root+ File.separator + path);
   	    		if(!fpath.exists()) {
   	    			fpath.mkdirs();
   	    		}
   		    	String fn = f.getOriginalFilename();
   				String suffix = fn.substring(fn.lastIndexOf("."));        			
   				byte[] data = f.getBytes();
   				File f1 = new File(fpath+File.separator+uuid+suffix);
   				FileOutputStream fos = new FileOutputStream(f1);
   				fos.write(data);
   				fos.flush();
   				fos.close();
   				//写入记录
   				DevUploadAttachmentEntity entity = new DevUploadAttachmentEntity();
   				entity.setId(uuid);
   				entity.setType("11");
   				entity.setFilename(fn);
   				entity.setFiletype(suffix);
   				entity.setFilesize(String.valueOf(data.length));
   				entity.setPath(path);
   				entity.setCreatetime(new Date());
   				devUploadAttachmentService.insert(entity);
   		    }
        	if(files != null && files.size() > 0) {
        		 for (int i = 0; i < files.size(); i++) {
        			 if(i == 0) {
        				Map<String,Object> map = devCarBasedatasService.checkExcel(files.get(i));
         	            boolean type = (boolean) map.get("type");//是否有错
         	            if(type){	//无错
         	            	List<List<String>> lists = (List<List<String>>) map.get("data");
         	                ListOn = devCarBasedatasService.ExcelToDevCarBaseEntity(lists,sellerId,uuid,carId);
         	            }else{   //有错误信息
         	            	errmsgList.addAll((List<ExcelErrList>) map.get("data"));
         	            	flag = false;
         	            }
        			 }else {   
        				Map<String,Object> map = devCarBasedatasService.checkExcel(files.get(i));
          	            boolean type = (boolean) map.get("type");
          	            if(type){	
          	            	List<List<String>> lists = (List<List<String>>) map.get("data");
          	                ListTw = devCarBasedatasService.ExcelToDevCarBaseEntity(lists,sellerId,uuid,carId);
          	            }else{
          	               errmsgList.addAll((List<ExcelErrList>) map.get("data"));
          	               flag = false;
          	            }
        			 }
     			}
        	}
        	if(flag) {  //两个excel都没有错误
        		 //两个excel中的数据通过双重循环按照车牌号相同的合成一条完整的车辆信息
        		 for (DevCarBasedatasEntity devCarBasedatasEntity : ListOn) {
        			 for (DevCarBasedatasEntity devCarBasedatasEntitytw : ListTw) {
     					if(devCarBasedatasEntity.getPlatenumber().equals(devCarBasedatasEntitytw.getPlatenumber())) {
     						DevCarBasedatasEntity entity = CombineBeansUtil.combineSydwCore(devCarBasedatasEntitytw, devCarBasedatasEntity);
     						entity.setId(UuidUtil.get32UUID());
     						carbaseList.add(entity);
     					}
     				}
 				}
        		 //批量插入
        		devCarBasedatasService.insertBatch(carbaseList);
        		return R.ok().put("errMsg","0");
        	}else {
        		return R.error().put("errMsg",errmsgList);
        	}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		return R.error();
    	}
    	
    	
    }


}
