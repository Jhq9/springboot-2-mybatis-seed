package isa.qa.project.utils;

import java.math.BigDecimal;

/**
 *  数字处理工具类
 *
 *  @author    May
 *  @date      2018/11/16 17:48
 *  @version   1.0
 */
public class NumberUtils {

    /**
     * 对一个Float数字进行四舍五入
     *
     * @param number 数字
     * @param scale 精度
     * @return 数字
     */
    public static Float scaleFloat(Float number, int scale) {
        BigDecimal bigDecimal = new BigDecimal(number);

        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
