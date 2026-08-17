package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DayDV extends AbstractDateTimeDV {
    private static final int DAY_SIZE = 5;

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public String dateToString(AbstractDateTimeDV.DateTimeData dateTimeData) {
        StringBuffer stringBuffer = new StringBuffer(6);
        stringBuffer.append("---");
        append(stringBuffer, dateTimeData.day, 2);
        append(stringBuffer, (char) dateTimeData.utc, 0);
        return stringBuffer.toString();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return parse(str);
        } catch (Exception unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_DAY});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public XMLGregorianCalendar getXMLGregorianCalendar(AbstractDateTimeDV.DateTimeData dateTimeData) {
        return AbstractDateTimeDV.datatypeFactory.newXMLGregorianCalendar(Integer.MIN_VALUE, Integer.MIN_VALUE, dateTimeData.unNormDay, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, dateTimeData.hasTimeZone() ? (dateTimeData.timezoneHr * 60) + dateTimeData.timezoneMin : Integer.MIN_VALUE);
    }

    public AbstractDateTimeDV.DateTimeData parse(String str) throws SchemaDateTimeException {
        AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(str, this);
        int length = str.length();
        if (str.charAt(0) != '-' || str.charAt(1) != '-' || str.charAt(2) != '-') {
            throw new SchemaDateTimeException("Error in day parsing");
        }
        dateTimeData.year = WinError.ERROR_INVALID_PIXEL_FORMAT;
        dateTimeData.month = 1;
        dateTimeData.day = parseInt(str, 3, 5);
        if (5 < length) {
            if (!isNextCharUTCSign(str, 5, length)) {
                throw new SchemaDateTimeException("Error in day parsing");
            }
            getTimeZone(str, dateTimeData, 5, length);
        }
        validateDateTime(dateTimeData);
        saveUnnormalized(dateTimeData);
        int i = dateTimeData.utc;
        if (i != 0 && i != 90) {
            normalize(dateTimeData);
        }
        dateTimeData.position = 2;
        return dateTimeData;
    }
}
