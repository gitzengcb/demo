package com.example.server.pojo;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.xsom.impl.scd.Iterators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class variable {
    private Report report = new Report();
    private JSONObject jsonrequest =new JSONObject();
    private JSONObject jsonHeader =new JSONObject();
    private JSONObject jsonInputParameter =new JSONObject();
    private JSONObject jsonOutputParameter =new JSONObject();
    private JSONObject responsebody =new JSONObject();
    private Failcase failcase=new Failcase();
    private List<Failcase> failcaselist=new ArrayList<>();

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
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

    public JSONObject getJsonInputParameter() {
        return jsonInputParameter;
    }

    public void setJsonInputParameter(JSONObject jsonInputParameter) {
        this.jsonInputParameter = jsonInputParameter;
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
                "report=" + report +
                ", jsonrequest=" + jsonrequest +
                ", jsonHeader=" + jsonHeader +
                ", jsonInputParameter=" + jsonInputParameter +
                ", jsonOutputParameter=" + jsonOutputParameter +
                ", responsebody=" + responsebody +
                ", failcase=" + failcase +
                ", failcaselist=" + failcaselist +
                '}';
    }
}
