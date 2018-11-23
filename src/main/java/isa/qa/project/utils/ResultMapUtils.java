package isa.qa.project.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *  Result map generator utils
 *
 *  @author    May
 *  @date      2018/11/22 9:30
 *  @version   1.0
 */
public class ResultMapUtils {

    /**
     * Generator the save request's response map
     *
     * @param key id name
     * @param id saved id value
     * @return result map
     */
    public static Map<String, Long> genIdResultMap(String key, Long id) {
        Map<String, Long> resultMap = new HashMap<>(1);

        resultMap.put(key, id);

        return resultMap;
    }

    /**
     * Generator the update or delete request's response map
     *
     * @param key result map key
     * @param isSuccess operator is success
     * @return result map
     */
    public static Map<String, Boolean> genUpdateResultMap(String key, Boolean isSuccess) {
        Map<String, Boolean> resultMap = new HashMap<>(1);

        resultMap.put(key, isSuccess);
        return resultMap;
    }

}
