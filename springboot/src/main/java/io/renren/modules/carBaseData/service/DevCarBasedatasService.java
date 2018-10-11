package io.renren.modules.carBaseData.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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
public interface DevCarBasedatasService extends IService<DevCarBasedatasEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public Map<String,Object> checkExcel(MultipartFile  files);

    public List<DevCarBasedatasEntity> ExcelToDevCarBaseEntity(List<List<String>> lists,String sellerId,String uuid,String carId);
   
    PageUtils carApplyprogressListDataPage(Map<String, Object> params);  //车企查看车辆申请进度

    void updateCarApplyNullFlag(String  id);  //更新车辆申请状态为未申请

}

