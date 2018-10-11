package io.renren.modules.apply.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.apply.dao.DevOpeHistoryDao;
import io.renren.modules.apply.entity.DevOpeHistoryEntity;
import io.renren.modules.apply.service.DevOpeHistoryService;


@Service("devOpeHistoryService")
public class DevOpeHistoryServiceImpl extends ServiceImpl<DevOpeHistoryDao, DevOpeHistoryEntity> implements DevOpeHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DevOpeHistoryEntity> page = this.selectPage(
                new Query<DevOpeHistoryEntity>(params).getPage(),
                new EntityWrapper<DevOpeHistoryEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<Map> getOpeHistoryNodeList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<Map> list=baseMapper.getOpeHistoryNodeList(params);  //获取节点图数据
		return list;
	}	
}
