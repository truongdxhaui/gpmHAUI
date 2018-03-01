package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.PrimaryKey;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Message.
 */
@Getter
@Setter
@Table(name = "MESSAGE")
public class Message extends BaseEntity {

    @PrimaryKey(name = "ID")
    private Integer id;

    @Column(name = "SEND_ID")
    private String sendID;

    @Column(name = "RECIVER_ID")
    private String reciverID;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CONTENT")
    private String content;
}
