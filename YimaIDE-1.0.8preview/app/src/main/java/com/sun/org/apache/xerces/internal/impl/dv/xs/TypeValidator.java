package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class TypeValidator {
    public static final short EQUAL = 0;
    public static final short GREATER_THAN = 1;
    public static final short INDETERMINATE = 2;
    public static final short LESS_THAN = -1;
    private static final boolean USE_CODE_POINT_COUNT_FOR_STRING_LENGTH = Boolean.parseBoolean(SecuritySupport.getSystemProperty("com.sun.org.apache.xerces.internal.impl.dv.xs.useCodePointCountForStringLength", "false"));

    private int getCodePointLength(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length - 1) {
            if (XMLChar.isHighSurrogate(str.charAt(i))) {
                int i3 = i + 1;
                if (XMLChar.isLowSurrogate(str.charAt(i3))) {
                    i2++;
                    i = i3;
                }
            }
            i++;
        }
        return length - i2;
    }

    public static final int getDigit(char c) {
        if (isDigit(c)) {
            return c - '0';
        }
        return -1;
    }

    public static final boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public void checkExtraRules(Object obj, ValidationContext validationContext) throws InvalidDatatypeValueException {
    }

    public int compare(Object obj, Object obj2) {
        return -1;
    }

    public abstract Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException;

    public abstract short getAllowedFacets();

    public int getDataLength(Object obj) {
        if (!(obj instanceof String)) {
            return -1;
        }
        String str = (String) obj;
        return !USE_CODE_POINT_COUNT_FOR_STRING_LENGTH ? str.length() : getCodePointLength(str);
    }

    public int getFractionDigits(Object obj) {
        return -1;
    }

    public int getTotalDigits(Object obj) {
        return -1;
    }

    public boolean isIdentical(Object obj, Object obj2) {
        return obj.equals(obj2);
    }
}
