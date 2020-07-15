package com.tab.quizdemo;

public class QuestionModel {
    String qid;
    String q_text;
    String q_o1;
    String q_o2;
    String q_o3;
    boolean isAttempted;

    public boolean isAttempted() {
        return isAttempted;
    }

    public void setAttempted(boolean attempted) {
        isAttempted = attempted;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    String totalTime;

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    String timer;

    public String getqRes() {
        return qRes;
    }

    public void setqRes(String qRes) {
        this.qRes = qRes;
    }

    String qRes;

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQ_text() {
        return q_text;
    }

    public void setQ_text(String q_text) {
        this.q_text = q_text;
    }

    public String getQ_o1() {
        return q_o1;
    }

    public void setQ_o1(String q_o1) {
        this.q_o1 = q_o1;
    }

    public String getQ_o2() {
        return q_o2;
    }

    public void setQ_o2(String q_o2) {
        this.q_o2 = q_o2;
    }

    public String getQ_o3() {
        return q_o3;
    }

    public void setQ_o3(String q_o3) {
        this.q_o3 = q_o3;
    }

    public String getQ_o4() {
        return q_o4;
    }

    public void setQ_o4(String q_o4) {
        this.q_o4 = q_o4;
    }

    public int getCor_o() {
        return cor_o;
    }

    public void setCor_o(int cor_o) {
        this.cor_o = cor_o;
    }

    String q_o4;
    int cor_o;


}
