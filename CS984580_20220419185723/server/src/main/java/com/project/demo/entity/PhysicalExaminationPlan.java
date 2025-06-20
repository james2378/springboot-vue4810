package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *体检计划：(PhysicalExaminationPlan)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "PhysicalExaminationPlan")
public class PhysicalExaminationPlan implements Serializable {

    //PhysicalExaminationPlan编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "physical_examination_plan_id")
    private Integer physical_examination_plan_id;
    // 体检人员
    @Basic
    private Integer physical_examination_personnel;
    // 姓名
    @Basic
    private String full_name;
    // 性别
    @Basic
    private String gender;
    // 年龄
    @Basic
    private String age;
    // 套餐名称
    @Basic
    private String package_name;
    // 套餐费用
    @Basic
    private String package_fee;
    // 预约日期
    @Basic
    private Timestamp appointment_date;
    // 体检项目详情
    @Basic
    private String details_of_physical_examination_items;
    // 审核状态
    @Basic
    private String examine_state;
    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
