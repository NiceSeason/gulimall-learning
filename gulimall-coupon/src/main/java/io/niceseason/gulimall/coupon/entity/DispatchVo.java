package io.niceseason.gulimall.coupon.entity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DispatchVo {
    public String id;
    public String userId;
    public String classId;
    public String departmentId;
    public String companyId;
}
