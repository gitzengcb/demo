package co;

import com.example.server.utils.RestAssuredUtil;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class aaa {
    public static void main(String[] args) throws Exception {
//        String s="dfg26d46846sb53ssg46844fhdfghdh876867868";
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
//        System.out.println(list);

        List list = new ArrayList<>();
        list.add(244);
        list.add("mdbgj");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder=stringBuilder.append(list);
        System.out.println(stringBuilder);
    }
}
