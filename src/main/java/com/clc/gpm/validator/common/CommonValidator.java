package com.tau.validator.common;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.common.Constants;
import com.tau.exception.InputCheckException;
import com.tau.utils.CheckUtil;
import com.tau.utils.StringUtil;
import com.tau.validator.annotation.Format;
import com.tau.validator.annotation.Format.FormatType;
import com.tau.validator.annotation.Fullsize;
import com.tau.validator.annotation.HaftsizeNumber;
import com.tau.validator.annotation.HalftsizeAlphabet;
import com.tau.validator.annotation.HalftsizeAlphabetNumber;
import com.tau.validator.annotation.HalftsizeAlphabetNumberSymbol;
import com.tau.validator.annotation.MaxLength;
import com.tau.validator.annotation.Required;

/**
 * <p>ファイル名 : CommonValidator</p>
 * <p>説明 : CommonValidator</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Component("commonValidator")
public class CommonValidator {
    /** StringUtilのインスタンス */
    @Autowired
    protected StringUtil strUtil;

    /**
     * checkUtil
     */
    @Autowired
    protected CheckUtil checkUtil;

    /**
     * <p>説明 : validateWithAnnotation</p> 
     * @author : [dts.bp34]
     * @since : [2017/12/26]
     * @param field Field
     * @param fieldVal String
     * @param errorItemNameList List<String>
     * @throws InputCheckException
     * @throws Exception 
     */
    public void validateWithAnnotation(Field field, String fieldVal,
            List<String> errorItemNameList) throws Exception {
        String val = fieldVal == null ? fieldVal : fieldVal.trim();

        // Check require
        if (field.isAnnotationPresent(Required.class)
                && strUtil.isNull(fieldVal)) {
            InputCheckException ex = new InputCheckException(
                    Constants.MSG_RIQUIRED_INPUT_ERR,
                    new String[] { field.getAnnotation(Required.class)
                            .displayFieldName() });
            ex.setFieldName(field.getName());
            errorItemNameList.add(field.getName());
            throw ex;
        }

        // Check length
        if (field.isAnnotationPresent(MaxLength.class)) {
            MaxLength anno = field.getAnnotation(MaxLength.class);
            if (!strUtil.isNull(val) && val.length() > anno.maxlength()) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_LENGTH_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check number halfsize : 0-9
        if (field.isAnnotationPresent(HaftsizeNumber.class)) {
            HaftsizeNumber anno = field.getAnnotation(HaftsizeNumber.class);
            if (!checkUtil.isHaftsizeNumeric(fieldVal)) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_HALFSIZE_NUMBER_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check alphabet haftsize : a-z, A-Z
        if (field.isAnnotationPresent(HalftsizeAlphabet.class)) {
            HalftsizeAlphabet anno = field
                    .getAnnotation(HalftsizeAlphabet.class);
            if (!checkUtil.isHaftSizeAlphabet(fieldVal)) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_HALFSIZE_ALPHABET_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check alphabet, number haftsize : a-z, A-Z
        if (field.isAnnotationPresent(HalftsizeAlphabetNumber.class)) {
            HalftsizeAlphabetNumber anno = field
                    .getAnnotation(HalftsizeAlphabetNumber.class);
            if (!checkUtil.isHalfSizeAlphabetAndNumber(fieldVal)) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_HALFSIZE_ALPHABET_NUMBER_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check alphabet, number, symbol haftsize : a-z, A-Z,...
        if (field.isAnnotationPresent(HalftsizeAlphabetNumberSymbol.class)) {
            HalftsizeAlphabetNumberSymbol anno = field
                    .getAnnotation(HalftsizeAlphabetNumberSymbol.class);
            if (!checkUtil.isHalfAlphabetNumberSymbol(fieldVal)) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_HALFSIZE_ALPHABET_NUMBER_SYMBOL_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check Fullsize : a-z, A-Z
        if (field.isAnnotationPresent(Fullsize.class)) {
            Fullsize anno = field.getAnnotation(Fullsize.class);
            if (!checkUtil.isFullSize(fieldVal)) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_FULLSIZE_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check date format
        // Format validate
        if (field.isAnnotationPresent(Format.class)) {
            Format anno = field.getAnnotation(Format.class);

            // Check date format
            if (anno.type().equals(FormatType.DATE)) {
                boolean isValidDate = checkUtil.isDateFormat(fieldVal,
                        anno.pattern());
                if (!isValidDate) {
                    InputCheckException ex = new InputCheckException(
                            Constants.MSG_DATE_FORMAT_INPUT_ERR, new String[] {
                                    anno.displayFieldName(), anno.pattern() });
                    ex.setFieldName(field.getName());
                    errorItemNameList.add(field.getName());
                    throw ex;
                }
            }
        }
    }

}
