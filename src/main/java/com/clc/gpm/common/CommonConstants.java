package com.clc.gpm.common;

import java.util.Arrays;
import java.util.List;

/**
 * <p>ファイル名 : Constants</p>
 * <p>説明 : Constants</p>
 * @author truong.pq
 * @since 2017/11/25
 */
public class CommonConstants {
    // root directory
    /**  ROOT_DIRECTORY */
    public static final String ROOT_DIRECTORY = "sites/";

    /**  ROOT_DIRECTORY_MASTER */
    public static final String ROOT_DIRECTORY_MASTER = "sites/master/";

    /**  ROOT_DIRECTORY_SHOHIN */
    public static final String ROOT_DIRECTORY_SHOHIN = "sites/shohin/";

    /**  ROOT_DIRECTORY_CHUMON */
    public static final String ROOT_DIRECTORY_CHUMON = "sites/chumon/";

    /**  ROOT_DIRECTORY_HANBAI */
    public static final String ROOT_DIRECTORY_HANBAI = "sites/hanbai/";

    /**  ROOT_DIRECTORY_HANSOKU */
    public static final String ROOT_DIRECTORY_HANSOKU = "sites/hansoku/";

    // common constan
    /** Message type enum*/
    public static enum MessageType {
        /**Waring*/
        WARN,

        /**Error*/
        ERROR,

        /**Information*/
        INFO
    };

    // Langguage code
    /** LG_CD_JA */
    public static final String LG_CD_JA = "ja";

    /** LG_CD_EN */
    public static final String LG_CD_EN = "en";

    /** LG_CD_RU */
    public static final String LG_CD_RU = "ru";

    /** LG_CD_ES */
    public static final String LG_CD_ES = "es";

    /** LIST_LANGUAGE_CD */
    public static final List<String> LIST_LANGUAGE_CD = Arrays.asList(LG_CD_JA, LG_CD_EN, LG_CD_RU, LG_CD_ES);

    /** DB_AVAILABLE */
    public static final String DB_AVAILABLE = "0";

    /**  DB_DELETED */
    public static final String DB_DELETED = "1";

    /** NUM_0 */
    public static final int NUM_0 = 0;

    /**  SORT_AZ */
    public static final String ASC = "ASC";

    /**  SORT_ZA */
    public static final String DESC = "DESC";

    /**  list sort type */
    public static final List<String> LST_SORT_TYPE = Arrays.asList(ASC, DESC);

    /** BLANK */
    public static final String BLANK = "";

    /** EMPTY */
    public static final String SPACE_HAFTSIZE = " ";

    /** COMMA */
    public static final String COMMA = ",";

    /** DATE_FORMAT_YYYYMMDD */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy/MM/dd";

    /** GRID_ROW_COUNT */
    public static final Integer GRID_ROW_COUNT = 10;

    // validate constan
    /** 船会社名 max length */
    public static final int VES_CO_NM_MAX_LENGTH = 100;

    /** Maker Name max length */
    public static final int MK_NM_MAX_LENGTH = 50;

    /** taa mem name max length */
    public static final int TAA_MEM_NM_MAX_LENGTH = 50;

    /** MAX TINYINT VALUE */
    public static final int MAX_TINYINT_VALUE_MYSQL = 127;

    /** MAX SMALLINT VALUE */
    public static final int MAX_SMALLINT_VALUE_MYSQL = 32767;

    /** MAX MEDIUMINT VALUE */
    public static final int MAX_MEDIUMINT_VALUE_MYSQL = 8388607;

    /** MAX INT VALUE */
    public static final int MAX_INT_VALUE_MYSQL = 2147483647;

    /** MAX BIGINT VALUE */
    public static final int MAX_BIGINT_VALUE_MYSQL = 2 ^ 64 - 1;

    /** id next record detail */
    public static final String BTN_NEXT_ID = "idNext";

    /** id previous record detail */
    public static final String BTN_PREV_ID = "idPrev";

    /** submitSuccess Attribute. */
    public static final String SUBMIT_SUCCESS = "submitSuccess";

    /** CURRENT_TAB */
    public static final String CURRENT_TAB = "currentTab";

    /** UPDATE_TIME */
    public static final String UPDATE_TIME = "updateTime";

    /** IS_BACK*/
    public static final String IS_BACK = "isBack";

    /** UPDATE_NOTIFY*/
    public static final String MESSAGE = "message";

    /** csv extension */
    public static final String CSV_EXTENTION = ".csv";

    /** Zip extension */
    public static final String ZIP_EXTENTION = ".zip";

    /** shift_jis charset*/
    public static final String SHIFT_JIS_CHARSET = "SHIFT_JIS";

    /** ISO8859_1 charset*/
    public static final String ISO8859_1_CHARSET = "ISO8859_1";

    /** REDIRECT*/
    public static final String REDIRECT = "redirect:";

    /** FORWARD*/
    public static final String FORWARD = "forward:";

    /** title page*/
    public static final String TITLE = "title";

