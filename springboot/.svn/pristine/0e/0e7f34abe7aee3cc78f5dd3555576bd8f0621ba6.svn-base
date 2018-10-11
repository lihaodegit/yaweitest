package io.renren.modules.apply.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.apply.entity.DevApplyEntity;
import io.renren.modules.apply.entity.DevCarBasedataEntity;
import io.renren.modules.apply.service.DevCarBasedataService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-26 13:35:28
 */
@RestController
@RequestMapping("apply/devcarbasedata")
public class DevCarBasedataController {
    @Autowired
    private DevCarBasedataService devCarBasedataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("apply:devcarbasedata:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = devCarBasedataService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("apply:devcarbasedata:update")
    public R update(@RequestBody DevCarBasedataEntity devCarBasedataEntity){
    	devCarBasedataService.updateById(devCarBasedataEntity);
        return R.ok();
    }


}
