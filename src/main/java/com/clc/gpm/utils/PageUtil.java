package com.clc.gpm.utils;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.dto.search.BaseSearchDTO;

/**
 * The type Page util.
 */
public class PageUtil {


    /**
     * Init search dto.
     *
     * @param searchDTO   the search dto
     * @param sortColumns the sort columns
     * @param sortColumn  the sort column
     */
    public static void initSearchDTO(BaseSearchDTO searchDTO, String[] sortColumns, String sortColumn) {
        // init page
        // TODO advance
        int currentRow = searchDTO.getPage();
        if (currentRow < 1) {
            currentRow = 1;
        }
        int startRow = (currentRow - 1) * CommonConstants.GRID_ROW_COUNT;
        searchDTO.setLimit(CommonConstants.GRID_ROW_COUNT);
        searchDTO.setOffset(startRow);
        // TODO advance
        // check lst order by null
        if (sortColumns == null) {
            searchDTO.setOrderBy(sortColumn);
        } else {
            if (searchDTO.getSortIndex() <= sortColumns.length - 1) {
                String sortValue = sortColumns[searchDTO.getSortIndex()];
                if (sortValue != null && !sortValue.equals(CommonConstants.BLANK)) {
                    searchDTO.setOrderBy(sortValue);
                } else {
                    searchDTO.setOrderBy(sortColumn);
                }
            } else {
                searchDTO.setOrderBy(sortColumn);
            }
        }
        // TODO advance
        // set order by
        String sortType = searchDTO.getSortType();
        if (sortType == null || searchDTO.getSortType().equals(CommonConstants.BLANK) || !CommonConstants.LST_SORT_TYPE.contains(sortType)) {
            searchDTO.setOrderType(CommonConstants.ASC);
        } else {
            searchDTO.setOrderType(searchDTO.getSortType());
        }
    }


    /**
     * Gets page.
     *
     * @param page          the page
     * @param totalRowCount the total row count
     * @return the page
     */
    public static int getPage(int page, int totalRowCount) {
        // kiem tra neu chi co 1 page
        // TODO advance ( Kiem tra lai maintance )
        if (totalRowCount <= CommonConstants.GRID_ROW_COUNT) {
            return 1;
        }
        // kiem tra page da bi xoa
        page = page >= 1 ? page - 1 : 0;
        if (page * CommonConstants.GRID_ROW_COUNT >= totalRowCount) {
            int totalPage = totalRowCount / CommonConstants.GRID_ROW_COUNT;
            if (totalRowCount % CommonConstants.GRID_ROW_COUNT > 0) {
                totalPage++;
            }
            // get current page
            return totalPage;
        }
        return page + 1;
    }
}
