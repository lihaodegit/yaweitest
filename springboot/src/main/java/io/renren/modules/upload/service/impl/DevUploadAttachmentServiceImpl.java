package io.renren.modules.upload.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.upload.dao.DevUploadAttachmentDao;
import io.renren.modules.upload.entity.DevUploadAttachmentEntity;
import io.renren.modules.upload.service.DevUploadAttachmentService;


@Service("devUploadAttachmentService")
public class DevUploadAttachmentServiceImpl extends ServiceImpl<DevUploadAttachmentDao, DevUploadAttachmentEntity> implements DevUploadAttachmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DevUploadAttachmentEntity> page = this.selectPage(
                new Query<DevUploadAttachmentEntity>(params).getPage(),
                new EntityWrapper<DevUploadAttachmentEntity>()
        );

        return new PageUtils(page);
    }
    /**
     * 依据主表主键查询
     */
	@Override
	public List<DevUploadAttachmentEntity> queryByMasterid(String masterId) {
		EntityWrapper<DevUploadAttachmentEntity> wrapper = new EntityWrapper<DevUploadAttachmentEntity>();
		DevUploadAttachmentEntity entity = new DevUploadAttachmentEntity();
		entity.setMasterid(masterId);
		wrapper.setEntity(entity);
		try {
			return this.baseMapper.selectList(wrapper);			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
    
}
