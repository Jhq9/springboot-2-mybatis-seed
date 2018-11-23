package isa.qa.project.utils;

import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 *  Bean Copy Utils
 *
 *  @author    May
 *  @date      2018/11/21 9:41
 *  @version   1.0
 */
public class CglibBeanCopierUtils {

    /**
     * 缓存用过的每个类对应的BeanCopier
     */
    private static Map<String, BeanCopier> beanCopierMap = new HashMap<>();

    private CglibBeanCopierUtils() {
    }

    /**
     * @param source 资源类
     * @param target 目标类
     * @title copyProperties
     * @desc (bean属性转换无)
     * @author yushaojian
     * @date 2015年11月25日下午4:56:44
     */
    public static void copyProperties(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }

}
