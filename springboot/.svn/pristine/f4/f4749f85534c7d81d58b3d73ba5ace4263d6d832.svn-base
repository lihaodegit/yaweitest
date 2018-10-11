package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.service.SysOrgService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.StringUtil;




@Service("sysOrgService")
public class SysOrgServiceImpl extends ServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
   
        String orgName = (String)params.get("orgName");
        String type = (String)params.get("type");
        if(StringUtil.isEmpty(type)) {
        	   Page<SysOrgEntity> page = this.selectPage(
                       new Query<SysOrgEntity>(params).getPage(),
                       new EntityWrapper<SysOrgEntity>().like("org_name",orgName).orderBy("create_time", false)
               );
        	   return new PageUtils(page);
        }else {
        	   Page<SysOrgEntity> page = this.selectPage(
                       new Query<SysOrgEntity>(params).getPage(),
                       new EntityWrapper<SysOrgEntity>().like("org_name",orgName).eq("type", type).orderBy("create_time", false)
               );
        	   return new PageUtils(page);
        }

    }

}
