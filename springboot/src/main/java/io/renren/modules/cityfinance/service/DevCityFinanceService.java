package io.renren.modules.cityfinance.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import io.renren.common.utils.PageUtils;
import io.renren.modules.carBaseData.entity.DevCarBasedatasEntity;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-25 09:24:09
 */
public interface DevCityFinanceService extends IService<DevCarBasedatasEntity> {

	Page<Map<String, Object>> selectCityFinanceApplyByPage(Map<String, Object> params);

	  PageUtils cityFinanceProgressListDataPage(Map<String, Object> params);
}

