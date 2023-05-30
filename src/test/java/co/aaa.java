package co;

import com.example.server.pojo.Buglist;
import com.example.server.utils.RestAssuredUtil;
import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;

public class aaa {

    public void ss(){

    }


    public static void main(String[] args) throws Exception {


//        System.out.println(k);

//        Map<Object, Object> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("sdgs","sdgsg");
//        List<Object> objects = new ArrayList<>();
//        Set<Object> objects1 = new HashSet<>();
//        objects.add(3);
//        objects.add(36);
//        objects.add(35);
//        objects1.add(1);
//        objects1.add("开");
//        objects1.add("啊");
//        objects1.add(235);
//        System.out.println(objects.containsAll(objects));
//        System.out.println(objects.indexOf(36));
//        System.out.println(objects1);


//        Buglist buglist = new Buglist();
//        System.out.println(buglist.getId());


//        String s="dfg26d46846sb53ssg3454646646844fhdfghdh876867868";
//        String d="0123456789";
//        List list = new ArrayList();
//        int a=0;
//        String str="";
//        for (int i=0;i<s.length();i++){
//            String ss=String.valueOf(s.charAt(i));
//            if (d.contains(ss)){
//                str=str+ss;
//                if (i==s.length()-1){
//                    if (a==str.length()){
//                        list.add(str);
//                    }
//                    else if (a<str.length()){
//                        list.clear();
//                        list.add(str);
//                    }
//                }
//            }
//            else {
//                if (str.length()>0){
//                    int b=str.length();
//                    if (a==0){
//                        list.add(str);
//                        a=b;
//                    }
//                    else if (a<b){
//                        list.clear();
//                        list.add(str);
//                        a=b;
//                    }else if (a==b){
//                        list.add(str);
//                    }
//                }
//                str="";
//            }
//        }
//        System.out.println(list.get(0));
//
//        List list = new ArrayList<>();
//        list.add(244);
//        list.add("mdbgj");
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder=stringBuilder.append(list);
//        System.out.println(stringBuilder);



//        String s="24f";
//        String str="看见好看接口";
//        String d="sd,35,346,sgwetr";
//        System.out.println(!s.isEmpty());
//        char[] chars = s.toCharArray();
//        System.out.println(String.valueOf(chars).startsWith("2"));
//        System.out.println(s.endsWith("2"));
////        byte[] bytes = d.getBytes();
//        String[] split = d.split("5");
//        String s1 = Arrays.toString(split);
//        System.out.println(s1);
//
//        System.out.println(d.replace("35","567"));
//        System.out.println(d.substring(2,6));





//        String z=" 355d gsg46 ";
//        System.out.println(Arrays.toString(z.split("5")));
//        System.out.println(z.trim());
//        byte[] bytes = z.getBytes();
//        System.out.println(Arrays.toString(bytes));
//        System.out.println(String.valueOf(z.toCharArray()));
//
//        System.out.println(z.isEmpty());
//
//        System.out.println(z.contains("gs"));
//        System.out.println(z.startsWith(" "));
//        System.out.println(z.endsWith(" "));
//        System.out.println(z.charAt(3));
//        System.out.println(z.indexOf("d"));
//        System.out.println(z.equals(" 355"));
//        System.out.println(z.toLowerCase());
//        System.out.println(z.toUpperCase());
//        System.out.println(z.substring(2));
        String a="25羟基维生素D,B型利钠肽N端片段,B淋巴细胞百分比(CD19),I型胶原羧基端肽β交联肽(β-cTX),NK细胞百分比(CD16+56),T抑制细胞百分比(CD8),T淋巴细胞百分比(CD3),T辅助细胞百分比(CD4),α肿瘤坏死因子（αTNF）,γ-谷胺酰基转移酶(γ-GT),三碘甲状腺原氨酸(T3),丙氨酸氨基转移酶（ALT）,乳酸脱氢酶(LDH),二氧化碳结合力(CO2-CP),人附睾蛋白4(HE4),低密度脂蛋白胆固醇(LDL-ch),促甲状腺激素受体抗体(TRAb),免疫球蛋白IgA(IgA）,免疫球蛋白IgE(IgE),免疫球蛋白IgG(IgG）,免疫球蛋白IgG4,免疫球蛋白IgM(IgM),凝血酶原时间测定（PT）,前白蛋白(PA),同型半胱氨酸(HCY),尿微量白蛋白（随机尿）,尿液有形成份检查,尿液物理化学检查,尿素(BU),尿肌酐（随机尿）,尿酸(UA),心肌肌钙蛋白T,总胆固醇(Tch),总胆汁酸(TBA),总胆红素(TB),总蛋白Tp,抗甲状腺球蛋白抗体(anti-TG),抗甲状腺过氧化物酶抗体(Anti-TPO),抗缪勒管激素（AMH）检测";
        String b="乳酸脱氢酶(LDH),二氧化碳结合力(CO2-CP),人附睾蛋白4(HE4),低密度脂蛋白胆固醇(LDL-ch),sdf,sdgdfhg";

        String[] strings=a.split(",");
        String[] strings2=b.split(",");
        String c;
        for (String s:strings ){
            for (String g:strings2){
                if (s.equals(g)){
                    System.out.println(s);
                }
            }

        }








    }
}
