package com.hhy.coursemanage.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;

/**
 * @program: kci_onlinesys
 * @description: 社区功能组件
 * @author: PlusL
 * @create: 2022-03-16 09:48
 **/
public class CommunityUtil {

    /**
     * 生成随机字符串
     * @return
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * MD5加密，用于密码加密，MD5只加密不解密
     * @param key
     * @return
     */
    public static String md5(String key){
        if(StringUtils.isEmpty(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    /**
     * 获取Json格式的字符串
     * @param code 编号
     * @param msg 返回信息（错误信息）
     * @param map 返回值
     * @return
     */
    public static String getJsonString(int code, String msg, Map<String, Object> map){
        JSONObject json = new JSONObject();
        json.put("code" ,code);
        json.put("msg" ,msg);
        if(map != null) {
            for (String key : map.keySet()){
                //将map中的值传入json
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }

    /**
     * 处理返回数据为空的情况，重载了getJsonString
     * @param code
     * @param msg
     * @return
     */
    public static String getJsonString(int code, String msg) {
        return getJsonString(code, msg, null);
    }

    public static String getJsonString(int code) {
        return getJsonString(code, null, null);
    }



}
