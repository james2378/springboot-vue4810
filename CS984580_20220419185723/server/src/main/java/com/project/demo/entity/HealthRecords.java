package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *健康档案：(HealthRecords)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HealthRecords")
public class HealthRecords implements Serializable {

    //HealthRecords编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_records_id")
    private Integer health_records_id;
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
    // 体检日期
    @Basic
    private Timestamp date_of_physical_examination;
    // 医生
    @Basic
    private String doctor;
    // 体检项目详情
    @Basic
    private String details_of_physical_examination_items;
    // 健康档案
    @Basic
    private String health_records;
    // 健康指南
    @Basic
    private String health_guidelines;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
