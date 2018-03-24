package com.clc.gpm.dto.search;

import com.clc.gpm.common.CommonConstants;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>ファイル名 : BaseSearchDTO</p>
 * <p>説明 : BaseSearchDTO</p>
 * @author minh.ls
 * @since : 2018/02/10
 */
@Getter
@Setter
public class BaseSearchDTO {
    // current page
	private Integer page = 0;
	//sort index
    private int sortIndex;
    // sort type
    private String sortType;
    // for sql command
    // offset
    private int offset;
    // limit
    private int limit = CommonConstants.GRID_ROW_COUNT;
    // order by
    private String orderBy;
    // order type
    private String orderType;
}
