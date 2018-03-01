package com.clc.gpm.dao.common;

import com.clc.gpm.dao.common.provider.BaseProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * <p>ファイル名 : BaseMapper</p>
 * <p>説明 : BaseMapper</p>
 *
 * @param <T> the type parameter
 * @author bp.truong.pq
 * @since 2017 /12/06
 */
public interface BaseMapper<T> {

    /**
     * get one record by primary key
     *
     * @param record T
     * @return T t
     */
    @SelectProvider(type = BaseProvider.class, method = "selectByPrimaryKey")
    T selectByPK(T record);

    /**
     * get all record from table
     *
     * @param entityClass Class<?>
     * @return List<T> list
     */
    @SelectProvider(type = BaseProvider.class, method = "selectAll")
    List<T> selectAll(Class<?> entityClass);

    /**
     * select records with example info
     *
     * @param record T
     * @return List<T> list
     */
    @SelectProvider(type = BaseProvider.class, method = "selectWithExample")
    List<T> selectWithExample(T record);

    /**
     * check exist record with primary key info
     *
     * @param record T
     * @return Boolean boolean
     */
    @SelectProvider(type = BaseProvider.class, method = "existPrimaryKey")
    Boolean existWithPK(T record);

    /**
     * count record with primary key info
     *
     * @param record T
     * @return Integer integer
     */
    @SelectProvider(type = BaseProvider.class, method = "existWithExample")
    Integer countWithExample(T record);

    /**
     * delete record with primary key info
     *
     * @param record T
     * @return Integer integer
     */
    @DeleteProvider(type = BaseProvider.class, method = "deleteByPrimaryKey")
    Integer deleteByPK(T record);

    /**
     * delete records with example info
     *
     * @param record T
     * @return Integer integer
     */
    @DeleteProvider(type = BaseProvider.class, method = "deleteWithExample")
    Integer deleteWithExample(T record);

    /**
     * insert record into
     *
     * @param record T
     * @return Integer integer
     */
    @InsertProvider(type = BaseProvider.class, method = "insert")
    Integer insert(T record);

    /**
     * update record with primary key
     *
     * @param record T
     * @return Integer integer
     */
    @UpdateProvider(type = BaseProvider.class, method = "updateByPrimaryKey")
    Integer updateByPK(T record);

    /**
     * check exclusive
     *
     * @param record T
     * @return Boolean boolean
     */
    @SelectProvider(type = BaseProvider.class, method = "checkExclusive")
    Boolean checkExclusive(T record);

}
