package com.clc.gpm.entity;

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
@Table(name = "TEAM_USER")
@Alias("TEAM_USER")
public class Team_User extends BaseEntity{

    @PrimaryKey(name = "TEAM_ID")
    private int teamId;

    @PrimaryKey(name = "USER_ID")
    private int userId;

    public Team_User(int teamId, int userId) {
        this.teamId = teamId;
        this.userId = userId;
    }
}