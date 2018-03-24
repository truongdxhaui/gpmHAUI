package com.clc.gpm.controller;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.common.MessageConstants;
import com.clc.gpm.dto.BaseDTO;
import com.clc.gpm.exception.BaseException;
import com.clc.gpm.utils.CheckUtil;
import com.clc.gpm.validator.common.AppValidator;
import com.clc.gpm.validator.common.BindingResult;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;



/**
 * <p>ファイル名 : AppController</p>
 * <p>説明 : BaseController controller class</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public abstract class AppController {

    /** logger*/
    protected final Logger logger = LogManager.getLogger(this.getClass());

    /** Screen error */
    private static final String SCREEN_ERROR = "Screen error!";

    /** SCREEN_MESSAGE */
    private static final String SCREEN_MESSAGE = "screenMessage";

    /** BINDING_RESULT */
    protected static final String BINDING_RESULT = "bindingResult";

    /** AbstractValidatorのインスタンス */
    private AppValidator validator;

    /** ApplicationContextのインスタンス */
    @Autowired
    private ApplicationContext context;

    /** HttpSession */
    @Autowired
    protected HttpSession session;

    /** modelMapper*/
    protected ModelMapper modelMapper;

    /** BindingResult */
    @Autowired
    protected BindingResult bindingResult;

    /** BindingResult */
    String userLogin = "USER LOGIN";

    /** constructor method */
    public AppController() {
    }

    /**
     * <p>説明 : init variable</p>
     * @author bp.truong.pq
     * @since 2017/11/25
     */
    @PostConstruct
    private void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
            public boolean applies(MappingContext<Object, Object> pContext) {
                if (null == pContext.getSource() || null == pContext.getDestinationType()) {
                    return false;
                }
                /*
                 * if (pContext.getSourceType().equals(String.class)) { if
                 * (CommonConstants.BLANK.equals(pContext.getSource().toString())) { return false; } }
                 */
                if (pContext.getSourceType().equals(String.class)
                        && (Integer.class.equals(pContext.getDestinationType()) || int.class.equals(pContext.getDestinationType()))) {
                    if (CheckUtil.isSignNumber(pContext.getSource().toString())) {
                        return true;
                    }
                    return false;
                }
                return true;
            }
        });
        invokeValidator();
    }

    /**
     * <p>説明 :invoke validator object</p>
     * @author bp.truong.pq
     * @since 2017/11/25
     */
    private void invokeValidator() {
        String validateName = this.getClass().getSimpleName();
        validateName = validateName.substring(0, validateName.indexOf("Controller")) + "Validator";

        try {
            // バリデータビーンオブジェクトを取得する
            validator = (AppValidator) context.getBean(validateName);
        } catch (Exception e) {
            logger.warn("No customize validator. Use simpleValidator");
            validator = (AppValidator) context.getBean("simpleValidator");
        }
    }

    /**
     * <p>説明 : Validate form input</p>
     * @author bp.truong.pq
     * @since 2017/11/25
     * @param targetObj Object
     * @param result BindingResult
     */
    protected void validate(Object targetObj, org.springframework.validation.BindingResult result) {
        if (validator != null) {
            bindingResult.clearErrors();
            validator.validate(targetObj, bindingResult);

            if (bindingResult.hasErrors()) {
                try {
                    // bindingresultにエラーを入力
                    if (bindingResult.hasErrors()) {
                        Iterator<Throwable> ite = bindingResult.getAllErrors().iterator();
                        int msg006 = 0;
                        BaseException ex = null;
                        while (ite.hasNext()) {
                            ex = (BaseException) ite.next();
                            if (ex.getMessageId().equals(MessageConstants.MSG_W_C0006)) {
                                msg006++;
                            }
                        }
                        int totalError = bindingResult.getAllErrors().size();
                        if (msg006 > 0) {
                            totalError -= msg006 - 1;
                        }
                        try {
                            result.rejectValue(SCREEN_MESSAGE, MessageConstants.MSG_95, new Integer[] { totalError }, SCREEN_ERROR);
                        } catch (NotReadablePropertyException e) {
                            logger.warn(e.getMessage());
                        }

                        ite = bindingResult.getAllErrors().iterator();
                        while (ite.hasNext()) {
                            ex = (BaseException) ite.next();
                            if (ex.getMessageId().equals(MessageConstants.MSG_W_C0006)) {
                                msg006++;
                            } else {
                                result.rejectValue(ex.getFieldName(), ex.getMessageId(), ex.getDefaultMessage());
                            }
                        }
                        if (msg006 > 0) {
                            result.rejectValue(SCREEN_MESSAGE, MessageConstants.MSG_W_C0006, new Integer[] { msg006 }, SCREEN_ERROR);
                        }
                    }
                } catch (Exception e) {
                    logger.warn("Input form not existed errors field");
                }
            }
        }
    }

    /**
     * <p>説明 : Redirect to view name</p>
     * @author bp.truong.pq
     * @since 2017/11/25
     * @param viewName String
     * @return ModelAndView
     */
    protected ModelAndView redirectTo(String viewName) {
        return new ModelAndView("redirect:" + viewName);
    }

    /**
     * <p>説明 : add Error when has logic error</p>
     * @author bp.truong.pq
     * @since 2017/11/25
     * @param result BindingResult
     * @param message String
     */
    public void addLogicError(org.springframework.validation.BindingResult result, String message) {
        result.rejectValue(SCREEN_MESSAGE, message);
    }

    /**
     * <p>説明 : add Error when has logic error</p>
     * @author bp.truong.pq]
     * @since 2017/11/25
     * @param result BindingResult
     * @param messageId String
     * @param paramMsg Object[]
     */
    public void addLogicError(org.springframework.validation.BindingResult result, String messageId, Object[] paramMsg) {
        result.rejectValue(SCREEN_MESSAGE, messageId, paramMsg, "errorLogic");

    }

    /**
     *
     * <p>説明 : addObjectToSession</p>
     * @author : minh.nt
     * @since 2018/01/24
     * @param session HttpSession
     * @param object Object
     * @param name  String
     */
    protected void addObjectToSession(HttpSession session, Object object, String name) {
        session.setAttribute(name, object);
    }

    /**
     *
     * <p>説明 : addObjectToSession</p>
     * @author : minh.nt
     * @since 2018/01/24
     * @param object Object
     * @param name  String
     */
    protected void addObjectToSession(String name, Object object) {
        session.setAttribute(name, object);
    }

    /**
     *
     * <p>説明 : getObjectFromSession</p>
     * @author : minh.nt
     * @since 2018/01/24
     * @param session HttpSession
     * @param name String
     * @return Object
     */
    protected Object getObjectFromSession(HttpSession session, String name) {
        return session.getAttribute(name);
    }

    /**
     *
     * <p>説明 : getObjectFromSession</p>
     * @author : minh.nt
     * @since 2018/01/24
     * @param name String
     * @return Object
     */
    protected Object getObjectFromSession(String name) {
        return session.getAttribute(name);
    }

    /**
     *
     * <p>説明 : removeObjectFromSession</p>
     * @author : minh.nt
     * @since 2018/01/24
     * @param session HttpSession
     * @param name String
     */
    protected void removeObjectFromSession(HttpSession session, String name) {
        session.removeAttribute(name);
    }

    /**
     *
     * <p>説明 : removeObjectFromSession</p>
     * @author : minh.nt
     * @since 2018/01/24
     * @param name String
     */
    protected void removeObjectFromSession(String name) {
        session.removeAttribute(name);
    }

    /**
     *
     * <p>説明 : convertJsonToListInteger</p>
     * @author : thien.nv
     * @since 2018/01/25
     * @param listStringCd String
     * @return List<Integer>
     */
    protected List<Integer> convertJsonToListInteger(String listStringCd) {
        Gson converter = new Gson();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        List<Integer> iList = converter.fromJson(listStringCd, type);
        return iList;
    }

    /**
     *
     * <p>説明 : initial model and View with view name</p>
     * @author : hung.nq
     * @since 2018/02/09
     * @param rootDirectory root directory
     * @param screenId 画面ID
     * @return ModelAndView
     */
    protected ModelAndView createViewName(String rootDirectory, String screenId ) {
        ModelAndView modelAndView = new ModelAndView();
        this.setViewName(modelAndView, rootDirectory, screenId);
        return modelAndView;
    }

    /**
     *
     * <p>説明 : initial model and View with view name</p>
     * @author : hung.pd
     * @since 2018/01/29
     * @param screenId 画面ID
     * @return ModelAndView
     */
    protected ModelAndView createViewNameMaster(String screenId) {
        ModelAndView modelAndView = new ModelAndView();
        this.setViewName(modelAndView, CommonConstants.ROOT_DIRECTORY_MASTER, screenId);
        return modelAndView;
    }

    /**
     *
     * <p>説明 : initial model and View with view name</p>
     * @author : minh.ls
     * @since 2018/02/09
     * @param screenId 画面ID
     * @return ModelAndView
     */
    protected ModelAndView createViewNameRoot(String screenId) {
        ModelAndView modelAndView = new ModelAndView();
        this.setViewName(modelAndView, CommonConstants.ROOT_DIRECTORY, screenId);
        return modelAndView;
    }

    /**
     *
     * <p>説明 : initial model and View with view name</p>
     * @author : hung.pd
     * @since 2018/01/29
     * @param modelAndView         Template
     * @param strings
     *                  first element path
     *                  second element view
     * @return
     */
    protected void setViewName(ModelAndView modelAndView, String... strings) {
        String rootPath = "";
        String viewName = "";
        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            String preViewName = modelAndView.getViewName();
            if (preViewName != null) {
                rootPath = preViewName.substring(0, preViewName.lastIndexOf("/") + 1);
            }
        }

        if (strings.length == 1) {
            viewName = rootPath + strings[0];
        }

        if (strings.length == 2) {
            viewName = strings[0] + strings[1];
        }
        modelAndView.setViewName(viewName);
    }

    /**
     *
     * <p>説明 : initial model and View with view name</p>
     * @author : hung.pd
     * @since 2018/01/29
     * @param screenId 画面ID
     * @return ModelAndView
     */
    protected ModelAndView createViewNameProduct(String screenId) {
        ModelAndView modelAndView = new ModelAndView();
        this.setViewName(modelAndView, CommonConstants.ROOT_DIRECTORY_SHOHIN, screenId);
        return modelAndView;
    }

    /**
     *
     * <p>説明 : copy create info</p>
     * @author : minh.ls
     * @update: duc.bv
     * @since 2018/02/05
     * @param dto BaseDTO
     */
    protected void copyCreateInfo(BaseDTO dto) {
        dto.setCreateUser(userLogin);
        dto.setCreateTime(LocalDateTime.now());
        dto.setUpdateUser(userLogin);
        dto.setUpdateTime(LocalDateTime.now());
        dto.setDeleteFlg(CommonConstants.DB_AVAILABLE);
    }

    /**
     * <p>説明 : Copy update info</p>
     * @author : minh.ls
     * @since 2018/02/05
     * @param dto                  BaseDTO
     */
    protected void copyUpdateInfo(BaseDTO dto) {
        dto.setUpdateUser(userLogin);
        dto.setUpdateTime(LocalDateTime.now());
    }

    /**
     *
     * <p>説明 : get user</p>
     * @return UserName
     */
    protected String getUser() {
        return userLogin;
    }

    /**
     * <p>説明 : FIXME Copy delete info</p>
     * @author : minh.ls
     * @since 2018/02/05
     * @param dto                     BaseDTO
     */
    protected void copyDeleteInfo(BaseDTO dto) {
        dto.setUpdateUser(userLogin);
        dto.setUpdateTime(LocalDateTime.now());
        dto.setDeleteFlg(CommonConstants.DB_DELETED);
        dto.setDeleteTime(LocalDateTime.now());
    }
}
