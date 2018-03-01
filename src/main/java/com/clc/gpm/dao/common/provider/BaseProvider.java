package com.clc.gpm.dao.common.provider;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.PrimaryKey;
import com.clc.gpm.dao.common.annotation.Table;
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
     * SQL_COUNT_ALL_RECODE
     */
    private static final String SQL_COUNT_ALL_RECODE = "count(*)";

    /**
     * select record by PrimaryKey
     *
     * @param entity Object
     * @return String string
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
                selectClause = selectClause.substring(0,
                        selectClause.length() - 1);
                SELECT(selectClause);
            }

            private String genarateSelectedField(String selectClause,
                    Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name()
                            + FORMAT_STRING_SQL_1 + field.getName()
                            + FORMAT_STRING_SQL_2);
                    selectClause = selectClause
                            + field.getAnnotation(PrimaryKey.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause
                            + field.getAnnotation(Column.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
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
                selectClause = selectClause.substring(0,
                        selectClause.length() - 1);
                SELECT(selectClause);
                FROM(tableName);

            }

            private String genarateSelectedField(String selectClause,
                    Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    selectClause = selectClause
                            + field.getAnnotation(PrimaryKey.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause
                            + field.getAnnotation(Column.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
                }
                return selectClause;
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
        Class<? extends Object> table = entity.getClass();
        String tableName = table.getAnnotation(Table.class).name();
        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                for (Field field : table.getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(Column.class).name()
                                + FORMAT_STRING_SQL_1 + field.getName()
                                + FORMAT_STRING_SQL_2);
                    }
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(Column.class).name()
                                + FORMAT_STRING_SQL_1 + field.getName()
                                + FORMAT_STRING_SQL_2);
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
                    if (field.isAnnotationPresent(Column.class)
                            || field.isAnnotationPresent(PrimaryKey.class)) {
                        if (field.get(entity) != null) {
                            if (field.isAnnotationPresent(Column.class)) {
                                WHERE(field.getAnnotation(Column.class).name()
                                        + FORMAT_STRING_SQL_1 + field.getName()
                                        + FORMAT_STRING_SQL_2);
                            } else {
                                WHERE(field.getAnnotation(PrimaryKey.class)
                                        .name() + FORMAT_STRING_SQL_1
                                        + field.getName()
                                        + FORMAT_STRING_SQL_2);
                            }
                        }

                    }

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
                    selectClause = genSqlForSelectWithExample(entity,
                            selectClause, field);
                }

                for (Field field : entity.getClass().getSuperclass()
                        .getDeclaredFields()) {
                    selectClause = genSqlForSelectWithExample(entity,
                            selectClause, field);
                }

                selectClause = selectClause.substring(0,
                        selectClause.length() - 1);
                SELECT(selectClause);
            }

            private String genSqlForSelectWithExample(Object entity,
                    String selectClause,
                    Field field) throws IllegalAccessException {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class)
                        || field.isAnnotationPresent(PrimaryKey.class)) {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Column.class)) {
                            WHERE(field.getAnnotation(Column.class).name()
                                    + FORMAT_STRING_SQL_1 + field.getName()
                                    + FORMAT_STRING_SQL_2);

                        } else {
                            WHERE(field.getAnnotation(PrimaryKey.class).name()
                                    + FORMAT_STRING_SQL_1 + field.getName()
                                    + FORMAT_STRING_SQL_2);

                        }
                    }
                }

                if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause
                            + field.getAnnotation(Column.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(PrimaryKey.class)) {
                    selectClause = selectClause
                            + field.getAnnotation(PrimaryKey.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
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
                        WHERE(field.getAnnotation(PrimaryKey.class).name()
                                + FORMAT_STRING_SQL_1 + field.getName()
                                + FORMAT_STRING_SQL_2);
                    }
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        WHERE(field.getAnnotation(PrimaryKey.class).name()
                                + FORMAT_STRING_SQL_1 + field.getName()
                                + FORMAT_STRING_SQL_2);
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

                for (Field field : entity.getClass().getSuperclass()
                        .getDeclaredFields()) {
                    genSqlForDeleteWithExample(entity, field);
                }
            }

            private void genSqlForDeleteWithExample(Object entity,
                    Field field) throws IllegalAccessException {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class)
                        || field.isAnnotationPresent(PrimaryKey.class)) {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Column.class)) {
                            WHERE(field.getAnnotation(Column.class).name()
                                    + FORMAT_STRING_SQL_1 + field.getName()
                                    + FORMAT_STRING_SQL_2);
                        } else {
                            WHERE(field.getAnnotation(PrimaryKey.class).name()
                                    + FORMAT_STRING_SQL_1 + field.getName()
                                    + FORMAT_STRING_SQL_2);
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
                    VALUES(field.getAnnotation(Column.class).name(),
                            FORMAT_STRING_SQL_3 + field.getName()
                                    + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(PrimaryKey.class)) {
                    VALUES(field.getAnnotation(PrimaryKey.class).name(),
                            FORMAT_STRING_SQL_3 + field.getName()
                                    + FORMAT_STRING_SQL_2);
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
                    genSqlForUpdate(field);
                }
            }

            private void genSqlForUpdate(Field field) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    WHERE(field.getAnnotation(PrimaryKey.class).name()
                            + FORMAT_STRING_SQL_1 + field.getName()
                            + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(Column.class)) {
                    SET(field.getAnnotation(Column.class).name()
                            + FORMAT_STRING_SQL_1 + field.getName()
                            + FORMAT_STRING_SQL_2);
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
                    WHERE(field.getAnnotation(PrimaryKey.class).name()
                            + FORMAT_STRING_SQL_1 + field.getName()
                            + FORMAT_STRING_SQL_2);
                }

                if (field.isAnnotationPresent(Column.class) && "UPDATE_TIME"
                        .equals(field.getAnnotation(Column.class).name())) {
                    WHERE(field.getAnnotation(Column.class).name()
                            + FORMAT_STRING_SQL_1 + field.getName()
                            + FORMAT_STRING_SQL_2);
                }
            }
        }.toString();
        return sql;
    }

    /**
     * <p>説明 : getSelectItem</p>
     *
     * @param tableNm String
     * @param colNm   String
     * @param colVal  String
     * @return String select item
     * @author : [dts.bp34]
     * @since : [2017/12/25]
     */
    public String getSelectItem(String tableNm, String colNm, String colVal) {
        String sql = new SQL() {
            {
                SELECT(colNm + " AS code", colVal + " AS name");
                FROM(tableNm);
            }
        }.toString();
        return sql;
    }
}
