package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Student.
 *
 * @author truon
 * @version 1.0
 * @created 05 -Nov-2017 4:08:31 PM
 */
@Table(name = "STUDENT")
@Getter
@Setter
public class Student extends BaseEntity {

    @Column(name = "USER_ID")
    private int userId;

        @Column(name = "STUDENT_ID")
    private String studentId;

    @Column(name = "CLASS_ID")
    private int classId;

    @Column(name = "CV_LINK")
    private String cv;

    @Column(name = "TEAM_ID")
    private int teamId;

}