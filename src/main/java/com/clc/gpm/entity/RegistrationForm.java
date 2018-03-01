package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type Registration form.
 *
 * @author truon
 * @version 1.0
 * @created 05 -Nov-2017 4:08:31 PM
 */
@Table(name = "REGISTRATION_FORM")
@Getter
@Setter
public class RegistrationForm extends BaseEntity {

    @Column(name = "ID")
    private int id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PROJECT_ID")
    private int projectId;

    @Column(name = "REASON")
    private String reason;

    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "TEAM_ID")
    private int teamId;

}
