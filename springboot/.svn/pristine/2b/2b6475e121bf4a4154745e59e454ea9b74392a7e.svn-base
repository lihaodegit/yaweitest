package io.renren.modules.qualifapply.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.qualifapply.dao.DevCarQualifapplyDao;
import io.renren.modules.qualifapply.entity.DevCarQualifapplyEntity;
import io.renren.modules.qualifapply.service.DevCarQualifapplyService;


@Service("devCarQualifapplyService")
public class DevCarQualifapplyServiceImpl extends ServiceImpl<DevCarQualifapplyDao, DevCarQualifapplyEntity> implements DevCarQualifapplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	EntityWrapper<DevCarQualifapplyEntity> wrapper = new EntityWrapper<DevCarQualifapplyEntity>();
    	wrapper.like("entname", params.get("key")==null?"":((String)params.get("key")));
        Page<DevCarQualifapplyEntity> page = this.selectPage(
                new Query<DevCarQualifapplyEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }
    /**
     * 判断当前车企的资质申请是否已经审核通过
     */
    @Override
    public boolean isAuditOk(String orgId) {    	
    	EntityWrapper<DevCarQualifapplyEntity> wrapper = new EntityWrapper<DevCarQualifapplyEntity>();
    	wrapper.eq("orgid", orgId).and().eq("auditresult", "1");
//    	Map<String,Object> params = new HashMap<String,Object>();
//    	params.put("orgid", orgId);
//    	params.put("auditresult", 1);
    	try {
    		List<Object> ls = this.baseMapper.selectObjs(wrapper);
//    		List<DevCarQualifapplyEntity> ls = this.selectByMap(params);
        	return !(ls==null||ls.size()==0);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		return false;
    	}    	    	
    }
    /**
     * 已填写未审核
     */
	@Override
	public boolean recorded(String orgId) {
		// 
		EntityWrapper<DevCarQualifapplyEntity> wrapper = new EntityWrapper<DevCarQualifapplyEntity>();
		wrapper.eq("orgid", orgId).andNew().isNull("auditresult");
		try {
			List<Object> ls = this.baseMapper.selectObjs(wrapper);
			return !(ls==null||ls.size()==0);
		}catch(Exception ex) {
			//出现异常就表示已经存在不允许再新增
			ex.printStackTrace();
			return true;
		}
	}

//	public static void main(String[] args) {
//		String orgId = "1";
//		EntityWrapper<DevCarQualifapplyEntity> wrapper = new EntityWrapper<DevCarQualifapplyEntity>();
//		wrapper.eq("orgid", orgId).andNew().isNull("auditresult").or().eq("auditresult", "0");
//		String sql = wrapper.getSqlSegment();
//		System.out.println(sql);
//	}
}
