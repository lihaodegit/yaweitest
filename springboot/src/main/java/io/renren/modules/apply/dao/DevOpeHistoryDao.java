package io.renren.modules.apply.dao;

import io.renren.modules.apply.entity.DevOpeHistoryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-30 14:49:04
 */
@Mapper
public interface DevOpeHistoryDao extends BaseMapper<DevOpeHistoryEntity> {
	
	  List<Map> getOpeHistoryNodeList(Map<String, Object> params);  //获取节点图数据	  
	  public Map applyUniSubmit(Map<String,Object> params);
	  
	  public Map qczjUpdate(Map<String,Object> params);
}
