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
        int authorizedid = dao.queryAuthorizedidByEmail(email, developerid);
        if (authorizedid != 0) {
            System.out.println("authorized_account已添加");
        } else {
            String insert1 = "insert into authorized_account values (null,'" + developerid + "','" + email + "','" +
                    "eventManage,eventLabelManage,userGroup,warningCenter,milepostManage,settingMail,versionManage,partnerManage,openApi,eventCloud,pushconfigure,summarize,newUserStartup,activeAnalysis,timeRangeAnalysis,countryDistribution,provinceDistribution,versions,userMobiles,userPixel,userOs,userOperators,userNetworking,exception,exceptionList,exceptionDetail,jailbroken,partnerData,keepInfoCustom,keepInfoStandard,loseUser,loseFunnel,lifeCycle,userReturn,userStartup,useInterval,useTime,pageInfo,eventData,eventDetail,pushlist,smsAuthInfo,smsAuthDetail,smsAuthConfig,smsAuthApply,userQualityAccess" +
                    "',null,1,null,null" + ")";
            dao.update(insert1);
            authorizedid = dao.queryAuthorizedidByEmail(email, developerid);
        }
        for (Map<String, Object> map : mapList) {
            String insert = "insert into authorized_list values (" + authorizedid + "," + map.get("productid") + "," + map.get("platform") + ")";
            dao.update(insert);
        }
    }

}
