package com.tenddata.openapi.Tool;

import java.util.List;
import java.util.Map;

/**
 * Created by obama on 2016/12/30.
 */
public class Authorize extends Tool {

    public static void addAddAuthorize(String email, String appid) {
        List<Map<String, Object>> mapList = dao.queryProductListByAppid(appid);
        //先判断应用是否存在
        if (mapList == null || mapList.size() < 1) {
            System.out.println("应用不存在");
            return;
        }
        String developerid = mapList.get(0).get("developerid").toString();
        String insert1 = "insert into authorized_account values (null,'" + developerid + "','" + email + "','" +
                "summarize,newUserStartup,activeAnalysis,timeRangeAnalysis,countryDistribution,provinceDistribution,versions,userMobiles,userPixel,userOs,userOperators,userNetworking,exception,exceptionList,exceptionDetail,jailbroken,partnerData,keepInfoCustom,keepInfoStandard,loseUser,loseFunnel,lifeCycle,userReturn,userStartup,useInterval,useTime,pageInfo,eventData,eventDetail,pushlist,smsAuthInfo,smsAuthDetail,smsAuthConfig,smsAuthApply,userQualityAccess" +
                "',null" + ")";
        dao.update(insert1);
        int authorizedid = dao.queryAuthorizedidByEmail(email, developerid);
        for (Map<String, Object> map : mapList) {
            String insert = "insert into authorized_list values (" + authorizedid + "," + map.get("productid") + "," + map.get("platformid") + ")";
            dao.update(insert);
        }
    }

}
