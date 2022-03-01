package com.example.server.pojo;

public class Initialization {
    private static Report report=new Report();

    public void testngstart_s(){
        report.setBasesum(0);
        report.setErrorsum(0);
        report.setSuccesssum(0);
        report.setSuccessfullist(null);
        report.setSuccessfullist(null);
    }
}
