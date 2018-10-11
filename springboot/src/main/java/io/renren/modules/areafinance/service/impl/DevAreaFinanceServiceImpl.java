package io.renren.modules.areafinance.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.modules.apply.dao.DevApplyDao;
import io.renren.modules.areafinance.dao.DevAreaFinanceDao;
import io.renren.modules.areafinance.service.DevAreaFinanceService;
import io.renren.modules.carBaseData.entity.DevCarBasedatasEntity;



@Service("devAreaFinanceService")
public class DevAreaFinanceServiceImpl extends ServiceImpl<DevAreaFinanceDao, DevCarBasedatasEntity> implements DevAreaFinanceService {
	@Autowired
    private DevApplyDao devApplyDao;
	
	@Override
	public Page<Map<String, Object>> selectAreaFinanceApplyByPage(Map<String, Object> params) {
		Page<Map<String,Object>> page =new Page<Map<String,Object>>(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map<String, Object>> selectApplyByPage = devApplyDao.selectAreaFinanceApplyByPage(page,params);
		page.setRecords(selectApplyByPage);
		return page;
	}
	
	@Override
	public PageUtils areaFinanceProgressListDataPage(Map<String, Object> params) {
		Page p=new Page(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map> list=baseMapper.areaFinanceProgressListDataPage(p, params);
        p.setRecords(list);
		return new PageUtils(p);
	}

}
