package io.renren.modules.upload.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.upload.entity.DevUploadAttachmentEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-26 14:27:43
 */
public interface DevUploadAttachmentService extends IService<DevUploadAttachmentEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    /**
     * 依据主键ID查询图片信息
     */
    public List<DevUploadAttachmentEntity> queryByMasterid(String masterId);
}

