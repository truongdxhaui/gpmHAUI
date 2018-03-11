package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.PrimaryKey;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * The type Graduationthesis.
 */
@Getter
@Setter
@Table(name = "GRADUATION_THESIS")
@Alias("Graduationthesis")
public class Graduationthesis extends BaseEntity {

    @PrimaryKey(name = "ID")
    private int id;

    @Column(name = "REGISTRATION_FORM_ID")
    private int registrationFormId;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "LINK_PROJECT")
    private String linkProject;

    @Column(name = "EXAMINATION_COUNCIL_ID")
    private int examinationCouncilId;

    @Column(name = "SCORE")
    private String score;

    @Column(name = "STATUS")
    private int status;

}
