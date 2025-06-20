package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *体检套餐：(PhysicalExaminationPackage)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "PhysicalExaminationPackage")
public class PhysicalExaminationPackage implements Serializable {

    //PhysicalExaminationPackage编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "physical_examination_package_id")
    private Integer physical_examination_package_id;
    // 套餐名称
    @Basic
    private String package_name;
    // 体检禁忌
    @Basic
    private String physical_examination_taboo;
    // 注意事项
    @Basic
    private String matters_needing_attention;
    // 套餐费用
    @Basic
    private String package_fee;
    // 套餐封面
    @Basic
    private String package_cover;
    // 体检项目详情
    @Basic
    private String details_of_physical_examination_items;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
