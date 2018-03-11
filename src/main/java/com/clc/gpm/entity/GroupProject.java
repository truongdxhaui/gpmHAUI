package com.clc.gpm.entity;


/*
* Nhóm đề tài
* */

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * The type Group project.
 */
@Getter
@Setter
@Table(name = "GROUP_PROJECT")
@Alias("GroupProject")
public class GroupProject extends BaseEntity {

    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LECTURER_ID")
    private int lecturerId;
}
