package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MonthDV extends AbstractDateTimeDV {
    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public String dateToString(AbstractDateTimeDV.DateTimeData dateTimeData) {
        StringBuffer stringBuffer = new StringBuffer(5);
        stringBuffer.append("--");
        append(stringBuffer, dateTimeData.month, 2);
        append(stringBuffer, (char) dateTimeData.utc, 0);
        return stringBuffer.toString();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return parse(str);
        } catch (Exception unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_MONTH});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public XMLGregorianCalendar getXMLGregorianCalendar(AbstractDateTimeDV.DateTimeData dateTimeData) {
        return AbstractDateTimeDV.datatypeFactory.newXMLGregorianCalendar(Integer.MIN_VALUE, dateTimeData.unNormMonth, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, dateTimeData.hasTimeZone() ? (dateTimeData.timezoneHr * 60) + dateTimeData.timezoneMin : Integer.MIN_VALUE);
    }

    public AbstractDateTimeDV.DateTimeData parse(String str) throws SchemaDateTimeException {
        AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(str, this);
        int length = str.length();
        dateTimeData.year = WinError.ERROR_INVALID_PIXEL_FORMAT;
        dateTimeData.day = 1;
        if (str.charAt(0) != '-' || str.charAt(1) != '-') {
            throw new SchemaDateTimeException("Invalid format for gMonth: ".concat(str));
        }
        int i = 4;
        dateTimeData.month = parseInt(str, 2, 4);
        if (str.length() >= 6 && str.charAt(4) == '-' && str.charAt(5) == '-') {
            i = 6;
        }
        if (i < length) {
            if (!isNextCharUTCSign(str, i, length)) {
                throw new SchemaDateTimeException("Error in month parsing: ".concat(str));
            }
            getTimeZone(str, dateTimeData, i, length);
        }
        validateDateTime(dateTimeData);
        saveUnnormalized(dateTimeData);
        int i2 = dateTimeData.utc;
        if (i2 != 0 && i2 != 90) {
            normalize(dateTimeData);
        }
        dateTimeData.position = 1;
        return dateTimeData;
    }
}
