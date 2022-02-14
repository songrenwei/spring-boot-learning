package com.srw.business.helper;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @Description: 参数摘要
 * @Author: songrenwei
 * @Date: 2020/12/10/10:53
 */
public class ParamDigestHelper {

    /**
     *
     * @param reqJSON 请求的参数，这里通常是JSON
     * @param excludeKeys 请求参数里面要去除那些可变值字段再求摘要
     * @return 去除参数的MD5摘要
     */
    public static String delDuplicationMD5(final String reqJSON, String... excludeKeys) {
        TreeMap paramTreeMap = JSONUtil.toBean(reqJSON, TreeMap.class);
        if (excludeKeys!=null) {
            List<String> dedupExcludeKeys = Arrays.asList(excludeKeys);
            if (!dedupExcludeKeys.isEmpty()) {
                for (String dedupExcludeKey : dedupExcludeKeys) {
                    paramTreeMap.remove(dedupExcludeKey);
                }
            }
        }

        String paramTreeMapJSON = JSONUtil.toJsonStr(paramTreeMap);
        String md5deDupParam = SecureUtil.md5(paramTreeMapJSON);
        return md5deDupParam;
    }

}
