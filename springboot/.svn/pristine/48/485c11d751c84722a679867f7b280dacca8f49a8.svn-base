package io.renren.modules.apply.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.apply.entity.DevApplyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-25 11:33:32
 */
public interface DevApplyService extends IService<DevApplyEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    PageUtils progressListDataPage(Map<String, Object> params);
	boolean update(String criteriastatus,String status);
	
	int qczjcnt(Map<String,Object> params);
	
	List<Map<String,Object>> qczjsel(Map<String,Object> params);
	
	boolean qczjUpdate(String criteriastatus,String status);
}

