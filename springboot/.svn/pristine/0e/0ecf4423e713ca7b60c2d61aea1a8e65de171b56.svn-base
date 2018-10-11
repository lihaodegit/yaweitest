package io.renren.modules.technology.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.apply.dao.DevApplyDao;
import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.technology.dao.TeChnologyDbDao;
import io.renren.modules.technology.service.TeChnologyDbService;


@Service("teChnologyDbService")
public class TeChnologyDbServiceImpl extends ServiceImpl<TeChnologyDbDao, DevApplyEntity> implements TeChnologyDbService {
   
	@Autowired
	private DevApplyDao devApplyDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DevApplyEntity> page = this.selectPage(
                new Query<DevApplyEntity>(params).getPage(),
                new EntityWrapper<DevApplyEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public Page<Map<String,Object>> selectApplyByPage(Map<String, Object> params) {
		Page<Map<String,Object>> page =new Page<Map<String,Object>>(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map<String, Object>> selectApplyByPage = devApplyDao.selectApplyByPage(page,params);
		page.setRecords(selectApplyByPage);
		return page;
	}
	
	
	@Override
	public PageUtils technologyProgressListDataPage(Map<String, Object> params) {
		Page p=new Page(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map> list=baseMapper.technologyProgressListDataPage(p, params);
        p.setRecords(list);
		return new PageUtils(p);
	}
	
	
	@Override
	public PageUtils technologyBhApplyListPage(Map<String, Object> params) {
		Page p=new Page(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map> list=baseMapper.technologyBhApplyListPage(p, params);
        p.setRecords(list);
		return new PageUtils(p);
	}

	@Override
	public void updateBhApplyStatus(String[] ids) {
		// TODO Auto-generated method stub
		baseMapper.updateBhApplyStatus(ids);
	}
	@Override
	public PageUtils carQualifiList(Map<String,Object> params) {
		Page p=new Page(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map> list=baseMapper.carqualifilist(p, params);
        p.setRecords(list);
		return new PageUtils(p);
	}

	@Override
	public PageUtils sellerQualifiList(Map<String,Object> params) {
		Page p=new Page(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map> list=baseMapper.sellerqualifilist(p, params);
        p.setRecords(list);
		return new PageUtils(p);
	}
	
	@Override
	public void updateCarQualifiApplyStatus(String auditresult,Map<String,Object>[] ids) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String,Object>();
		for(Map<String,Object> map:ids) {			
			params.put("auditresult", auditresult);
			params.put("id", map.get("id"));
			params.put("auditoption", map.get("auditoption"));
			params.put("auditer", map.get("auditer"));
			baseMapper.updateCarQualifiApplyStatus(params);
		}
		
	}
	@Override
	public void updateSellerQualifiApplyStatus(String auditresult,Map<String,Object>[] ids) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String,Object>();
		for(Map<String,Object> map:ids) {
			params.put("auditresult", auditresult);
			params.put("id", map.get("id"));
			params.put("auditoption", map.get("auditoption"));
			params.put("auditer", map.get("auditer"));
			baseMapper.updateSellerQualifiApplyStatus(params);
		}		
	}
}
