package com.sc.bean;

public class TestCaseDB {
    private  int id;
    private String type;

    private String url;

    private String params;

    private String header;

    private String result;

    private String checkP;

    private String correlation;

    private boolean validFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCheckP() {
        return checkP;
    }

    public void setCheckP(String checkP) {
        this.checkP = checkP;
    }

    public String getCorrelation() {
        return correlation;
    }

    public void setCorrelation(String correlation) {
        this.correlation = correlation;
    }

    public boolean isValidFlag() {
        return validFlag;
    }

    public void setValidFlag(boolean validFlag) {
        this.validFlag = validFlag;
    }

    @Override
    public String toString() {
        return "TestCaseDB{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", params='" + params + '\'' +
                ", header='" + header + '\'' +
                ", result='" + result + '\'' +
                ", checkP='" + checkP + '\'' +
                ", correlation='" + correlation + '\'' +
                ", validFlag=" + validFlag +
                '}';
    }
}
