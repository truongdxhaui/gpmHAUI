package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.PrimaryKey;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * The type Lecturer.
 *
 * @author truon
 * @version 1.0
 * @created 05 -Nov-2017 4:08:31 PM
 */
@Getter
@Setter
@Table(name = "LECTURER")
@Alias("Lecturer")
public class Lecturer extends BaseEntity{

    @Column(name = "USER_ID")
    private int userId;

    @PrimaryKey(name = "LECTURER_ID")
    private Integer lecturerId;

    @Column(name = "POSITION")
    private int position;

    @Column(name = "SPECIALIZE")
    private String specialize;

    @Column(name = "FACULTY_ID")
    private int facultyId;
}