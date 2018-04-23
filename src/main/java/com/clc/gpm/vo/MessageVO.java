package com.clc.gpm.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>ファイル名 : MessageVO</p>
 * FIXME<p>説明 : Thong tin message</p>
 *
 * @author hung.pd
 * @since 2018 /02/08
 */
@Getter
@Setter
public class MessageVO {

    /** Type message */
    private String type;

    /** message */
    private String messageId;

    /** message params*/
    private String[] params = new String[] {};

    /**
     * This constructor message vo
     *
     * @param type      Type message
     * @param messageId Message id
     */
    public MessageVO(String type, String messageId) {
        this.type = type;
        this.messageId = messageId;
    }

    /**
     * This constructor message vo
     *
     * @param messageId Message id
     */
    public MessageVO(String messageId) {
        this.messageId = messageId;
    }

    /**
     * This constructor message vo
     *
     * @param messageId Message id
     * @param params    Message parameter
     */
    public MessageVO(String messageId, String[] params) {
        this.messageId = messageId;
        this.params = params;
    }

    /**
     * This constructor message vo no agrument
     */
    public MessageVO() {
    }

    /**
     * This constructor message vo
     *
     * @param type      Type message
     * @param messageId Message id
     * @param params    Message parameter
     */
    public MessageVO(String type, String messageId, String[] params) {
        this.type = type;
        this.messageId = messageId;
        this.params = params;
    }
}