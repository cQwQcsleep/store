package com.sun.org.apache.xerces.internal.jaxp.datatype;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DatatypeFactoryImpl extends DatatypeFactory {
    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDuration(String str) {
        return new DurationImpl(str);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDurationDayTime(boolean z, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        return new DurationDayTimeImpl(z, bigInteger, bigInteger2, bigInteger3, bigInteger4 != null ? new BigDecimal(bigInteger4) : null);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDurationYearMonth(boolean z, BigInteger bigInteger, BigInteger bigInteger2) {
        return new DurationYearMonthImpl(z, bigInteger, bigInteger2);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public XMLGregorianCalendar newXMLGregorianCalendar() {
        return new XMLGregorianCalendarImpl();
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDuration(long j) {
        return new DurationImpl(j);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDurationYearMonth(boolean z, int i, int i2) {
        return new DurationYearMonthImpl(z, i, i2);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public XMLGregorianCalendar newXMLGregorianCalendar(String str) {
        return new XMLGregorianCalendarImpl(str);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDuration(boolean z, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigDecimal bigDecimal) {
        return new DurationImpl(z, bigInteger, bigInteger2, bigInteger3, bigInteger4, bigInteger5, bigDecimal);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDurationYearMonth(String str) {
        return new DurationYearMonthImpl(str);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public XMLGregorianCalendar newXMLGregorianCalendar(GregorianCalendar gregorianCalendar) {
        return new XMLGregorianCalendarImpl(gregorianCalendar);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDurationYearMonth(long j) {
        return new DurationYearMonthImpl(j);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public XMLGregorianCalendar newXMLGregorianCalendar(BigInteger bigInteger, int i, int i2, int i3, int i4, int i5, BigDecimal bigDecimal, int i6) {
        return new XMLGregorianCalendarImpl(bigInteger, i, i2, i3, i4, i5, bigDecimal, i6);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDurationDayTime(long j) {
        return new DurationDayTimeImpl(j);
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDurationDayTime(String str) {
        if (str != null) {
            return new DurationDayTimeImpl(str);
        }
        x0e.a("Trying to create an xdt:dayTimeDuration with an invalid lexical representation of \"null\"");
        return null;
    }

    @Override // javax.xml.datatype.DatatypeFactory
    public Duration newDurationDayTime(boolean z, int i, int i2, int i3, int i4) {
        return new DurationDayTimeImpl(z, i, i2, i3, i4);
    }
}
