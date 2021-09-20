import api.Ascm_Api;
import api.Ecs_Api;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.*;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class start {

    public static requestParams rp = new requestParams();
    public static String temp = "";
    public static int count = 0;


    public static void main(String[] args) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            //读取配置文件
            String confJson = new FileUtil().readFileInSameFolder();
            JsonNode confJn = mapper.readTree(confJson);
            rp = mapper.readValue(confJn.toString(), requestParams.class);


            //获取root下所有组织信息
            String GetOrganizationTreeJson = new Ascm_Api().GetOrganizationTree(rp);
            JsonNode orgTreejn = mapper.readTree(GetOrganizationTreeJson);
            orgTreejn = orgTreejn.get("data").get("children");


            //反序列化组织信息
            orgInfo oi = new orgInfo();
            List<orgInfo> oilist = new ArrayList<>();
            for(int i=0;i<orgTreejn.size();i++) {
                oi = mapper.readValue(orgTreejn.get(i).toString(), orgInfo.class);
                oilist.add(oi);
            }

            //筛选出一级组织下的所有二、三、四级组织ID加入List
            List<orderOrgTree> ootlist = new ArrayList<>();

            //一级组织
            for(int i=0;i<oilist.size();i++) {
                orderOrgTree oot = new orderOrgTree();
                oot.setFirstOrgNum(oilist.get(i).getId());
                //二级组织
                if(oilist.get(i).getChildren() != null) {
                    for(int j=0;j<oilist.get(i).getChildren().size();j++) {
                        oot.setChildren(oilist.get(i).getChildren().get(j).getId());
                        //三级组织
                        if(oilist.get(i).getChildren().get(j).getChildren() != null) {
                            for(int k=0;k<oilist.get(i).getChildren().get(j).getChildren().size();k++) {
                                oot.setChildren(oilist.get(i).getChildren().get(j).getChildren().get(k).getId());
                                //四级组织
                                if(oilist.get(i).getChildren().get(j).getChildren().get(k).getChildren() != null) {
                                    for(int t=0;t<oilist.get(i).getChildren().get(j).getChildren().get(k).getChildren().size();t++) {
                                        oot.setChildren(oilist.get(i).getChildren().get(j).getChildren().get(k).getChildren().get(t).getId());
                                    }
                                }
                            }
                        }
                    }
                }
                ootlist.add(oot);
            }


            //获取当前所有ECS硬盘
            String diskInfojson = new Ecs_Api().DescribeDisks(rp);
            JsonNode diskInfojn = mapper.readTree(diskInfojson);
            diskInfojn = diskInfojn.get("Disks").get("Disk");

            //反序列化ECS
            diskInfo di = new diskInfo();
            List<diskInfo> dilist = new ArrayList<>();
            for(int i=0;i<diskInfojn.size();i++) {
                di = mapper.readValue(diskInfojn.get(i).toString(), diskInfo.class);
                dilist.add(di);
            }


            //获取所有自动快照策略
            String AutoSnapshotPolicyJson = new Ecs_Api().DescribeAutoSnapshotPolicyEX(rp);
            JsonNode AutoSnapshotPolicyjn = mapper.readTree(AutoSnapshotPolicyJson);
            AutoSnapshotPolicyjn = AutoSnapshotPolicyjn.get("AutoSnapshotPolicies").get("AutoSnapshotPolicy");


            //反序列化自动快照策略json
            AutoSnapshotPolicyEty aspe = new AutoSnapshotPolicyEty();
            List<AutoSnapshotPolicyEty> aspelist = new ArrayList<>();
            for(int i=0;i<AutoSnapshotPolicyjn.size();i++) {
                aspe = mapper.readValue(AutoSnapshotPolicyjn.get(i).toString(), AutoSnapshotPolicyEty.class);
                aspelist.add(aspe);
            }


            //筛选出未设置自动快照策略的硬盘，并设置自动快照策略
            for(int i=0;i<dilist.size();i++) {
                if(dilist.get(i).isEnableAutomatedSnapshotPolicy() != true){
                    count++;
                    a:for(orderOrgTree firstOrg:ootlist) {
                        //硬盘属于一级组织
                        if(firstOrg.getFirstOrgNum() == dilist.get(i).getDepartment()){
                            for(AutoSnapshotPolicyEty aspeTemp:aspelist) {
                                //一级组织下有对应的自动快照策略
                                if(aspeTemp.getDepartment() == firstOrg.getFirstOrgNum()){
                                    temp = new Ecs_Api().ApplyAutoSnapshotPolicy(rp, aspeTemp.getAutoSnapshotPolicyId(), dilist.get(i).getDiskId(), String.valueOf(aspeTemp.getDepartment()));
                                    if(temp.contains("ode\":\"200"))
                                        count--;
                                    break a;
                                }
                            }
                        }
                        //硬盘属于下级组织
                        for(int subOrgNum:firstOrg.getChildren()) {
                            if(subOrgNum == dilist.get(i).getDepartment()) {
                                for(AutoSnapshotPolicyEty aspeTemp:aspelist) {
                                    //下级组织下有对应的自动快照策略
                                    if(aspeTemp.getDepartment() == firstOrg.getFirstOrgNum()){
                                        temp = new Ecs_Api().ApplyAutoSnapshotPolicy(rp, aspeTemp.getAutoSnapshotPolicyId(), dilist.get(i).getDiskId(), String.valueOf(dilist.get(i).getDepartment()));
                                        if(temp.contains("ode\":\"200"))
                                            count--;
                                        break a;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(count == 0)
                System.out.println("success");
            System.out.println("error");


        }catch (Exception e) {
            System.out.println("error");
        }
    }
}
