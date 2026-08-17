package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xpath.internal.XPath;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class DayTimeDurationDV extends DurationDV {
    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.DurationDV, com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return parse(str, 2);
        } catch (Exception unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, "dayTimeDuration"});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.DurationDV, com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public Duration getDuration(AbstractDateTimeDV.DateTimeData dateTimeData) {
        int i = dateTimeData.day;
        int i2 = (i < 0 || dateTimeData.hour < 0 || dateTimeData.minute < 0 || dateTimeData.second < XPath.MATCH_SCORE_QNAME) ? -1 : 1;
        DatatypeFactory datatypeFactory = AbstractDateTimeDV.datatypeFactory;
        boolean z = i2 == 1;
        BigInteger bigIntegerValueOf = i != Integer.MIN_VALUE ? BigInteger.valueOf(i * i2) : null;
        int i3 = dateTimeData.hour;
        BigInteger bigIntegerValueOf2 = i3 != Integer.MIN_VALUE ? BigInteger.valueOf(i3 * i2) : null;
        int i4 = dateTimeData.minute;
        return datatypeFactory.newDuration(z, (BigInteger) null, (BigInteger) null, bigIntegerValueOf, bigIntegerValueOf2, i4 != Integer.MIN_VALUE ? BigInteger.valueOf(i4 * i2) : null, dateTimeData.second != -2.147483648E9d ? new BigDecimal(String.valueOf(((double) i2) * dateTimeData.second)) : null);
    }
}
