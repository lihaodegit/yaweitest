package io.renren.modules.police.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.modules.apply.dao.DevApplyDao;
import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.police.dao.PoliceDao;
import io.renren.modules.police.service.PoliceService;

@Service("policeService")

public class PoliceServiceImpl  extends ServiceImpl<PoliceDao, DevApplyEntity> implements PoliceService {
	
	@Autowired
	private DevApplyDao devApplyDao;
	@Override
	public Page<Map<String,Object>> selectPoliceApplyByPage(Map<String, Object> params) {
		Page<Map<String,Object>> page =new Page<Map<String,Object>>(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map<String, Object>> selectApplyByPage = devApplyDao.selectPoliceApplyByPage(page,params);
		page.setRecords(selectApplyByPage);
		return page;
	}
	
	@Override
	public PageUtils policeProgressListDataPage(Map<String, Object> params) {
		Page p=new Page(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map> list=baseMapper.policeProgressListDataPage(p, params);
        p.setRecords(list);
		return new PageUtils(p);
	}
	

}
