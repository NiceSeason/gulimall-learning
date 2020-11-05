package io.niceseason.gulimall.order.enume;

public enum OrderStatusEnum {

    CREATE_NEW(0,"待付款"),
    PAYED(1,"已付款"),
    SENDED(2,"已发货"),
    RECIEVED(3,"已完成"),
    CANCLED(4,"已取消"),
    SERVICING(5,"售后中"),
    SERVICED(6,"售后完成");


    private String msg;
    private Integer code;

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    OrderStatusEnum(Integer code, String msg){
        this.msg = msg;
        this.code = code;
    }
}
