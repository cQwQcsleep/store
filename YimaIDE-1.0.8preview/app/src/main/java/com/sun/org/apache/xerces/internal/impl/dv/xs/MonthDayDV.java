package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MonthDayDV extends AbstractDateTimeDV {
    private static final int MONTHDAY_SIZE = 7;

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public String dateToString(AbstractDateTimeDV.DateTimeData dateTimeData) {
        StringBuffer stringBuffer = new StringBuffer(8);
        stringBuffer.append("--");
        append(stringBuffer, dateTimeData.month, 2);
        stringBuffer.append(LocaleUtility.IETF_SEPARATOR);
        append(stringBuffer, dateTimeData.day, 2);
        append(stringBuffer, (char) dateTimeData.utc, 0);
        return stringBuffer.toString();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return parse(str);
        } catch (Exception unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_MONTHDAY});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public XMLGregorianCalendar getXMLGregorianCalendar(AbstractDateTimeDV.DateTimeData dateTimeData) {
        return AbstractDateTimeDV.datatypeFactory.newXMLGregorianCalendar(Integer.MIN_VALUE, dateTimeData.unNormMonth, dateTimeData.unNormDay, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, dateTimeData.hasTimeZone() ? (dateTimeData.timezoneHr * 60) + dateTimeData.timezoneMin : Integer.MIN_VALUE);
    }

    public AbstractDateTimeDV.DateTimeData parse(String str) throws SchemaDateTimeException {
        AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(str, this);
        int length = str.length();
        dateTimeData.year = WinError.ERROR_INVALID_PIXEL_FORMAT;
        if (str.charAt(0) != '-' || str.charAt(1) != '-') {
            throw new SchemaDateTimeException("Invalid format for gMonthDay: ".concat(str));
        }
        dateTimeData.month = parseInt(str, 2, 4);
        if (str.charAt(4) != '-') {
            throw new SchemaDateTimeException("Invalid format for gMonthDay: ".concat(str));
        }
        dateTimeData.day = parseInt(str, 5, 7);
        if (7 < length) {
            if (!isNextCharUTCSign(str, 7, length)) {
                throw new SchemaDateTimeException("Error in month parsing:".concat(str));
            }
            getTimeZone(str, dateTimeData, 7, length);
        }
        validateDateTime(dateTimeData);
        saveUnnormalized(dateTimeData);
        int i = dateTimeData.utc;
        if (i != 0 && i != 90) {
            normalize(dateTimeData);
        }
        dateTimeData.position = 1;
        return dateTimeData;
    }
}
