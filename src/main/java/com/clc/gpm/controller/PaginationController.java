package com.clc.gpm.controller;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.form.AppForm;
import com.clc.gpm.utils.Utilities;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.Map.Entry;

/**
 * <p>ファイル名 : Pagination controller</p>
 * <p>説明    : FIXME TableController</p>
 * @author : minh.nt
 * @since  : 2018/01/04
 */
public abstract class PaginationController extends AppController {
    /** PAGE_NUM_ON_SCREEN = 5 */
    private static final int PAGE_NUM_ON_SCREEN = 10;

    /** PAGE = "page" */
    private static final String PAGE = "page";

    /** CURENT_PAGE = "currentPage" */
    private static final String TABLE_CURENT_PAGE = "currentPage";

    /** TOTAL_ROW_COUNT = "totalRowCount" */
    private static final String TOTAL_RECORD = "totalRecord";

    /** DISPLAY_DATA = "displayData" */
    private static final String DISPLAY_DATA = "displayData";

    /** START_ROW = "startRow"*/
    private static final String START_ROW = "startRow";

    /** TOTAL_PAGE = "totalPage" */
    protected static final String TOTAL_PAGE = "totalPage";

    /**
     * FIXME Confirm lại xem có cần thiết tạo ra method này không?
     * <p>説明 : getFirstElement</p> 
     * @author : minh.nt
     * @since  : 2017/12/27
     * @param page FIXME page id
     * @return FIXME start row
     */
    protected int getFirstElement(int page) {
        int currentRow = page;
        if (page < 1) {
            currentRow = 1;
        }

        int startRow = (currentRow - 1) * CommonConstants.GRID_ROW_COUNT;
        return startRow;
    }

    /**
     * 
     * <p>説明 : setDisplayDataOnPage</p> 
     * @author : minh.nt
     * @since  : 2017/12/27
     * @param page int
     * @param totalPage int
     * @return displayData List<Integer>
     */
    protected List<Integer> setDisplayDataOnPage(int page, int totalPage) {
        int last = 0;
        int isOdd = PAGE_NUM_ON_SCREEN % 2;
        if (page > (PAGE_NUM_ON_SCREEN / 2 + isOdd)) {
            last = Math.min(page + (PAGE_NUM_ON_SCREEN / 2), totalPage);
        } else {
            last = Math.min(PAGE_NUM_ON_SCREEN, totalPage);
        }
        int first = Math.max(1, last - (PAGE_NUM_ON_SCREEN - 1));
        List<Integer> displayData = new ArrayList<Integer>();
        for (int i = first; i <= last; i++) {
            displayData.add(i);
        }
        return displayData;
    }

