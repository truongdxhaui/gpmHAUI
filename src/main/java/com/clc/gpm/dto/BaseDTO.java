package com.clc.gpm.dto;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>ファイル名 : BaseDTO</p>
 * <p>説明 : BaseDTO</p>
 * @author truong.pq
 * @since 2017/11/25
 */
@Setter
@Getter
public class BaseDTO {
    /**
     * createUser
     */
    protected String createUser;

    /**
     * createClass
     */
    protected String createClass;

    /**
     * updateUser
     */
    protected String updateUser;

    /**
     * updateClass
     */
    protected String updateClass;

    /**
     * createTime
     */
    protected LocalDateTime createTime;

    /**
     * updateTime
     */
    protected LocalDateTime updateTime;

    /**
     * deleteFlag
     */
    protected String deleteFlg;

    /**
     * deleteTime
     */
    protected LocalDateTime deleteTime;

    /**
     * BaseDTO
     */
    public BaseDTO() {
        super();
    }

    /**
     * <p>説明 : get updateTime for display</p> 
     * @author : minh.ls
     * @since : 2018/02/26
     * @return str updateTime
     */
    public String getUpdateTimeDisp() {
        return StringUtil.toStrDateFormat(updateTime, CommonConstants.DATE_FORMAT_YYYYMMDD);
    }

    /**
     * <p>説明 : get createTime for display</p> 
     * @author : minh.ls
     * @since : 2018/02/26
     * @return String createTime
     */
    public String getCreateTimeDisp() {
        return StringUtil.toStrDateFormat(createTime, CommonConstants.DATE_FORMAT_YYYYMMDD);
    }
}
