package io.niceseason.gulimall.ware.enume;

public enum WareTaskStatusEnum {

    UnLock("未锁定", 0),
    Locked("锁定", 1),
    hasUnLocked("已解锁", 2);


    private String msg;
    private Integer code;

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    WareTaskStatusEnum(String msg, Integer code){
        this.code = code;
        this.msg = msg;
    }
}
