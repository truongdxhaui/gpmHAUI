package com.clc.gpm.service;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.common.MessageConstants;
import com.clc.gpm.dto.BaseDTO;
import com.clc.gpm.entity.BaseEntity;
import com.clc.gpm.exception.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.RollbackException;
import java.time.LocalDateTime;

/**
 * <p>ファイル名 : BaseService</p>
 * <p>説明 : BaseService</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public class BaseService {

    @Autowired
    CommonService commonService;

    /** logger*/
    protected final Logger logger = LogManager.getLogger(BaseService.class);

    /** modelMapper*/
    protected ModelMapper modelMapper = new ModelMapper();
    
    /**
     * roll back
     * @param errMessage String
     */
    protected void rollBack(String errMessage) {
        logger.info("rollBack {}", errMessage);
        RollbackException ex = new RollbackException(errMessage);
        throw ex;

    }

    /**
     * 
     * <p>説明 : throw RuntimeException</p> 
     * @param errMessage String
     */
    protected void returnSystemException(String errMessage) {
        logger.info("RuntimeException {}", errMessage);
        throw new RuntimeException(MessageConstants.MSG_1296);
    }
    
    /**
     * roll back
     * @param errMessage String
     * @throws LogicException 
     */
    protected void returnLogicError(String errMessage) throws LogicException {
        logger.info("Logic error {}", errMessage);
        LogicException ex = new LogicException(errMessage);
        throw ex;
    }

    /**
     * 
     * <p>説明 : copy create class info</p> 
     * @author : minh.ls
     * @since : 2018/02/05
     * @param dto BaseDTO
     */
    protected void copyCreateInfo(BaseDTO dto) {
        dto.setCreateClass(this.getClass().toString());
        dto.setUpdateClass(this.getClass().toString());
    }
    /**
     * 
     * <p>説明 : copy update class info</p> 
     * @author : duc.bv
     * @since : 2018/02/22
     * @param dto BaseDTO
     */
    protected void copyUpdateInfo(BaseDTO dto) {
    	dto.setUpdateClass(this.getClass().toString());
        dto.setUpdateTime(LocalDateTime.now());
    }
    
    /**
     * 
     * <p>説明 : Copy delete info</p> 
     * @author : hung.nq
     * @since : 2018/02/26
     * @param dto BaseDTO
     */
    protected void copyDeleteInfo (BaseDTO dto) {
        dto.setUpdateClass(this.getClass().toString());
        dto.setUpdateTime(LocalDateTime.now());
        dto.setDeleteFlg(CommonConstants.DB_DELETED);
        dto.setDeleteTime(LocalDateTime.now());
    }

    /**
     *
     * <p>説明 : copy create class info</p>
     * @author : minh.ls
     * @since : 2018/02/05
     * @param dto BaseDTO
     */
    protected void copyCreateInfoEntity(BaseEntity dto) {
        String userLogin = commonService.getCurrentUsername();
        dto.setCreateUser(userLogin);
        dto.setCreateTime(LocalDateTime.now());
        dto.setUpdateUser(userLogin);
        dto.setUpdateTime(LocalDateTime.now());
        dto.setDeleteFlg(CommonConstants.DB_AVAILABLE);
        dto.setCreateClass(this.getClass().toString());
        dto.setUpdateClass(this.getClass().toString());
        dto.setDeleteFlg("0");
    }
    /**
     *
     * <p>説明 : copy update class info</p>
     * @author : duc.bv
     * @since : 2018/02/22
     * @param dto BaseDTO
     */
    protected void copyUpdateInfoEntity(BaseEntity dto) {
        String userLogin = commonService.getCurrentUsername();
        dto.setUpdateUser(userLogin);
        dto.setUpdateTime(LocalDateTime.now());
        dto.setUpdateClass(this.getClass().toString());
        dto.setUpdateTime(LocalDateTime.now());
    }

    /**
     *
     * <p>説明 : Copy delete info</p>
     * @author : hung.nq
     * @since : 2018/02/26
     * @param dto BaseDTO
     */
    protected void copyDeleteInfoEntity(BaseEntity dto) {
        String userLogin = commonService.getCurrentUsername();
        dto.setUpdateClass(this.getClass().toString());
        dto.setUpdateTime(LocalDateTime.now());
        dto.setDeleteFlg(CommonConstants.DB_DELETED);
        dto.setDeleteTime(LocalDateTime.now());
    }
}