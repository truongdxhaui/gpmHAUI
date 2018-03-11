package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.PrimaryKey;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 *
 *
 * @author truon
 * @version 1.0
 * @created 05 -Nov-2017 4:08:31 PM
 */
@Getter
@Setter
@Table(name = "TEAM")
@Alias("Team")
public class Team extends BaseEntity{

    @PrimaryKey(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "REGISTRATION_FORM_ID")
    private int registrationFormId;

}