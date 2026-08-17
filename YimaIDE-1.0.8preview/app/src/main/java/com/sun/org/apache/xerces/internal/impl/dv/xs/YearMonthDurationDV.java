package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class YearMonthDurationDV extends DurationDV {
    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.DurationDV, com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return parse(str, 1);
        } catch (Exception unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, "yearMonthDuration"});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.DurationDV, com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public Duration getDuration(AbstractDateTimeDV.DateTimeData dateTimeData) {
        int i = dateTimeData.year;
        int i2 = (i < 0 || dateTimeData.month < 0) ? -1 : 1;
        DatatypeFactory datatypeFactory = AbstractDateTimeDV.datatypeFactory;
        boolean z = i2 == 1;
        BigInteger bigIntegerValueOf = i != Integer.MIN_VALUE ? BigInteger.valueOf(i * i2) : null;
        int i3 = dateTimeData.month;
        return datatypeFactory.newDuration(z, bigIntegerValueOf, i3 != Integer.MIN_VALUE ? BigInteger.valueOf(i2 * i3) : null, (BigInteger) null, (BigInteger) null, (BigInteger) null, (BigDecimal) null);
    }
}
