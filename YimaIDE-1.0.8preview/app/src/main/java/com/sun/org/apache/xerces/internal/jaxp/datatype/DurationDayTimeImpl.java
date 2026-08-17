package com.sun.org.apache.xerces.internal.jaxp.datatype;

import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class DurationDayTimeImpl extends DurationImpl {
    private static final long serialVersionUID = 844792794952655204L;

    public DurationDayTimeImpl(boolean z, int i, int i2, int i3, int i4) {
        this(z, DurationImpl.wrap(i), DurationImpl.wrap(i2), DurationImpl.wrap(i3), i4 != Integer.MIN_VALUE ? new BigDecimal(String.valueOf(i4)) : null);
    }

    private void convertToCanonicalDayTime() {
        while (getSeconds() >= 60) {
            this.seconds = this.seconds.subtract(BigDecimal.valueOf(60L));
            this.minutes = BigInteger.valueOf(getMinutes()).add(BigInteger.ONE);
        }
        while (getMinutes() >= 60) {
            this.minutes = this.minutes.subtract(BigInteger.valueOf(60L));
            this.hours = BigInteger.valueOf(getHours()).add(BigInteger.ONE);
        }
        while (getHours() >= 24) {
            this.hours = this.hours.subtract(BigInteger.valueOf(24L));
            this.days = BigInteger.valueOf(getDays()).add(BigInteger.ONE);
        }
    }

    public float getValue() {
        BigDecimal bigDecimal = this.seconds;
        return (((((getDays() * 24) + getHours()) * 60) + getMinutes()) * 60) + (bigDecimal == null ? 0.0f : bigDecimal.floatValue());
    }

    public DurationDayTimeImpl(boolean z, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigDecimal bigDecimal) {
        super(z, (BigInteger) null, (BigInteger) null, bigInteger, bigInteger2, bigInteger3, bigDecimal);
        convertToCanonicalDayTime();
    }

    public DurationDayTimeImpl(String str) {
        super(str);
        if (getYears() <= 0 && getMonths() <= 0) {
            convertToCanonicalDayTime();
        } else {
            kg9.a("Trying to create an xdt:dayTimeDuration with an invalid lexical representation of \"", str, "\", data model requires a format PnDTnHnMnS.");
            throw null;
        }
    }

    public DurationDayTimeImpl(long j) {
        super(j);
        convertToCanonicalDayTime();
        this.years = null;
        this.months = null;
    }
}
