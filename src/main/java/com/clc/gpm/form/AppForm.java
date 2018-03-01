package com.clc.gpm.form;

import com.clc.gpm.validator.common.ErrorInfoValue;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * The type App form.
 */
@Getter
@Setter
public class AppForm implements Serializable {

    private static final long serialVersionUID = -410827961360725279L;

    /**
     * screenMessage
     */
    private String screenMessage;

    /**
     * createTime
     */
    private LocalDateTime createTime;

    /**
     * createUser
     */
    private String createUser;

    /**
     * createClass
     */
    private String createClass;

    /**
     * updateTime
     */
    private LocalDateTime updateTime;

    /**
     * updateUser
     */
    private String updateUser;

    /**
     * updateClass
     */
    private String updateClass;

    /**
     * screenName
     */
    private String screenName;

    /**
     * Error list
     */
    private List<ErrorInfoValue> error;

}
