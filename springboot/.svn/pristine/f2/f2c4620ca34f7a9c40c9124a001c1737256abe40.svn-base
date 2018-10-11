package io.renren.modules.sys.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.service.SysOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-18 10:01:51
 */
@RestController
@RequestMapping("sys/sysorg")
public class SysOrgController {
    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:sysorg:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysOrgService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orgId}")
//    @RequiresPermissions("generator:sysorg:info")
    public R info(@PathVariable("orgId") String orgId){
			SysOrgEntity sysOrg = sysOrgService.selectById(orgId);

        return R.ok().put("sysOrg", sysOrg);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:sysorg:save")
    public R save(@RequestBody SysOrgEntity sysOrg){
			sysOrgService.insert(sysOrg);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:sysorg:update")
    public R update(@RequestBody SysOrgEntity sysOrg){
			sysOrgService.updateById(sysOrg);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:sysorg:delete")
    public R delete(@RequestBody String[] orgIds){
			sysOrgService.deleteBatchIds(Arrays.asList(orgIds));

        return R.ok();
    }

    /**
            * 获取组织机构树形结构
     */
    @RequestMapping("/getOrgList")
//    @RequiresPermissions("generator:sysorg:info")
    public R getOrgList(@RequestParam Map<String, Object> params){
        List<SysOrgEntity> list = sysOrgService.selectList(new EntityWrapper<SysOrgEntity>());
        List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
        for ( SysOrgEntity sysOrgEntity:list) {
            Map<String,Object> map = new HashMap<String,Object>();
            String label = sysOrgEntity.getOrgName();
            String id = sysOrgEntity.getOrgId();
            map.put("label",label);
            map.put("id",id);
            listData.add(map);
        }

        return R.ok().put("data",listData);
    }

}
