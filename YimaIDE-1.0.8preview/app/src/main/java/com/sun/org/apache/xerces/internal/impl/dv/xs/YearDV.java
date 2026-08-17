package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class YearDV extends AbstractDateTimeDV {
    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public String dateToString(AbstractDateTimeDV.DateTimeData dateTimeData) {
        StringBuffer stringBuffer = new StringBuffer(5);
        append(stringBuffer, dateTimeData.year, 4);
        append(stringBuffer, (char) dateTimeData.utc, 0);
        return stringBuffer.toString();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return parse(str);
        } catch (Exception unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_YEAR});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public XMLGregorianCalendar getXMLGregorianCalendar(AbstractDateTimeDV.DateTimeData dateTimeData) {
        return AbstractDateTimeDV.datatypeFactory.newXMLGregorianCalendar(dateTimeData.unNormYear, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, dateTimeData.hasTimeZone() ? (dateTimeData.timezoneHr * 60) + dateTimeData.timezoneMin : Integer.MIN_VALUE);
    }

    public AbstractDateTimeDV.DateTimeData parse(String str) throws SchemaDateTimeException {
        AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(str, this);
        int length = str.length();
        int i = str.charAt(0) == '-' ? 1 : 0;
        int iFindUTCSign = findUTCSign(str, i, length);
        int i2 = (iFindUTCSign == -1 ? length : iFindUTCSign) - i;
        if (i2 < 4) {
            f63.a("Year must have 'CCYY' format");
            return null;
        }
        if (i2 > 4 && str.charAt(i) == '0') {
            f63.a("Leading zeros are required if the year value would otherwise have fewer than four digits; otherwise they are forbidden");
            return null;
        }
        if (iFindUTCSign == -1) {
            dateTimeData.year = parseIntYear(str, length);
        } else {
            dateTimeData.year = parseIntYear(str, iFindUTCSign);
            getTimeZone(str, dateTimeData, iFindUTCSign, length);
        }
        dateTimeData.month = 1;
        dateTimeData.day = 1;
        validateDateTime(dateTimeData);
        saveUnnormalized(dateTimeData);
        int i3 = dateTimeData.utc;
        if (i3 != 0 && i3 != 90) {
            normalize(dateTimeData);
        }
        dateTimeData.position = 0;
        return dateTimeData;
    }
}
