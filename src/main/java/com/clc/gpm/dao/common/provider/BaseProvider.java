package com.clc.gpm.dao.common.provider;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.PrimaryKey;
import com.clc.gpm.dao.common.annotation.Table;
import com.clc.gpm.entity.BaseEntity;
import com.clc.gpm.utils.StringUtil;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;

/**
 * <p>ファイル名 : UnivMstMapper</p>
 * <p>説明 : UnivMstMapper</p>
 *
 * @author bp.truong.pq
 * @since 2017 /11/25
 */
public class BaseProvider {

    /** The Constant CREATE_CLASS. */
    private static final String CREATE_CLASS = "createClass";

    /** The Constant CREATE_TIME. */
    private static final String CREATE_TIME = "createTime";

    /** The Constant CREATE_USER. */
    private static final String CREATE_USER = "createUser";

    /** The Constant AND_DELETE_FLG_0. */
    private static final String AND_DELETE_FLG_0 = " AND DELETE_FLG = \"0\"";

    /** The Constant DELETE_FLG_0. */
    private static final String DELETE_FLG_0 = "DELETE_FLG = \"0\"";

    /** The Constant AS_NAME. */
    private static final String AS_NAME = " AS name";

    /** The Constant AS_CODE. */
    private static final String AS_CODE = " AS code";

    /** The Constant DELETE_FLAG. */
    private static final String DELETE_FLAG = "deleteFlg";

    /**
     * FORMAT_STRING_SQL_3
     */
    private static final String FORMAT_STRING_SQL_3 = "#{";

    /**
     * STRING_COMMA
     */
    private static final String STRING_COMMA = ",";

    /**
     * STRING_AS
     */
    private static final String STRING_AS = " AS ";

    /**
     * FORMAT_STRING_SQL_2
     */
    private static final String FORMAT_STRING_SQL_2 = "}";

    /**
     * FORMAT_STRING_SQL_1
     */
    private static final String FORMAT_STRING_SQL_1 = " = #{";
    /**
     * FORMAT_STRING_SQL_1
     */
    private static final String FORMAT_STRING_SQL_NOT_EQUAL = " <> #{";
    /**
     * SQL_COUNT_ALL_RECODE
     */
    private static final String SQL_COUNT_ALL_RECODE = "count(*)";

    /**
     * FORMAT_STRING_SQL_4
     */
    private static final String FORMAT_STRING_SQL_4 = " = \"";

    /**
     * FORMAT_STRING_SQL_5
     */
    private static final String FORMAT_STRING_SQL_5 = "\"";

