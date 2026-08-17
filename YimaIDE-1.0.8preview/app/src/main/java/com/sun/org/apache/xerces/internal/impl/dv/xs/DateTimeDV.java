package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xpath.internal.XPath;
import java.math.BigInteger;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DateTimeDV extends AbstractDateTimeDV {
    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return parse(str);
        } catch (Exception unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_DATETIME});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public XMLGregorianCalendar getXMLGregorianCalendar(AbstractDateTimeDV.DateTimeData dateTimeData) {
        DatatypeFactory datatypeFactory = AbstractDateTimeDV.datatypeFactory;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(dateTimeData.unNormYear);
        int i = dateTimeData.unNormMonth;
        int i2 = dateTimeData.unNormDay;
        int i3 = dateTimeData.unNormHour;
        int i4 = dateTimeData.unNormMinute;
        double d = dateTimeData.unNormSecond;
        return datatypeFactory.newXMLGregorianCalendar(bigIntegerValueOf, i, i2, i3, i4, (int) d, d != XPath.MATCH_SCORE_QNAME ? getFractionalSecondsAsBigDecimal(dateTimeData) : null, dateTimeData.hasTimeZone() ? (dateTimeData.timezoneHr * 60) + dateTimeData.timezoneMin : Integer.MIN_VALUE);
    }

    public AbstractDateTimeDV.DateTimeData parse(String str) throws SchemaDateTimeException {
        AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(str, this);
        int length = str.length();
        int iIndexOf = indexOf(str, 0, length, 'T');
        int date = getDate(str, 0, iIndexOf, dateTimeData);
        getTime(str, iIndexOf + 1, length, dateTimeData);
        if (date != iIndexOf) {
            f63.a(str.concat(" is an invalid dateTime dataype value. Invalid character(s) seprating date and time values."));
            return null;
        }
        validateDateTime(dateTimeData);
        saveUnnormalized(dateTimeData);
        int i = dateTimeData.utc;
        if (i != 0 && i != 90) {
            normalize(dateTimeData);
        }
        return dateTimeData;
    }
}
