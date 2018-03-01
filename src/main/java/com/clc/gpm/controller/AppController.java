package com.clc.gpm.controller;

import com.clc.gpm.common.Constants;
import com.clc.gpm.exception.BaseException;
import com.clc.gpm.utils.StringUtil;
import com.clc.gpm.validator.common.AppValidator;
import com.clc.gpm.validator.common.BindingResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * The type App controller.
 */
public class AppController {
    private static final String SCREEN_MESSAGE = "screenMessage";

    /**
     * The Logger.
     */
    protected Log logger;

    /**
     * The Log.
     */
    protected  Log log;

    private AppValidator validator;

    @Autowired
    private ApplicationContext context;

    /**
     * The Request.
     */
    @Autowired
    protected HttpServletRequest request;

    /**
     * The Response.
     */
    @Autowired
    protected HttpServletResponse response;

    /**
     * The Str util.
     */
    @Autowired
    protected StringUtil strUtil;


    /**
     * The Binding result.
     */
    @Autowired
    protected BindingResult bindingResult;

    /**
     * Instantiates a new App controller.
     */
//Constructor
    public AppController() {
    }

    @PostConstruct
    private void init(){
        this.logger = LogFactory.getLog(this.getClass());


    }

    /**
     * <p>説明 :invoke validator object</p>
     * @author [bp.truong.pq]
     * @since [2017/11/25]
     */
    private void invokeValidator() {
        String validateName = this.getClass().getSimpleName();
        validateName = validateName.substring(0, 1).toLowerCase()
                + validateName.substring(1, validateName.indexOf("Controller"))
                + "Validator";

        try {
            // get validator bean object
            validator = (AppValidator) context.getBean(validateName);
        } catch (Exception e) {
            logger.warn("No customize validator. Use simpleValidator");
            validator = (AppValidator) context.getBean("simpleValidator");
        }
    }

    /**
     * <p>説明 : Validate form input</p>
     *
     * @param targetObj Object
     * @param result    BindingResult
     * @author [bp.truong.pq]
     * @since [2017 /11/25]
     */
    protected void validate(Object targetObj,
                            org.springframework.validation.BindingResult result) {

        if (validator != null) {
            bindingResult.clearErrors();
            validator.validate(targetObj, bindingResult);

            if (bindingResult.hasErrors()) {
                try {

                    // insert error to bindingresult
                    if (bindingResult.hasErrors()) {
                        Iterator<Throwable> ite = bindingResult.getAllErrors()
                                .iterator();
                        BaseException ex = null;

                        while (ite.hasNext()) {
                            ex = (BaseException) ite.next();
                            result.rejectValue(ex.getFieldName(),
                                    ex.getMessageId(), ex.getDefaultMessage());
                        }
                        result.rejectValue(SCREEN_MESSAGE,
                                Constants.MSG_TOTAL_ERR,
                                new Integer[] {
                                        bindingResult.getAllErrors().size() },
                                "Screen error!");
                    }

                } catch (Exception e) {
                    logger.warn("Input form not existed errors field");
                }
            }
        }

    }


    /**
     * Redirect to model and view.
     *
     * @param viewName the view name
     * @return the model and view
     */
    protected ModelAndView redirectTo(String viewName) {
        return new ModelAndView("redirect:" + viewName);
    }


    /**
     * Add logic error.
     *
     * @param result  the result
     * @param message the message
     */
    public void addLogicError(
            org.springframework.validation.BindingResult result,
            String message) {
        result.rejectValue(SCREEN_MESSAGE, message);
    }

    /**
     * Add logic error.
     *
     * @param result    the result
     * @param messageId the message id
     * @param paramMsg  the param msg
     */
    public void addLogicError(
            org.springframework.validation.BindingResult result,
            String messageId, Object[] paramMsg) {
        result.rejectValue(SCREEN_MESSAGE, messageId, paramMsg, "errorLogic");

    }

}
