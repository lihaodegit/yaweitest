package io.renren.modules.police.dao;

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
public interface PoliceDao extends BaseMapper<DevApplyEntity> {

	List<Map> policeProgressListDataPage(Page p,Map<String, Object> map);

}
