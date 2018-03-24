package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.provider.BaseProvider;
import com.clc.gpm.dto.SelectItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * <p>ファイル名 : CommonMapper</p>
 * <p>説明 : CommonMapper</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Mapper
public interface CommonMapper {

    /**
     * <p>説明 : getSelectItem</p> .
     *
     * @author truong.dx
     * @since 2017/12/23
     * @param tableNm       Table name
     * @param colCd         Column code
     * @param colNm         Column name
     * @return List<SelectItemDTO>
     */
    @SelectProvider(type = BaseProvider.class, method = "getSelectItem")
    List<SelectItemDTO> getSelectItem(String tableNm, String colCd, String colNm);

    /**
     * 
     * <p>説明 : getSelectItemWithCondition</p> 
     * @author hung.nq
     * @since 2018/02/08
     * @param tableNm       Table name
     * @param colCd         Column code
     * @param colNm         Column name
     * @param where         Where condition
     * @return  List selected item
     */
    @SelectProvider(type = BaseProvider.class, method = "getSelectItemWithCondition")
    List<SelectItemDTO> getSelectItemWithCondition(String tableNm, String colCd, String colNm, String where);

    /**
     * 
     * <p>説明 : getColValue</p> 
     * @author hung.nq
     * @since 2018/02/08
     * @param tableNm       Table name
     * @param colNm         Column name
     * @return  List column value
     */
    @SelectProvider(type = BaseProvider.class, method = "getColValue")
    List<String> getColValue(String tableNm, String colNm);

    /**
     * 
     * <p>説明 : getColValue</p> 
     * @author hung.nq
     * @since 2018/02/08
     * @param tableNm       Table name
     * @param colNm         Column name
     * @param where         Where condition
     * @return      List column value
     */
    @SelectProvider(type = BaseProvider.class, method = "getColValueWithCondition")
    List<String> getColValueWithCondition(String tableNm, String colNm, String where);

    /**
     * 
     * <p>説明 : selectAll</p> 
     * @author hung.pd
     * @since 2018/02/26
     * @param tableNm       Table name
     * @return List column name
     */
    @SelectProvider(type = BaseProvider.class, method = "selectAllWithoutAnotation")
    @ResultType(Map.class)
    List<Map<String, Object>> selectAllWithoutAnotation(String tableNm);
}