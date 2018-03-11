package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Table(name = "SCHOOL")
@Getter
@Setter
@Alias("School")
public class School extends BaseEntity{

    @Column(name = "ID")
    private Integer id;

    @Column(name = "SCHOOL_ID")
    private String schoolId;

    @Column(name = "NAME")
    private String name;

}
