package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.PrimaryKey;
import com.clc.gpm.dao.common.annotation.Table;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * The type Registration form.
 *
 * @author truon
 * @version 1.0
 * @created 05 -Nov-2017 4:08:31 PM
 */
@Table(name = "REGISTRATION_FORM")
@Alias("RegistrationForm")
public class RegistrationForm extends BaseEntity {

    @PrimaryKey(name = "ID")
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

    @Column(name = "TYPE")
    private int type;

    public RegistrationForm() {
    }

    public RegistrationForm(int teamId) {
        this.teamId = teamId;
        this.deleteFlg = "0";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
