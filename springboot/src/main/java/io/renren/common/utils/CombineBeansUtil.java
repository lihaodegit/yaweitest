package io.renren.common.utils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import io.renren.modules.carBaseData.entity.DevCarBasedatasEntity;

public class CombineBeansUtil {
    
    
    /**
     * 该方法是用于相同对象不同属性值的合并，如果两个相同对象中同一属性都有值，那么sourceBean中的值会覆盖tagetBean重点的值
     * @param devCarBasedatasEntitytw    被提取的对象bean
     * @param devCarBasedatasEntity    用于合并的对象bean
     * @return targetBean,  合并后的对象
     */
    public static DevCarBasedatasEntity combineSydwCore(DevCarBasedatasEntity devCarBasedatasEntitytw,DevCarBasedatasEntity devCarBasedatasEntity){
        Class sourceBeanClass = devCarBasedatasEntitytw.getClass();
        Class targetBeanClass = devCarBasedatasEntity.getClass();
        
        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();
        for(int i=0; i<sourceFields.length; i++){
            Field sourceField = sourceFields[i]; 
          if(Modifier.isStatic(sourceField.getModifiers())){
             continue;
           }
            Field targetField = targetFields[i];  
          if(Modifier.isStatic(targetField.getModifiers())){
            continue;
           }
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if( !(sourceField.get(devCarBasedatasEntitytw) == null) &&  !"serialVersionUID".equals(sourceField.getName().toString())){
                    targetField.set(devCarBasedatasEntity,sourceField.get(devCarBasedatasEntitytw));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return devCarBasedatasEntity;
    }
    
   
}