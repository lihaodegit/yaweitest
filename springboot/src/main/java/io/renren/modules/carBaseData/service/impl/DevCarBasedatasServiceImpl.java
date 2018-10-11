package io.renren.modules.carBaseData.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.ExcelUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.StringUtil;
import io.renren.modules.carBaseData.dao.DevCarBasedatasDao;
import io.renren.modules.carBaseData.entity.DevCarBasedatasEntity;
import io.renren.modules.carBaseData.entity.ExcelErrList;
import io.renren.modules.carBaseData.entity.ExcelMsgEntity;
import io.renren.modules.carBaseData.service.DevCarBasedatasService;
import io.renren.modules.generator.dao.SysDictDao;
import io.renren.modules.generator.entity.SysDictEntity;
import io.renren.modules.qualifapply.dao.DevSellerQualifapplyDao;
import io.renren.modules.qualifapply.entity.DevSellerQualifapplyEntity;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.entity.SysOrgEntity;


@Service("devCarBasedatasService")
public class DevCarBasedatasServiceImpl extends ServiceImpl<DevCarBasedatasDao, DevCarBasedatasEntity> implements DevCarBasedatasService {

	@Autowired
	private SysDictDao sysDictDao;
	@Autowired
	private SysOrgDao sysOrgDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String saleorgname = (String) params.get("key");
    	if(StringUtil.isEmpty(saleorgname)) {
    		Page<DevCarBasedatasEntity> page = this.selectPage(
                    new Query<DevCarBasedatasEntity>(params).getPage(),
                    new EntityWrapper<DevCarBasedatasEntity>()
            );
    		return new PageUtils(page);
    	}else {
    		Page<DevCarBasedatasEntity> page = this.selectPage(
                    new Query<DevCarBasedatasEntity>(params).getPage(),
                    new EntityWrapper<DevCarBasedatasEntity>().like("saleorgname", saleorgname)
                    
            );
    		return new PageUtils(page);
    	}
        

        
    }

    public Map<String,Object> checkExcel(MultipartFile  file){
        List<List<String>> all = new ArrayList<List<String>>();
        List<ExcelErrList> errmsgList = new ArrayList<ExcelErrList>();
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = true;
        List<List<String>> lists = null;
        try {
                String fileName  = file.getOriginalFilename();
                ExcelUtil excelUtil  = new ExcelUtil(file.getInputStream(),"");
                lists = excelUtil.read(0, 5, Integer.MAX_VALUE);
                List<ExcelMsgEntity> msgErrlist = new ArrayList<ExcelMsgEntity>();
                for (int i = 0;i < lists.size() - 1;i++) {
                    StringBuffer errMsg = new StringBuffer();
                    ExcelMsgEntity excelMsgEntity = new ExcelMsgEntity();
                    for (int ii = 0;ii < lists.get(i).size();ii++){ // 一行数据一个车辆实体类
                        List<String> list = lists.get(i);
                        String value = list.get(ii);

                        if(list.size() > 35){
                            if(ii == 10){
                                if(StringUtil.isEmpty(value)){
                                    errMsg.append("车辆牌照不能为空,");
                                    flag = false;
                                }else{
                                    DevCarBasedatasEntity entity = this.selectOne(new EntityWrapper<DevCarBasedatasEntity>().eq("platenumber", value));
                                    if(entity != null){
                                        errMsg.append("车辆牌照已经存在,");
                                        flag = false;
                                    }
                                }
                            }
                            if(ii == 36){
                                if(StringUtil.isEmpty(value)){
                                    errMsg.append("车辆售价不能为空,");
                                    flag = false;
                                }
                            }else if(ii == 37){
                                if(StringUtil.isEmpty(value)){
                                    errMsg.append("区市信息不能为空,");
                                    flag = false;
                                }else {
                                	 SysDictEntity entity = new SysDictEntity();
                                     entity.setItemName(value);
                                     entity.setDictType("CAR_AREA");
             					     SysDictEntity selectOne = sysDictDao.selectOne(entity);
             					     if(selectOne == null) {
             					    	 errMsg.append("区市信息不规范（例市南区）,");
                                         flag = false;
             					     }
                                }
                            }else if(ii == 38){
                                if(StringUtil.isEmpty(value)){
                                    errMsg.append("附件名字位置备注不能为空,");
                                    flag = false;
                                }
                            }
                        }else{
                            if(ii == 4){
                                if(StringUtil.isEmpty(value)){
                                    errMsg.append("车辆牌照不能为空,");
                                    flag = false;
                                }else{
                                    DevCarBasedatasEntity entity = this.selectOne(new EntityWrapper<DevCarBasedatasEntity>().eq("platenumber", value));
                                    if(entity != null){
                                        errMsg.append("车辆牌照已经存在,");
                                        flag = false;
                                    }
                                }
                            }

                        }
                        if(errMsg.toString().length() > 0){
                            String msg = errMsg.toString().substring(0,errMsg.toString().lastIndexOf(","));
                            excelMsgEntity.setRows((i + 1)+"");
                            excelMsgEntity.setErrMsg(msg);
                        }
                    }
                    msgErrlist.add(excelMsgEntity);
                }
                ExcelErrList excelErrList = new ExcelErrList();
                excelErrList.setFileName(fileName);
                excelErrList.setList(msgErrlist);
                errmsgList.add(excelErrList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){
            map.put("type",flag);
            map.put("data",lists);
        }else{
            map.put("type",flag);
            map.put("data",errmsgList);
        }
        return  map;
    }

    public List<DevCarBasedatasEntity> ExcelToDevCarBaseEntity(List<List<String>> lists,String sellerId,String uuid,String carId){
        List<DevCarBasedatasEntity> devList = new ArrayList<DevCarBasedatasEntity>();
        for (int i = 0;i < lists.size() - 1;i++) {
            StringBuffer receiptdate = new StringBuffer();
            StringBuffer runcarddate = new StringBuffer();
            DevCarBasedatasEntity carBasedataEntity = new DevCarBasedatasEntity();
            SysOrgEntity sysOrg = sysOrgDao.selectById(sellerId);
            if(sysOrg != null) {
            	carBasedataEntity.setSaleorgname(sysOrg.getOrgName());
            }
            carBasedataEntity.setSaleorgid(sellerId);
            carBasedataEntity.setYlzd1(uuid);
            carBasedataEntity.setYlzd3(carId);
            for (int ii = 0;ii < lists.get(i).size();ii++){ // 一行数据一个车辆实体类
                List<String> list = lists.get(i);
                String value = list.get(ii);

                if(list.size() > 35){
                   if(ii == 1){
                    carBasedataEntity.setCarprop(value);
                    SysDictEntity entity = new SysDictEntity();
                    entity.setItemName(value);
                    entity.setDictType("CAR_PROP");
					SysDictEntity selectOne = sysDictDao.selectOne(entity);
					if(selectOne != null) {
                       carBasedataEntity.setCarpropCode(selectOne.getItemValue());
					}
                   }else if(ii == 2){
                       carBasedataEntity.setCarcity(value);
                   }else if(ii == 3){
                       carBasedataEntity.setCarorg(value);
                   }else if(ii == 4){
                       carBasedataEntity.setCartype(value);
                       SysDictEntity entity = new SysDictEntity();
                       entity.setItemName(value);
                       entity.setDictType("CAR_TYPE");
   					   SysDictEntity selectOne = sysDictDao.selectOne(entity);
   				       if(selectOne != null) {
   					       carBasedataEntity.setCartypeCode(selectOne.getItemValue());
   				       }
                   }else if(ii == 5){
                       carBasedataEntity.setCaruse(value);
                       SysDictEntity entity = new SysDictEntity();
                       entity.setItemName(value);
                       entity.setDictType("CAR_USE");
   					   SysDictEntity selectOne = sysDictDao.selectOne(entity);
   					   if(selectOne != null) {
   					     carBasedataEntity.setCaruseCode(selectOne.getItemValue());
   					   }
                   }else if(ii == 6){
                       carBasedataEntity.setCarmodel(value);
                   }else if(ii == 7){
                       carBasedataEntity.setEkgval(Float.parseFloat(value));
                   }else if(ii == 8){
                       carBasedataEntity.setNoticebatch(value);
                   }else if(ii == 9){
                       carBasedataEntity.setVin(value);
                   }else if(ii == 10){
                       carBasedataEntity.setPlatenumber(value);
                   }else if(ii == 11){
                       carBasedataEntity.setApplystandard(value);
                   }else if(ii == 12){
                       carBasedataEntity.setPricereal(Float.parseFloat(value));
                   }else if(ii == 13){
                       carBasedataEntity.setReceiptnum(value);
                   }else if(ii == 14){
                       receiptdate.append(value+"-");
                   }else if(ii == 15){
                       receiptdate.append(value+"-");
                   }else if(ii == 16){
                       receiptdate.append(value);
                       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                       try {
                           Date date = simpleDateFormat.parse(receiptdate.toString());
                           carBasedataEntity.setReceiptdate(date);
                       } catch (ParseException e) {
                           e.printStackTrace();
                       }

                   }else if(ii == 17){
                       runcarddate.append(value+"-");
                   }else if(ii == 18){
                       runcarddate.append(value+"-");
                   }else if(ii == 19){
                       runcarddate.append(value);
                       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                       try {
                           Date date = simpleDateFormat.parse(receiptdate.toString());
                           carBasedataEntity.setRuncarddate(date);
                       } catch (ParseException e) {
                           e.printStackTrace();
                       }
                   }else if(ii == 20){
                       carBasedataEntity.setBatterysinglemodel(value);
                   }else if(ii == 21){
                       carBasedataEntity.setBatterysingleorg(value);
                   }else if(ii == 22){
                       carBasedataEntity.setBatteryboxmodel(value);
                   }else if(ii == 23){
                       carBasedataEntity.setBatterypower(Float.parseFloat(value));
                   }else if(ii == 24){
                       carBasedataEntity.setBatteryorg(value);
                   }else if(ii == 25){
                       carBasedataEntity.setBatteryprice(Float.parseFloat(value));
                   }else if(ii == 26){
                       carBasedataEntity.setBatteryqualityyear(Integer.parseInt(value));
                   }else if(ii == 27){
                       carBasedataEntity.setMachinemodel(value);
                   }else if(ii == 28){
                       carBasedataEntity.setMachinepower(Float.parseFloat(value));
                   }else if(ii == 29){
                       carBasedataEntity.setMachineorg(value);
                   }else if(ii == 30){
                       carBasedataEntity.setMachineprice(Float.parseFloat(value));
                   }else if(ii == 36){
                       carBasedataEntity.setPrice(Float.parseFloat(value));
                   }else if(ii == 37){
                       carBasedataEntity.setCararea(value);
                       SysDictEntity entity = new SysDictEntity();
                       entity.setItemName(value);
                       entity.setDictType("CAR_AREA");
   					   SysDictEntity selectOne = sysDictDao.selectOne(entity);
   					   if(selectOne != null) {
   						carBasedataEntity.setCarareaCode(selectOne.getItemValue()); 
   					   }
   					   
                   }else if(ii == 38){
                       carBasedataEntity.setLocremark(value);
                   }

                }else{
                    if(ii == 1){
                        carBasedataEntity.setCarcity(value);
                    }else if(ii == 2){
                        carBasedataEntity.setCartype(value);
                    }else if(ii == 3){
                        carBasedataEntity.setCaruse(value);
                    }else if(ii == 4){
                        carBasedataEntity.setPlatenumber(value);
                    }else if(ii == 5){
                        carBasedataEntity.setRunmiles(Float.parseFloat(value));
                    }else if(ii == 6){
                        carBasedataEntity.setChargetime(Float.parseFloat(value));
                    }else if(ii == 7){
                        carBasedataEntity.setChargepower(Float.parseFloat(value));
                    }else if(ii == 8){
                        carBasedataEntity.setTotalrunmiles(Float.parseFloat(value));
                    }else if(ii == 9){
                        carBasedataEntity.setMonthrunmiles(Float.parseFloat(value));
                    }else if(ii == 10){
                        carBasedataEntity.setPowerconsume(Float.parseFloat(value));
                    }else if(ii == 11){
                        carBasedataEntity.setDayrunhours(Float.parseFloat(value));
                    }else if(ii == 12){
                    }else if(ii == 13){
                    }else if(ii == 14){
                    }else if(ii == 15){
                    }else if(ii == 16){
                        carBasedataEntity.setTotalchargepower(Float.parseFloat(value));
                    }else if(ii == 17){
                        carBasedataEntity.setIsmonitor(value);
                    }else if(ii == 18){
                        carBasedataEntity.setMonitororg(value);
                    }
                }
            }
            devList.add(carBasedataEntity);
        }
        return devList;
    }

	@Override
	public PageUtils carApplyprogressListDataPage(Map<String, Object> params) {
		Page p=new Page(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
		List<Map> list=baseMapper.carApplyprogressListDataPage(p, params);
        p.setRecords(list);
		return new PageUtils(p);
	}
	
	@Override
	public void updateCarApplyNullFlag(String id) {
		// TODO Auto-generated method stub
		baseMapper.updateCarApplyNullFlag(id);
	}
}
