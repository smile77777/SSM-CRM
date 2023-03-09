package com.ljc.crm.commons.pojo;

public class ReturnObject {
    private String code;//1成功，0失败
    private String message;//提示信息
    private Object retrunData;//返回的其他数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetrunData() {
        return retrunData;
    }

    public void setRetrunData(Object retrunData) {
        this.retrunData = retrunData;
    }
}
