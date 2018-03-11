package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * The type Role.
 */

@Getter
@Setter
@Table(name = "ROLE")
@Alias("Role")
public class Role extends BaseEntity{

    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

}
