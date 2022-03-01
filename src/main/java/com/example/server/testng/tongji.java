package com.example.server.testng;

import java.time.LocalDateTime;

public class tongji {
    private  int basesum;
    private  int successsum;
    private  int errorsum;
    private  String successfullist;
    private  String failurelist;
    private  LocalDateTime createTime;


    public  int getErrorsum() {
        return errorsum;
    }

    public  int getSuccesssum() {
        return successsum;
    }

    public  int getBasesum() {
        return basesum;
    }

    public  void setErrorsum(int errorsum) {
        this.errorsum = errorsum;
    }

    public  void setBasesum(int sum) {
        this.basesum = sum;
    }

    public  void setSuccesssum(int successsum) {
        this.successsum = successsum;
    }
    public  void setFailurelist(String failurelist) {
        this.failurelist = failurelist;
    }

    public  void setSuccessfullist(String successfullist) {
        this.successfullist = successfullist;
    }

    public  String getFailurelist() {
        return failurelist;
    }

    public  String getSuccessfullist() {
        return successfullist;
    }

    public  void setCreateTime(LocalDateTime createTime) {
        this.createTime=createTime;
    }

    public  LocalDateTime getCreateTime() {
        return createTime;
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }


    @Override
    public String toString() {
        return "tongji{" +
                "basesum=" + basesum +
                ", successsum=" + successsum +
                ", errorsum=" + errorsum +
                ", successfullist=" + successfullist +
                ", failurelist=" + failurelist +
                ", createTime=" + createTime +
                '}';
    }
}
