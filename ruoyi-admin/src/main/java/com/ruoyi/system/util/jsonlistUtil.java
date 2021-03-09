package com.ruoyi.system.util;

import com.ruoyi.common.utils.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qincan
 * @create: 2021-02-05 12:38
 * @description:
 * @version: 1.0
 */

public class jsonlistUtil {
    public static List<String[]> getJsonList(String jsonlist,String [] title){
        JSONArray productArray = JSONArray.fromObject(jsonlist);
        List<String[]> list=new ArrayList<>();
        for (int i = 0; i < productArray.size(); i++) {
            JSONObject jsonObject = productArray.getJSONObject(i);
           String [] array=new String[title.length];
            for (int i1 = 0; i1 < title.length; i1++) {
                if(jsonObject.has(title[i1])&& StringUtils.isNotBlank(jsonObject.getString(title[i1]))){
                    array[i1]=jsonObject.getString(title[i1]);
                }else{
                    array[i1]=null;
                }
            }
            list.add(array);
        }
        return list;
    }
}
