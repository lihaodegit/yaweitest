package io.renren.modules.apply.dao;

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
public interface DevApplyDao extends BaseMapper<DevApplyEntity> {

	List<Map> progressListDataPage(Page p,Map<String, Object> map);

	List<Map<String,Object>> selectApplyByPage(@Param("page")Page<Map<String,Object>> page, @Param("params")Map<String, Object> params);

	public int applyUniSubmit(Map<String,Object> params);
	
	List<Map<String,Object>> selectPoliceApplyByPage(@Param("page")Page<Map<String,Object>> page, @Param("params")Map<String, Object> params);
	
	List<Map<String,Object>> selectCityFinanceApplyByPage(@Param("page")Page<Map<String,Object>> page, @Param("params")Map<String, Object> params);
	
	List<Map<String,Object>> selectAreaFinanceApplyByPage(@Param("page")Page<Map<String,Object>> page, @Param("params")Map<String, Object> params);

	List<Map<String,Object>> qczjcnt(Map<String,Object> params);
	List<Map<String,Object>> qczjsel(Map<String,Object> params);
	
	public Map qczjUpdate(Map<String,Object> params);
}
