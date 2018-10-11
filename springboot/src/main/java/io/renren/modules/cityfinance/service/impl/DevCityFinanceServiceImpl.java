package io.renren.modules.cityfinance.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.modules.apply.dao.DevApplyDao;
import io.renren.modules.carBaseData.entity.DevCarBasedatasEntity;
import io.renren.modules.cityfinance.dao.DevCityFinanceDao;
import io.renren.modules.cityfinance.service.DevCityFinanceService;



@Service("devCityFinanceService")
public class DevCityFinanceServiceImpl extends ServiceImpl<DevCityFinanceDao, DevCarBasedatasEntity> implements DevCityFinanceService {
    @Autowired
    private DevApplyDao devApplyDao;
    
	@Override
	public Page<Map<String, Object>> selectCityFinanceApplyByPage(Map<String, Object> params) {
		Page<Map<String,Object>> page =new Page<Map<String,Object>>(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map<String, Object>> selectApplyByPage = devApplyDao.selectCityFinanceApplyByPage(page,params);
		page.setRecords(selectApplyByPage);
		return page;
	}

	@Override
	public PageUtils cityFinanceProgressListDataPage(Map<String, Object> params) {
		Page p=new Page(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map> list=baseMapper.cityFinanceProgressListDataPage(p, params);
        p.setRecords(list);
		return new PageUtils(p);
	}
}