    /**
     * Select by primary key.
     *
     * @param entity the entity
     * @return the string
     */
    public String selectByPrimaryKey(Object entity) {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                String selectClause = "";
                // SELECT("*");
                FROM(tableName);

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);

                }

                for (Field field : table.getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);
                }
                selectClause = selectClause.substring(0, selectClause.length() - 1);
                SELECT(selectClause);
            }

            private String genarateSelectedField(String selectClause, Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    selectClause = selectClause + field.getAnnotation(PrimaryKey.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause + field.getAnnotation(Column.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                }
                return selectClause;
            }
        }.toString();
        return sql;
    }

    /**
     * select all
     *
     * @param classType Class<? extends Object>
     * @return String string
     */
    public String selectAll(Class<? extends Object> classType) {

        Class<? extends Object> table = classType;

        String tableName = table.getAnnotation(Table.class).name();
        String sql = new SQL() {
            {
                String selectClause = "";

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);

                }

                for (Field field : table.getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);
                }
                selectClause = selectClause.substring(0, selectClause.length() - 1);
                SELECT(selectClause);
                FROM(tableName);

            }

            private String genarateSelectedField(String selectClause, Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    selectClause = selectClause + field.getAnnotation(PrimaryKey.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause + field.getAnnotation(Column.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                }
                return selectClause;
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : deleteLogicAll</p>
     *
     * @param entity class entity
     * @return sql delete logic all
     * @author hung.pd
     * @since 2018 /03/07
     */
    public String deleteLogicAll(Object entity) {

        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                UPDATE(tableName);
                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (CREATE_USER.equals(field.getName()) || CREATE_TIME.equals(field.getName()) || CREATE_CLASS.equals(field.getName())) {
                        continue;
                    }

                    if (field.isAnnotationPresent(Column.class)) {
                        if (DELETE_FLAG.equals(field.getName())) {
                            SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_4 + CommonConstants.DB_DELETED + FORMAT_STRING_SQL_5);
                        } else {
                            SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                        }
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * Check exist of recode by primary key
     *
     * @param entity Object
     * @return String string
     */
    public String existPrimaryKey(Object entity) {

        BaseEntity baseEntity = (BaseEntity) entity;
        if (StringUtil.isEmpty(baseEntity.getDeleteFlg())) {
            baseEntity.setDeleteFlg("0");
        }

        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();
        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                for (Field field : table.getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * Check exist of recode by example record
     *
     * @param entity Object
     * @return String string
     * @throws Exception Exception
     */
    public String existWithExample(Object entity) throws Exception {

        Class<? extends Object> table = entity.getClass();

        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                        if (field.get(entity) != null) {
                            if (field.isAnnotationPresent(Column.class)) {
                                WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                            } else {
                                WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                            }
                        }

                    }

                }
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : FIXME Kiểm tra xem có tồn tại một record khác PK nhưng có data của cột với data của record cần check</p>
     *
     * @param entity : Table Entity
     * @return True : duplicate
     * @throws Exception : Exception
     * @author : [truong.pq]
     * @since [2018 /02/23]
     */
    public String isDuplicateValueColum(Object entity) throws Exception {

        Class<? extends Object> table = entity.getClass();

        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);

                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        genarateWhereField(field);
                    }
                }
            }

            private void genarateWhereField(Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_NOT_EQUAL + field.getName() + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(Column.class)) {
                    WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                }
            }

        }.toString();
        return sql;
    }

    /**
     * select all record with example record
     *
     * @param entity Object
     * @return String string
     * @throws Exception Exception
     */
    public String selectWithExample(Object entity) throws Exception {

        Class<? extends Object> table = entity.getClass();

        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                String selectClause = "";

                // SELECT("*");
                FROM(tableName);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    selectClause = genSqlForSelectWithExample(entity, selectClause, field);
                }

                for (Field field : entity.getClass().getSuperclass().getDeclaredFields()) {
                    selectClause = genSqlForSelectWithExample(entity, selectClause, field);
                }

                selectClause = selectClause.substring(0, selectClause.length() - 1);
                SELECT(selectClause);
            }

            private String genSqlForSelectWithExample(Object entity, String selectClause, Field field) throws IllegalAccessException {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Column.class)) {
                            WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);

                        } else {
                            WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);

                        }
                    }
                }

                if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause + field.getAnnotation(Column.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(PrimaryKey.class)) {
                    selectClause = selectClause + field.getAnnotation(PrimaryKey.class).name() + STRING_AS + field.getName() + STRING_COMMA;
                }
                return selectClause;
            }
        }.toString();
        return sql;
    }

    /**
     * delete by primary key
     *
     * @param entity Object
     * @return String string
     */
    public String deleteByPrimaryKey(Object entity) {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                DELETE_FROM(tableName);
                for (Field field : table.getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * delete with Example record
     *
     * @param entity Object
     * @return String string
     * @throws Exception Exception
     */
    public String deleteWithExample(Object entity) throws Exception {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                DELETE_FROM(tableName);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    genSqlForDeleteWithExample(entity, field);
                }

                for (Field field : entity.getClass().getSuperclass().getDeclaredFields()) {
                    genSqlForDeleteWithExample(entity, field);
                }
            }

            private void genSqlForDeleteWithExample(Object entity, Field field) throws IllegalAccessException {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Column.class)) {
                            WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                        } else {
                            WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                        }
                    }

                }
            }
        }.toString();
        return sql;
    }

    /**
     * insert
     *
     * @param entity Object
     * @return String string
     */
    public String insert(Object entity) {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                INSERT_INTO(tableName);
                for (Field field : table.getDeclaredFields()) {
                    genSqlForInsert(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    genSqlForInsert(field);
                }
            }

            private void genSqlForInsert(Field field) {
                if (field.isAnnotationPresent(Column.class)) {
                    VALUES(field.getAnnotation(Column.class).name(), FORMAT_STRING_SQL_3 + field.getName() + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(PrimaryKey.class)) {
                    VALUES(field.getAnnotation(PrimaryKey.class).name(), FORMAT_STRING_SQL_3 + field.getName() + FORMAT_STRING_SQL_2);
                }
            }
        }.toString();
        return sql;
    }

    /**
     * update by primaryKey
     *
     * @param entity Object
     * @return String string
     */
    public String updateByPrimaryKey(Object entity) {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                UPDATE(tableName);
                for (Field field : table.getDeclaredFields()) {

                    genSqlForUpdate(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (CREATE_USER.equals(field.getName()) || CREATE_TIME.equals(field.getName()) || CREATE_CLASS.equals(field.getName())) {
                        continue;
                    }
                    genSqlForUpdate(field);
                }
            }

            private void genSqlForUpdate(Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(Column.class)) {
                    SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                }
            }
        }.toString();
        return sql;
    }

    /**
     * update not null field by primaryKey
     *
     * @param entity Object
     * @return String string
     * @throws IllegalAccessException the illegal access exception
     */
    public String updateNotNullByPK(Object entity) throws IllegalAccessException {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql;
        sql = new SQL() {
            {
                UPDATE(tableName);
                for (Field field : table.getDeclaredFields()) {
                    field.setAccessible(true);
                    genSqlForUpdate(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    field.setAccessible(true);
                    genSqlForUpdate(field);
                }
            }

            private void genSqlForUpdate(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(Column.class)) {
                    if (field.get(entity) != null) {
                        SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }
            }
        }.toString();

        return sql;
    }

    /**
     * checkExclusive
     *
     * @param entity Object
     * @return String string
     */
    public String checkExclusive(Object entity) {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                for (Field field : table.getDeclaredFields()) {
                    genSqlForCheckExclusive(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    genSqlForCheckExclusive(field);
                }
            }

            private void genSqlForCheckExclusive(Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                }

                if (field.isAnnotationPresent(Column.class) && "UPDATE_TIME".equals(field.getAnnotation(Column.class).name())) {
                    WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                }
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : getSelectItem</p>
     *
     * @param tableNm String
     * @param colCode String
     * @param colName String
     * @return string SQL
     * @author : truong.dx
     * @since [2017 /12/25]
     */
    public String getSelectItem(String tableNm, String colCode, String colName) {
        String sql = new SQL() {
            {
                SELECT(colCode + AS_CODE, colName + AS_NAME);
                FROM(tableNm);
                WHERE(DELETE_FLG_0);

            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : get SelectItem With Condition</p>
     *
     * @param tableNm tablename
     * @param colCd   Column cd
     * @param colNm   Column name
     * @param where   where clause
     * @return string SQL
     * @author : hung.nq
     * @since [2018 /02/08]
     */
    public String getSelectItemWithCondition(String tableNm, String colCd, String colNm, String where) {
        String sql = new SQL() {
            {
                SELECT(colCd + AS_CODE, colNm + AS_NAME);
                FROM(tableNm);
                WHERE(where + AND_DELETE_FLG_0);
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : getColValue</p>
     *
     * @param tableNm tablename
     * @param colNm   Column name
     * @param where   where clause
     * @return string SQL
     * @author : hung.nq
     * @since [2018 /02/08]
     */
    public String getColValueWithCondition(String tableNm, String colNm, String where) {
        String sql = new SQL() {
            {
                SELECT(colNm + AS_NAME);
                FROM(tableNm);
                WHERE(where + AND_DELETE_FLG_0);
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : getColValue</p>
     *
     * @param tableNm tablename
     * @param colNm   Column name
     * @return string SQL
     * @author : hung.nq
     * @since [2018 /02/08]
     */
    public String getColValue(String tableNm, String colNm) {
        String sql = new SQL() {
            {
                SELECT(colNm + AS_NAME);
                FROM(tableNm);
                WHERE(DELETE_FLG_0);
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : Delete all table</p>
     *
     * @param tableNm table name
     * @return string SQL
     * @author : hung.nq
     * @since [2018 /02/08]
     */
    public String deleteTable(String tableNm) {
        String sql = new SQL() {
            {
                DELETE_FROM(tableNm);
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : Delete Logic Object</p>
     *
     * @param entity Entity
     * @return SQL query
     * @author : [minh.ls]
     * @since [2018 /02/06]
     */
    public String deleteLogicByPK(Object entity) {
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                UPDATE(tableName);
                for (Field field : table.getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                    }
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (CREATE_USER.equals(field.getName()) || CREATE_TIME.equals(field.getName()) || CREATE_CLASS.equals(field.getName())) {
                        continue;
                    }

                    if (field.isAnnotationPresent(Column.class)) {
                        if (DELETE_FLAG.equals(field.getName())) {
                            SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_4 + CommonConstants.DB_DELETED + FORMAT_STRING_SQL_5);
                        } else {
                            SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2);
                        }
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : selectAllWithoutAnotation</p>
     *
     * @param tblName Table name
     * @return Select * from {table}
     * @author hung.pd
     * @since 2018 /02/26
     */
    public String selectAllWithoutAnotation(String tblName) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(tblName);
                WHERE(DELETE_FLG_0);
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : select All Keys</p>
     *
     * @param <T>    Class entity
     * @param entity Entity class
     * @return SQL select all keys in a table
     * @author hung.pd
     * @since 2018 /03/07
     */
    public <T extends BaseEntity> String selectAllKeys(Class<T> entity) {
        String tableName = entity.getAnnotation(Table.class).name();
        String sql = new SQL() {
            {
                StringBuilder fieldKey = new StringBuilder();
                for (Field field : entity.getDeclaredFields()) {
                    if (field.getAnnotation(PrimaryKey.class) != null) {
                        fieldKey.append(field.getAnnotation(PrimaryKey.class).name() + STRING_AS + field.getName() + CommonConstants.COMMA);
                    }
                }
                fieldKey = fieldKey.deleteCharAt(fieldKey.length() - 1);
                SELECT(fieldKey.toString());
                FROM(tableName);
            }
        }.toString();
        return sql;
    }
}