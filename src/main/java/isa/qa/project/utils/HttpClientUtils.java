package isa.qa.project.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *  Http 工具类
 *
 *  @author    May
 *  @date      2018/11/21 10:48
 *  @version   1.0
 */
public class HttpClientUtils {

    private static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";

    private static final String IP_UNKNOWN = "unknown";

    private static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";

    private static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    private static final String HEADER_HTTP_CLIENT_IP = "HTTP_CLIENT_IP";

    private static final String HEADER_HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    /**
     * comma -> 逗号分隔符
     */
    private static final String SEPARATOR_COMMA = ",";

    /**
     * 从Request的Header中解析出ip address
     */
    public static String getIpAddress(final HttpServletRequest request) {
        String ip = request.getHeader(HEADER_X_FORWARDED_FOR);
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_HTTP_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_HTTP_X_FORWARDED_FOR);
        }
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.contains(SEPARATOR_COMMA)) {
            ip = ip.substring(0, ip.indexOf(SEPARATOR_COMMA)).trim();
        }

        return ip;
    }
}
