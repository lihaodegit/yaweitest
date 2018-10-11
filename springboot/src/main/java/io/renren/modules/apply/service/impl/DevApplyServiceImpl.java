package io.renren.modules.apply.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.apply.dao.DevApplyDao;
import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.apply.service.DevApplyService;


@Service("devApplyService")
public class DevApplyServiceImpl extends ServiceImpl<DevApplyDao, DevApplyEntity> implements DevApplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DevApplyEntity> page = this.selectPage(
                new Query<DevApplyEntity>(params).getPage(),
                new EntityWrapper<DevApplyEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public PageUtils progressListDataPage(Map<String, Object> params) {
		Page p=new Page(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map> list=baseMapper.progressListDataPage(p, params);
        p.setRecords(list);
		return new PageUtils(p);
	}
	@Override
	public boolean update(String criteriastatus,String status) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("criteriastatus", criteriastatus);
		params.put("status", status);
		this.baseMapper.applyUniSubmit(params);
		return false;
	}

	@Override
	public int qczjcnt(Map<String, Object> params) {
		//
		try {
			List<Map<String,Object>> ls = this.baseMapper.qczjcnt(params);
			Long cnt = (Long)ls.get(0).get("cnt");
			return cnt.intValue();
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}		
	}

	@Override
	public List<Map<String, Object>> qczjsel(Map<String, Object> params) {
		// 
		try {
			return this.baseMapper.qczjsel(params);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean qczjUpdate(String criteriastatus, String status) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("criteriastatus", criteriastatus);
		params.put("status", status);
		this.baseMapper.qczjUpdate(params);
		return false;
	}
}
