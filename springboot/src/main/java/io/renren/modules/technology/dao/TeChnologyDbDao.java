package io.renren.modules.technology.dao;

import io.renren.modules.apply.entity.DevApplyEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-25 11:33:32
 */
@Mapper
public interface TeChnologyDbDao extends BaseMapper<DevApplyEntity> {

	List<Map> technologyProgressListDataPage(Page p,Map<String, Object> map);
	
	List<Map> technologyBhApplyListPage(Page p,Map<String, Object> map);
	
	void updateBhApplyStatus(String[] ids);  //更新申请表状态
	
	List<Map> carqualifilist(Page p,Map<String,Object> params);
	List<Map> sellerqualifilist(Page p,Map<String,Object> params);
	void updateCarQualifiApplyStatus(Map<String,Object> params);
	void updateSellerQualifiApplyStatus(Map<String,Object> params);
} 
