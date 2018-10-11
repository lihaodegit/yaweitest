package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.SysDictEntity;
import io.renren.modules.generator.service.SysDictService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.UuidUtil;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-19 13:40:25
 */
@RestController
@RequestMapping("generator/sysdict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:sysdict:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysDictService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dictId}")
    @RequiresPermissions("generator:sysdict:info")
    public R info(@PathVariable("dictId") String dictId){
			SysDictEntity sysDict = sysDictService.selectById(dictId);

        return R.ok().put("sysDict", sysDict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:sysdict:save")
    public R save(@RequestBody SysDictEntity sysDict){
    	//sysDict.setDictId(UuidUtil.get32UUID());
			sysDictService.insert(sysDict);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:sysdict:update")
    public R update(@RequestBody SysDictEntity sysDict){
			sysDictService.updateById(sysDict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:sysdict:delete")
    public R delete(@RequestBody String[] dictIds){
			sysDictService.deleteBatchIds(Arrays.asList(dictIds));

        return R.ok();
    }

}
