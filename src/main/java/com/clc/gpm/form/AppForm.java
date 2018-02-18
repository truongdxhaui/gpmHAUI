package com.clc.gpm.form;

import com.clc.gpm.validator.common.ErrorInfoValue;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>ファイル名 : AppForm</p>
 * <p>説明 : AppForm</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
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

    /**
     * errorsを取得します。
     *
     * @return errors
     */
    public List<ErrorInfoValue> getError() {
        return error;
    }

    /**
     * errorsを設定します。
     *
     * @param error List<ErrorInfoValue>
     */
    public void setError(List<ErrorInfoValue> error) {
        this.error = error;
    }

    /**
     * get screenMessage
     * @return the screenMessage
     */
    public String getScreenMessage() {
        return screenMessage;
    }

    /**
     * set screenMessage
     * @param screenMessage the screenMessage to set
     */
    public void setScreenMessage(String screenMessage) {
        this.screenMessage = screenMessage;
    }

    /**
     * get createTime
     * @return the createTime
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * set createTime
     * @param createTime the createTime to set
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * get createUser
     * @return the createUser
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * set createUser
     * @param createUser the createUser to set
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * get createClass
     * @return the createClass
     */
    public String getCreateClass() {
        return createClass;
    }

    /**
     * set createClass
     * @param createClass the createClass to set
     */
    public void setCreateClass(String createClass) {
        this.createClass = createClass;
    }

    /**
     * get updateTime
     * @return the updateTime
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * set updateTime
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * get updateUser
     * @return the updateUser
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * set updateUser
     * @param updateUser the updateUser to set
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * get updateClass
     * @return the updateClass
     */
    public String getUpdateClass() {
        return updateClass;
    }

    /**
     * set updateClass
     * @param updateClass the updateClass to set
     */
    public void setUpdateClass(String updateClass) {
        this.updateClass = updateClass;
    }

    /**
     * get screenName
     * @return the screenName
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * set screenName
     * @param screenName the screenName to set
     */
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

}