    /** TAB_MENU*/
    public static final String TAB_MENU = "tabmenu";

    /** not exist index */
    public static final Integer NOT_EXIST_INDEX = -1;

    /** csvFile*/
    public static final String FIELD_CSV_FILE = "csvFile";

    /** 更新画面コード*/
    public static final int DETAIL_ACTION = 3;

    /** 更新画面コード*/
    public static final int UPDATE_ACTION = 3;

    /** 登録画面コード*/
    public static final int REGISTER_ACTION = 4;

    /** 削除画面コード*/
    public static final int DELETE_ACTION = 5;

    /** 有フラグ*/
    public static final String ABLE_CODE = "1";

    /** 有フラグ*/
    public static final String ABLE_VALUE = "有";

    /** 無フラグ*/
    public static final String UNABLE_CODE = "0";

    /** 無フラグ*/
    public static final String UNABLE_VALUE = "無";

    /**存在しないコード*/
    public static final int NOT_EXIST_CD = -1;

    /**参照コード*/
    public static final String REF_CODE = "010";

    /**更新コード*/
    public static final String UPDATE_CODE = "020";

    /**承認コード*/
    public static final String APPROVE_CODE = "030";

    /**CSVコード*/
    public static final String CSV_CODE = "040";

    /** 権限コードリスト */
    public static final List<String> LST_AUTH_CD = Arrays.asList(REF_CODE, UPDATE_CODE, APPROVE_CODE, CSV_CODE);

    /**参照コード*/
    public static final String REF_TEXT = "参照";

    /**更新コード*/
    public static final String UPDATE_TEXT = "更新";

    /**承認コード*/
    public static final String APPROVE_TEXT = "承認";

    /**CSVコード*/
    public static final String CSV_TEXT = "CSV";

    /**CSV_CONTENT_TYPE フラグ*/
    public static final String CSV_CONTENT_TYPE = "application/octet-stream";

    /**CSV_CONTENT_TYPE フラグ*/
    public static final String ZIP_CONTENT_TYPE = "application/zip";

    /** NUMBER_FORMAT_4_DIGITAL*/
    public static final String NUMBER_FORMAT_4_DIGITAL = "%04d";

    /** NUMBER_FORMAT_5_DIGITAL*/
    public static final String NUMBER_FORMAT_5_DIGITAL = "%05d";

    /** MSG_RIQUIRED_INPUT_ERR*/
    public static final String NUMBER_FORMAT_7_DIGITAL = "%07d";

    /**存在しないコード*/
    public static final String NOT_EXIST_CD_STR = "-1";

    /** MSG_DUPLICATED_DATA */
    public static final String MSG_DUPLICATED_DATA = "ER0000";

    /**THREE_NUMBER */
    public static final int THREE_NUMBER = 3;

    /**ONE_NUMBER */
    public static final int ONE_NUMBER = 1;

    /**TOW_NUMBER */
    public static final int TWO_NUMBER = 2;

    /** CTRY_2DIGIT_JAPAN */
    public static final String CTRY_2DIGIT_JAPAN = "JA";

    // code mst
    /** ITEM_TYPE_CODE */
    public static final String ITEM_TYPE_CODE = "S380";

    /** ACQUISITION_OF_SHIP_TYPE */
    public static final String ACQUISITION_OF_SHIP_TYPE = "S400";

    /** COMPANY_CODE */
    public static final String COMPANY_CODE = "B610";

    /** PRODUCT_CODE */
    public static final String PRODUCT_CODE = "S140";

    /** SALE_AREA__TYPE_CODE */
    public static final String SALE_AREA__TYPE_CODE = "B170";

    /** SHIPPING_AREA_TYPE_CODE */
    public static final String SHIPPING_AREA_TYPE_CODE = "B120";

    /** MSG_GRADE_NAME_EXIST_ERR */
    public static final String MSG_GRADE_NAME_EXIST_ERR = "C0013";

    /** AUC_EXTR_CLS_CODE */
    public static final String AUC_EXTR_CLS_CODE = "S560";

    /** TGNA_TRADING_DIV_CODE */
    public static final String TGNA_TRADING_DIV_CODE = "S660";

    /** INS_TGNA_STTL_FEE_TRF_CODE */
    public static final String INS_TGNA_STTL_FEE_TRF_CODE = "B540";

    /** MST_CODE_400 */
    public static final String MST_CODE_400 = "S400";

    /** MST_CODE_190 */
    public static final String MST_CODE_190 = "S190";

    /**THREE_NUMBER */
    public static final String UPLOAD_FILE_CSV_FORM = "uploadFileCSVForm";

    /**UTF8_CHARSET */
    // public static final String UTF8_CHARSET = "UTF-8";
    public static final String UTF8_CHARSET = "UTF8";

    public static final String ACTION = "action";

    /** STR_MONTH */
    public static final String STR_MONTH_PATTERN = "%02d月";

    /** STR_YEAR */
    public static final String STR_YEAR_PATTERN = "%s年/%s";
}
