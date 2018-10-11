package io.renren.modules.apply.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.apply.dao.DevCarBasedataDao;
import io.renren.modules.apply.entity.DevCarBasedataEntity;
import io.renren.modules.apply.service.DevCarBasedataService;


@Service("devCarBasedataService")
public class DevCarBasedataServiceImpl extends ServiceImpl<DevCarBasedataDao, DevCarBasedataEntity> implements DevCarBasedataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String key = (String)params.get("key");
    	String orgid=(String)params.get("orgid");
        Page<DevCarBasedataEntity> page = this.selectPage(
                new Query<DevCarBasedataEntity>(params).getPage(),
                new EntityWrapper<DevCarBasedataEntity>()
                .like(StringUtils.isNotBlank(key),"platenumber", key)
                .eq(orgid != null,"saleorgid", orgid)
                .isNull("ylzd2")
        );

        return new PageUtils(page);
    }

}
