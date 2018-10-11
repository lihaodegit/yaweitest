package io.renren.modules.apply.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.apply.entity.DevOpeHistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-30 14:49:04
 */
public interface DevOpeHistoryService extends IService<DevOpeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    List<Map> getOpeHistoryNodeList(Map<String, Object> params);
    
}

