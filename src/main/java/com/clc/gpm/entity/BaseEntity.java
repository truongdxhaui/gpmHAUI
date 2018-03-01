package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Base entity.
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1264383025283185295L;

    /**
     * The Create time.
     */
    @Column(name = "CREATE_TIME")
    protected LocalDateTime createTime;

    /**
     * The Create user.
     */
    @Column(name = "CREATE_USER")
    protected String createUser;

    /**
     * The Create class.
     */
    @Column(name = "CREATE_CLASS")
    protected String createClass;

    /**
     * The Update time.
     */
    @Column(name = "UPDATE_TIME")
    protected LocalDateTime updateTime;

    /**
     * The Update user.
     */
    @Column(name = "UPDATE_USER")
    protected String updateUser;

    /**
     * The Update class.
     */
    @Column(name = "UPDATE_CLASS")
    protected String updateClass;

    /**
     * The Delete flg.
     */
    @Column(name = "DELETE_FLG")
    protected String deleteFlg;

    /**
     * The Delete time.
     */
    @Column(name = "DELETE_TIME")
    protected LocalDateTime deleteTime;

}
