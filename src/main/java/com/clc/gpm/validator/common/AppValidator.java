package com.clc.gpm.validator.common;

import com.clc.gpm.exception.BaseException;
import com.clc.gpm.form.AppForm;
import com.clc.gpm.utils.StringUtil;
import com.clc.gpm.validator.annotation.Ignore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * The type App validator.
 */
public abstract class AppValidator {

    /** CAN_NOT_TO_GET_DATA_FOR_FIELD  */
    private static final String CAN_NOT_TO_GET_DATA_FOR_FIELD = "Can not to get data for Field {}.";

    /**
     * SCREEN MESSAGE
     */
    protected final static String SCREEN_MESSAGE_ITEM = "screenMessage";
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
    
    /** MULTIPARTFILE_CLASS */
    private static final String MULTIPARTFILE_CLASS = "org.springframework.web.multipart.MultipartFile";
    
    /** LIST_CLASS */
    private static final String LIST_CLASS = "java.util.List";

    /**
     * logger
     */
    protected Logger logger;

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
        this.logger = LogManager.getLogger(this.getClass());
    }


    /**
     * Validate field.
     *
     * @param clazz      the clazz
     * @param fieldName  the field name
     * @param fieldValue the field value
     * @param errors     the errors
     */
    public abstract void validateField(AppForm clazz, String fieldName, String fieldValue, BindingResult errors);


    /**
     * Validate logic.
     *
     * @param clazz  the clazz
     * @param errors the errors
     */
    public abstract void validateLogic(AppForm clazz, BindingResult errors);


    /**
     * Validate.
     *
     * @param targetObj the target obj
     * @param errors    the errors
     * @param items     the items
     */
    public void validate(Object targetObj, BindingResult errors, String... items) {

        String property = "";
        String fieldVal = "";
        Class<? extends Object> clazz = targetObj.getClass();

        Field[] fields = clazz.getDeclaredFields();
        AppForm appForm = AppForm.class.cast(targetObj);
        errorItemNameList = new ArrayList<String>();

        for (Field field : fields) {

            property = field.getName();
            String fieldClassName = field.getType().getName();

            if (field.isAnnotationPresent(Ignore.class)) {
                logger.debug(FIELD_STRING_BEGIN + property + FIELD_STRING_END);
                continue;
            }

            // FIXME validate list
            if (LIST_CLASS.equals(fieldClassName)) {

                List<?> objVal = new ArrayList<Object>();
                try {
                    Method method = targetObj.getClass().getMethod("get" + property.substring(0, 1).toUpperCase() + property.substring(1), new Class[] {});
                    objVal = (List<?>) method.invoke(targetObj, new Object[] {});
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warn(FIELD_STRING_BEGIN + property + "] no getter.");
                }

                if (objVal != null) {
                    for (int i = 0; i < objVal.size(); i++) {
                        Object obj = objVal.get(i);
                        String listName = property + "[" + i + "].";
                        if (!(obj == null || obj instanceof String || obj instanceof Number)) {
                            validateListObject(appForm, obj, errors, listName);
                        } else {
                            validateItem(appForm, field, (String) obj, property, errors);
                        }
                    }
                }
             // FIXME validate MultipartFile
            } else if (MULTIPARTFILE_CLASS.equals(fieldClassName)) {
                MultipartFile file = null;
                try {
                    field.setAccessible(true);
                    file = (MultipartFile) field.get(targetObj);
                } catch (Exception e) {
                    logger.debug(CAN_NOT_TO_GET_DATA_FOR_FIELD, property);
                    e.printStackTrace();
                }
                /*validatemultipartfileitem(field, file, property, errors);*/

            } else {
                // validate property
                try {
                    field.setAccessible(true);
                    fieldVal = StringUtil.convertNull(field.get(targetObj));
                } catch (Exception e) {
                    logger.debug(CAN_NOT_TO_GET_DATA_FOR_FIELD, property);
                    e.printStackTrace();
                }

                validateItem(appForm, field, fieldVal, property, errors);
            }

        }

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
     * @param clazz AppForm
     */
    private void validateItem(AppForm clazz, Field field, String fieldVal, String property, BindingResult errors) {

        try {
            commonValidator.validateWithAnnotation(field, fieldVal, errorItemNameList);

            if (!StringUtil.isNull(fieldVal)) {
                validateField(clazz, property, fieldVal, errors);
            }
        } catch (Throwable ex) {
            if (ex instanceof BaseException) {
                logger.debug(FIELD_STRING_BEGIN + property + HAS_ERROR + ((BaseException) ex).getMessageId());
            } else {
                logger.debug(FIELD_STRING_BEGIN + property + HAS_ERROR + ex.getMessage());
            }
            errors.addError(ex);
        }
    }

    private void validateListObject(AppForm appForm, Object targetObj, BindingResult errors, String listName) {
        String property = "";
        String fieldVal = "";
        Class<? extends Object> clazz = targetObj.getClass();

        Field[] fields = clazz.getDeclaredFields();
        errorItemNameList = new ArrayList<String>();
        
        for (Field field : fields) {
            property = field.getName();
            if (!LIST_CLASS.equals(field.getType().getName()) && !MULTIPARTFILE_CLASS.equals(field.getType().getName())) {
                try {
                    field.setAccessible(true);
                    fieldVal = StringUtil.convertNull(field.get(targetObj));
                } catch (Exception e) {
                    logger.debug(CAN_NOT_TO_GET_DATA_FOR_FIELD, property);
                    e.printStackTrace();
                }

                try {
                    commonValidator.validateWithAnnotation(field, fieldVal, errorItemNameList);

                    if (!StringUtil.isNull(fieldVal)) {
                        validateField(appForm, property, fieldVal, errors);
                    }
                } catch (Throwable ex) {
                    if (ex instanceof BaseException) {
                        logger.debug(FIELD_STRING_BEGIN + property + HAS_ERROR + ((BaseException) ex).getMessageId());
                    } else {
                        logger.debug(FIELD_STRING_BEGIN + property + HAS_ERROR + ex.getMessage());
                    }
                    BaseException errEx = (BaseException) ex;
                    errEx.setFieldName(listName + errEx.getFieldName());
                    errors.addError(errEx);
                }
            }
        }
    }

    /**
     * Gets error item name list.
     *
     * @return the error item name list
     */
    public List<String> getErrorItemNameList() {
        return errorItemNameList;
    }

    /**
     * Sets error item name list.
     *
     * @param errorItemNameList the error item name list
     */
    public void setErrorItemNameList(List<String> errorItemNameList) {
        this.errorItemNameList = errorItemNameList;
    }

/*    *//**
    private void validateMultiPartFileItem(Field field, MultipartFile fieldVal, String property, BindingResult errors) {
        if (field.isAnnotationPresent(FileValidation.class)) {
            FileValidation anno = field.getAnnotation(FileValidation.class);
            boolean isValidFile = CheckUtil.checkMultipartFile(fieldVal, anno.fileName(), anno.fileExtension());
            if (!isValidFile) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_1413);
                ex.setFieldName(field.getName());
                errors.addError(ex);
            }
        }
    }*/
}