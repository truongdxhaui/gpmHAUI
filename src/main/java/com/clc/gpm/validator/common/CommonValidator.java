package com.clc.gpm.validator.common;

import com.clc.gpm.common.MessageConstants;
import com.clc.gpm.exception.InputCheckException;
import com.clc.gpm.utils.CheckUtil;
import com.clc.gpm.utils.StringUtil;
import com.clc.gpm.validator.annotation.*;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>ファイル名 : CommonValidator</p>
 * <p>説明 : CommonValidator</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Component("commonValidator")
public class CommonValidator {

    /**
     * <p>説明 : validateWithAnnotation</p> 
     * @author : truong.dx
     * @since : 2017/12/26
     * @param field Field
     * @param fieldVal String
     * @param errorItemNameList List<String>
     * @throws InputCheckException  Input Check Exception
     */
    public void validateWithAnnotation(Field field, String fieldVal, List<String> errorItemNameList) throws InputCheckException {
        // FIXME NO CHECK
        Ignore annotationIgnore = field.getDeclaredAnnotation(Ignore.class);
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotationIgnore != null || ArrayUtils.isEmpty(annotations)) {
            return;
        }

        // requireチェック
        Required annotation = field.getDeclaredAnnotation(Required.class);
        if (annotation != null && StringUtil.isNull(fieldVal)) {
            InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC101,
                    new String[] { annotation.displayFieldName() });
            ex.setFieldName(field.getName());
            errorItemNameList.add(field.getName());
            throw ex;
        }

        // 長さチェック
        MaxLength annotationML = field.getDeclaredAnnotation(MaxLength.class);
        if (annotationML != null) {
            String val = fieldVal == null ? fieldVal : fieldVal.trim();
            if (!StringUtil.isNull(val) && val.length() > annotationML.maxlength()) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_2, new String[] { annotationML.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // 半角数字のチェック : 0-9
        HaftsizeNumber annotationHN = field.getDeclaredAnnotation(HaftsizeNumber.class);
        if (annotationHN != null) {
            if (!CheckUtil.isHaftsizeNumeric(fieldVal)) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC203, new String[] { annotationHN.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // 半角英語のチェック : a-z, A-Z
        HalftsizeAlphabet annotationHA = field.getDeclaredAnnotation(HalftsizeAlphabet.class);
        if (annotationHA != null) {
            if (!CheckUtil.isHaftSizeAlphabet(fieldVal)) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC202, new String[] { annotationHA.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // 半角（英数）のチェック : a-z, A-Z
        HalftsizeAlphabetNumber annotationHAN = field.getDeclaredAnnotation(HalftsizeAlphabetNumber.class);
        if (annotationHAN != null) {
            if (!CheckUtil.isHalfSizeAlphabetAndNumber(fieldVal)) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC204, new String[] { annotationHAN.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // 半角（英/数/記号）のチェック : a-z, A-Z,...
        HalftsizeAlphabetNumberSymbol annotationHANS = field.getDeclaredAnnotation(HalftsizeAlphabetNumberSymbol.class);
        if (annotationHANS != null) {
            if (!CheckUtil.isHalfAlphabetNumberSymbol(fieldVal)) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC205, new String[] { annotationHANS.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // 全角文字のチェック : a-z, A-Z
        Fullsize annotationF = field.getDeclaredAnnotation(Fullsize.class);
        if (annotationF != null) {
            if (!CheckUtil.isFullSize(fieldVal)) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC201, new String[] { annotationF.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // 日付フォーマットのチェック
        // 検証フォーマット
        Format annotationFormat = field.getDeclaredAnnotation(Format.class);
        if (annotationFormat != null) {
            // 日付フォーマットのチェック
            if (annotationFormat.type().equals(Format.FormatType.DATE)) {
                boolean isValidDate = CheckUtil.isDateFormat(fieldVal, annotationFormat.pattern());
                if (!isValidDate) {
                    InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC206,
                            new String[] { annotationFormat.displayFieldName(), annotationFormat.pattern() });
                    ex.setFieldName(field.getName());
                    errorItemNameList.add(field.getName());
                    throw ex;
                }
            }
        }

        // sizeのチェック
        // 検証フォーマット
        Size annotationSize = field.getDeclaredAnnotation(Size.class);
        if (annotationSize != null) {
            if (CheckUtil.isHaftsizeNumeric(fieldVal)) {
                int val = fieldVal == null ? 0 : Integer.parseInt(fieldVal);
                if (val < annotationSize.min() || val > annotationSize.max()) {
                    InputCheckException ex = new InputCheckException(MessageConstants.MSG_9, new String[] { annotationSize.displayFieldName() });
                    ex.setFieldName(field.getName());
                    errorItemNameList.add(field.getName());
                    throw ex;
                }
            } else {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC203, new String[] { annotationSize.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }
    }
}