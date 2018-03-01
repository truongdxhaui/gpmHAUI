package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Project.
 *
 * @author truon
 * @version 1.0
 * @created 05 -Nov-2017 4:08:31 PM
 */
@Table(name = "PROJECT")
@Getter
@Setter
public class Project extends BaseEntity {

    @Column(name = "ID")
    private int id;

    @Column(name = "LECTURER_ID")
    private int lectureId;

    @Column(name = "ADDRESS_APPLICABLE")
    private String addressApplicable;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "FIELD_OF_APPLICATION")
    private String fieldOfApplication;

    @Column(name = "NAME")
    private String name;

    @Column(name = "REQUIREMENT")
    private String requirement;

    @Column(name = "LEVEL")
    private Integer level;

    //Nhom de tau
    @Column(name = "GROUP_PROJECT_ID")
    private int groupProjectId;

    @Column(name = "REQUIEMENT_BASIC")
    private String requirement_Basic;

    @Column(name = "REQUIREMENT_GP")
    private String requirement_GP;

    @Column(name = "CONTENT_PROJECT_BASIC")
    private String contentProjectBasic;

    @Column(name = "CONTENT_PROJECT_GP")
    private String contentProject_GP;

    @Column(name = "RESUILT")
    private String resuilt;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "STATUS")
    private int status;

    /*========================
    Hệ áp dụng của đề tài
    * Cao đẳng: 0
    * Đại học: 1
    * Cả 2: 2
    ===========================*/
    @Column(name = "TYPE")
    private int type;
}