    /**
    *
    * <p>説明 : getPrevElement</p>
    * @author : minh.nt
    * @since  : 2017/12/27
    * @param mdCd Integer
    * @param list List<Integer>
    * @return Integer
    */
    protected int getPrevElement(int mdCd, List<Integer> list) {
        int idPrev = -1;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == mdCd) {
                    idPrev = (i == 0) ? -1 : list.get(i - 1);
                    break;
                }
            }
        }
        return idPrev;
    }
    

    /**
     * 
     * <p>説明 : getPrevElement</p> 
     * @author : hung.nq
     * @since 2018/02/23
     * @param mdCd mdCd
     * @param list list
     * @return String
     */
    protected String getPrevElement(String mdCd, List<String> list) {
        String idPrev = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(mdCd)) {
                    idPrev = (i == 0) ? "" : list.get(i - 1);
                    break;
                }
            }
        }
        return idPrev;
    }

    /**
     * <p>説明 : get prev obj detail</p> 
     * @author : truong.dx
     * @since  : 2018/02/08
     * @param item HashMap<String, Object>
     * @param lstItm List<HashMap<String, Object>>
     * @return HashMap<String, Object>
     */
    protected HashMap<String, Object> getPrevElementByHashMap(HashMap<String, Object> item, List<HashMap<String, Object>> lstItm) {
        int pos = lstItm.indexOf(item);
        if (pos > 0) {
            return lstItm.get(pos - 1);
        }
        return null;
    }

    /**
     * <p>説明 : get next obj detail</p> 
     * @author : truong.dx
     * @since  : 2018/02/08
     * @param item HashMap<String, Object>
     * @param lstItm List<HashMap<String, Object>>
     * @return HashMap<String, Object>
     */
    protected HashMap<String, Object> getNextElementByHashMap(HashMap<String, Object> item, List<HashMap<String, Object>> lstItm) {
        int pos = lstItm.indexOf(item);
        if (pos < lstItm.size() - 1 && pos != -1) {
            return lstItm.get(pos + 1);
        }
        return null;
    }

    /**
    *
    * <p>説明 : getNextElement</p>
    * @author : thien.nv
    * @since 2017/12/25
    * @param mdCd Integer
    * @param list List<Integer>
    * @return Integer
    */
    protected int getNextElement(int mdCd, List<Integer> list) {
        int idNext = -1;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == mdCd) {
                    idNext = (i == (list.size() - 1)) ? -1 : list.get(i + 1);
                    break;
                }
            }
        }

        return idNext;
    }
    

    /**
     * 
     * <p>説明 : getNextElement</p> 
     * @author : hung.nq
     * @since 2018/02/23
     * @param cd code
     * @param list list
     * @return String
     */
    protected String getNextElement(String cd, List<String> list) {
        String idNext = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(cd)) {
                    idNext = (i == (list.size() - 1)) ? "" : list.get(i + 1);
                    break;
                }
            }
        }
        return idNext;
    }

    /**
     * 
     * <p>説明 : Binding data pagination</p> 
     * @author : minh.nt
     * @since 2018/01/04
     * @param modelAndView ModelAndView
     * @param page int
     * @param totalRecord int
     */
    protected void setPaginationData(ModelAndView modelAndView, int page, int totalRecord) {
        int offset = getOffsetByPage(page);
        int totalPage = getTotalPage(totalRecord);

        modelAndView.addObject(TOTAL_RECORD, totalRecord);
        modelAndView.addObject(DISPLAY_DATA, setDisplayDataOnPage(page, totalPage));
        modelAndView.addObject(START_ROW, offset);
        modelAndView.addObject(PAGE, 1);
        modelAndView.addObject(TABLE_CURENT_PAGE, page == 0 ? 1 : page);
        modelAndView.addObject(TOTAL_PAGE, totalPage);
    }

    /**
     * <p>説明 : FIXME get curent record by page index</p> 
     * @author : minh.ls
     * @since 2018/02/12
     * @param page page number
     * @return curent record
     */
    protected int getOffsetByPage(Integer page) {
        if (page == null || page <= 1) {
            return 0;
        } else {
            return (page - 1) * CommonConstants.GRID_ROW_COUNT;
        }
    }

    /**
     * <p>説明 : FIXME get total page by total record</p> 
     * @author : minh.ls
     * @since 2018/02/10
     * @param totalRecord total of record
     * @return total page
     */
    private int getTotalPage(int totalRecord) {
        return (int) Math.ceil((double) totalRecord / CommonConstants.GRID_ROW_COUNT);
    }
    
    /**
     * <p>説明 : FIXME copy data when paging</p> 
     * @author : minh.ls
     * @since 2018/02/23
     * @param formSource : AppForm Source
     * @param formDestination : AppForm Destination
     */
    protected void copyPaginationSortData(AppForm formSource, AppForm formDestination) {
        int page = formSource.getPage();
        String sortType = formSource.getSortType();
        int sortIndex = formSource.getSortIndex();
        
        Utilities.map(formDestination, formSource);
        
        formSource.setPage(page);
        formSource.setSortType(sortType);
        formSource.setSortIndex(sortIndex);
    }

    /**
     * <p>説明 : convert map to url param</p> 
     * @author : minh.ls
     * @since : 2018/03/06
     * @param map Map
     * @return String
     */
    protected String convertMapToUrlParam(Map<String, Object> map) {
        if (map == null) {
            return null;
        }

        Iterator<Entry<String, Object>> it = map.entrySet().iterator();
        StringBuilder str = new StringBuilder();
        while (it.hasNext()) {
            Entry<String, Object> element = (Entry<String, Object>) it.next();
            str.append(element.getKey()).append("=").append(element.getValue());
            if (it.hasNext()) {
                str.append("&");
            }
        }
        return str.toString();
    }

}