package com.clc.gpm.dao.common.typehandler;

import com.mysql.jdbc.CallableStatement;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * LocalDateTypeHandler
 *
 * @author bp.thien.nv
 */
@MappedTypes(LocalDate.class)
public class LocalDateTypeHandler implements TypeHandler<Object> {

    public void setParameter(PreparedStatement ps, int i, Object parameter,
            JdbcType jdbcType) throws SQLException {

        LocalDate date = (LocalDate) parameter;
        if (date != null) {
            ps.setDate(i, Date.valueOf(date));
        } else {
            ps.setDate(i, null);
        }
    }

    public Object getResult(ResultSet rs,
            String columnName) throws SQLException {
        Date date = rs.getDate(columnName);
        if (date != null) {
            return date.toLocalDate();
        } else {
            return null;
        }
    }


    /**
     * Gets result.
     *
     * @param cs          the cs
     * @param columnIndex the column index
     * @return the result
     * @throws SQLException the sql exception
     */
    public Object getResult(CallableStatement cs,
            int columnIndex) throws SQLException {
        Date date = cs.getDate(columnIndex);
        if (date != null) {
            return date.toLocalDate();
        } else {
            return null;
        }
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        Date date = rs.getDate(columnIndex);
        if (date != null) {
            return date.toLocalDate();
        } else {
            return null;
        }
    }

    @Override
    public Object getResult(java.sql.CallableStatement cs,
            int columnIndex) throws SQLException {
        Date date = cs.getDate(columnIndex);
        if (date != null) {
            return date.toLocalDate();
        } else {
            return null;
        }
    }

}
