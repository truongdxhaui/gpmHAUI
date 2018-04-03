package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.PrimaryKey;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * The type User.
 */
@Getter
@Setter
@Table(name = "USER")
@Alias("User")
public class User extends BaseEntity {
    @PrimaryKey(name = "ID")
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "GENDER")
    private boolean gender;

    @Column(name = "ACCESS")
    private String access;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "HKTT")
    private String hktt;

    @Column(name = "MOBILE")
    private String moble;

    @Column(name = "NATION")
    private String nation;

    @Column(name = "RELIGION")
    private String religion;

    @Column(name = "AVATAR")
    private String avatar;

    @Column(name = "DEGREE")
    private String degree;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private int status;

    public User(int id) {
        this.id = id;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }
}