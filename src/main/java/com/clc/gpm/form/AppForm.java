package com.clc.gpm.form;

import com.clc.gpm.validator.common.ErrorInfoValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>ファイル名 : AppForm</p>
 * <p>説明 : AppForm</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Setter
@Getter
public class AppForm implements Serializable {

    private static final long serialVersionUID = -410827961360725279L;

    /** screenMessage */
    private String screenMessage;

    /** createTime */
    private LocalDateTime createTime;

    /** createUser */
    private String createUser;

    /** createClass */
    private String createClass;

    /**updateTime */
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    protected LocalDateTime updateTime;

    /**updateUser */
    private String updateUser;

    /**updateClass */
    private String updateClass;

    /**screenName */
    private String screenName;

    /**dayCalendar */
    private String dayCalendar;

    /**Error list */
    private List<ErrorInfoValue> error;

    /**current page number */
    private int page;

    /** paging flag*/
    private boolean paging = false;

    /**
     * ソートフィルドインデックス
     */
    private int sortIndex;

    /**
     * ソート順
     */
    private String sortType;
}
