package com.tau.validator.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tau.exception.BaseException;
import com.tau.forms.AppForm;
import com.tau.utils.CheckUtil;
import com.tau.utils.MessageUtil;
import com.tau.utils.StringUtil;
import com.tau.validator.annotation.Ignore;

/**
 * <p>ファイル名 : AppValidator</p>
 * <p>説明 : AppValidator</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public abstract class AppValidator {

    /**
     * HAS_ERROR
     */
    private static final String HAS_ERROR = "] has error: ";

    /**
     * FIELD_STRING_END
     */
    private static final String FIELD_STRING_END = "] is ignore validate.";

    /**
     * FIELD_STRING_BEGIN
     */
    private static final String FIELD_STRING_BEGIN = "Field [";

    /**
     * logger
     */
    protected Log logger;

    /**
     * strUtil
     */
    @Autowired
    protected StringUtil strUtil;

    /**
     * checkUtil
     */
    @Autowired
    protected CheckUtil checkUtil;

    /**
     * msgUtil
     */
    @Autowired
    protected MessageUtil msgUtil;

    /**
     * commonValidator
     */
    @Autowired
    protected CommonValidator commonValidator;

    /**
     * errorItemNameList
     */
    private List<String> errorItemNameList;

    /**
     * init variable
     */
    @PostConstruct
    public void init() {

        // initial logger
        this.logger = LogFactory.getLog(this.getClass());
    }


    /**
     * <p>説明 : validateField</p> 
     * @author : [dts.bp34]
     * @since : [2017/12/26]
     * @param fieldName String
     * @param fieldValue String
     * @param errors 
     */
    public abstract void validateField(String fieldName, String fieldValue,
            BindingResult errors);

    /**
     * 
     * <p>説明 : Validate logic</p>
     * @author [bp.truong.pq]
     * @since [2017/11/25] 
     * @param clazz AppForm
     * @param errors BindingResult
     */
    public abstract void validateLogic(AppForm clazz, BindingResult errors);

    
    /**
     * <p>説明 : validate</p> 
     * @author : [dts.bp34]
     * @since : [2017/12/26]
     * @param targetObj Object
     * @param errors BindingResult
     * @param items 
     */
    public void validate(Object targetObj, BindingResult errors,
            String... items) {

        String property = "";
        String fieldVal = "";
        Class<? extends Object> clazz = targetObj.getClass();

        Field[] fields = clazz.getDeclaredFields();
        AppForm appForm = AppForm.class.cast(targetObj);
        errorItemNameList = new ArrayList<String>();

        for (Field field : fields) {

            property = field.getName();

            if (field.isAnnotationPresent(Ignore.class)) {
                logger.debug(FIELD_STRING_BEGIN + property + FIELD_STRING_END);
                continue;
            }

            // Check property List type
            if ("java.util.List".equals(field.getType().getName())) {

                List<?> objVal = new ArrayList<Object>();
                try {
                    // set value of property List
                    Method method = targetObj.getClass()
                            .getMethod("get"
                                    + property.substring(0, 1).toUpperCase()
                                    + property.substring(1), new Class[] {});
                    objVal = (List<?>) method.invoke(targetObj,
                            new Object[] {});
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warn(FIELD_STRING_BEGIN + property + "] no getter.");
                }

                if (objVal != null) {
                    for (Object obj : objVal) {
                        if (!(obj == null || obj instanceof String
                                || obj instanceof Number)) {
                            validate(obj, errors, property);
                        } else {
                            validateItem(field, (String) obj, property, errors);
                        }
                    }
                }
            } else {

                try {
                    field.setAccessible(true);
                    fieldVal = strUtil.convertNull(field.get(targetObj));
                } catch (Exception e) {
                    logger.debug("Can not to get data for Field [" + property
                            + "].");
                    e.printStackTrace();
                }

                validateItem(field, fieldVal, property, errors);
            }

        }

        // Validate logic
        if (!errors.hasErrors()) {
            validateLogic(appForm, errors);
        }
    }

    /**
     * Validate item
     *
     * @param field Field
     * @param fieldVal String
     * @param property String
     * @param errors BindingResult
     */
    private void validateItem(Field field, String fieldVal, String property,
            BindingResult errors) {

        try {

            // Validate wit annotation
            // TODO : validate annotation by order ?
            commonValidator.validateWithAnnotation(field, fieldVal,
                    errorItemNameList);

            if (!strUtil.isNull(fieldVal)) {
                validateField(property, fieldVal, errors);
            }
        } catch (Throwable ex) {
            if (ex instanceof BaseException) {
                logger.debug(FIELD_STRING_BEGIN + property + HAS_ERROR
                        + ((BaseException) ex).getMessageId());
            } else {
                logger.debug(FIELD_STRING_BEGIN + property + HAS_ERROR
                        + ex.getMessage());
            }
            errors.addError(ex);
        }
    }

    /**
     * <p>説明 : getErrorItemNameList</p> 
     * @author : [dts.bp34]
     * @since : [2017/12/26]
     * @return List<String>
     */
    public List<String> getErrorItemNameList() {
        return errorItemNameList;
    }

    /**
     * <p>説明 : ???</p> 
     * @author : [dts.bp34]
     * @since : [2017/12/26]
     * @param errorItemNameList 
     */
    public void setErrorItemNameList(List<String> errorItemNameList) {
        this.errorItemNameList = errorItemNameList;
    }

}
