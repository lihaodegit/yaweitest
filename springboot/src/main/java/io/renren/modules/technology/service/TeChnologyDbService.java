package io.renren.modules.technology.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import io.renren.common.utils.PageUtils;
import io.renren.modules.apply.entity.DevApplyEntity;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-30 10:08:15
 */
public interface TeChnologyDbService extends IService<DevApplyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Page<Map<String,Object>> selectApplyByPage(Map<String, Object> params);
    
    PageUtils technologyProgressListDataPage(Map<String, Object> params);
    
    PageUtils technologyBhApplyListPage(Map<String, Object> params);
    
    void updateBhApplyStatus(String[] ids);
    
    PageUtils carQualifiList(Map<String, Object> params);
    
    PageUtils sellerQualifiList(Map<String, Object> params);
    
    void updateCarQualifiApplyStatus(String auditresult,Map<String,Object>[] ids);
    
    void updateSellerQualifiApplyStatus(String auditresult,Map<String,Object>[] ids);
}

