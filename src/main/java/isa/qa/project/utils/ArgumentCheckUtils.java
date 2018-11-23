package isa.qa.project.utils;

import java.util.Collection;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 *  常用的数据校验工具类
 *  
 *  @author    May
 *  @date      2018/7/30 14:05
 *  @version   1.0
 */
public class ArgumentCheckUtils {

    private ArgumentCheckUtils() {
    }

    /**
     * 校验对象是否为空
     *
     * @param object 对象
     * @param message 错误信息
     */
    public static void checkNonNull(Object object, String message) {
        checkArgument(Objects.nonNull(object), message);
    }

    /**
     * 校验是否是空集合
     *
     * @param collection 集合
     * @param message 错误信息
     */
    public static void checkEmptyCollection(Collection<?> collection, String message) {
        checkArgument(Objects.nonNull(collection) && collection.size() >= 1, message);
    }

    /**
     * 校验a与b是否相等
     *
     * @param a value a
     * @param b value b
     * @param message error message
     */
    public static void checkEquals(Object a, Object b, String message) {
        checkArgument(Objects.equals(a, b), message);
    }
}
