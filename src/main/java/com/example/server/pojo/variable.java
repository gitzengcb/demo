package com.example.server.pojo;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class variable {
    private JSONObject jsonrequest =new JSONObject();
    private JSONObject jsonHeader =new JSONObject();
//    private JSONObject jsonInputParameter =new JSONObject();
    private JSONObject jsonOutputParameter =new JSONObject();
    private JSONObject responsebody =new JSONObject();
    private Testrecords testrecords=new Testrecords();
    private Failcase failcase=new Failcase();
    private List<Failcase> failcaselist=new ArrayList<>();
//    private List<String> inputlist=new ArrayList<>();


    public Testrecords getTestrecords() {
        return testrecords;
    }

    public void setTestrecords(Testrecords testrecords) {
        this.testrecords = testrecords;
    }

    public JSONObject getJsonrequest() {
        return jsonrequest;
    }

    public void setJsonrequest(JSONObject jsonrequest) {
        this.jsonrequest = jsonrequest;
    }

    public JSONObject getJsonHeader() {
        return jsonHeader;
    }

    public void setJsonHeader(JSONObject jsonHeader) {
        this.jsonHeader = jsonHeader;
    }


    public JSONObject getJsonOutputParameter() {
        return jsonOutputParameter;
    }

    public void setJsonOutputParameter(JSONObject jsonOutputParameter) {
        this.jsonOutputParameter = jsonOutputParameter;
    }

    public JSONObject getResponsebody() {
        return responsebody;
    }

    public void setResponsebody(JSONObject responsebody) {
        this.responsebody = responsebody;
    }

    public Failcase getFailcase() {
        return failcase;
    }

    public void setFailcase(Failcase failcase) {
        this.failcase = failcase;
    }

    public List<Failcase> getFailcaselist() {
        return failcaselist;
    }

    public void setFailcaselist(List<Failcase> failcaselist) {
        this.failcaselist = failcaselist;
    }


    @Override
    public String toString() {
        return "variable{" +
                ", jsonrequest=" + jsonrequest +
                ", jsonHeader=" + jsonHeader +
//                ", jsonInputParameter=" + jsonInputParameter +
                ", jsonOutputParameter=" + jsonOutputParameter +
                ", responsebody=" + responsebody +
                ", failcase=" + failcase +
                ", failcaselist=" + failcaselist +
//                ", inputlist=" + inputlist +
                '}';
    }
}
