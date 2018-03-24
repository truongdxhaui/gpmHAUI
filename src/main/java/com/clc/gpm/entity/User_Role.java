package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user_role")
public class User_Role extends BaseEntity {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;
}
