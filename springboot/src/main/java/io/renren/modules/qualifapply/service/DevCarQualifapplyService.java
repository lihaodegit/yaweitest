package io.renren.modules.qualifapply.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.qualifapply.entity.DevCarQualifapplyEntity;

import java.util.Map;

/**
 * 车企和经销商资质申请表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-25 08:54:59
 */
public interface DevCarQualifapplyService extends IService<DevCarQualifapplyEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    /**
     * 查询车企是否申请通过
     */
    boolean isAuditOk(String orgId);
    /**
     * 已填写未审核(通过审核时间判断)
     */
    boolean recorded(String orgId);    
}

