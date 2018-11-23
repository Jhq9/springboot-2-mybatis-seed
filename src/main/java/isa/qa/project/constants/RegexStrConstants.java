package isa.qa.project.constants;

/**
 * 通用的正则表达式常量类
 *
 * @author May
 * @version 1.0
 * @date 2018/5/14 19:14
 */
public final class RegexStrConstants {

    /**
     * 国内手机号码 正则表达式
     */
    public static final String PHONE_REGEX = "0?(13|14|15|17|18|19)[0-9]{9}";

    /**
     * 扫描得到的url里匹配{id}字符串
     */
    public static final String PATH_VARIABLE_REGEX = "\\{([^{}]+)\\}";

    /**
     * 路径上id值的正则
     */
    public static final String ID_NUM_REGEX = "[1-9][0-9]*";
}
