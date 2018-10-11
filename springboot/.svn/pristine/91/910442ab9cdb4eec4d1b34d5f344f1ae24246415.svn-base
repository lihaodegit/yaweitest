package io.renren.modules.carBaseData.dao;

import io.renren.modules.carBaseData.entity.DevCarBasedatasEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-25 09:24:09
 */
@Mapper
public interface DevCarBasedatasDao extends BaseMapper<DevCarBasedatasEntity> {
	
	List<Map> carApplyprogressListDataPage(Page p,Map<String, Object> map);
	
	void updateCarApplyNullFlag(String id);  //更新车辆状态为未申请
}
