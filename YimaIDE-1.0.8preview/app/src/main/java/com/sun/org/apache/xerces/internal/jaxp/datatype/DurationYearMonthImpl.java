package com.sun.org.apache.xerces.internal.jaxp.datatype;

import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class DurationYearMonthImpl extends DurationImpl {
    private static final long serialVersionUID = -4430140662861507958L;

    public DurationYearMonthImpl(String str) {
        super(str);
        if (getDays() > 0 || getHours() > 0 || getMinutes() > 0 || getSeconds() > 0) {
            kg9.a("Trying to create an xdt:yearMonthDuration with an invalid lexical representation of \"", str, "\", data model requires PnYnM.");
            throw null;
        }
        convertToCanonicalYearMonth();
    }

    private void convertToCanonicalYearMonth() {
        while (getMonths() >= 12) {
            this.months = this.months.subtract(BigInteger.valueOf(12L));
            this.years = BigInteger.valueOf(getYears()).add(BigInteger.ONE);
        }
    }

    public int getValue() {
        return (getYears() * 12) + getMonths();
    }

    public DurationYearMonthImpl(boolean z, int i, int i2) {
        this(z, DurationImpl.wrap(i), DurationImpl.wrap(i2));
    }

    public DurationYearMonthImpl(long j) {
        super(j);
        convertToCanonicalYearMonth();
        this.days = null;
        this.hours = null;
        this.minutes = null;
        this.seconds = null;
        this.signum = calcSignum(this.signum >= 0);
    }

    public DurationYearMonthImpl(boolean z, BigInteger bigInteger, BigInteger bigInteger2) {
        super(z, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null, (BigInteger) null, (BigDecimal) null);
        convertToCanonicalYearMonth();
    }
}
